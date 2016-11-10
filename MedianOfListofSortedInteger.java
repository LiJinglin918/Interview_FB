// 38. 题目是给定一个list of sorted integer arrays，要求找所有的数的median
/* e.g. [1,3,6,7,9], [2,4,8], [5], return 5
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=193898&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3090%5D%5Bvalue%5D%3D1%26searchoption%5B3090%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
*/
// Use a min Head which is priorityQueue to pop out the minimum number
// each time pop one, counter plus one util counter equals to the totalNumber / 2
// Time complexity: O(nlgK) -- lgk for sort in the priotiyqueue and pop out n /2 times, space complexity: O(k)












/*
 * 1. Use the priority queue to store the arrays' first value, index of the array, and the original index of the array.
 * 2. when count of poll() reaches the size / 2, find the median. If even, average the median and previous.
 * 3. for each poll(), find the next candidate by calling the input.get(min.arrayIndex)[min.index + 1], then put into heap 
 * 4. Time complexity: O(nlgK) -- log k for sort in the priority queue and pop out n /2 times, space complexity: O(k)
 */

import java.util.*;
public class MedianOfKSortedArrays {
	public double findMedian(List<double[]> input) {
		PriorityQueue<Number> minNumber = new PriorityQueue<>(input.size(), new Comparator<Number>() {
			public int compare(Number o1, Number o2) {
				if (o1.value > o2.value) {
					return 1;
				}
				return -1;
			}
		});
		
		// calculate the total size of the numbers. 
		int size = 0;
		for (int i = 0; i < input.size(); i++) {
			
			// the arrays are all sorted, so put the first in the heap
			minNumber.add(new Number(input.get(i)[0], i, 0));
			
			// size should be all the input numbers
			size += input.get(i).length;
		}
		
		// use two variables to store the current and previous result, return depends on whether the size is odd or even 
		double res = 0;
		double previous = 0;
		
		int count = 0;
		while (count <= size / 2) {
			Number min = minNumber.poll();
			count++;
			
			// store the previous, in case the size is even
			previous = res;
			res = min.value;
			
			// in order to avoid overflow, restrict the condition. 
			if (input.get(min.arrayIndex).length - 1 > min.OriginalArrayindex) {
				
				// Because when the poll() is calling, the current arrayIndex will minus 1. so the arrrayIndex remain the same. 
				minNumber.add(new Number(input.get(min.arrayIndex)[min.OriginalArrayindex + 1], min.arrayIndex, min.OriginalArrayindex + 1));
			}
		}
		if (size % 2 == 0)
			return (previous + res) / 2.0; 
		
		return res;
	}
	public class Number {
		double value;
		int arrayIndex;
		int OriginalArrayindex;
		public Number(double value, int arrayIndex, int index) {
			this.value = value;
			this.arrayIndex = arrayIndex;
			this.OriginalArrayindex = index;
		}
	}
}
