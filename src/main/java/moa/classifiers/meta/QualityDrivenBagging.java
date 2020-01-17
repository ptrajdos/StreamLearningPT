/**
 * 
 */
package moa.classifiers.meta;

import com.yahoo.labs.samoa.instances.Instance;

import moa.capabilities.CapabilitiesHandler;
import moa.classifiers.AbstractClassifier;
import moa.classifiers.MultiClassClassifier;
import moa.core.Measurement;

/**
 * @author pawel trajdos
 * @since 0.0.1
 * @version 0.0.1
 *
 */
public class QualityDrivenBagging extends AbstractClassifier implements MultiClassClassifier, CapabilitiesHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6536938183472821463L;

	/**
	 * 
	 */
	public QualityDrivenBagging() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isRandomizable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double[] getVotesForInstance(Instance inst) {
		// TODO Auto-generated method stub
		int numClasses= inst.dataset().numClasses();
		return new double[numClasses];
	}

	@Override
	public void resetLearningImpl() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void trainOnInstanceImpl(Instance inst) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Measurement[] getModelMeasurementsImpl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getModelDescription(StringBuilder out, int indent) {
		// TODO Auto-generated method stub
		
	}

}
