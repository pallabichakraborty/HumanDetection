package com.human.recognition;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;

public class hogFeaturesCalculator {

	public static double[] calculateHOGFeatureVector(String enableLogging,String inputImage, String outputImageFile,String flagNormalizedImageGen) throws IOException
	{
        
		//Declaring BufferedImage to store the input image
		BufferedImage bfImage;
		//Declaring variables to store the dimensions of pixels in the picture
		int imageWidth;
		int imageHeight;
		//Declaring variables to store the dimensions of pixels in the picture
		int inputimageWidth;
		int inputimageHeight;

		//Declaring the templates for row and columns for convolution of image
		int[][] convctemp=new int[3][1];
		convctemp[0][0]=-1;
		convctemp[1][0]=0;
		convctemp[2][0]=1;

		int[][] convrtemp=new int[1][3];
		convrtemp[0][0]=-1;
		convrtemp[0][1]=0;
		convrtemp[0][2]=1;

		//Extracting the image data and the dimensions of the image
		bfImage = ImageIO.read(new File(inputImage));
		inputimageWidth = bfImage.getWidth();
		inputimageHeight= bfImage.getHeight();

		//Declaring the input Pixel array with the image width and image height.
		int[][] inputPixelArray = new int[inputimageWidth-32][inputimageHeight-32];

		//Reading data from the image
		inputPixelArray=utilities.readInputImage(enableLogging, bfImage, inputimageWidth, inputimageHeight);

		if(enableLogging.equals("Y"))
		{
			System.out.println("Original Image without Transposing");
			
			for(int i=0;i<inputimageWidth-32;i++)
			{
				for(int j=0;j<inputimageHeight-32;j++)
				{
					System.out.print(inputPixelArray[i][j]+"\t");
				}
				System.out.println();
			}
		}
		

		//Generate GrayScale image file
		//utilities.getImageFromArray(inputPixelArray, inputimageWidth, inputimageHeight,outputImageFile);


		imageWidth = inputimageHeight-32;
		imageHeight= inputimageWidth-32;

		//Transposed Array
		int[][] transposedPixelArray = new int[imageWidth][imageHeight];
		transposedPixelArray=utilities.transposeArray(inputPixelArray, imageHeight, imageWidth);


		if(enableLogging.equals("Y"))
		{
			System.out.println("------------------------------------------------------------------------------");
			
			System.out.println("Transposed Array");
			
			for(int i=0;i<imageWidth;i++)
			{
				for(int j=0;j<imageHeight;j++)
				{
					System.out.print(transposedPixelArray[i][j]+"\t");
				}
				System.out.println();
			}

			System.out.println("------------------------------------------------------------------------------");

		}
		
		//Declaring arrays to store the convolved arrays in the R and C axis,r pointing downward and c pointing to the right
		int[][] convRAxisArray=new int[imageWidth][imageHeight];
		int[][] convCAxisArray=new int[imageWidth][imageHeight];

		//Calculate the convolution in the C axis
		convCAxisArray=utilities.convolveImageCAxis(enableLogging, transposedPixelArray, imageWidth, imageHeight, convctemp[0][0], convctemp[1][0], convctemp[2][0]);

		if(enableLogging.equals("Y"))
		{
			for(int i=0;i<imageWidth;i++)
			{
				for(int j=0;j<imageHeight;j++)
				{
					System.out.print(convCAxisArray[i][j]+"\t");
				}
				System.out.println();
			}

			System.out.println("------------------------------------------------------------------------------");

		}
		
		//Calculate the convolution in the R axis
		convRAxisArray=utilities.convolveImageRAxis(enableLogging, transposedPixelArray, imageWidth, imageHeight, convrtemp[0][0], convrtemp[0][1], convrtemp[0][2]);

		if(enableLogging.equals("Y"))
		{
			for(int i=0;i<imageWidth;i++)
			{
				for(int j=0;j<imageHeight;j++)
				{
					System.out.print(convRAxisArray[i][j]+"\t");
				}
				System.out.println();
			}

			System.out.println("------------------------------------------------------------------------------");

		}
		
		//Declaring arrays to store the gradient magnitude and angle
		int[][] convMagArray=new int[imageWidth][imageHeight];
		double[][] convAngleArray=new double[imageWidth][imageHeight];

		//Calculating the Magnitude and Gradient Angles
		convMagArray=utilities.computeConvMagnitude(enableLogging, convCAxisArray, convRAxisArray, imageWidth, imageHeight);
		convAngleArray=utilities.computeConvAngleArray(enableLogging, convCAxisArray, convRAxisArray, imageWidth, imageHeight);

		if(enableLogging.equals("Y"))
		{
			for(int i=0;i<imageWidth;i++)
			{
				for(int j=0;j<imageHeight;j++)
				{
					System.out.print(convMagArray[i][j]+"\t");
				}
				System.out.println();
			}

			System.out.println("------------------------------------------------------------------------------");

			for(int i=0;i<imageWidth;i++)
			{
				for(int j=0;j<imageHeight;j++)
				{
					System.out.print(convAngleArray[i][j]+"\t");
				}
				System.out.println();
			}
		}
		


		//Declaring arrays to store normalized magnitudes
		int[][] normXYMagValues=new int[imageWidth][imageHeight];
		//Calculate Normalized Magnitude by dividing the magnitude with a square root of 2
		normXYMagValues=utilities.normalizeMagValue(enableLogging, convMagArray, imageWidth, imageHeight);

		//Generate image file
		if(flagNormalizedImageGen.equals("Y"))
		{
			utilities.getImageFromArray(normXYMagValues, imageWidth, imageHeight,outputImageFile);
		}

		//Declaring array to store angle array when the RC axis is changed to XY plane
		double[][] normXYAngeValues=new double[imageWidth][imageHeight];
		//Calculate Angle Array by negating the angle in RC axis to obtain the angle in XY plane.
		normXYAngeValues=utilities.chngAngleToXYPlane(enableLogging, convAngleArray, imageWidth, imageHeight);

		if(enableLogging.equals("Y"))
		{
			System.out.println("------------------------------------------------------------------------------");

			for(int i=0;i<imageWidth;i++)
			{
				for(int j=0;j<imageHeight;j++)
				{
					System.out.print(normXYAngeValues[i][j]+"\t");
				}
				System.out.println();
			}
		}
		

		//Compute local edge orientation histograms (LEOHs) and output

		int rowcount = imageWidth / 8;

		int columncount = imageHeight / 8;

		String[][] histogramangle = new String[rowcount][columncount];

		histogramangle=utilities.computeLEOH(enableLogging, normXYAngeValues, normXYMagValues, imageWidth, imageHeight);

		if(enableLogging.equals("Y"))
		{
			System.out.println("------------------------------------------------------------------------------");

			System.out.println("Histogram Values \n");


			// Print the array
			for (int j = 0; j < rowcount; j++) {
				for (int k = 0; k < columncount; k++) {
					System.out.print(StringUtils.rightPad(histogramangle[j][k], 35, " ") + "\t");

				}
				System.out.println();

			}
		}
		

		String[][] blockArray=new String[rowcount-1][columncount-1];
		blockArray=utilities.createBlocksConcatenateCells(enableLogging, histogramangle, imageWidth, imageHeight);

		if(enableLogging.equals("Y")||(inputImage.contains("crop001030c")||inputImage.contains("crop001034b")||inputImage.contains("00000003a_cut")||inputImage.contains("00000057a_cut")||inputImage.contains("crop001008b")||inputImage.contains("00000053a_cut")))
		{
			System.out.println("------------------------------------------------------------------------------");

			System.out.println("Block Values for "+inputImage);


			// Print the array
			for (int j = 0; j < rowcount-1; j++) {
				for (int k = 0; k < columncount-1; k++) {
					System.out.println(StringUtils.rightPad(blockArray[j][k], 4, " ") + "\t");

				}

			}
		}
		

		//Generating Normalized Block Array
		String[][] normalizedblockArray = new String[rowcount-1][columncount-1];
		normalizedblockArray=utilities.createNormalizedBlock(enableLogging, blockArray, imageWidth, imageHeight);

		if(enableLogging.equals("Y"))
		{
			System.out.println("------------------------------------------------------------------------------");

			System.out.println("NormalizedBlock Values \n");


			// Print the array
			for (int j = 0; j < rowcount-1; j++) {
				for (int k = 0; k < columncount-1; k++) {
					System.out.print(StringUtils.rightPad(normalizedblockArray[j][k], 200, " ") + "\t");

				}
				System.out.println();

			}
		}
		

		//Generating HOG Feature Vector
		double[] hogFeatureArray=new double[(rowcount-1)*(columncount-1)*36];
		hogFeatureArray=utilities.createHOGFeatureVector(enableLogging, normalizedblockArray, imageWidth, imageHeight);

		return hogFeatureArray;
	}

}
