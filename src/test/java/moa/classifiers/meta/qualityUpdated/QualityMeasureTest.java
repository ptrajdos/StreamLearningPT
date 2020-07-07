package moa.classifiers.meta.qualityUpdated;

import com.yahoo.labs.samoa.instances.Instances;
import com.yahoo.labs.samoa.instances.WekaToSamoaInstanceConverter;

import junit.framework.TestCase;
import moa.classifiers.OracleClassifier;
import weka.core.Utils;
import weka.tools.SerialCopier;
import weka.tools.data.RandomDataGenerator;

public abstract class QualityMeasureTest extends TestCase {
	
	public abstract QualityMeasure getQualityMeasure();
	
	public void testSerializable() {
		QualityMeasure measure = this.getQualityMeasure();
		
		try {
			QualityMeasure copy = (QualityMeasure) SerialCopier.makeCopy(measure);
		} catch (Exception e) {
			fail("Quality Measure serialization test. Exception" + e.getClass().getCanonicalName());
		}
	}

	public void testVals() {
		QualityMeasure measure = this.getQualityMeasure();
		
		double bestVal = measure.bestValue();
		assertTrue("Best Value", Double.isFinite(bestVal));
		
		double worstVal  = measure.worstValue();
		assertTrue("Worst Value", Double.isFinite(worstVal));
	}
	
	public void testPerfectClassifier() {
		QualityMeasure measure = this.getQualityMeasure();
		
		OracleClassifier ora = new OracleClassifier();
		
		WekaToSamoaInstanceConverter conv = new WekaToSamoaInstanceConverter();
		RandomDataGenerator gen = new RandomDataGenerator();
		Instances data = conv.samoaInstances(gen.generateData());
		
		ora.prepareForUse();
		ora.trainOnInstance(data.get(0));
		
		ConfusionMatrix confMatrix = new ConfusionMatrixSimple();
		confMatrix.setClassifier(ora);
		
		int numInstances = data.numInstances();
		for(int i=0;i<numInstances;i++) {
			confMatrix.update(data.get(i));
		}
		
		double quality = measure.getMeasure(confMatrix);
		assertTrue("PerfectClassifier test", Utils.eq(quality, measure.bestValue()));
		
	}

}
