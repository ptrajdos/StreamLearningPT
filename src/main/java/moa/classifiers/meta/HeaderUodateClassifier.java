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
 * @version 0.0.1
 *
 */
public class HeaderUodateClassifier extends SingleClassifierEnhancer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1105288297300062651L;

	/**
	 * 
	 */
	public HeaderUodateClassifier() {
		super();
	}

	/* (non-Javadoc)
	 * @see moa.classifiers.meta.SingleClassifierEnhancer#trainOnInstanceImpl(com.yahoo.labs.samoa.instances.Instance)
	 */
	@Override
	public void trainOnInstanceImpl(Instance inst) {
		InstancesHeader header = new InstancesHeader(inst.dataset());
		this.setModelContext(header);
		super.trainOnInstanceImpl(inst);
	}
	
	

}
