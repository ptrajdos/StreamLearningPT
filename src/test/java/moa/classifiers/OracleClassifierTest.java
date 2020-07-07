package moa.classifiers;

import com.yahoo.labs.samoa.instances.Instance;
import com.yahoo.labs.samoa.instances.Instances;
import com.yahoo.labs.samoa.instances.WekaToSamoaInstanceConverter;

import junit.framework.Test;
import junit.framework.TestSuite;
import moa.classifiers.AbstractMultipleClassifierTestCase;
import moa.classifiers.Classifier;
import moa.classifiers.OracleClassifier;
import weka.tools.data.RandomDataGenerator;

public class OracleClassifierTest extends AbstractMultipleClassifierTestCase {

	public OracleClassifierTest(String name) {
		super(name);
	}

	@Override
	protected Classifier[] getRegressionClassifierSetups() {
		return new Classifier[] {new OracleClassifier()};
	}
	
	public void testDoubleInitialization() {
		RandomDataGenerator gen = new RandomDataGenerator();
		WekaToSamoaInstanceConverter conv = new WekaToSamoaInstanceConverter();
		Instances data = conv.samoaInstances(gen.generateData());
		Instance testInstance = data.get(0);
		
		Classifier[] classifiers =  this.getRegressionClassifierSetups();
		
		for(int i=0;i<classifiers.length;i++) {
			classifiers[i].trainOnInstance(testInstance);
			classifiers[i].trainOnInstance(testInstance);
		}
	}
	
	
	 /**
	   * Returns a test suite.
	   *
	   * @return		the test suite
	   */
	  public static Test suite() {
	    return new TestSuite(OracleClassifierTest.class);
	  }

	  /**
	   * Runs the test from commandline.
	   *
	   * @param args	ignored
	   */
	  public static void main(String[] args) {
	    runTest(suite());
	  }

	
}
