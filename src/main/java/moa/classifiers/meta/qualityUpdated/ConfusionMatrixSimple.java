package moa.classifiers.meta.qualityUpdated;

import java.io.Serializable;
import java.util.Arrays;

import com.yahoo.labs.samoa.instances.Instance;

import moa.AbstractMOAObject;
import moa.classifiers.Classifier;
import weka.core.Utils;

public class ConfusionMatrixSimple extends AbstractMOAObject implements ConfusionMatrix, Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1033839192164786797L;
	
	private Classifier classifier;
	
	private double[][] confusionMatrix;
	
	private int numClasses=0;
	
	protected boolean initialised=false;
	
	private double eps=1E-6;

	@Override
	public void setClassifier(Classifier classifier) {
		this.classifier = classifier;
		this.reset();

	}

	@Override
	public Classifier getClassifier() {
		return this.classifier;
	}

	@Override
	public void reset() {
		this.initialised=false;

	}
	
	protected void initialise(Instance inst) {
		if(!this.initialised) {
			this.numClasses = inst.numClasses();
			this.confusionMatrix = new double[this.numClasses][numClasses];
			for(int i=0;i<confusionMatrix.length;i++)
				Arrays.fill(confusionMatrix[i], this.eps);
			this.initialised = true;
		}
	}
	
	

	@Override
	public void update(Instance inst) {
		this.initialise(inst);
		int trueClass = (int) inst.classValue();
		double[] prediction = this.classifier.getVotesForInstance(inst);
		double sum = Utils.sum(prediction);
		if(Utils.eq(sum, 0))
			return;
		int predClass = Utils.maxIndex(prediction);
		this.confusionMatrix[predClass][trueClass] +=1.0;
	}

	@Override
	public int getNumClasses() {
		return this.numClasses;
	}

	@Override
	public double getValue(int predictedClassNum, int trueClassNum) {
		return this.confusionMatrix[predictedClassNum][trueClassNum];
	}

	@Override
	public void getDescription(StringBuilder sb, int indent) {
		sb.append("Confusion matrix");
		
	}

}
