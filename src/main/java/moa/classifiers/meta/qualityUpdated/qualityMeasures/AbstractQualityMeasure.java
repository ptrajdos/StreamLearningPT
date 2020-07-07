/**
 * 
 */
package moa.classifiers.meta.qualityUpdated.qualityMeasures;

import moa.classifiers.meta.qualityUpdated.QualityMeasure;
import moa.core.ObjectRepository;
import moa.options.AbstractOptionHandler;
import moa.tasks.TaskMonitor;

/**
 * An abstract class for Quality Measures
 * @author pawel trajdos
 * @since 0.0.1
 * @version 0.0.1
 *
 */
public abstract class AbstractQualityMeasure extends AbstractOptionHandler implements QualityMeasure {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -6868264651017788853L;


	@Override
	public void getDescription(StringBuilder sb, int indent) {
		// TODO Auto-generated method stub

	}


	@Override
	protected void prepareForUseImpl(TaskMonitor monitor, ObjectRepository repository) {
		//TODO do nothing
	}

}
