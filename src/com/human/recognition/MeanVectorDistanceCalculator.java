package com.human.recognition;

public class MeanVectorDistanceCalculator {
	
	public static void calculateDistanceFromMeanVector(String vectorType,int arrayLength,double[] hogFeatureTrainingArray1,double[] hogFeatureTrainingArray2,double[] hogFeatureTrainingArray3,double[] hogFeatureTrainingArray4,double[] hogFeatureTrainingArray5,double[] hogFeatureTrainingArray6,double[] hogFeatureTrainingArray7,double[] hogFeatureTrainingArray8,double[] hogFeatureTrainingArray9,double[] hogFeatureTrainingArray10)
	{
		//Calculate Mean Vector for the vector type provided
		double[] meanVector=new double[arrayLength];
		double distFromMeanVector=0;
		
		for(int k=0;k<arrayLength;k++)
		{
			meanVector[k]=(hogFeatureTrainingArray1[k]+hogFeatureTrainingArray2[k]+hogFeatureTrainingArray3[k]+hogFeatureTrainingArray4[k]+hogFeatureTrainingArray5[k]+hogFeatureTrainingArray6[k]+hogFeatureTrainingArray7[k]+hogFeatureTrainingArray8[k]+hogFeatureTrainingArray9[k]+hogFeatureTrainingArray10[k])/10.0;
		}
		
		
		System.out.println("Mean "+vectorType+" Vector");


		for (int arr = 0; arr < 15*7*36; arr++) {
			System.out.println(Math.round(Math.sqrt(meanVector[arr])*100.0)/100.0);
		}

		distFromMeanVector=0;
		//Calculate Distance from Image 1
		for(int k=0;k<arrayLength;k++)
		{
			distFromMeanVector=distFromMeanVector+Math.pow((meanVector[k]-hogFeatureTrainingArray1[k]), 2);
		}
		distFromMeanVector=Math.round(Math.sqrt(distFromMeanVector)*100.0)/100.0;
		System.out.println("Distance for 1st "+vectorType+" Feature Vector from Mean Vector: "+distFromMeanVector);

		distFromMeanVector=0;
		//Calculate Distance from Image 2
		for(int k=0;k<arrayLength;k++)
		{
			distFromMeanVector=distFromMeanVector+Math.pow((meanVector[k]-hogFeatureTrainingArray2[k]), 2);
		}
		distFromMeanVector=Math.round(Math.sqrt(distFromMeanVector)*100.0)/100.0;
		System.out.println("Distance for 2nd "+vectorType+" Feature Vector from Mean Vector: "+distFromMeanVector);

		distFromMeanVector=0;
		//Calculate Distance from Image 3
		for(int k=0;k<arrayLength;k++)
		{
			distFromMeanVector=distFromMeanVector+Math.pow((meanVector[k]-hogFeatureTrainingArray3[k]), 2);
		}
		distFromMeanVector=Math.round(Math.sqrt(distFromMeanVector)*100.0)/100.0;
		System.out.println("Distance for 3rd "+vectorType+" Feature Vector from Mean Vector: "+distFromMeanVector);

		distFromMeanVector=0;
		//Calculate Distance from Image 4
		for(int k=0;k<arrayLength;k++)
		{
			distFromMeanVector=distFromMeanVector+Math.pow((meanVector[k]-hogFeatureTrainingArray4[k]), 2);
		}
		distFromMeanVector=Math.round(Math.sqrt(distFromMeanVector)*100.0)/100.0;
		System.out.println("Distance for 4th "+vectorType+" Feature Vector from Mean Vector: "+distFromMeanVector);

		distFromMeanVector=0;
		//Calculate Distance from Image 5
		for(int k=0;k<arrayLength;k++)
		{
			distFromMeanVector=distFromMeanVector+Math.pow((meanVector[k]-hogFeatureTrainingArray5[k]), 2);
		}
		distFromMeanVector=Math.round(Math.sqrt(distFromMeanVector)*100.0)/100.0;
		System.out.println("Distance for 5th "+vectorType+" Feature Vector from Mean Vector: "+distFromMeanVector);

		distFromMeanVector=0;
		//Calculate Distance from Image 6
		for(int k=0;k<arrayLength;k++)
		{
			distFromMeanVector=distFromMeanVector+Math.pow((meanVector[k]-hogFeatureTrainingArray6[k]), 2);
		}
		distFromMeanVector=Math.round(Math.sqrt(distFromMeanVector)*100.0)/100.0;
		System.out.println("Distance for 6th "+vectorType+" Feature Vector from Mean Vector: "+distFromMeanVector);

		distFromMeanVector=0;
		//Calculate Distance from Image 7
		for(int k=0;k<arrayLength;k++)
		{
			distFromMeanVector=distFromMeanVector+Math.pow((meanVector[k]-hogFeatureTrainingArray7[k]), 2);
		}
		distFromMeanVector=Math.round(Math.sqrt(distFromMeanVector)*100.0)/100.0;
		System.out.println("Distance for 7th "+vectorType+" Feature Vector from Mean Vector: "+distFromMeanVector);

		distFromMeanVector=0;
		//Calculate Distance from Image 8
		for(int k=0;k<arrayLength;k++)
		{
			distFromMeanVector=distFromMeanVector+Math.pow((meanVector[k]-hogFeatureTrainingArray8[k]), 2);
		}
		distFromMeanVector=Math.round(Math.sqrt(distFromMeanVector)*100.0)/100.0;
		System.out.println("Distance for 8th "+vectorType+" Feature Vector from Mean Vector: "+distFromMeanVector);

		distFromMeanVector=0;
		//Calculate Distance from Image 9
		for(int k=0;k<arrayLength;k++)
		{
			distFromMeanVector=distFromMeanVector+Math.pow((meanVector[k]-hogFeatureTrainingArray9[k]), 2);
		}
		distFromMeanVector=Math.round(Math.sqrt(distFromMeanVector)*100.0)/100.0;
		System.out.println("Distance for 9th "+vectorType+" Feature Vector from Mean Vector: "+distFromMeanVector);

		distFromMeanVector=0;
		//Calculate Distance from Image 10
		for(int k=0;k<arrayLength;k++)
		{
			distFromMeanVector=distFromMeanVector+Math.pow((meanVector[k]-hogFeatureTrainingArray10[k]), 2);
		}
		distFromMeanVector=Math.round(Math.sqrt(distFromMeanVector)*100.0)/100.0;
		System.out.println("Distance for 10th "+vectorType+" Feature Vector from Mean Vector: "+distFromMeanVector);


		
	}

}
