package moa.classifiers.meta.qualityUpdated;

import com.yahoo.labs.samoa.instances.Instances;
import com.yahoo.labs.samoa.instances.WekaToSamoaInstanceConverter;

import moa.classifiers.bayes.NaiveBayes;
import weka.tools.data.RandomDataGenerator;

public class ConfusionMatrixSimpleTest extends ConfusionMatrixTest {

	

	@Override
	public ConfusionMatrix getConfusionMatrix() {
		return new ConfusionMatrixSimple();
	}
	
	public void testSInit() {
		ConfusionMatrixSimple cnfMatrix = (ConfusionMatrixSimple) this.getConfusionMatrix();
		
		NaiveBayes nbClassifier = new NaiveBayes();
		nbClassifier.prepareForUse();
		
		cnfMatrix.setClassifier(nbClassifier);
		
		RandomDataGenerator gen = new RandomDataGenerator();
		WekaToSamoaInstanceConverter conv = new WekaToSamoaInstanceConverter();
		Instances data = conv.samoaInstances(gen.generateData());
		
		cnfMatrix.initialise(data.get(0));
		
		int numClasses = cnfMatrix.getNumClasses();
		
		assertTrue("Class num", numClasses == data.numClasses());
		for(int i =0; i<numClasses;i++  )
			for(int j=0; j<numClasses;j++) {
				double val = cnfMatrix.getValue(i, j);
				assertTrue("Response interval", val>0 & val<1);
			}
		
	}

}
