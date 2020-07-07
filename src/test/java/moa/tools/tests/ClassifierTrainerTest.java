package moa.tools.tests;

import com.yahoo.labs.samoa.instances.WekaToSamoaInstanceConverter;

import junit.framework.TestCase;
import moa.classifiers.Classifier;
import moa.classifiers.OracleClassifier;
import moa.classifiers.bayes.NaiveBayes;
import weka.core.Instances;
import weka.tools.data.RandomDataGenerator;
import weka.tools.tests.DistributionChecker;

public class ClassifierTrainerTest extends TestCase {

	public void testTrainer1() {
		RandomDataGenerator gen = new RandomDataGenerator();
		Instances dataWeka = gen.generateData();
		WekaToSamoaInstanceConverter conv = new WekaToSamoaInstanceConverter();
		com.yahoo.labs.samoa.instances.Instances samoaData = conv.samoaInstances(dataWeka);
		
		
		NaiveBayes nbClassifier = new NaiveBayes();
		
		
		Classifier classifier = new OracleClassifier();//TODO distribution do not fit with other classifiers. Why?
		classifier.prepareForUse();
		
		ClassifierTrainer.trainClassifier(classifier, samoaData);
		
		int numInstances = samoaData.numInstances();
		for(int i=0;i<numInstances;i++) {
			double[] distribution = classifier.getVotesForInstance(samoaData.get(i));
			assertTrue("Distribution Check ", DistributionChecker.checkDistribution(distribution));
		}
		
		
	}
}
