package moa.classifiers.meta;

import junit.framework.Test;
import junit.framework.TestSuite;
import moa.classifiers.AbstractMultipleClassifierTestCase;
import moa.classifiers.Classifier;


public class SingleClassifierEnhancerTest extends AbstractMultipleClassifierTestCase {

	public SingleClassifierEnhancerTest(String name) {
		super(name);
		this.setNumberTests(1);
		
	}

	@Override
	protected Classifier[] getRegressionClassifierSetups() {
		
		return new Classifier[] {new SingleClassifierEnhancer()};
	}

	/**
	   * Returns a test suite.
	   *
	   * @return		the test suite
	   */
	  public static Test suite() {
	    return new TestSuite(SingleClassifierEnhancerTest.class);
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
