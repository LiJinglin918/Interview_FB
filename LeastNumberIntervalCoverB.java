// 95. Least Number Interval Cover B
/*
For example:
Given A=[[0,3],[3,4],[4,6],[2,7]] B=[0,6] return 2 since we can use [0,3] [2,7] to cover the B
Given A=[[0,3],[4,7]] B=[0,6] return 0 since we cannot find any interval combination from A to cover the B
*/

// make sure every time we choose one interval we cover more time
// every time we chooes one, make the start time as its end time
// Always choose the interval that whose end time is the biggest
// and start time is smaller or equal to current start time
// util we got the end time bigger than input.end

// time complexity: O(nlgn)

/**
 * 1. sort the interval array, according to the start. If start is same, sort the array according to its end
 * 2. use a pointer "i" to traverse the interval array. 
 * 3. if "the intervals at the position i" (intervals[i]) 's end <= interval.start, i++
 * 4. If .................................................'s start > interval.start, means the rest of the interval array are not candidates. break
 * 5. Use a variable "end" to store the max of the intervals's end
 * 6. When a start != end, res++ . Update the start
 * 7. time complexity: O(nlgn)
 */

import java.util.*;

public class LeastIntervalCoverB {
	public int findCover(Interval[] intervals, Interval interval) {
		int res = 0;
		
		// sort the intervals array
		Arrays.sort(intervals, new Comparator<Interval>() {
			public int compare(Interval inter1, Interval inter2) {
				if (inter1.start == inter2.start)
					return inter1.end - inter2.end;
				return inter1.start - inter2.start;
			}
		});
		
		int start = interval.start;
		int end = -1;
		int i = 0;
		
		// traverse the intervals array
		while (i < intervals.length && end < interval.end) {
			if (intervals[i].end <= start) {
				i++;
				continue;
			}
			if (intervals[i].start > start) {
				break;
			}
			
			// use variable "end" to be the greatest end number in the interval array. (Update)
			while (i < intervals.length && end < interval.end && intervals[i].start <= start) {
				end = Math.max(intervals[i].end, end);
				i++;
			}
			
			// if start != end. res++. Update the start
			if (start != end) {
				res++;
				start = end;
			}
		}
		if (end < interval.end) {
			return 0;
		}
		return res;
	}
	public class Interval {
		int start;
		int end;
		Interval(int x, int y) {
			start = x;
			end = y;
		}
	}
}
