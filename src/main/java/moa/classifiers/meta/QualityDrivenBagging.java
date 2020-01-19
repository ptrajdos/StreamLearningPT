/**
 * 
 */
package moa.classifiers.meta;

import java.util.Arrays;

import com.github.javacliparser.IntOption;
import com.yahoo.labs.samoa.instances.Instance;

import moa.capabilities.CapabilitiesHandler;
import moa.classifiers.MultiClassClassifier;

/**
 * @author pawel trajdos
 * @since 0.0.1
 * @version 0.0.1
 *
 */
public class QualityDrivenBagging extends OzaBag implements MultiClassClassifier, CapabilitiesHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5849789559439763278L;
	
	/**
	 * Options
	 */
	
	public IntOption chunkSizeOption = new IntOption("chunkSize", 'c', "The chunk size used for classifier creation and evaluation.", 500, 1, Integer.MAX_VALUE);
	
	/**
	 * Variables
	 */
	
	protected boolean[] activeClassifiers;

	/* (non-Javadoc)
	 * @see moa.classifiers.meta.OzaBag#resetLearningImpl()
	 */
	@Override
	public void resetLearningImpl() {
		super.resetLearningImpl();
		this.activeClassifiers = new boolean[this.ensemble.length];
	}

	/* (non-Javadoc)
	 * @see moa.classifiers.meta.OzaBag#getVotesForInstance(com.yahoo.labs.samoa.instances.Instance)
	 */
	@Override
	public double[] getVotesForInstance(Instance inst) {
		
		return super.getVotesForInstance(inst);
	}
	
	
	
	
	



}
