package moa.classifiers.meta;

import junit.framework.Test;
import junit.framework.TestSuite;
import moa.classifiers.Classifier;

public class OnlineAccuracyUpdatedEnsembleSTest extends OnlineAccuracyUpdatedEnsembleTest {

	public OnlineAccuracyUpdatedEnsembleSTest(String name) {
		super(name);
	}

	@Override
	protected Classifier[] getRegressionClassifierSetups() {
		return new Classifier[] {new OnlineAccuracyUpdatedEnsembleS()};
	}
	
	 /**
	   * Returns a test suite.
	   *
	   * @return		the test suite
	   */
	  public static Test suite() {
	    return new TestSuite(OnlineAccuracyUpdatedEnsembleSTest.class);
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
