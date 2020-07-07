package moa.classifiers;

import junit.framework.Test;
import junit.framework.TestSuite;
import moa.classifiers.AbstractMultipleClassifierTestCase;
import moa.classifiers.Classifier;
import moa.classifiers.OracleClassifier;

public class OracleClassifierTest extends AbstractMultipleClassifierTestCase {

	public OracleClassifierTest(String name) {
		super(name);
	}

	@Override
	protected Classifier[] getRegressionClassifierSetups() {
		return new Classifier[] {new OracleClassifier()};
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
