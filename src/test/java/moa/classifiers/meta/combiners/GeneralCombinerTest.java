package moa.classifiers.meta.combiners;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import weka.classifiers.meta.generalOutputCombiners.OutputCombiner;
import weka.classifiers.meta.generalOutputCombiners.VoteCombiner;

public class GeneralCombinerTest extends ClassifierResponseSimpleCombinerTest{
	
	public ClassifierResponseSimpleCombiner getCombiner() {
		return new GeneralCombiner();
	}
	
	

}
