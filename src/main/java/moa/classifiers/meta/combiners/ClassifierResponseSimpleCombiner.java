/**
 * 
 */
package moa.classifiers.meta.combiners;

import com.yahoo.labs.samoa.instances.Instance;

/**
 * Interface for classes able to combine raw predictions of multiple learners
 * @author pawel trajdos
 * @since 0.0.1
 * @version 0.0.1
 *
 */
public interface ClassifierResponseSimpleCombiner {
	
	/**
	 * Combine multiple predictions
	 * @param rawPredictions -- raw predictions of a classifier [classifiers]x[responses]
	 * @param weights weight associated with classifiers
	 * @return combined result
	 */
	double[] combine(double[][] rawPredictions, Instance testInstance, double[] weights);
	
	/**
	 * Combine multiple predictions
	 * @param rawPredictions  -- raw predictions of a classifier [classifiers]x[responses]
	 * @return combined result
	 */
	double[] combine(double[][] rawPredictions, Instance testInstance);

}
