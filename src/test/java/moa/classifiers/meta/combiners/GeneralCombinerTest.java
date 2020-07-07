package moa.classifiers.meta.combiners;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import weka.classifiers.meta.generalOutputCombiners.OutputCombiner;
import weka.classifiers.meta.generalOutputCombiners.VoteCombiner;

public class GeneralCombinerTest extends ClassifierResponseSimpleCombinerTest{
	
	public ClassifierResponseSimpleCombiner getCombiner() {
		return new GeneralCombiner();
	}
	
	@Test
	public void testGetAndSet() {
		GeneralCombiner comb = (GeneralCombiner) this.getCombiner();
		
		OutputCombiner intComb = comb.getCombiner();
		assertTrue("Default combiner", intComb instanceof OutputCombiner);
		
		OutputCombiner newComb = new VoteCombiner();
		comb.setCombiner(newComb);
		assertTrue("Properly set the new combiner", comb.getCombiner().equals(newComb));
	}

	

}
