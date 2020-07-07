/**
 * 
 */
package moa.tools.options;

import moa.classifiers.Classifier;
import moa.options.ClassOption;

/**
 * @author pawel trajdos
 * @since 0.0.1
 * @version 0.0.1
 *
 */
public class MOAOptionUtilsPT {

	
	/**
	 * Generates ClassOption object from MOA classifier.
	 * The object is suitable for use with @see weka.classifiers.meta.MOA
	 * @param classifier
	 * @return
	 */
	public static ClassOption generateClassOptionForMOAClassifier(Classifier classifier) {
		
		 ClassOption m_Classifier = new ClassOption(
				"classifier", 'B', "The MOA classifier to use from within WEKA.",
				Classifier.class, classifier.getClass().getName().replace("moa.classifiers.", ""),
				classifier.getClass().getName());
		 
		 String cliString = classifier.getCLICreationString(Object.class);//TODO String z parametrami jest ok!
		 
		 m_Classifier.setValueViaCLIString(cliString);
		 	
		return m_Classifier;
	}

}
