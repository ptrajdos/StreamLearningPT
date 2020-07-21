/**
 * 
 */
package moa.classifiers.meta;

import com.yahoo.labs.samoa.instances.Instance;

/**
 * 
 * @author pawel trajdos
 * @since 0.0.1
 * @version 0.0.1
 *
 */
public class OnlineQualityUpdatedEnsembleWithSampling extends OnlineQualityUpdatedEnsemble {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5707278669539050562L;
	

	/**
	 * 
	 */
	public OnlineQualityUpdatedEnsembleWithSampling() {
		super();
	}

	@Override
	public boolean isRandomizable() {
		return true;
	}

	@Override
	protected void trainEnsembleMembersOnInstance(Instance inst) {
		if(this.activeClassifiers == 0 )
			return;
		if(this.activeClassifiers == 1) {
			if(this.classifierRandom.nextBoolean())
				this.ensemble[0].trainOnInstance(inst);
		}else {
			int updIdx = classifierRandom.nextInt(activeClassifiers);
			this.ensemble[updIdx].trainOnInstance(inst);
		}
		
	}


}
