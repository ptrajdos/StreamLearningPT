package moa.classifiers.meta.qualityUpdated;

import com.yahoo.labs.samoa.instances.Instance;
import com.yahoo.labs.samoa.instances.Instances;
import com.yahoo.labs.samoa.instances.WekaToSamoaInstanceConverter;

import junit.framework.TestCase;
import moa.classifiers.Classifier;
import moa.classifiers.OracleClassifier;
import moa.classifiers.bayes.NaiveBayes;
import weka.tools.SerialCopier;
import weka.tools.data.RandomDataGenerator;

public abstract class ConfusionMatrixTest extends TestCase {
	
	public abstract ConfusionMatrix getConfusionMatrix();

	public void testSerialization() {
		ConfusionMatrix cnf = this.getConfusionMatrix();
		
		try {
			ConfusionMatrix copy = (ConfusionMatrix) SerialCopier.makeCopy(cnf);
		} catch (Exception e) {
			fail("Serialization has failed -- an exception has been caught");
		}
		
	}
	
	public void testGetAndSet() {
		ConfusionMatrix matrix = this.getConfusionMatrix();
		
		assertTrue("No classifier at the beginning:", matrix.getClassifier()==null);
		
		Classifier classifier = new OracleClassifier();
		
		matrix.setClassifier(classifier);
		
		assertTrue("Get classifier", matrix.getClassifier().equals(classifier));
	}
	
	public void testInit() {
		ConfusionMatrix cnfMatrix = this.getConfusionMatrix();
		
		OracleClassifier ora = new OracleClassifier();
		ora.prepareForUse();
		
		cnfMatrix.setClassifier(ora);
		
		RandomDataGenerator gen = new RandomDataGenerator();
		WekaToSamoaInstanceConverter conv = new WekaToSamoaInstanceConverter();
		Instances data = conv.samoaInstances(gen.generateData());
		
		Instance testInstance = data.get(0);
		cnfMatrix.getClassifier().trainOnInstance(testInstance);
		
		cnfMatrix.update(testInstance);
		
		int numClasses = cnfMatrix.getNumClasses();
		
		assertTrue("Number of classes", numClasses == data.numClasses() );
		
		int testInstanceTrueClass = (int) testInstance.classValue();
		
		for(int i=0;i<numClasses;i++)
			for(int j=0;j<numClasses;j++) {
				double val = cnfMatrix.getValue(i, j);
				if(i == testInstanceTrueClass & j ==testInstanceTrueClass)
					assertTrue("Hit", val >1.0 & val <1.1);
				else
					assertTrue("Empty", val>0 & val < 0.1);
			}
		
		
		
		
	}

}
