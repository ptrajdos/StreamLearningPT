package moa.classifiers.meta.qualityUpdated.qualityMeasures;

import moa.classifiers.meta.qualityUpdated.QualityMeasure;
import moa.classifiers.meta.qualityUpdated.QualityMeasureTest;

public class QualityMeasureKappaTest extends QualityMeasureTest {

	@Override
	public QualityMeasure getQualityMeasure() {
		return new QualityMeasureKappa();
	}

	

}
