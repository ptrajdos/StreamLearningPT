/**
 * 
 */
package moa.classifiers.meta;

import com.yahoo.labs.samoa.instances.Instance;

import moa.classifiers.AbstractClassifier;
import moa.classifiers.Classifier;
import moa.core.Measurement;
import moa.core.ObjectRepository;
import moa.options.ClassOption;
import moa.tasks.TaskMonitor;

/**
 * @author pawel
 *
 */
public class SingleClassifierEnhancer extends AbstractClassifier {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1523429806395907027L;
	
	/**
	 * Type of classifier to use as a component classifier.
	 */
	public ClassOption learnerOption = new ClassOption("learner", 'l', "Classifier to train.", Classifier.class, 
			"trees.HoeffdingTree -e 2000000 -g 100 -c 0.01");
	
	protected Classifier baseClassifier;

	
	public SingleClassifierEnhancer() {
		super();
	}

	/* (non-Javadoc)
	 * @see moa.learners.Learner#isRandomizable()
	 */
	@Override
	public boolean isRandomizable() {
		if(this.baseClassifier != null)
			return this.baseClassifier.isRandomizable();
		return false;
	}

	/* (non-Javadoc)
	 * @see moa.classifiers.AbstractClassifier#getVotesForInstance(com.yahoo.labs.samoa.instances.Instance)
	 */
	@Override
	public double[] getVotesForInstance(Instance inst) {
		return this.baseClassifier.getVotesForInstance(inst);
	}
	
	

	/* (non-Javadoc)
	 * @see moa.classifiers.AbstractClassifier#prepareForUseImpl(moa.tasks.TaskMonitor, moa.core.ObjectRepository)
	 */
	@Override
	public void prepareForUseImpl(TaskMonitor monitor, ObjectRepository repository) {
		this.resetLearningImpl();
		super.prepareForUseImpl(monitor, repository);
	}

	/* (non-Javadoc)
	 * @see moa.classifiers.AbstractClassifier#resetLearningImpl()
	 */
	@Override
	public void resetLearningImpl() {
		this.baseClassifier = ((Classifier) getPreparedClassOption(this.learnerOption)).copy();
		this.baseClassifier.resetLearning();
	}

	/* (non-Javadoc)
	 * @see moa.classifiers.AbstractClassifier#trainOnInstanceImpl(com.yahoo.labs.samoa.instances.Instance)
	 */
	@Override
	public void trainOnInstanceImpl(Instance inst) {
		this.baseClassifier.trainOnInstance(inst);

	}

	/* (non-Javadoc)
	 * @see moa.classifiers.AbstractClassifier#getModelMeasurementsImpl()
	 */
	@Override
	protected Measurement[] getModelMeasurementsImpl() {
		return this.baseClassifier.getModelMeasurements();
	}

	/* (non-Javadoc)
	 * @see moa.classifiers.AbstractClassifier#getModelDescription(java.lang.StringBuilder, int)
	 */
	@Override
	public void getModelDescription(StringBuilder out, int indent) {
		this.baseClassifier.getModel().getDescription(out, indent);

	}

}
