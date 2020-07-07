/**
 * 
 */
package moa.classifiers;

import com.yahoo.labs.samoa.instances.Instance;

import moa.classifiers.AbstractClassifier;
import moa.core.Measurement;

/**
 * @author pawel
 *
 */
public class OracleClassifier extends AbstractClassifier {

	/**
	 * 
	 */
	private static final long serialVersionUID = -689172309887107421L;
	
	private int numClasses =0;
	private boolean initialised=false;


	@Override
	public boolean isRandomizable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double[] getVotesForInstance(Instance inst) {
		double[] prediction = new double[numClasses];
		prediction[(int) inst.classValue()]=1.0;
		return prediction;
	}

	@Override
	public void resetLearningImpl() {
		this.initialised=false;
	}
	

	@Override
	public void trainOnInstanceImpl(Instance inst) {
		this.initialise(inst);
	}
	
	private void initialise(Instance instance) {
		if(!this.initialised) {
			this.numClasses = instance.numClasses();
		}
	}

	@Override
	protected Measurement[] getModelMeasurementsImpl() {
		return null;
	}

	@Override
	public void getModelDescription(StringBuilder out, int indent) {
		// TODO Auto-generated method stub
	}

}
