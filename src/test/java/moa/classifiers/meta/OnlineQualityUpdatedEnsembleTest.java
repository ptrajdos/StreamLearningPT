package moa.classifiers.meta;

import junit.framework.Test;
import junit.framework.TestSuite;
import moa.classifiers.AbstractMultipleClassifierTestCase;
import moa.classifiers.Classifier;

public class OnlineQualityUpdatedEnsembleTest extends AbstractMultipleClassifierTestCase {

	public OnlineQualityUpdatedEnsembleTest(String name) {
		super(name);
		this.setNumberTests(1);
	}

	@Override
	protected Classifier[] getRegressionClassifierSetups() {
		return new Classifier[] {new OnlineQualityUpdatedEnsemble()};
	}
	
	
	 /**
	   * Returns a test suite.
	   *
	   * @return		the test suite
	   */
	  public static Test suite() {
	    return new TestSuite(OnlineQualityUpdatedEnsembleTest.class);
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
