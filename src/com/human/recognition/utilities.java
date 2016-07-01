package com.human.recognition;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


/*
 * Description  : Utilities declared to be able to be called by the main program
 * Developed by : Pallabi Chakraborty
 * 
 * Changes Logs
 * ----------------------------------------------------------------------------------------------------------------------------------
 * Changes Done Date      Changes Done
 * ----------------------------------------------------------------------------------------------------------------------------------
 * 27-Nov-2015            Initial Draft   
 */

public class utilities {

	
	static double errorCount=0;

	// This method reads the image and extracts the pixel array and returns
	// the same.
	public static int[][] readInputImage(String enableLogging, BufferedImage bfImage, int imageWidth, int imageHeight) throws IOException {

		// Extracting the Pixel Values from the image and storing the same in an
		// array which is declared as an integer array with the 2-D array of
		// imageWidth and imageHeight
		

		int[][] array = new int[imageWidth-32][imageHeight-32];

		for (int j = 16; j < imageWidth-16; j++) {
			for (int k = 16; k < imageHeight-16; k++) {
				Color c = new Color(bfImage.getRGB(j, k));
				
				array[j-16][k-16] = (int)Math.round(0.21*c.getRed() + 0.72*c.getGreen() + 0.07*c.getBlue());
			}
		}

		return array;

	}
	
	// This method transposes the input array
	public static int[][] transposeArray(int[][] inputPixelArray,int imageWidth,int imageHeight)
	{
		int[][] array = new int[imageHeight][imageWidth];
		for(int i=0;i<imageHeight;i++)
		{
			for(int j=0;j<imageWidth;j++)
			{
				array[i][j]=inputPixelArray[j][i];
			}
		}
		return array;
	}

	// This method convolves the pixel matrix with the template and gives
	// resultant array in C Axis
	public static int[][] convolveImageCAxis(String enableLogging, int[][] inputPixelArray, int imageWidth,
			int imageHeight, int convctemp00, int convctemp10, int convctemp20) {
		// Declaring the destination array
		int[][] convCAxisArray = new int[imageWidth][imageHeight];

		for (int j = 0; j < imageWidth; j++) {
			for (int k = 0; k < imageHeight; k++) {
				// As the center cell is taken as reference cell, this the
				// border rows and columns will always be out of boundary,
				// assigning -1 to such cells.
				if (j == 0 || k == 0 || j == imageWidth - 1 || k == imageHeight - 1) {
					convCAxisArray[j][k] = 0;
				} else {
					// Multiplying the template with the records in the array
					convCAxisArray[j][k] = inputPixelArray[j][k - 1] * convctemp00 + inputPixelArray[j][k] * convctemp10
							+ inputPixelArray[j][k + 1] * convctemp20;

				}
			}

		}

		// If the Logging enabled file is Y then output the array
		if (enableLogging.equals("Y")) {
			System.out.println("Convolution through C \n");
			
		}

		return convCAxisArray;
	}

	// This method convolves the pixel matrix with the template and gives
	// resultant array in R Axis
	public static int[][] convolveImageRAxis(String enableLogging, int[][] inputPixelArray, int imageWidth,
			int imageHeight, int convrtemp00, int convrtemp01, int convrtemp02) {
		// Declaring the destination array
		int[][] convRAxisArray = new int[imageWidth][imageHeight];

		for (int j = 0; j < imageWidth; j++) {
			for (int k = 0; k < imageHeight; k++) {
				// As the center cell is taken as reference cell, this the
				// border rows and columns will always be out of boundary,
				// assigning -1 to such cells.
				if (j == 0 || k == 0 || j == imageWidth - 1 || k == imageHeight - 1) {
					convRAxisArray[j][k] = 0;
				} else {
					// Multiplying the template with the records in the array
					convRAxisArray[j][k] = inputPixelArray[j - 1][k] * convrtemp00 + inputPixelArray[j][k] * convrtemp01
							+ inputPixelArray[j + 1][k] * convrtemp02;

				}
			}

		}

		// If the Logging enabled file is Y then output the array
		if (enableLogging.equals("Y")) {
			System.out.println("Convolution through R \n");
			
		}

		return convRAxisArray;
	}

	// This method takes two arrays for R and C and gives the magnitude of the
	// gradient
	public static int[][] computeConvMagnitude(String enableLogging, int[][] convCAxisArray, int[][] convRAxisArray,
			int imageWidth, int imageHeight) {
		// Declaring the destination array
		int[][] convmagarray = new int[imageWidth][imageHeight];

		for (int j = 0; j < imageWidth; j++) {
			for (int k = 0; k < imageHeight; k++) {
				if (j == 0 || k == 0 || j == imageWidth - 1 || k == imageHeight - 1) {
					convmagarray[j][k] = 0;
				} 
				else
				{
					convmagarray[j][k] = (int) Math.round(Math.sqrt(
							Math.pow((double) convCAxisArray[j][k], 2.0) + Math.pow((double) convRAxisArray[j][k], 2.0)));

				}
				
			}

		}

	
		return convmagarray;
	}

	// This method takes two arrays for R and C and gives the angle of the
	// gradient
	public static double[][] computeConvAngleArray(String enableLogging, int[][] convCAxisArray, int[][] convRAxisArray,
			int imageWidth, int imageHeight) {
		// Declaring the destination array
		double[][] convanglearray = new double[imageWidth][imageHeight];

		for (int j = 0; j < imageWidth; j++) {
			for (int k = 0; k < imageHeight; k++) {
				if (j == 0 || k == 0 || j == imageWidth - 1 || k == imageHeight - 1) {
					convanglearray[j][k] = 0;
				} 
				else
				{
					if (convCAxisArray[j][k] == 0) {
						if (convRAxisArray[j][k] < 0) {
							convanglearray[j][k] = -90;
						} 
						else if (convRAxisArray[j][k] == 0)
						{
							convanglearray[j][k] = -999;
						}
						else
						{
							convanglearray[j][k] = 90;
						}
					} else {
						convanglearray[j][k] = Math.round(Math
								.toDegrees(Math.atan((double) convRAxisArray[j][k] / (double) convCAxisArray[j][k]))*100.0)/100.0;
					}
				}
				
			}

		}

		

		return convanglearray;
	}

	// This method normalizes the magnitude value, so that pixel values lie
	// within the range [0, 255].
	public static int[][] normalizeMagValue(String enableLogging, int[][] convmagarray, int imageWidth, int imageHeight) {
		int[][] convNormArray = new int[imageWidth][imageHeight];

		// Normalize the Convoluted Magnitudes
		for (int j = 0; j < imageWidth; j++) {
			for (int k = 0; k < imageHeight; k++) {
				if (j == 0 || k == 0 || j == imageWidth - 1 || k == imageHeight - 1) {
					convNormArray[j][k] = 0;
				} 
				else
				{
					convNormArray[j][k] = (int) Math.round((double)convmagarray[j][k] / Math.sqrt(2));
				}
				
			}
		}
		
		return convNormArray;
	}

	// This method is used to calculate the gradient angle when the coordinate
	// system is changed to XY plane
	public static double[][] chngAngleToXYPlane(String enableLogging, double[][] convanglearray, int imageWidth,
			int imageHeight) {
		// Declaring the destination array
		double[][] convxyanglearray = new double[imageWidth][imageHeight];

		for (int j = 0; j < imageWidth; j++) {
			for (int k = 0; k < imageHeight; k++) {
				if (j == 0 || k == 0 || j == imageWidth - 1 || k == imageHeight - 1) {
					convxyanglearray[j][k] = 0;
				} 
				else
				{
					if(convanglearray[j][k]!=0)
					{
						convxyanglearray[j][k] = -1 * convanglearray[j][k];		
					}
						
				}
			}

		}

		

		return convxyanglearray;
	}

	
	//This function accepts a pixel array and generates a bmp file from the same.
	public static void getImageFromArray(int[][] normXYMagValues, int imageWidth, int imageHeight,String outputImageFile) throws IOException {
		//Creating a BufferedImage for Gray Scale.
		
		
		int[][] transposedArray=utilities.transposeArray(normXYMagValues, imageWidth, imageHeight);
		
		BufferedImage image = new BufferedImage(imageHeight,imageWidth, BufferedImage.TYPE_BYTE_GRAY);
		WritableRaster raster=(WritableRaster) image.getData();
		
		//Set the pixel data into the image
		//Set the pixel data into the image
				for (int x = 0; x < imageHeight; x++) {
				    for (int y = 0; y < imageWidth; y++) {
				    	raster.setSample(x, y, 0, transposedArray[x][y]);
				    }
				
		}
		
		image.setData(raster);
		
		//If the output file exists delete it and recreate a new file.
        File outputfile = new File(outputImageFile);
        
        if (outputfile.exists())
		{
        	outputfile.delete();
		}
        //Write the image into the output file
        ImageIO.write(image, "bmp", outputfile);
    }
	
	// Computing local edge orientation histograms (LEOHs) using the quantized
		// angles and normalized grandient magnitude
	public static String[][] computeLEOH(String enableLogging, double[][] normXYAngeValues, int[][] normXYMagValues,int imageWidth, int imageHeight) 
	{
		//Calculating the row count and column count for the histogram array for LEOH
		int rowcount = imageWidth / 8;

		int columncount = imageHeight / 8;


		//Creating the array to save the histograms set
		String[][] histogramangle = new String[rowcount][columncount];


		for (int l = 0; l < rowcount; l++) {


			for (int m = 0; m < columncount; m++) {

				double countQuant0 = 0.0;
				double countQuant1 = 0.0;
				double countQuant2 = 0.0;
				double countQuant3 = 0.0;
				double countQuant4 = 0.0;
				double countQuant5 = 0.0;
				double countQuant6 = 0.0;
				double countQuant7 = 0.0;
				double countQuant8 = 0.0;


				for (int j = 8 * l; j < 8 * (l + 1); j++) {

					for (int k = 8 * m; k < 8 * (m + 1); k++) {

						if (j != 0 && k != 0 && j != imageWidth - 1 && k != imageHeight - 1 && normXYAngeValues[j][k]!=999){
							
							if (normXYAngeValues[j][k] < 0) {
								normXYAngeValues[j][k] = normXYAngeValues[j][k] + 360;
							}

							
							//For Angle Value equal to 10, add the weighted vote to [0-20) bucket
							if(normXYAngeValues[j][k]==10.0)
							{
								countQuant0=countQuant0+normXYMagValues[j][k];
							}
							//For values between 10 and 30, the value is divided into the buckets 0 and 1
							else if(normXYAngeValues[j][k]>10.0 && normXYAngeValues[j][k]<30.0)
							{
								countQuant0=countQuant0+(1-(Math.abs(normXYAngeValues[j][k]-10.0)/20.0))*normXYMagValues[j][k];
								countQuant1=countQuant1+(1-(Math.abs(normXYAngeValues[j][k]-30.0)/20.0))*normXYMagValues[j][k];
							}
							//For Angle Value equal to 30, add the weighted vote to [20-40) bucket
							else if(normXYAngeValues[j][k]==30.0)
							{
								countQuant1=countQuant1+normXYMagValues[j][k];
							}
							//For values between 30 and 50, the value is divided into the buckets 1 and 2
							else if(normXYAngeValues[j][k]>30.0 && normXYAngeValues[j][k]<50.0)
							{
								countQuant1=countQuant1+(1-(Math.abs(normXYAngeValues[j][k]-30.0)/20.0))*normXYMagValues[j][k];
								countQuant2=countQuant2+(1-(Math.abs(normXYAngeValues[j][k]-50.0)/20.0))*normXYMagValues[j][k];
							}
							//For Angle Value equal to 50, add the weighted vote to [40-60) bucket
							else if(normXYAngeValues[j][k]==50.0)
							{
								countQuant2=countQuant2+normXYMagValues[j][k];
							}
							//For values between 50 and 70, the value is divided into the buckets 2 and 3
							else if(normXYAngeValues[j][k]>50.0 && normXYAngeValues[j][k]<70.0)
							{
								countQuant2=countQuant2+(1-(Math.abs(normXYAngeValues[j][k]-50.0)/20.0))*normXYMagValues[j][k];
								countQuant3=countQuant3+(1-(Math.abs(normXYAngeValues[j][k]-70.0)/20.0))*normXYMagValues[j][k];
							}
							//For Angle Value equal to 70, add the weighted vote to [60-80) bucket
							else if(normXYAngeValues[j][k]==70.0)
							{
								countQuant3=countQuant3+normXYMagValues[j][k];
							}
							//For values between 70 and 90, the value is divided into the buckets 3 and 4
							else if(normXYAngeValues[j][k]>70.0 && normXYAngeValues[j][k]<90.0)
							{
								countQuant3=countQuant3+(1-(Math.abs(normXYAngeValues[j][k]-70.0)/20.0))*normXYMagValues[j][k];
								countQuant4=countQuant4+(1-(Math.abs(normXYAngeValues[j][k]-90.0)/20.0))*normXYMagValues[j][k];
							}
							//For Angle Value equal to 90, add the weighted vote to [80-100) bucket
							else if(normXYAngeValues[j][k]==90.0)
							{
								countQuant4=countQuant4+normXYMagValues[j][k];
							}
							//For values between 90 and 110, the value is divided into the buckets 4 and 5
							else if(normXYAngeValues[j][k]>90.0 && normXYAngeValues[j][k]<110.0)
							{
								countQuant4=countQuant4+(1-(Math.abs(normXYAngeValues[j][k]-90.0)/20.0))*normXYMagValues[j][k];
								countQuant5=countQuant5+(1-(Math.abs(normXYAngeValues[j][k]-110.0)/20.0))*normXYMagValues[j][k];
							}
							//For Angle Value equal to 110, add the weighted vote to [100-120) bucket
							else if(normXYAngeValues[j][k]==110.0)
							{
								countQuant5=countQuant5+normXYMagValues[j][k];
							}
							//For values between 110 and 130, the value is divided into the buckets 5 and 6
							else if(normXYAngeValues[j][k]>110.0 && normXYAngeValues[j][k]<130.0)
							{
								countQuant5=countQuant5+(1-(Math.abs(normXYAngeValues[j][k]-110.0)/20.0))*normXYMagValues[j][k];
								countQuant6=countQuant6+(1-(Math.abs(normXYAngeValues[j][k]-130.0)/20.0))*normXYMagValues[j][k];
							}
							//For Angle Value equal to 130, add the weighted vote to [120-140) bucket
							else if(normXYAngeValues[j][k]==130.0)
							{
								countQuant6=countQuant6+normXYMagValues[j][k];
							}
							//For values between 130 and 150, the value is divided into the buckets 6 and 7
							else if(normXYAngeValues[j][k]>130.0 && normXYAngeValues[j][k]<150.0)
							{
								countQuant6=countQuant6+(1-(Math.abs(normXYAngeValues[j][k]-130.0)/20.0))*normXYMagValues[j][k];
								countQuant7=countQuant7+(1-(Math.abs(normXYAngeValues[j][k]-150.0)/20.0))*normXYMagValues[j][k];
							}
							//For Angle Value equal to 150, add the weighted vote to [140-160) bucket
							else if(normXYAngeValues[j][k]==150.0)
							{
								countQuant7=countQuant7+normXYMagValues[j][k];
							}
							//For values between 150 and 170, the value is divided into the buckets 7 and 8
							else if(normXYAngeValues[j][k]>150.0 && normXYAngeValues[j][k]<170.0)
							{
								countQuant7=countQuant7+(1-(Math.abs(normXYAngeValues[j][k]-150.0)/20.0))*normXYMagValues[j][k];
								countQuant8=countQuant8+(1-(Math.abs(normXYAngeValues[j][k]-170.0)/20.0))*normXYMagValues[j][k];
							}
							//For Angle Value equal to 170, add the weighted vote to [160-180) bucket
							else if(normXYAngeValues[j][k]==170.0)
							{
								countQuant8=countQuant8+normXYMagValues[j][k];
							}
							//For values between 170 and 190, the value is divided into the buckets 8 and 0
							else if(normXYAngeValues[j][k]>170.0 && normXYAngeValues[j][k]<190.0)
							{
								countQuant8=countQuant8+(1-(Math.abs(normXYAngeValues[j][k]-170.0)/20.0))*normXYMagValues[j][k];
								countQuant0=countQuant0+(1-(Math.abs(normXYAngeValues[j][k]-190.0)/20.0))*normXYMagValues[j][k];
							}
							//For Angle Value equal to 190, add the weighted vote to [180-200) bucket
							else if(normXYAngeValues[j][k]==190.0)
							{
								countQuant0=countQuant0+normXYMagValues[j][k];
							}
							//For values between 190 and 210, the value is divided into the buckets 0 and 1
							else if(normXYAngeValues[j][k]>190.0 && normXYAngeValues[j][k]<210.0)
							{
								countQuant0=countQuant0+(1-(Math.abs(normXYAngeValues[j][k]-190.0)/20.0))*normXYMagValues[j][k];
								countQuant1=countQuant1+(1-(Math.abs(normXYAngeValues[j][k]-210.0)/20.0))*normXYMagValues[j][k];
							}
							//For Angle Value equal to 210, add the weighted vote to [200-220) bucket
							else if(normXYAngeValues[j][k]==210.0)
							{
								countQuant1=countQuant1+normXYMagValues[j][k];
							}
							//For values between 210 and 230, the value is divided into the buckets 1 and 2
							else if(normXYAngeValues[j][k]>210.0 && normXYAngeValues[j][k]<230.0)
							{
								countQuant1=countQuant1+(1-(Math.abs(normXYAngeValues[j][k]-210.0)/20.0))*normXYMagValues[j][k];
								countQuant2=countQuant2+(1-(Math.abs(normXYAngeValues[j][k]-230.0)/20.0))*normXYMagValues[j][k];
							}
							//For Angle Value equal to 230, add the weighted vote to [220-230) bucket
							else if(normXYAngeValues[j][k]==230.0)
							{
								countQuant2=countQuant2+normXYMagValues[j][k];
							}
							//For values between 230 and 250, the value is divided into the buckets 2 and 3
							else if(normXYAngeValues[j][k]>230.0 && normXYAngeValues[j][k]<250.0)
							{
								countQuant2=countQuant2+(1-(Math.abs(normXYAngeValues[j][k]-230.0)/20.0))*normXYMagValues[j][k];
								countQuant3=countQuant3+(1-(Math.abs(normXYAngeValues[j][k]-250.0)/20.0))*normXYMagValues[j][k];
							}
							//For Angle Value equal to 250, add the weighted vote to [240-260) bucket
							else if(normXYAngeValues[j][k]==250.0)
							{
								countQuant3=countQuant3+normXYMagValues[j][k];
							}
							//For values between 250 and 270, the value is divided into the buckets 3 and 4
							else if(normXYAngeValues[j][k]>250.0 && normXYAngeValues[j][k]<270.0)
							{
								countQuant3=countQuant3+(1-(Math.abs(normXYAngeValues[j][k]-250.0)/20.0))*normXYMagValues[j][k];
								countQuant4=countQuant4+(1-(Math.abs(normXYAngeValues[j][k]-270.0)/20.0))*normXYMagValues[j][k];
							}
							//For Angle Value equal to 270, add the weighted vote to [260-280) bucket
							else if(normXYAngeValues[j][k]==270.0)
							{
								countQuant4=countQuant4+normXYMagValues[j][k];
							}
							//For values between 270 and 290, the value is divided into the buckets 4 and 5
							else if(normXYAngeValues[j][k]>270.0 && normXYAngeValues[j][k]<290.0)
							{
								countQuant4=countQuant4+(1-(Math.abs(normXYAngeValues[j][k]-270.0)/20.0))*normXYMagValues[j][k];
								countQuant5=countQuant5+(1-(Math.abs(normXYAngeValues[j][k]-290.0)/20.0))*normXYMagValues[j][k];
							}
							//For Angle Value equal to 290, add the weighted vote to [280-300) bucket
							else if(normXYAngeValues[j][k]==290.0)
							{
								countQuant5=countQuant5+normXYMagValues[j][k];
							}
							//For values between 290 and 310, the value is divided into the buckets 5 and 6
							else if(normXYAngeValues[j][k]>290.0 && normXYAngeValues[j][k]<310.0)
							{
								countQuant5=countQuant5+(1-(Math.abs(normXYAngeValues[j][k]-290.0)/20.0))*normXYMagValues[j][k];
								countQuant6=countQuant6+(1-(Math.abs(normXYAngeValues[j][k]-310.0)/20.0))*normXYMagValues[j][k];
							}
							//For Angle Value equal to 310, add the weighted vote to [300-320) bucket
							else if(normXYAngeValues[j][k]==310.0)
							{
								countQuant6=countQuant6+normXYMagValues[j][k];
							}
							//For values between 310 and 330, the value is divided into the buckets 6 and 7
							else if(normXYAngeValues[j][k]>310.0 && normXYAngeValues[j][k]<330.0)
							{
								countQuant6=countQuant6+(1-(Math.abs(normXYAngeValues[j][k]-310.0)/20.0))*normXYMagValues[j][k];
								countQuant7=countQuant7+(1-(Math.abs(normXYAngeValues[j][k]-330.0)/20.0))*normXYMagValues[j][k];
							}
							//For Angle Value equal to 330, add the weighted vote to [320-340) bucket
							else if(normXYAngeValues[j][k]==330.0)
							{
								countQuant7=countQuant7+normXYMagValues[j][k];
							}
							//For values between 330 and 350, the value is divided into the buckets 7 and 8
							else if(normXYAngeValues[j][k]>330.0 && normXYAngeValues[j][k]<350.0)
							{
								countQuant7=countQuant7+(1-(Math.abs(normXYAngeValues[j][k]-330.0)/20.0))*normXYMagValues[j][k];
								countQuant8=countQuant8+(1-(Math.abs(normXYAngeValues[j][k]-350.0)/20.0))*normXYMagValues[j][k];
							}
							//For Angle Value equal to 350, add the weighted vote to [340-360) bucket
							else if(normXYAngeValues[j][k]==350.0)
							{
								countQuant8=countQuant8+normXYMagValues[j][k];
							}
							//For values between 350 and 0, the value is divided into the buckets 0 and 8
							else if(normXYAngeValues[j][k]>350.0 && normXYAngeValues[j][k]<360.0)
							{
								countQuant8=countQuant8+(1-((Math.abs(normXYAngeValues[j][k]-350.0))/20.0))*normXYMagValues[j][k];
								countQuant0=countQuant0+(1-((Math.abs(normXYAngeValues[j][k]-360.0)+10)/20.0))*normXYMagValues[j][k];
							}
							//For values between 0 and 10, the value is divided into the buckets 7 and 8
							else if(normXYAngeValues[j][k]>=0.0 && normXYAngeValues[j][k]<10.0)
							{
								countQuant8=countQuant8+(1-((normXYAngeValues[j][k]+10.0)/20.0))*normXYMagValues[j][k];
								countQuant0=countQuant0+(1-(normXYAngeValues[j][k]/20.0))*normXYMagValues[j][k];
							}

						}

					}

				}


				//Creating the histogram using the buckets magnitude divided by the average cell values
				histogramangle[l][m] = String.valueOf(Math.round(countQuant0)) + " "
						+ String.valueOf(Math.round(countQuant1)) + " "
						+ String.valueOf(Math.round(countQuant2)) + " "
						+ String.valueOf(Math.round(countQuant3)) + " "
						+ String.valueOf(Math.round(countQuant4)) + " "
						+ String.valueOf(Math.round(countQuant5)) + " "
						+ String.valueOf(Math.round(countQuant6)) + " "
						+ String.valueOf(Math.round(countQuant7)) + " "
						+ String.valueOf(Math.round(countQuant8));

			}

		}


		return histogramangle;

	}

	//Create blocks from the cell histograms
	public static String[][] createBlocksConcatenateCells(String enableLogging, String[][] histogramangle,int imageWidth, int imageHeight)
	{
		//Calculating the row count and column count for the histogram array for LEOH
		int rowcount = imageWidth / 8;
        int columncount = imageHeight / 8;
        
        
      //Creating the array to generate block array
        String[][] blockArray = new String[rowcount-1][columncount-1];
        
		for (int l = 0; l < rowcount-1; l++) 
		{
           for (int m = 0; m < columncount-1; m++) 
           {
        	   blockArray[l][m]=histogramangle[l][m]+" "+histogramangle[l][m+1]+" "+histogramangle[l+1][m]+" "+histogramangle[l+1][m+1];
		   }
		}
        
		return blockArray;
        

	}
	
	//Create normalized block array from block array
	public static String[][] createNormalizedBlock(String enableLogging, String[][] blockArray,int imageWidth, int imageHeight)
	{
		
		//Calculating the row count and column count for the histogram array for LEOH
				int rowcount = imageWidth / 8;
		        int columncount = imageHeight / 8;
		        
		        
		      //Creating the array to generate block array
		        String[][] normalizedblockArray = new String[rowcount-1][columncount-1];
		        
		        for (int l = 0; l < rowcount-1; l++) 
				{
		           for (int m = 0; m < columncount-1; m++) 
		           {
		        	   
		        	   String[] blockvalues=blockArray[l][m].split(" ");
		        	   
		        	   double sum=0;
		        	   double L2Norm=0;
		        	   double normalizedvalue=0;
		        	   
		        	   for(String value:blockvalues)
		        	   {
		        		   sum=sum+Math.pow(Double.parseDouble(value.trim()), 2);
		        	   }
		        	   
		        	   L2Norm=Math.sqrt(sum);
		        	   
		        	   for(String blockvalue:blockvalues)
		        	   {
		        		   normalizedvalue=Math.round((Double.parseDouble(blockvalue.trim())/L2Norm)*100.0)/100.0;
		        		   if(normalizedblockArray[l][m]==null)
		        		   {
		        			   normalizedblockArray[l][m]=String.valueOf(normalizedvalue);
		        		   }
		        		   else
		        		   {
		        			   normalizedblockArray[l][m]=normalizedblockArray[l][m]+" "+normalizedvalue;
		        		   }
		        		  
		        	   }
		        	   
		           }
		           
				}
		        
		        return normalizedblockArray;
	}
	
	//Create HOG feature vector
	public static double[] createHOGFeatureVector(String enableLogging, String[][] normalizedblockArray,int imageWidth, int imageHeight)
	{
		
		//Calculating the row count and column count for the histogram array for LEOH
		int rowcount = imageWidth / 8;
        int columncount = imageHeight / 8;
        
        double[] hogFeatureArray=new double[(rowcount-1)*(columncount-1)*36];
        
        int arrayValue=0;
        
        for (int l = 0; l < rowcount-1; l++) 
		{
           for (int m = 0; m < columncount-1; m++) 
           {
        	   String[] blockValues=normalizedblockArray[l][m].split(" ");
        	   
        	   for(String blockValue:blockValues)
        	   {
        			   hogFeatureArray[arrayValue]=Double.parseDouble(blockValue.trim());
        			   arrayValue=arrayValue+1;
            	  
        	   }
        	   
        	   
           }
           
		}
        
        return hogFeatureArray;
	}
	
	public static double[] multiplyArrayWithConstant(int arrayLength,double[] array,double constData)
	{
		double[] mulArray=new double[arrayLength];
		
		for(int i=0;i<arrayLength;i++)
		{
			mulArray[i]=array[i]*constData;
		}
		
		return mulArray;
	}
	
	public static double[] calculateOutputAdjustWeight(String posOrNegFlag,int featureVectorLength,double[] hogFeatureTrainingArray,double[] weight)
	{
		double alpha=0.5;
		double output=0;
		
		System.out.println("Alpha Considered:"+alpha);
	
		for(int i=0;i<featureVectorLength;i++)
		{
			output=output+weight[i]*hogFeatureTrainingArray[i];
		}
		
		//To add the last value of weight for which the feature vector is added with a 1
		output=output+weight[featureVectorLength];


		//As there is an error in the detection, thus adjustment to be done
		if(posOrNegFlag.equals("P"))
		{
			if(output<0)
			{
				errorCount++;

				double[] adjustment=utilities.multiplyArrayWithConstant(featureVectorLength, hogFeatureTrainingArray, alpha);

				for(int i=0;i<featureVectorLength;i++)
				{
					weight[i]=weight[i]+adjustment[i];
				}
			}

		}
		else if(posOrNegFlag.equals("N"))
		{
			if(output>0)
			{
				errorCount++;

				double[] adjustment=utilities.multiplyArrayWithConstant(featureVectorLength, hogFeatureTrainingArray, alpha);

				for(int i=0;i<featureVectorLength;i++)
				{
					weight[i]=weight[i]-adjustment[i];
				}
			}
		}
		
		return weight;
	}
	//Train the system to be able to distinguish between human and non human
	//Returns final weight vector which can be used to ascertain if the test image is human or not
	public static double[] trainSystemHumanNonHuman(int featureVectorLength,double[] hogFeatureTrainingArray1, double[] hogFeatureTrainingArray2, double[] hogFeatureTrainingArray3, double[] hogFeatureTrainingArray4, double[] hogFeatureTrainingArray5, double[] hogFeatureTrainingArray6, double[] hogFeatureTrainingArray7, double[] hogFeatureTrainingArray8, double[] hogFeatureTrainingArray9, double[] hogFeatureTrainingArray10,double[] hogFeatureTrainingArray11, double[] hogFeatureTrainingArray12, double[] hogFeatureTrainingArray13, double[] hogFeatureTrainingArray14, double[] hogFeatureTrainingArray15, double[] hogFeatureTrainingArray16, double[] hogFeatureTrainingArray17, double[] hogFeatureTrainingArray18, double[] hogFeatureTrainingArray19, double[] hogFeatureTrainingArray20)
	{
		double[] weight=new double[featureVectorLength+1];
		
		boolean nextLoopRequired=true;
		int iterationNumber=0;
		
		//Assign 0 to weight array as the initial weight
		for(int i=0;i<featureVectorLength+1;i++)
		{
			weight[i]=-1.0;
		}
		
		System.out.println("Initial Weight after training the system:");
		
		for(int k=0;k<featureVectorLength+1;k++)
		{
			System.out.println(weight[k]);
		}
		
		
		while(nextLoopRequired)
		{   
			iterationNumber++;
			errorCount=0;
			
			//Train the System for the 1st Positive Array
			System.out.println("Training with 1st Positive Image Sample");
			weight=utilities.calculateOutputAdjustWeight("P", featureVectorLength, hogFeatureTrainingArray1, weight);
			//Train the System for the 1st Negative Array
			System.out.println("Training with 1st Negative Image Sample");
			weight=utilities.calculateOutputAdjustWeight("N", featureVectorLength, hogFeatureTrainingArray11, weight);
			//Train the System for the 2nd Positive Array
			System.out.println("Training with 2nd Positive Image Sample");
			weight=utilities.calculateOutputAdjustWeight("P", featureVectorLength, hogFeatureTrainingArray2, weight);
			//Train the System for the 2nd Negative Array
			System.out.println("Training with 2nd Negative Image Sample");
			weight=utilities.calculateOutputAdjustWeight("N", featureVectorLength, hogFeatureTrainingArray12, weight);
			//Train the System for the 3rd Positive Array
			System.out.println("Training with 3rd Positive Image Sample");
			weight=utilities.calculateOutputAdjustWeight("P", featureVectorLength, hogFeatureTrainingArray3, weight);
			//Train the System for the 3rd Negative Array
			System.out.println("Training with 3rd Negative Image Sample");
			weight=utilities.calculateOutputAdjustWeight("N", featureVectorLength, hogFeatureTrainingArray13, weight);
			//Train the System for the 4th Positive Array
			System.out.println("Training with 4th Positive Image Sample");
			weight=utilities.calculateOutputAdjustWeight("P", featureVectorLength, hogFeatureTrainingArray4, weight);
			//Train the System for the 4th Negative Array
			System.out.println("Training with 4th Negative Image Sample");
			weight=utilities.calculateOutputAdjustWeight("N", featureVectorLength, hogFeatureTrainingArray14, weight);
			//Train the System for the 5th Positive Array
			System.out.println("Training with 5th Positive Image Sample");
			weight=utilities.calculateOutputAdjustWeight("P", featureVectorLength, hogFeatureTrainingArray5, weight);
			//Train the System for the 5th Negative Array
			System.out.println("Training with 5th Negative Image Sample");
			weight=utilities.calculateOutputAdjustWeight("N", featureVectorLength, hogFeatureTrainingArray15, weight);
			//Train the System for the 6th Positive Array
			System.out.println("Training with 6th Positive Image Sample");
			weight=utilities.calculateOutputAdjustWeight("P", featureVectorLength, hogFeatureTrainingArray6, weight);
			//Train the System for the 6th Negative Array
			System.out.println("Training with 6th Negative Image Sample");
			weight=utilities.calculateOutputAdjustWeight("N", featureVectorLength, hogFeatureTrainingArray16, weight);
			//Train the System for the 7th Positive Array
			System.out.println("Training with 7th Positive Image Sample");
			weight=utilities.calculateOutputAdjustWeight("P", featureVectorLength, hogFeatureTrainingArray7, weight);
			//Train the System for the 7th Negative Array
			System.out.println("Training with 7th Negative Image Sample");
			weight=utilities.calculateOutputAdjustWeight("N", featureVectorLength, hogFeatureTrainingArray17, weight);
			//Train the System for the 8th Positive Array
			System.out.println("Training with 8th Positive Image Sample");
			weight=utilities.calculateOutputAdjustWeight("P", featureVectorLength, hogFeatureTrainingArray8, weight);
			//Train the System for the 8th Negative Array
			System.out.println("Training with 8th Negative Image Sample");
			weight=utilities.calculateOutputAdjustWeight("N", featureVectorLength, hogFeatureTrainingArray18, weight);
			//Train the System for the 9th Positive Array
			System.out.println("Training with 9th Positive Image Sample");
			weight=utilities.calculateOutputAdjustWeight("P", featureVectorLength, hogFeatureTrainingArray9, weight);
			//Train the System for the 9th Negative Array
			System.out.println("Training with 9th Negative Image Sample");
			weight=utilities.calculateOutputAdjustWeight("N", featureVectorLength, hogFeatureTrainingArray19, weight);
			//Train the System for the 10th Positive Array
			System.out.println("Training with 10th Positive Image Sample");
			weight=utilities.calculateOutputAdjustWeight("P", featureVectorLength, hogFeatureTrainingArray10, weight);
			//Train the System for the 10th Negative Array
			System.out.println("Training with 10th Negative Image Sample");
			weight=utilities.calculateOutputAdjustWeight("N", featureVectorLength, hogFeatureTrainingArray20, weight);
			
			if(errorCount==0)
			{
				nextLoopRequired=false;
			}
		}
		
		System.out.println("Number of Iterations:"+iterationNumber);
		return weight;
	}
	
	//Method to test if a particular image is of a human or not
	public static void testHumanOrNonHuman(String enableLogging,int featureVectorLength,double[] weight,String inputTestPosImage,String outputTestPosImage,String expectedResult,String flagNormalizedImageGen) throws IOException
	{
		//Declaring vector to store HOG Training Vector for  Test Images
		double[] hogFeatureTestArray=new double[featureVectorLength];


		hogFeatureTestArray=hogFeaturesCalculator.calculateHOGFeatureVector(enableLogging, inputTestPosImage, outputTestPosImage,flagNormalizedImageGen);
        
		if(inputTestPosImage.contains("crop001008b")||inputTestPosImage.contains("00000053a"))
		{
			System.out.println("HOG descriptor for "+inputTestPosImage);

			System.out.println("------------------------------------------------------------------------------");

			
			System.out.println("HOG Feature Values \n");


			for (int testarr = 0; testarr < 15*7*36; testarr++) {
				System.out.println(hogFeatureTestArray[testarr]);
			}
		}
		
		double output=0;

		for(int i=0;i<featureVectorLength;i++)
		{
			output=output+weight[i]*hogFeatureTestArray[i];
		}
		
		//To add the last value of weight for which the feature vector is added with a 1
		output=output+weight[featureVectorLength];

		if(output<0)
		{
			System.out.println("---------------------------------------------------------------------------------------------------------------------");
			System.out.println("Image stored in path "+inputTestPosImage+" is a non human image, expected "+expectedResult);
		}
		else
		{
			System.out.println("---------------------------------------------------------------------------------------------------------------------");
			System.out.println("Image stored in path "+inputTestPosImage+" is a human image, expected "+expectedResult);
		}
		
	}
	
		

}
