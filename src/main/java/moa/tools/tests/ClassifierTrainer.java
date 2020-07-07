/**
 * 
 */
package moa.tools.tests;

import com.yahoo.labs.samoa.instances.Instances;

import moa.classifiers.Classifier;

/**
 * Class supporting classifier training
 * @author pawel trajdos
 * @since 0.0.1
 * @version 0.0.1
 *
 */
public class ClassifierTrainer {
	
	
	public static void trainClassifier(Classifier classifier, Instances instances, int numInstances) {
		int numInstancesPositive = numInstances>0? numInstances:1;
		int realNumInstances = Math.min(instances.numInstances(), numInstancesPositive);
		for(int i=0;i<realNumInstances;i++)
			classifier.trainOnInstance(instances.get(i));
	}
	
	public static void trainClassifier(Classifier classifier, Instances instances) {
		int numInstances = instances.numInstances();
		trainClassifier(classifier, instances,numInstances);
	}

	

}
