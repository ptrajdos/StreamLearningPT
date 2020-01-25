/**
 * 
 */
package moa.classifiers.meta;

/**
 * Online Accuracy Updated Ensemble not constrained to tree classifier
 * @author pawel trajdos
 * @since 0.0.1
 * @version 0.0.1
 *
 */
public class OnlineAccuracyUpdatedEnsembleSNoTree extends OnlineAccuracyUpdatedEnsembleS {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4391512350593807500L;

	/**
	 * 
	 */
	public OnlineAccuracyUpdatedEnsembleSNoTree() {
		super();
	}

	/* (non-Javadoc)
	 * @see moa.classifiers.meta.OnlineAccuracyUpdatedEnsemble#enforceMemoryLimit()
	 */
	@Override
	protected void enforceMemoryLimit() {
		//DO nothing
	}
	
	

}
