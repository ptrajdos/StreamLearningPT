/**
 * 
 */
package moa.classifiers.meta;

import com.yahoo.labs.samoa.instances.Instance;
import com.yahoo.labs.samoa.instances.InstancesHeader;

/**
 * Class that initializes header information for base classifier
 * @author pawel trajdos
 * @since 0.0.1
 * @version 0.0.2
 *
 */
public class HeaderUpdateClassifier extends SingleClassifierEnhancer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1105288297300062651L;

	/**
	 * 
	 */
	public HeaderUpdateClassifier() {
		super();
	}

	/* (non-Javadoc)
	 * @see moa.classifiers.meta.SingleClassifierEnhancer#trainOnInstanceImpl(com.yahoo.labs.samoa.instances.Instance)
	 */
	@Override
	public void trainOnInstanceImpl(Instance inst) {
		if(this.baseClassifier.getModelContext() == null) {
			InstancesHeader header = new InstancesHeader(inst.dataset());
			this.setModelContext(header);
			this.baseClassifier.setModelContext(header);
		}
		super.trainOnInstanceImpl(inst);
	}
	
	

}
