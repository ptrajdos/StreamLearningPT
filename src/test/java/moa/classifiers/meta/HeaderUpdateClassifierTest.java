package moa.classifiers.meta;

import junit.framework.Test;
import junit.framework.TestSuite;
import moa.classifiers.Classifier;

public class HeaderUpdateClassifierTest extends SingleClassifierEnhancerTest {

	public HeaderUpdateClassifierTest(String name) {
		super(name);
	}

	/* (non-Javadoc)
	 * @see moa.classifiers.meta.SingleClassifierEnhancerTest#getRegressionClassifierSetups()
	 */
	@Override
	protected Classifier[] getRegressionClassifierSetups() {
		
		OnlineAccuracyUpdatedEnsembleS ens = new OnlineAccuracyUpdatedEnsembleS();
		
		HeaderUpdateClassifier headUpd = new HeaderUpdateClassifier();
		headUpd.learnerOption.setCurrentObject(ens);
		
		return new Classifier[] {headUpd};
	}

	
	 /**
	   * Returns a test suite.
	   *
	   * @return		the test suite
	   */
	  public static Test suite() {
	    return new TestSuite(HeaderUpdateClassifierTest.class);
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
