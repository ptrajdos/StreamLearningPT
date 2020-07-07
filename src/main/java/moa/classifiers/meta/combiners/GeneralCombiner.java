/**
 * 
 */
package moa.classifiers.meta.combiners;

import java.io.Serializable;
import java.util.Arrays;

import com.github.javacliparser.ClassOption;
import com.yahoo.labs.samoa.instances.Instance;
import com.yahoo.labs.samoa.instances.SamoaToWekaInstanceConverter;

import moa.core.ObjectRepository;
import moa.options.AbstractOptionHandler;
import moa.tasks.TaskMonitor;
import weka.classifiers.meta.generalOutputCombiners.MeanCombiner;
import weka.classifiers.meta.generalOutputCombiners.OutputCombiner;
import weka.core.Utils;
import weka.core.UtilsPT;

/**
 * Simple combiner class that uses combiners from 'WekaEnsembleSystems' package
 * @author pawel trajdos
 * @since 0.0.1
 * @version 0.0.1
 *
 */
public class GeneralCombiner extends AbstractOptionHandler implements ClassifierResponseSimpleCombiner {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2573930501552105657L;
	
	public ClassOption internalCombiner = new ClassOption("internal_combiner", 'c', "internal Combiner", OutputCombiner.class,
			MeanCombiner.class.getCanonicalName());
	
	private OutputCombiner combiner = new MeanCombiner(); 


	@Override
	public double[] combine(double[][] rawPredictions, Instance testInstance, double[] weights) {
		try {
			double[][] processedPredictions = this.preprocessRawPredictions(rawPredictions, testInstance);
			return this.combiner.getCombinedDistributionForInstance(processedPredictions, this.convertInstance(testInstance),weights);
		} catch (Exception e) {
			double[] preds = new double[ testInstance.numClasses()];
			e.printStackTrace();
			return preds;
		}
	}

	@Override
	public double[] combine(double[][] rawPredictions, Instance testInstance) {
		try {
			double[][] processedPredictions = this.preprocessRawPredictions(rawPredictions, testInstance);
			return this.combiner.getCombinedDistributionForInstance(processedPredictions, this.convertInstance(testInstance));
		} catch (Exception e) {
			double[] preds = new double[testInstance.numClasses()];
			e.printStackTrace();
			return preds;
		}
	}
	
	protected weka.core.Instance convertInstance(Instance inputInstance){
		SamoaToWekaInstanceConverter conv = new SamoaToWekaInstanceConverter();
		return conv.wekaInstance(inputInstance);
	}
	
	public double[][] preprocessRawPredictions(double[][] rawPredictions, Instance inst){
		int numClasses = inst.numClasses();
		int numPredictions =rawPredictions.length;
		
		double[][] processedPredictions = new double[numPredictions][];
		double[] tmpArray;
		double arraySum=0;
		for(int i=0;i<numPredictions;i++) {
			tmpArray = Arrays.copyOf(rawPredictions[i], numClasses);
			arraySum = Utils.sum(tmpArray);
			if(!Utils.eq(arraySum, 0.0))
				processedPredictions[i] = UtilsPT.softMax(tmpArray);
			else
				processedPredictions[i] = tmpArray;
		}
		
		
		
		return processedPredictions;
	}

	/**
	 * @return the combiner
	 */
	public OutputCombiner getCombiner() {
		return this.combiner;
	}

	/**
	 * @param combiner the combiner to set
	 */
	public void setCombiner(OutputCombiner combiner) {
		this.combiner = combiner;
	}

	@Override
	public void getDescription(StringBuilder sb, int indent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void prepareForUseImpl(TaskMonitor monitor, ObjectRepository repository) {
		// TODO Auto-generated method stub
		
	}
	
	

}
