/**
 * 
 */
package moa.classifiers.meta.qualityUpdated;

/**
 * @author pawel trajdos
 * @since 0.0.1
 * @version 0.0.1
 *
 */
public interface QualityMeasure {
	
	/**
	 * Returns the value of the quality measure based on the confusion matrix
	 * @param confusionMatrix
	 * @return
	 */
	public double getMeasure(ConfusionMatrix confusionMatrix);
	
	/**
	 * Returns the value that indicates the best classification results
	 * @return
	 */
	public double bestValue();
	
	
	/**
	 * Returns the value that indicates the worst classification results
	 * @return
	 */
	public double worstValue();

}
