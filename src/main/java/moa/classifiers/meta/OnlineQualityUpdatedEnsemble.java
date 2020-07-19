/**
 * 
 */
package moa.classifiers.meta;

import java.util.Arrays;

import com.github.javacliparser.FloatOption;
import com.github.javacliparser.IntOption;
import com.yahoo.labs.samoa.instances.Instance;

import moa.classifiers.AbstractClassifier;
import moa.classifiers.Classifier;
import moa.classifiers.MultiClassClassifier;
import moa.classifiers.meta.combiners.ClassifierResponseSimpleCombiner;
import moa.classifiers.meta.combiners.GeneralCombiner;
import moa.classifiers.meta.qualityUpdated.ConfusionMatrix;
import moa.classifiers.meta.qualityUpdated.ConfusionMatrixSimple;
import moa.classifiers.meta.qualityUpdated.QualityMeasure;
import moa.classifiers.meta.qualityUpdated.qualityMeasures.QualityMeasureKappa;
import moa.core.Measurement;
import moa.core.ObjectRepository;
import moa.options.ClassOption;
import moa.tasks.TaskMonitor;
import weka.core.Utils;

/**
 * @author pawel trajdos
 * @since 0.0.1
 * @version 0.0.1
 *
 */
public class OnlineQualityUpdatedEnsemble extends AbstractClassifier implements MultiClassClassifier {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8594674035795055343L;
	
	/**
	 * Type of classifier to use as a component classifier.
	 */
	public ClassOption learnerOption = new ClassOption("learner", 'l', "Classifier to train.", Classifier.class, 
			"trees.HoeffdingTree -e 2000000 -g 100 -c 0.01");
	
	public ClassOption qualityCriterionOption = new ClassOption("quality_measure", 'q', "Quality measure to use", QualityMeasure.class,
			ClassOption.objectToCLIString(new QualityMeasureKappa(), QualityMeasure.class));
	

	public ClassOption combinerOption = new ClassOption("classifier_combiner", 'c', "Base classifier combiner", ClassifierResponseSimpleCombiner.class,
			ClassOption.objectToCLIString(new GeneralCombiner(), ClassifierResponseSimpleCombiner.class) );
	
	
	

	/**
	 * Number of component classifiers.
	 */
	public IntOption memberCountOption = new IntOption("memberCount", 'n',
			"The maximum number of classifiers in an ensemble.", 11, 1, Integer.MAX_VALUE);

	/**
	 * Chunk size.
	 */
	public FloatOption windowSizeOption = new FloatOption("windowSize", 'w',
			"The window size used for classifier creation and evaluation.", 100, 1, Integer.MAX_VALUE);
	
	public FloatOption qualityThresholdOption = new FloatOption("qualityThreshold", 't',
			"Threshold for quality measure", 0.5, 0, 1);
	
	protected Classifier candidate;
	
	protected int windowSize=100;
	
	protected Classifier[] ensemble;
	protected ConfusionMatrix[] confusionMatrices;
	protected ConfusionMatrix candidateConfusionMatrix;
	protected double[] weights;
	
	protected QualityMeasure qualityMeasure; 
	
	protected int instancesProcessed=0;
	
	protected int activeClassifiers =0;
	
	protected ClassifierResponseSimpleCombiner combiner;
	
	protected double qualityThreshold=0.5;
	
	protected double eps=1e-6;

	/**
	 * 
	 */
	public OnlineQualityUpdatedEnsemble() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void prepareForUseImpl(TaskMonitor monitor, ObjectRepository repository) {
		super.prepareForUseImpl(monitor, repository);
		this.init();
		
	}
	
	@Override
	public void resetLearningImpl() {
		this.init();

	}

	
	private void init() {
		this.windowSize = (int) this.windowSizeOption.getValue();
		
		this.initialiseCandidate();
		
		
		
		int ensembleSize = this.memberCountOption.getValue();
		this.ensemble = new Classifier[ensembleSize];
		
		this.qualityMeasure = (QualityMeasure) getPreparedClassOption(this.qualityCriterionOption);
		
		this.confusionMatrices = new ConfusionMatrix[ensembleSize];
		for(int i=0;i<ensembleSize;i++)
			this.confusionMatrices[i] = this.getNewConfusionMatrix();
		
		
		this.weights = new double[ensembleSize];
		Arrays.fill(this.weights, 1.0);
		Utils.normalize(this.weights);
		
		
		this.activeClassifiers=0;
		this.instancesProcessed=0;
		
		this.combiner = (GeneralCombiner) getPreparedClassOption(this.combinerOption);
		
		this.qualityThreshold = this.qualityThresholdOption.getValue();
		
	}
	
	protected ConfusionMatrix getNewConfusionMatrix() {
		return new ConfusionMatrixSimple();
	}
	
	protected void initialiseCandidate() {
		this.candidate = ((Classifier) getPreparedClassOption(this.learnerOption)).copy();
		this.candidate.resetLearning();
		this.candidateConfusionMatrix = this.getNewConfusionMatrix();
		this.candidateConfusionMatrix.setClassifier(this.candidate);
	}
	
	

	@Override
	public boolean isRandomizable() {
		return false;
	}

	@Override
	public double[] getVotesForInstance(Instance inst) {
		if(this.activeClassifiers ==0)
			return this.candidate.getVotesForInstance(inst);
		
		return this.combineClassifiers(inst);
		
	}
	
	protected double[] combineClassifiers(Instance inst) {
		double[][] responses = new double[this.activeClassifiers][];
		
		for(int i=0;i<responses.length;i++)
			responses[i] = this.ensemble[i].getVotesForInstance(inst);
		
		double[] weightsSubset = Arrays.copyOf(this.weights, responses.length);
		
		double[] response = this.combiner.combine(responses, inst,weightsSubset);
		
		return response;
	}

	
	@Override
	public void trainOnInstanceImpl(Instance inst) {
		
		this.updateConfusionMatrices(inst);
		
		this.updateEnsemble(inst);
		
		this.candidate.trainOnInstance(inst);
		
		
		this.instancesProcessed++;

	}
	
	protected void updateConfusionMatrices(Instance inst) {
		for(int i=0;i<this.activeClassifiers;i++)
			this.confusionMatrices[i].update(inst);
		this.candidateConfusionMatrix.update(inst);
	}
	
	protected void updateEnsemble(Instance inst) {
		
		if(this.instancesProcessed>0 & (this.instancesProcessed % this.windowSize ==0)) {
			if(this.activeClassifiers < this.ensemble.length) {
				this.ensemble[this.activeClassifiers] = this.candidate;
				this.confusionMatrices[this.activeClassifiers] = this.candidateConfusionMatrix;
				this.activeClassifiers++;
				
				this.initialiseCandidate();
				
				this.updateWeights();
			}else {
				double[] qualities = new double[this.ensemble.length];
				for(int i =0 ;i < this.ensemble.length;i++)
						qualities[i] = this.qualityMeasure.getMeasure(this.confusionMatrices[i]);
				
					
				int minIndex = Utils.minIndex(qualities);
				double minVal = qualities[minIndex];
				double candidateQuality = this.qualityMeasure.getMeasure(this.candidateConfusionMatrix);
				
				if(minVal < candidateQuality) {
					this.ensemble[minIndex] = this.candidate;
					this.confusionMatrices[minIndex] =this.candidateConfusionMatrix;
					
					this.initialiseCandidate();
					
				}
				
				this.updateWeights();
				
			}
			this.resetMatrices();
		}
		for(int i=0;i<this.activeClassifiers;i++)
			this.ensemble[i].trainOnInstance(inst);
	}
	
	protected void updateWeights() {
		double[] newWeights = new double[this.ensemble.length];
		double[] qualities =  new double[this.ensemble.length];
		for(int i=0;i<this.ensemble.length;i++) {
			if(i <this.activeClassifiers) {
				qualities[i] = this.qualityMeasure.getMeasure(this.confusionMatrices[i]);
				newWeights[i] = qualities[i]>this.qualityThreshold? qualities[i]:0;
			}
			else
				newWeights[i]=0;
		}
		
		double weiSum = Utils.sum(newWeights);
		if(weiSum>this.eps) {
			Utils.normalize(newWeights);
		}else {
			double qsum = Utils.sum(qualities);
			if(qsum>this.eps) {
				Utils.normalize(qualities);
				newWeights = qualities;
			}else {
				newWeights = new double[this.ensemble.length];
				Arrays.fill(newWeights, 1.0/this.ensemble.length);
			}
			
			
		}
		
		
		
		this.weights = newWeights;
	}
	
	protected void resetMatrices() {
		for(int i=0;i<this.activeClassifiers;i++)
			this.confusionMatrices[i].reset();
		this.candidateConfusionMatrix.reset();
	}

	@Override
	protected Measurement[] getModelMeasurementsImpl() {
		Measurement[] m = new Measurement[0];
        return m;
	}

	@Override
	public void getModelDescription(StringBuilder out, int indent) {
		// TODO Auto-generated method stub

	}



}
