package moa.classifiers.meta;

import moa.classifiers.AbstractMultipleClassifierTestCase;
import moa.classifiers.Classifier;

public class QualityDrivenBaggingTest extends AbstractMultipleClassifierTestCase{

	public QualityDrivenBaggingTest(String name) {
		super(name);
		this.setNumberTests(1);
	}

	@Override
	protected Classifier[] getRegressionClassifierSetups() {
		return new Classifier[] {new QualityDrivenBagging()};
	}

	
	

}
