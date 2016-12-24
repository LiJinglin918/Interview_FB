/*
 * Common Element in K Sorted Arrays
 * 
 * Given 'n' sorted arrays, find elements which are common in all of these 'n' arrays. For example, 

{23, 34, 67, 89, 123, 566, 1000},
{11, 22, 23, 24,33, 37, 185, 566, 987, 1223, 1234},
{23, 43, 67, 98, 566, 678},
{1, 4, 5, 23, 34, 76, 87, 132, 566, 665},
{1, 2, 3, 23, 24, 344, 566}
for these given arrays, output should be 23 and 566 since these elements are present in all arrays.                         

{1, 3, 4, 4, 5, 43, 67, 98, 566, 678},
{1, 4, 4, 5, 23, 34, 76, 87, 132, 566, 665},
{1, 2, 4, 4, 5, 23, 24, 344, 566}
For above arrays, output should be 1,4,4,5,566,
 */

// http://www.ideserve.co.in/learn/find-common-elements-in-n-sorted-arrays


public class CommonElement_KSortedArray {
	public static void main(String[] args)
    {
        int[][] arrays = {
                           {23, 34, 67, 89, 123, 566, 1000},
                           {11, 22, 23, 24,33, 37, 185, 566, 987, 1223, 1234},
                           {23, 43, 67, 98, 566, 678},
                           {1, 4, 5, 23, 34, 76, 87, 132, 566, 665},
                           {1, 2, 3, 23, 24, 344, 566}
                         };
         
        new CommonElement_KSortedArray().printCommonElements(arrays);
    }
	public void printCommonElements(int[][] array) {
		if (array.length < 2)
			return;
		
		// the current index in 0th array
		int baseIndex = 0;
		
		// store the current index for each row
		int[] indices = new int[array.length - 1];
		
		// result, count
		int totalMatchFound;
		boolean smallestArrayTraversalComplete = false;
		
		// go through the 0th array, check whether found in other arrays
		while (baseIndex < array[0].length && !smallestArrayTraversalComplete) {
			totalMatchFound = 0;
			for (int i = 1; i < array.length; i++) {
				
				// get the index for this array where we last stopped
				int currIndex = indices[i - 1];
				
				// skip all the elements in this array which are less than the element at baseIndex in 0th array
				while (currIndex < array[i].length && array[i][currIndex] < array[0][baseIndex]) {
					currIndex++;
				}
				
				// check if common element(which is equal to element at baseIndex in 0th array) has been found in this array
				if (currIndex < array[i].length) {
					if (array[i][currIndex] == array[0][baseIndex]) {
						totalMatchFound++;
					}
				}
				
				// indicates that we have looked at all the elements of 'i'th array
				else {
					smallestArrayTraversalComplete = true;
				}
				
				// store this incremented index for this array- 
                // which would help to resume from this point for next matching round
				indices[i - 1] = currIndex;
			}
			
			// check if element arrays[0][baseIndex] is found in all other arrays
			if (totalMatchFound == array.length - 1) {
				System.out.println(array[0][baseIndex]);
			}
			baseIndex++;
		}
	}
}
