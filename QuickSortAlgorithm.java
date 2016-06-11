import java.util.Random;
import java.io.*;
import java.util.Scanner;

public class QuickSortAlgorithm 
{
	static final int MAX_RANGE = 10000; //max range of numbers in array
	static int A[];
	static boolean uniform = false;
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args)
	{
		//Get user-set values.
		A = new int[PromptForArraySize()];
		if(A.length != 0)
		{
			uniform = PromptForUniformity();
			
			//Initialize and sort array. Print elapsed time.
			GenerateArrayValues();
			long startTime = System.nanoTime();
			QuickSort(0, A.length - 1);
			long endTime = System.nanoTime();
			System.out.println("QuickSort took " + String.valueOf(endTime - startTime) 
					+ " nanoseconds to complete");
			
			//Print output.
			System.out.println("See output.txt for results.");
			File outputFile = new File("output.txt");
		    try
		    {
		    	outputFile.createNewFile();
		    	FileWriter writer = new FileWriter(outputFile);
			    for(int value : A)
			    {
			      writer.write(String.valueOf(value) + " "); 
			    }
			      writer.flush();
			      writer.close();
		    }
		    catch(IOException e)
		    {
		    	System.out.println(e);
		    }
		}
		
	}
	
	//Prompt user for array size.
	static int PromptForArraySize()
	{
		int retVal = 0;
		
		//Prompt user.
		System.out.println("Please enter the size of the array: ");
		
		//Read in the value and convert it to an integer.
		try
		{
			retVal = Integer.parseInt(scan.nextLine());
			
			//Disallow nonpositive integers.
			if(retVal <= 0)
			{
				System.out.println("Array size must be greater than 0.");
				retVal = 0;
			}
		}
		catch(NumberFormatException e)
		{
			System.out.println("This is not a valid array size.");
		}
		
		return retVal;
	}
	
	//Prompt user to determine whether or not they want a uniform array.
	static boolean PromptForUniformity()
	{
		boolean retVal = false;
		
		//Prompt user.
		System.out.println("Do you want all the elements to have the same value?"
				+ "\nType \"true\" for Yes. Otherwise, press Enter.");
		
		//Read in the value and convert it to a boolean.
		try
		{
			retVal = Boolean.parseBoolean(scan.nextLine());
		}
		catch(NumberFormatException e)
		{
			retVal = false;
		}
		
		return retVal;
	}
	
	//Generate random values for the array.
	static void GenerateArrayValues()
	{
		Random randomGenerator = new Random();
		
		//If the user wants uniformity, generate one number and reuse it.
		//Else, generate a new number for each array position.
		if(uniform)
		{
			int value = randomGenerator.nextInt(MAX_RANGE);
			for (int i = 0; i < A.length; i++)
		    {
		    	A[i] = value;
		    }
		}
		else
		{
		    for (int i = 0; i < A.length; i++)
		    {
		    	A[i] = randomGenerator.nextInt(MAX_RANGE);
		    }
		}
	}
	
	static void QuickSort(int start, int end)
	{
		//The base case is when there is only one element.
		if(start < end)
		{
			//Recursively sort the subarrays on each side of the pivot.
			int pivot = Partition(start, end);
			QuickSort(start, pivot - 1);
			QuickSort(pivot + 1, end);
		}
	}
	
	//Partitions a subarray into greater-than and less-than portions and returns the pivot.
	static int Partition(int start, int end)
	{
		int pivotVal = A[end];
		int i = start - 1;
		
		//Create left and right partitions around the pivot point.
		for(int j = start; j < end; j++)
		{
			//For values < pivotVal, move them to the left partition.
			if(A[j] <= pivotVal)
			{
				i++;
				int temp = A[i];
				A[i] = A[j];
				A[j] = temp;
			}
		}
		
		//Place the pivot point in the middle and the first value greater than the pivot point
		//at the end of the array.
		int temp2 = A[i + 1];
		A[i + 1] = A[end];
		A[end] = temp2;
		
		return i + 1;
	}

}
