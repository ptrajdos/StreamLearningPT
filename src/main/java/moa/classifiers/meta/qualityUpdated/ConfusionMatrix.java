/**
 * 
 */
package moa.classifiers.meta.qualityUpdated;

import com.yahoo.labs.samoa.instances.Instance;

import moa.classifiers.Classifier;

/**
 * An interface for a confusion matrix
 * @author pawel trajdos
 * @since 0.0.1
 * @version 0.0.1
 *
 */
public interface ConfusionMatrix {
	/**
	 * Sets the classifier related with the confusion matrix
	 * Resets the confusion matrix
	 * @param classifier -- classifier to set
	 */
	public void setClassifier(Classifier classifier);
	
	/**
	 * Returns the internal classifier
	 * @return
	 */
	public Classifier getClassifier();
	
	/**
	 * Resets the confusion matrix without resetting the underlying classifier
	 */
	public void reset();
	
	/**
	 * Update the confusion matrix using the instance
	 * @param inst -- instance to use
	 */
	public void update(Instance inst);
	
	/**
	 * Returns the number of classes
	 * @return
	 */
	public int getNumClasses();
	
	/**
	 * Get the entry of a confusion matris
	 * @param predictedClassNum -- the number of predicted class
	 * @param trueClassNum -- ground truth class
	 * @return
	 */
	public double getValue(int predictedClassNum, int trueClassNum);
	
	
	

}
