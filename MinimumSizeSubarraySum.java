// 先是只是正整数，后面扩展到负数。
/*
Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return 0 instead.
For example, given the array [2,3,1,2,4,3] and s = 7,
the subarray [4,3] has the minimal length under the problem constraint.
*/
// to sum to the index end, minus the number of index start.






/*
 * use two pointers, start and end to traverse the array
 * (end - start is the length of the candidate string)
 * When traverse the array, store the sum, update the minimum length
 * If the length has been calculated, increase start, update sum
 */

import java.util.*;
public class MinimumSizeOfSubarray {
	public int minSubArrayLen(int s, int[] nums) {
		if (nums == null || nums.length == 0)
			return 0;
		int res = Integer.MAX_VALUE;
		int start = 0;
		int end = 0;
		int sum = 0;
		while (end < nums.length) {
			sum += sum + nums[end];
			while (sum >= s) {
				res = Math.min(res,  end - start + 1);
				sum = sum - nums[start];
				start++;
			}
			end++;
		}
		return res == Integer.MAX_VALUE? 0 : res;
	}
}




/*=====================================================================================================================*/







/*
	 * in 2d array, find the sum == k	题目理解不清楚。
	 * 1. use an array to calculate the sum of each row (int[] rowSum)
	 * 2. use two pointers, right to point from left to right
	 * left points from right to left
	 * 3. for each col, calculate the sum (rowSum[row])
	 * 4. if the cuSum - target exists in the hashset, return true
	 * 
	 */
	
	public class FindSubRect {
		public boolean find(int[][] matrix, int target) {
			for (int right = 0; right < matrix[0].length; right++) {
				int[] rowSum = new int[matrix.length];
				for (int left = right; left >= 0; left--) {
					Set<Integer> sum = new HashSet<>();
					int curSum = 0;
					sum.add(0);
					for (int row = 0; row < matrix.length; row++) {
						rowSum[row] += matrix[row][left];
						curSum += rowSum[row];
						if (sum.contains(curSum - target)) {
							return true;
						}
						sum.add(curSum);
					}
				}
			}
			return false;
		}
	}



/*=====================================================================================================================*/



// Maximum size of subarray
// [1, -1, 5, -2, 3] => sum [1, 0, 5, 3, 6]
// Note k = Sum1 - Sum2, which is a subarray
// use hashmap record the sum to index, every time we meet a sum, if sum - k appears in the hashmap
// then there is a subarray sum equals to k. find the length
// if this sum is already in the hashmap, skip it. otherwise put in
// because we always use the index most left, so if a some sum comes later, we dont put it in hashmap










/*
	 * [1,-1,5,-2,3] => [1,0,5,3,6].
	 * 1. Use a hashmap to record the sum to index. 
	 * 2. everytime, we meet a sum, if sum - k appears in the hashmap, 
	 * then there is a subarray sum equals to k, find the length
	 */
	public int maxSubArrayLne (int[] nums, int k) {
		int res = 0;
		HashMap<Integer, Integer> sumToIndex = new HashMap<>();
		sumToIndex.put(0,  -1);
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			if (sumToIndex.containsKey(sum - k)) {
				res = Math.max(res, i - sumToIndex.get(sum - k));
			}
			if (!sumToIndex.containsKey(sum)) {
				sumToIndex.put(sum, i);
			}
		}
		return res;
	}
