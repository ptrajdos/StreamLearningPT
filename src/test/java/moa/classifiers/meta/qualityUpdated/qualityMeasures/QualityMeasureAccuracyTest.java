package moa.classifiers.meta.qualityUpdated.qualityMeasures;

import static org.junit.Assert.*;

import org.junit.Test;

import moa.classifiers.meta.qualityUpdated.QualityMeasure;
import moa.classifiers.meta.qualityUpdated.QualityMeasureTest;

public class QualityMeasureAccuracyTest extends QualityMeasureTest {

	@Override
	public QualityMeasure getQualityMeasure() {
		return new QualityMeasureAccuracy();
	}
	
	



}
