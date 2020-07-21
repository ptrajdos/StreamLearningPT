package moa.classifiers.meta;

import junit.framework.Test;
import junit.framework.TestSuite;
import moa.classifiers.Classifier;

public class OnlineQualityUpdatedEnsembleWithSamplingTest extends OnlineQualityUpdatedEnsembleTest {

	public OnlineQualityUpdatedEnsembleWithSamplingTest(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Classifier[] getRegressionClassifierSetups() {
		return new Classifier[] {new OnlineQualityUpdatedEnsembleWithSampling()};
	}
	
	
	 /**
	   * Returns a test suite.
	   *
	   * @return		the test suite
	   */
	  public static Test suite() {
	    return new TestSuite(OnlineQualityUpdatedEnsembleWithSamplingTest.class);
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
