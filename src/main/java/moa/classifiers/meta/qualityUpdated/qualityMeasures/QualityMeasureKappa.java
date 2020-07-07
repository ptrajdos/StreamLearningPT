/**
 * 
 */
package moa.classifiers.meta.qualityUpdated.qualityMeasures;

import moa.classifiers.meta.qualityUpdated.ConfusionMatrix;

/**
 * Kappa coefficient measure
 * @author pawel trajdos
 * @since 0.0.1
 * @version 0.0.1
 */
public class QualityMeasureKappa extends AbstractQualityMeasure {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2974903228510664480L;

	/**
	 * 
	 */
	public QualityMeasureKappa() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public double getMeasure(ConfusionMatrix confusionMatrix) {
		int numClasses = confusionMatrix.getNumClasses();
		
		double overallSum=0;
		double diagonalSum=0;
		double[] rowSums = new double[numClasses];
		double[] colSums = new double[numClasses];
		
		double val;
		for(int r=0;r<numClasses;r++) {
			for(int c=0;c<numClasses;c++) {
				val = confusionMatrix.getValue(r, c);
				overallSum+=val;
				if(r==c)
					diagonalSum+=val;
				rowSums[r]+=val;
				colSums[c]+=val;
			}
		}
		
		double acc = diagonalSum/overallSum;
		double pRand=0;
		for(int i=0;i<numClasses;i++) {
			pRand+= (rowSums[i]*colSums[i])/ (overallSum*overallSum);
		}
		
		double preKappa = (acc - pRand)/(1.0 - pRand);
		double normalised = 0.5*(preKappa + 1.0);
		return normalised;
	}

	@Override
	public double bestValue() {
		return 1.0;
	}

	@Override
	public double worstValue() {
		return 0;
	}

	@Override
	public void getDescription(StringBuilder sb, int indent) {
		super.getDescription(sb, indent);
		sb.append("Kappa Quality measure");
	}
	

}
