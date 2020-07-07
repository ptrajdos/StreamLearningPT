/**
 * 
 */
package moa.classifiers.meta.qualityUpdated.qualityMeasures;

import moa.classifiers.meta.qualityUpdated.ConfusionMatrix;
import weka.core.Utils;

/**
 * Calculates accuracy
 * @author pawel trajdos
 * @since 0.0.1
 * @version 0.0.1
 *
 */
public class QualityMeasureAccuracy extends AbstractQualityMeasure {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6188226336037019719L;


	@Override
	public double getMeasure(ConfusionMatrix confusionMatrix) {
		double diagonalSum =0;
		double totalSum =0;
		int numClasses = confusionMatrix.getNumClasses();
		
		for(int i=0;i<numClasses;i++)
			for(int j=0;j<numClasses;j++) {
				totalSum+=confusionMatrix.getValue(i, j);
				if(i==j)
					diagonalSum+=confusionMatrix.getValue(i, j);
			}
				
		if(Utils.eq(totalSum, 0))
			return 0.0;
		
		double accuracy = diagonalSum/totalSum;
			
		return accuracy;
	}


	@Override
	public double bestValue() {
		return 1.0;
	}


	@Override
	public double worstValue() {
		return 0.0;
	}


	@Override
	public void getDescription(StringBuilder sb, int indent) {
		super.getDescription(sb, indent);
		sb.append("Accuracy Quality measure");
	}
	

}
