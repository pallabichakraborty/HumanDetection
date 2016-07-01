package com.human.recognition;

import java.io.IOException;


/*
 * Developed by         : Pallabi Chakraborty
 * 
 * Changes Logs
 * ----------------------------------------------------------------------------------------------------------------------------------
 * Changes Done Date      Changes Done
 * ----------------------------------------------------------------------------------------------------------------------------------
 * 27-Nov-2015            Initial Draft              
 */
public class humanRecognition {

	public static void main(String[] args) throws IOException {

		/*
		 * Input - 
		/Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Training/Positive/crop001030c.bmp /Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Training/Positive/crop001030c_Normalized.bmp 
		/Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Training/Positive/crop001034b.bmp /Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Training/Positive/crop001034b_Normalized.bmp 
		/Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Training/Positive/crop001063b.bmp /Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Training/Positive/crop001063b_Normalized.bmp 
		/Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Training/Positive/crop001070a.bmp /Users/pallabichakraborty/Dsktop/MachineVision/Assignments/Project/Project2/Training/Positive/crop001070a_Normalized.bmp 
		/Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Training/Positive/crop001275b.bmp /Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Training/Positive/crop001275b_Normalized.bmp 
		/Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Training/Positive/crop001278a.bmp /Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Training/Positive/crop001278a_Normalized.bmp 
		/Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Training/Positive/crop001500b.bmp /Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Training/Positive/crop001500b_Normalized.bmp 
		/Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Training/Positive/crop001672b.bmp /Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Training/Positive/crop001672b_Normalized.bmp 
		/Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Training/Positive/person_and_bike_026a.bmp /Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Training/Positive/person_and_bike_026a_Normalized.bmp 
		/Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Training/Positive/person_and_bike_151a.bmp /Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Training/Positive/person_and_bike_151a_Normalized.bmp
        /Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Training/Negative/01-03e_cut.bmp /Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Training/Negative/01-03e_cut_Normalized.bmp
        /Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Training/Negative/00000003a_cut.bmp /Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Training/Negative/00000003a_cut_Normalized.bmp 
        /Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Training/Negative/00000057a_cut.bmp /Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Training/Negative/00000057a_cut_Normalized.bmp 
        /Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Training/Negative/00000090a_cut.bmp /Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Training/Negative/00000090a_cut_Normalized.bmp 
        /Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Training/Negative/00000091a_cut.bmp /Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Training/Negative/00000091a_cut_Normalized.bmp 
        /Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Training/Negative/00000118a_cut.bmp /Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Training/Negative/00000118a_cut_Normalized.bmp 
        /Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Training/Negative/no_person__no_bike_219_cut.bmp /Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Training/Negative/no_person__no_bike_219_cut_Normalized.bmp 
        /Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Training/Negative/no_person__no_bike_258_Cut.bmp /Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Training/Negative/no_person__no_bike_258_Cut_Normalized.bmp 
        /Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Training/Negative/no_person__no_bike_259_cut.bmp /Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Training/Negative/no_person__no_bike_259_cut_Normalized.bmp 
        /Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Training/Negative/no_person__no_bike_264_cut.bmp /Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Training/Negative/no_person__no_bike_264_cut_Normalized.bmp
        /Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Test/Positive/crop_000010b.bmp /Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Test/Positive/crop_000010b_Normalized.bmp 
        /Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Test/Positive/crop001008b.bmp /Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Test/Positive/crop001008b_Normalized.bmp 
        /Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Test/Positive/crop001028a.bmp /Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Test/Positive/crop001028a_Normalized.bmp 
        /Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Test/Positive/crop001045b.bmp /Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Test/Positive/crop001045b_Normalized.bmp 
        /Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Test/Positive/crop001047b.bmp /Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Test/Positive/crop001047b_Normalized.bmp
        /Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Test/Negative/00000053a_cut.bmp /Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Test/Negative/00000053a_cut_Normalized.bmp 
        /Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Test/Negative/00000062a_cut.bmp /Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Test/Negative/00000062a_cut_Normalized.bmp 
        /Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Test/Negative/00000093a_cut.bmp /Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Test/Negative/00000093a_cut_Normalized.bmp 
        /Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Test/Negative/no_person__no_bike_213_cut.bmp /Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Test/Negative/no_person__no_bike_213_cut_Normalized.bmp 
        /Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Test/Negative/no_person__no_bike_247_cut.bmp /Users/pallabichakraborty/Desktop/MachineVision/Assignments/Project/Project2/Test/Negative/no_person__no_bike_247_cut_Normalized.bmp
 	 */
		
		//Declaring length of the Feature Vector Array Length
		int featureVectorLength=15*7*36;
		//Declaring variable for Positive Training input image file
		String inputTrainingImage1;
		String inputTrainingImage2;
		String inputTrainingImage3;
		String inputTrainingImage4;
		String inputTrainingImage5;
		String inputTrainingImage6;
		String inputTrainingImage7;
		String inputTrainingImage8;
		String inputTrainingImage9;
		String inputTrainingImage10;
		//Declaring variable for Positive Training input image file
		String inputTrainingImage11;
		String inputTrainingImage12;
		String inputTrainingImage13;
		String inputTrainingImage14;
		String inputTrainingImage15;
		String inputTrainingImage16;
		String inputTrainingImage17;
		String inputTrainingImage18;
		String inputTrainingImage19;
		String inputTrainingImage20;
		//Declaring variable for the Positive Training output image file
		String outputTrainingImage1;
		String outputTrainingImage2;
		String outputTrainingImage3;
		String outputTrainingImage4;
		String outputTrainingImage5;
		String outputTrainingImage6;
		String outputTrainingImage7;
		String outputTrainingImage8;
		String outputTrainingImage9;
		String outputTrainingImage10;
		//Declaring variable for the Positive Training output image file
		String outputTrainingImage11;
		String outputTrainingImage12;
		String outputTrainingImage13;
		String outputTrainingImage14;
		String outputTrainingImage15;
		String outputTrainingImage16;
		String outputTrainingImage17;
		String outputTrainingImage18;
		String outputTrainingImage19;
		String outputTrainingImage20;
		//Declaring variable for Test input image file
		String inputTestPosImage1;
		String inputTestPosImage2;
		String inputTestPosImage3;
		String inputTestPosImage4;
		String inputTestPosImage5;
		String inputTestNegImage1;
		String inputTestNegImage2;
		String inputTestNegImage3;
		String inputTestNegImage4;
		String inputTestNegImage5;
		//Declaring variable for Test output image file
		String outputTestPosImage1;
		String outputTestPosImage2;
		String outputTestPosImage3;
		String outputTestPosImage4;
		String outputTestPosImage5;
		String outputTestNegImage1;
		String outputTestNegImage2;
		String outputTestNegImage3;
		String outputTestNegImage4;
		String outputTestNegImage5;
		//Declaring variable for enabling logging
		String enableLogging="N";
		//Declaring flag to decide if normalized image to be generated or not
		String flagNormalizedImageGen="N";
		//Declaring vector to store HOG Training Vector for Positive Training Images
		double[] hogFeatureTrainingArray1=new double[featureVectorLength];
		double[] hogFeatureTrainingArray2=new double[featureVectorLength];
		double[] hogFeatureTrainingArray3=new double[featureVectorLength];
		double[] hogFeatureTrainingArray4=new double[featureVectorLength];
		double[] hogFeatureTrainingArray5=new double[featureVectorLength];
		double[] hogFeatureTrainingArray6=new double[featureVectorLength];
		double[] hogFeatureTrainingArray7=new double[featureVectorLength];
		double[] hogFeatureTrainingArray8=new double[featureVectorLength];
		double[] hogFeatureTrainingArray9=new double[featureVectorLength];
		double[] hogFeatureTrainingArray10=new double[featureVectorLength];
		//Declaring vector to store HOG Training Vector for Negative Training Images
		double[] hogFeatureTrainingArray11=new double[featureVectorLength];
		double[] hogFeatureTrainingArray12=new double[featureVectorLength];
		double[] hogFeatureTrainingArray13=new double[featureVectorLength];
		double[] hogFeatureTrainingArray14=new double[featureVectorLength];
		double[] hogFeatureTrainingArray15=new double[featureVectorLength];
		double[] hogFeatureTrainingArray16=new double[featureVectorLength];
		double[] hogFeatureTrainingArray17=new double[featureVectorLength];
		double[] hogFeatureTrainingArray18=new double[featureVectorLength];
		double[] hogFeatureTrainingArray19=new double[featureVectorLength];
		double[] hogFeatureTrainingArray20=new double[featureVectorLength];
	
		
        //Initializing the variables for the input Training Images and Output Images
		//Positive
		inputTrainingImage1=args[0].trim();
		outputTrainingImage1=args[1].trim();
		inputTrainingImage2=args[2].trim();
		outputTrainingImage2=args[3].trim();
		inputTrainingImage3=args[4].trim();
		outputTrainingImage3=args[5].trim();
		inputTrainingImage4=args[6].trim();
		outputTrainingImage4=args[7].trim();
		inputTrainingImage5=args[8].trim();
		outputTrainingImage5=args[9].trim();
		inputTrainingImage6=args[10].trim();
		outputTrainingImage6=args[11].trim();
		inputTrainingImage7=args[12].trim();
		outputTrainingImage7=args[13].trim();
		inputTrainingImage8=args[14].trim();
		outputTrainingImage8=args[15].trim();
		inputTrainingImage9=args[16].trim();
		outputTrainingImage9=args[17].trim();
		inputTrainingImage10=args[18].trim();
		outputTrainingImage10=args[19].trim();
		//Negative
		inputTrainingImage11=args[20].trim();
		outputTrainingImage11=args[21].trim();
		inputTrainingImage12=args[22].trim();
		outputTrainingImage12=args[23].trim();
		inputTrainingImage13=args[24].trim();
		outputTrainingImage13=args[25].trim();
		inputTrainingImage14=args[26].trim();
		outputTrainingImage14=args[27].trim();
		inputTrainingImage15=args[28].trim();
		outputTrainingImage15=args[29].trim();
		inputTrainingImage16=args[30].trim();
		outputTrainingImage16=args[31].trim();
		inputTrainingImage17=args[32].trim();
		outputTrainingImage17=args[33].trim();
		inputTrainingImage18=args[34].trim();
		outputTrainingImage18=args[35].trim();
		inputTrainingImage19=args[36].trim();
		outputTrainingImage19=args[37].trim();
		inputTrainingImage20=args[38].trim();
		outputTrainingImage20=args[39].trim();
		 //Initializing the variables for the input Test Images and Output Images
		//Positive
		inputTestPosImage1=args[40].trim();
		outputTestPosImage1=args[41].trim();
		inputTestPosImage2=args[42].trim();
		outputTestPosImage2=args[43].trim();
		inputTestPosImage3=args[44].trim();
		outputTestPosImage3=args[45].trim();
		inputTestPosImage4=args[46].trim();
		outputTestPosImage4=args[47].trim();
		inputTestPosImage5=args[48].trim();
		outputTestPosImage5=args[49].trim();
		//Negative
		inputTestNegImage1=args[50].trim();
		outputTestNegImage1=args[51].trim();
		inputTestNegImage2=args[52].trim();
		outputTestNegImage2=args[53].trim();
		inputTestNegImage3=args[54].trim();
		outputTestNegImage3=args[55].trim();
		inputTestNegImage4=args[56].trim();
		outputTestNegImage4=args[57].trim();
		inputTestNegImage5=args[58].trim();
		outputTestNegImage5=args[59].trim();

		
		//Calculate HOG Vectors for Positive Training Images 
		if(enableLogging.equals("Y"))
		{
			System.out.println("HOG Training Image 1");
		}
		hogFeatureTrainingArray1=hogFeaturesCalculator.calculateHOGFeatureVector(enableLogging, inputTrainingImage1, outputTrainingImage1,flagNormalizedImageGen);
		
		System.out.println("HOG descriptor for "+inputTrainingImage1);

		System.out.println("------------------------------------------------------------------------------");

			System.out.println("HOG Feature Values \n");


			for (int trainarr1 = 0; trainarr1 < 15*7*36; trainarr1++) {
				System.out.println(hogFeatureTrainingArray1[trainarr1]);
			}

		if(enableLogging.equals("Y"))
		{
			System.out.println("HOG Training Image 2");
		}
		
		hogFeatureTrainingArray2=hogFeaturesCalculator.calculateHOGFeatureVector(enableLogging, inputTrainingImage2, outputTrainingImage2,flagNormalizedImageGen);
		
		System.out.println("HOG descriptor for "+inputTrainingImage2);

		System.out.println("------------------------------------------------------------------------------");

			System.out.println("HOG Feature Values \n");


			for (int trainarr2 = 0; trainarr2 < 15*7*36; trainarr2++) {
				System.out.println(hogFeatureTrainingArray2[trainarr2]);
			}
			
		if(enableLogging.equals("Y"))
		{
			System.out.println("HOG Training Image 3");
		}
		hogFeatureTrainingArray3=hogFeaturesCalculator.calculateHOGFeatureVector(enableLogging, inputTrainingImage3, outputTrainingImage3,flagNormalizedImageGen);
		
		if(enableLogging.equals("Y"))
		{
			System.out.println("HOG Training Image 4");
		}
		hogFeatureTrainingArray4=hogFeaturesCalculator.calculateHOGFeatureVector(enableLogging, inputTrainingImage4, outputTrainingImage4,flagNormalizedImageGen);
		
		if(enableLogging.equals("Y"))
		{
			System.out.println("HOG Training Image 5");
		}
		hogFeatureTrainingArray5=hogFeaturesCalculator.calculateHOGFeatureVector(enableLogging, inputTrainingImage5, outputTrainingImage5,flagNormalizedImageGen);
		
		if(enableLogging.equals("Y"))
		{
			System.out.println("HOG Training Image 6");
		}
		hogFeatureTrainingArray6=hogFeaturesCalculator.calculateHOGFeatureVector(enableLogging, inputTrainingImage6, outputTrainingImage6,flagNormalizedImageGen);
		
		if(enableLogging.equals("Y"))
		{
			System.out.println("HOG Training Image 7");
		}
		hogFeatureTrainingArray7=hogFeaturesCalculator.calculateHOGFeatureVector(enableLogging, inputTrainingImage7, outputTrainingImage7,flagNormalizedImageGen);
		
		if(enableLogging.equals("Y"))
		{
			System.out.println("HOG Training Image 8");
		}
		hogFeatureTrainingArray8=hogFeaturesCalculator.calculateHOGFeatureVector(enableLogging, inputTrainingImage8, outputTrainingImage8,flagNormalizedImageGen);
		
		if(enableLogging.equals("Y"))
		{
			System.out.println("HOG Training Image 9");
		}
		hogFeatureTrainingArray9=hogFeaturesCalculator.calculateHOGFeatureVector(enableLogging, inputTrainingImage9, outputTrainingImage9,flagNormalizedImageGen);
		
		if(enableLogging.equals("Y"))
		{
			System.out.println("HOG Training Image 10");
		}
		hogFeatureTrainingArray10=hogFeaturesCalculator.calculateHOGFeatureVector(enableLogging, inputTrainingImage10, outputTrainingImage10,flagNormalizedImageGen);


		//Calculate Distance from Mean Vector for Positive Feature Vectors
		MeanVectorDistanceCalculator.calculateDistanceFromMeanVector("Positive", featureVectorLength, hogFeatureTrainingArray1, hogFeatureTrainingArray2, hogFeatureTrainingArray3, hogFeatureTrainingArray4, hogFeatureTrainingArray5, hogFeatureTrainingArray6, hogFeatureTrainingArray7, hogFeatureTrainingArray8, hogFeatureTrainingArray9, hogFeatureTrainingArray10);

		//Calculate HOG Vectors for Negative Training Images 
		if(enableLogging.equals("Y"))
		{
			System.out.println("HOG Training Negative Image 1");
		}
		hogFeatureTrainingArray11=hogFeaturesCalculator.calculateHOGFeatureVector(enableLogging, inputTrainingImage11, outputTrainingImage11,flagNormalizedImageGen);

		if(enableLogging.equals("Y"))
		{
			System.out.println("HOG Training Negative Image 2");
		}
		hogFeatureTrainingArray12=hogFeaturesCalculator.calculateHOGFeatureVector(enableLogging, inputTrainingImage12, outputTrainingImage12,flagNormalizedImageGen);


		System.out.println("HOG descriptor for "+inputTrainingImage12);

		System.out.println("------------------------------------------------------------------------------");

		
		System.out.println("HOG Feature Values \n");


		for (int trainarr3 = 0; trainarr3 < 15*7*36; trainarr3++) {
			System.out.println(hogFeatureTrainingArray12[trainarr3]);
		}
		
		if(enableLogging.equals("Y"))
		{
			System.out.println("HOG Training Negative Image 3");
		}
		hogFeatureTrainingArray13=hogFeaturesCalculator.calculateHOGFeatureVector(enableLogging, inputTrainingImage13, outputTrainingImage13,flagNormalizedImageGen);

		System.out.println("HOG descriptor for "+inputTrainingImage13);

		System.out.println("------------------------------------------------------------------------------");

		
		System.out.println("HOG Feature Values \n");


		for (int trainarr4 = 0; trainarr4 < 15*7*36; trainarr4++) {
			System.out.println(hogFeatureTrainingArray12[trainarr4]);
		}
		
		if(enableLogging.equals("Y"))
		{
			System.out.println("HOG Training Negative Image 4");
		}
		hogFeatureTrainingArray14=hogFeaturesCalculator.calculateHOGFeatureVector(enableLogging, inputTrainingImage14, outputTrainingImage14,flagNormalizedImageGen);

		if(enableLogging.equals("Y"))
		{
			System.out.println("HOG Training Negative Image 5");
		}
		hogFeatureTrainingArray15=hogFeaturesCalculator.calculateHOGFeatureVector(enableLogging, inputTrainingImage15, outputTrainingImage15,flagNormalizedImageGen);

		if(enableLogging.equals("Y"))
		{
			System.out.println("HOG Training Negative Image 6");
		}
		hogFeatureTrainingArray16=hogFeaturesCalculator.calculateHOGFeatureVector(enableLogging, inputTrainingImage16, outputTrainingImage16,flagNormalizedImageGen);

		if(enableLogging.equals("Y"))
		{
			System.out.println("HOG Training Negative Image 7");
		}
		hogFeatureTrainingArray17=hogFeaturesCalculator.calculateHOGFeatureVector(enableLogging, inputTrainingImage17, outputTrainingImage17,flagNormalizedImageGen);

		if(enableLogging.equals("Y"))
		{
			System.out.println("HOG Training Negative Image 8");
		}
		hogFeatureTrainingArray18=hogFeaturesCalculator.calculateHOGFeatureVector(enableLogging, inputTrainingImage18, outputTrainingImage18,flagNormalizedImageGen);

		if(enableLogging.equals("Y"))
		{
			System.out.println("HOG Training Negative Image 9");
		}
		hogFeatureTrainingArray19=hogFeaturesCalculator.calculateHOGFeatureVector(enableLogging, inputTrainingImage19, outputTrainingImage19,flagNormalizedImageGen);

		if(enableLogging.equals("Y"))
		{
			System.out.println("HOG Training Negative Image 10");
		}
		hogFeatureTrainingArray20=hogFeaturesCalculator.calculateHOGFeatureVector(enableLogging, inputTrainingImage20, outputTrainingImage20,flagNormalizedImageGen);


		System.out.println("---------------------------------------------------------------------------------------------------------------------");
		//Calculate Distance from Mean Vector for Negative Feature Vectors
		MeanVectorDistanceCalculator.calculateDistanceFromMeanVector("Negative", featureVectorLength, hogFeatureTrainingArray11, hogFeatureTrainingArray12, hogFeatureTrainingArray13, hogFeatureTrainingArray14, hogFeatureTrainingArray15, hogFeatureTrainingArray16, hogFeatureTrainingArray17, hogFeatureTrainingArray18, hogFeatureTrainingArray19, hogFeatureTrainingArray20);
		
		//Calculate the final weight after training the system with Training Samples
		double[] weight=new double[featureVectorLength];
		weight=utilities.trainSystemHumanNonHuman(featureVectorLength, hogFeatureTrainingArray1, hogFeatureTrainingArray2, hogFeatureTrainingArray3, hogFeatureTrainingArray4, hogFeatureTrainingArray5, hogFeatureTrainingArray6, hogFeatureTrainingArray7, hogFeatureTrainingArray8, hogFeatureTrainingArray9, hogFeatureTrainingArray10, hogFeatureTrainingArray11, hogFeatureTrainingArray12, hogFeatureTrainingArray13, hogFeatureTrainingArray14, hogFeatureTrainingArray15, hogFeatureTrainingArray16, hogFeatureTrainingArray17, hogFeatureTrainingArray18, hogFeatureTrainingArray19, hogFeatureTrainingArray20);

		if(enableLogging.equals("Y"))
		{
			System.out.println("---------------------------------------------------------------------------------------------------------------------");
			System.out.println("Final Weight after training the system:");
			
			for(int k=0;k<featureVectorLength;k++)
			{
				System.out.println(weight[k]);
			}
		}
		
		System.out.println("******************************************************************************************");
		System.out.println("Training Images Classification Verification");
		System.out.println("******************************************************************************************");
		//Positive Images
		utilities.testHumanOrNonHuman(enableLogging, featureVectorLength, weight, inputTrainingImage1, outputTrainingImage1, "Human",flagNormalizedImageGen);
		utilities.testHumanOrNonHuman(enableLogging, featureVectorLength, weight, inputTrainingImage2, outputTrainingImage2, "Human",flagNormalizedImageGen);
		utilities.testHumanOrNonHuman(enableLogging, featureVectorLength, weight, inputTrainingImage3, outputTrainingImage3, "Human",flagNormalizedImageGen);
		utilities.testHumanOrNonHuman(enableLogging, featureVectorLength, weight, inputTrainingImage4, outputTrainingImage4, "Human",flagNormalizedImageGen);
		utilities.testHumanOrNonHuman(enableLogging, featureVectorLength, weight, inputTrainingImage5, outputTrainingImage5, "Human",flagNormalizedImageGen);
		utilities.testHumanOrNonHuman(enableLogging, featureVectorLength, weight, inputTrainingImage6, outputTrainingImage6, "Human",flagNormalizedImageGen);
		utilities.testHumanOrNonHuman(enableLogging, featureVectorLength, weight, inputTrainingImage7, outputTrainingImage7, "Human",flagNormalizedImageGen);
		utilities.testHumanOrNonHuman(enableLogging, featureVectorLength, weight, inputTrainingImage8, outputTrainingImage8, "Human",flagNormalizedImageGen);
		utilities.testHumanOrNonHuman(enableLogging, featureVectorLength, weight, inputTrainingImage9, outputTrainingImage9, "Human",flagNormalizedImageGen);
		utilities.testHumanOrNonHuman(enableLogging, featureVectorLength, weight, inputTrainingImage10, outputTrainingImage10, "Human",flagNormalizedImageGen);
		//Negative Images
		utilities.testHumanOrNonHuman(enableLogging, featureVectorLength, weight, inputTrainingImage11, outputTrainingImage11, "Non Human",flagNormalizedImageGen);
		utilities.testHumanOrNonHuman(enableLogging, featureVectorLength, weight, inputTrainingImage12, outputTrainingImage12, "Non Human",flagNormalizedImageGen);
		utilities.testHumanOrNonHuman(enableLogging, featureVectorLength, weight, inputTrainingImage13, outputTrainingImage13, "Non Human",flagNormalizedImageGen);
		utilities.testHumanOrNonHuman(enableLogging, featureVectorLength, weight, inputTrainingImage14, outputTrainingImage14, "Non Human",flagNormalizedImageGen);
		utilities.testHumanOrNonHuman(enableLogging, featureVectorLength, weight, inputTrainingImage15, outputTrainingImage15, "Non Human",flagNormalizedImageGen);
		utilities.testHumanOrNonHuman(enableLogging, featureVectorLength, weight, inputTrainingImage16, outputTrainingImage16, "Non Human",flagNormalizedImageGen);
		utilities.testHumanOrNonHuman(enableLogging, featureVectorLength, weight, inputTrainingImage17, outputTrainingImage17, "Non Human",flagNormalizedImageGen);
		utilities.testHumanOrNonHuman(enableLogging, featureVectorLength, weight, inputTrainingImage18, outputTrainingImage18, "Non Human",flagNormalizedImageGen);
		utilities.testHumanOrNonHuman(enableLogging, featureVectorLength, weight, inputTrainingImage19, outputTrainingImage19, "Non Human",flagNormalizedImageGen);
		utilities.testHumanOrNonHuman(enableLogging, featureVectorLength, weight, inputTrainingImage20, outputTrainingImage20, "Non Human",flagNormalizedImageGen);
		
		System.out.println("******************************************************************************************");
		System.out.println("Test Images Classification");
		System.out.println("******************************************************************************************");
		
		//Check the test image to test if the image is human or non human
		//Positive Images
		utilities.testHumanOrNonHuman(enableLogging, featureVectorLength, weight, inputTestPosImage1, outputTestPosImage1, "Human",flagNormalizedImageGen);
		utilities.testHumanOrNonHuman(enableLogging, featureVectorLength, weight, inputTestPosImage2, outputTestPosImage2, "Human",flagNormalizedImageGen);
		utilities.testHumanOrNonHuman(enableLogging, featureVectorLength, weight, inputTestPosImage3, outputTestPosImage3, "Human",flagNormalizedImageGen);
		utilities.testHumanOrNonHuman(enableLogging, featureVectorLength, weight, inputTestPosImage4, outputTestPosImage4, "Human",flagNormalizedImageGen);
		utilities.testHumanOrNonHuman(enableLogging, featureVectorLength, weight, inputTestPosImage5, outputTestPosImage5, "Human",flagNormalizedImageGen);
		//Negative Images
		utilities.testHumanOrNonHuman(enableLogging, featureVectorLength, weight, inputTestNegImage1, outputTestNegImage1, "Non Human",flagNormalizedImageGen);
		utilities.testHumanOrNonHuman(enableLogging, featureVectorLength, weight, inputTestNegImage2, outputTestNegImage2, "Non Human",flagNormalizedImageGen);
		utilities.testHumanOrNonHuman(enableLogging, featureVectorLength, weight, inputTestNegImage3, outputTestNegImage3, "Non Human",flagNormalizedImageGen);
		utilities.testHumanOrNonHuman(enableLogging, featureVectorLength, weight, inputTestNegImage4, outputTestNegImage4, "Non Human",flagNormalizedImageGen);
		utilities.testHumanOrNonHuman(enableLogging, featureVectorLength, weight, inputTestNegImage5, outputTestNegImage5, "Non Human",flagNormalizedImageGen);
		
		
		
	
	}

}
