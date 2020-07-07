package moa.classifiers.meta.combiners;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

import com.yahoo.labs.samoa.instances.Instance;
import com.yahoo.labs.samoa.instances.WekaToSamoaInstanceConverter;

import weka.core.Instances;
import weka.core.Utils;
import weka.tools.data.RandomDataGenerator;
import weka.tools.tests.DistributionChecker;

public abstract class ClassifierResponseSimpleCombinerTest {
	
	public abstract ClassifierResponseSimpleCombiner getCombiner();
	
	@Test 
	public void testZeroResponses() {
		RandomDataGenerator gen = new RandomDataGenerator();
		Instances data = gen.generateData();
		WekaToSamoaInstanceConverter conv = new WekaToSamoaInstanceConverter();
		Instance testInstance = conv.samoaInstance(data.get(0));
		
		int numClasses = data.numClasses();
		int numClassifiers = 5; 
		double[][] rawResponses = new double[numClassifiers][numClasses];
		
		ClassifierResponseSimpleCombiner comb = this.getCombiner();
		
		double[] combinedResponse = comb.combine(rawResponses, testInstance);
		
		assertTrue("Zero combinations", Utils.eq(Utils.sum(combinedResponse), 0));
		
		double[] weights = this.generateUniformWeights(numClassifiers);
		
		combinedResponse = comb.combine(rawResponses, testInstance,weights);
		assertTrue("Zero combinations", Utils.eq(Utils.sum(combinedResponse), 0));
		
	}
	
	@Test
	public void testFaultyInitialConditions() {
		RandomDataGenerator gen = new RandomDataGenerator();
		Instances data = gen.generateData();
		WekaToSamoaInstanceConverter conv = new WekaToSamoaInstanceConverter();
		Instance testInstance = conv.samoaInstance(data.get(0));
		int numClasses = data.numClasses();
		int numClassifiers = 5; 
		ClassifierResponseSimpleCombiner comb = this.getCombiner();
		
		double[][] rawResponses = null;
		
		double[] combinedResponse;
		combinedResponse = comb.combine(rawResponses, testInstance);
		assertTrue("Raw responses are null", Utils.eq(Utils.sum(combinedResponse), 0));
		
		double[] unifWeigts = this.generateUniformWeights(numClassifiers);
		
		combinedResponse = comb.combine(rawResponses, testInstance,unifWeigts);
		assertTrue("Raw responses are null, with weights", Utils.eq(Utils.sum(combinedResponse), 0));
		
		rawResponses = this.generateRandomResponses(numClassifiers, numClasses, 0);
		
		
		combinedResponse = comb.combine(rawResponses, testInstance,null);
		assertTrue("Null weights", Utils.eq(Utils.sum(combinedResponse), 0));
		
	}
	
	@Test 
	public void testResponses() {
		RandomDataGenerator gen = new RandomDataGenerator();
		Instances data = gen.generateData();
		WekaToSamoaInstanceConverter conv = new WekaToSamoaInstanceConverter();
		Instance testInstance = conv.samoaInstance(data.get(0));
		int numClasses = data.numClasses();
		int numClassifiers = 5; 
		ClassifierResponseSimpleCombiner comb = this.getCombiner();
		int[] seeds = {1,2,3};
		for(int i =0;i<seeds.length;i++) {
			double[][] rawResponses = this.generateRandomResponses(numClassifiers, numClasses, seeds[i]);
			double[] weights = this.generateRandomWeights(numClassifiers, seeds[i]);
			
			double[] combinedResponse = comb.combine(rawResponses, testInstance,weights);
			assertTrue("Distribution Check", DistributionChecker.checkDistribution(combinedResponse));
		}
	}
	
	protected double[][] generateRandomResponses(int numClassifiers, int numClasses,int seed){
		double [][] rawResps = new double[numClassifiers][numClasses];
		Random rnd = new Random();
		rnd.setSeed(seed);
		for(int i=0;i<numClassifiers;i++) {
			for(int j=0;j<numClasses;j++) {
				rawResps[i][j] = rnd.nextDouble();
			}
			Utils.normalize(rawResps[i]);
		}
		
		return rawResps;
	} 
	
	protected double[] generateUniformWeights(int length) {
		double[] weights = new double[length];
		Arrays.fill(weights, 1.0);
		Utils.normalize(weights);
		return weights;
	}
	
	protected double[] generateRandomWeights(int length, int seed) {
		double[] weights = new double[length];
		Random rnd  = new Random(seed);
		for(int i=0;i<length;i++)
			weights[i] = rnd.nextDouble();
		
		Utils.normalize(weights);
		return weights;
	}
	

}
