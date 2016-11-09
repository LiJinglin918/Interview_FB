// 54. 给一个partial sorted的数组比如1，3，5，2，4，6，8，10，20，30，11，12，13，数组有N个数，分为M个segment，N>>>M，要求输出排序后的数组。第一反应就是merge k sorted list，按照那个写了下，问了问time 和space 的complexity
//merge k list(use M pointer to record the position of the begin of the sorted segment)
//http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=192644&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3090%5D%5Bvalue%5D%3D1%26searchoption%5B3090%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

// Use a class Number to record the cur number and index and end index of a segment
// For instance, we have {1, 3, 9, 2, 6, 8}
// Then we have a Number(value = 1, index = 0, endIndex = 2) and Number(value = 2, index = 3, endIndex = 5)
// Traverse through the array find the start and end of every segment
// store the start number of every segment into a min heap.
// So every time we use heap, which pop out the samllest number, and we check its index
// if the index is samller than the endIndex, which means this segment is not over, we 
// need to put the next number of the segment into heap.
/* Time complexity: O(nlgm) -n is the number of numbers in array -m is the number of segment
 Space complexity: O(n) */










/*
 * partial sorted array. like 1,3,4,2,4,5,6,11,66,33,5. Merge them
 * 1. use a PriorityQueue to store the Number
 * 2. The number class contains the first value of the sorted segment, the start, and the end of the sorted segment
 * (for example, 1,3,9,2,6,8, we have Number(value = 1, index = 0, endIndex = 2) and Number (value = 2, index = 3, endIndex = 5)) 
 * 3. traverse the array, if the next number is smaller than the current one, put into the priority queue
 * 4. traverse the priority queue again, put the next number of the segment into the heap.
 * 5. Time complexity: O(n log m) -n is the number of numbers in array -m is the number of segment. Space complexity: O(n)
 */

import java.util.*;
public class PartialSortedArraySort {
	public int[] sortArray(int[] input) {
		if (input.length == 0)
			return new int[]{};
		int[] res = new int[input.length];
		
		// use two pointer to traverse the input
		int mover = 0;
		int start = 0;
		
		// according to the value of Number, put into the PriorityQueue
		PriorityQueue<Number> minNumber = new PriorityQueue<>(new Comparator<Number>() {
			public int compare(Number o1, Number o2) {
				return o1.value - o2.value;
			}
		});
		
		// Traverse the input, if the next number is smaller, the new segment is start, put into the heap 
		for (int i = 0; i < input.length; i++) {
			if (i > 0 && input[i] < input[i - 1]) {
				minNumber.add(new Number(input[start], start, i - 1));
				start = i;
			}
		}
		
		// add the last one, because there is no element smaller than some integer
		minNumber.add(new Number(input[start], start, input.length - 1));
		
		// after put input into the heap, store the heap into the result
		while (!minNumber.isEmpty()) {
			Number min = minNumber.poll();
			res[mover++] = min.value;
			
			// after poll the smallest value, put its next Number in the sagment into the heap
			if (min.index < min.endIndex) {
				minNumber.add(new Number(input[min.index + 1], min.index + 1, min.endIndex));
			}
		}
		return res;
	}
	public class Number {
		int value;
		int index;
		int endIndex;
		public Number(int value, int index, int endIndex) {
			this.value = value;
			this.index = index;
			this.endIndex = endIndex;
		}
	}
}
