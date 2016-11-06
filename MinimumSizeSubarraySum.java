// 先是只是正整数，后面扩展到负数。
/*
Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return 0 instead.
For example, given the array [2,3,1,2,4,3] and s = 7,
the subarray [4,3] has the minimal length under the problem constraint.
*/
// to sum to the index end, minus the number of index start.

public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int res = Integer.MAX_VALUE;
        int start = 0, end = 0, sum = 0;
        while (end < nums.length) {
            sum = sum + nums[end];
            while (sum >= s) {
                res = Math.min(res, end - start + 1);
                sum = sum - nums[start];
                start++;
            }
            end++;
        }
        return res == Integer.MAX_VALUE? 0 : res;
    }
}

// minimum size of subarray //only positive number
public int minSubArrayLen(int s, int[] nums) {
    int minLen = Integer.MAX_VALUE;
    int fast = 0;
    int slow = 0;
    int sum = 0;
    while (fast < nums.length) {
        sum += nums[fast];
        while (sum >= s) {
            minLen = Math.min(minLen, fast - slow + 1);
            sum -= nums[slow];
            slow++;
        }
        fast++;
    }
    return minLen == Integer.MAX_VALUE ? 0 : minLen;
}

extends to 2d and find if rectangle sum = k
class FindSubRect {
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


// Maximum size of subarray
// [1, -1, 5, -2, 3] => sum [1, 0, 5, 3, 6]
// Note k = Sum1 - Sum2, which is a subarray
// use hashmap record the sum to index, every time we meet a sum, if sum - k appears in the hashmap
// then there is a subarray sum equals to k. find the length
// if this sum is already in the hashmap, skip it. otherwise put in
// because we always use the index most left, so if a some sum comes later, we dont put it in hashmap
public int maxSubArrayLen(int[] nums, int k) {
    int maxLen = 0;
    HashMap<Integer, Integer> sumToIndex = new HashMap<>();
    sumToIndex.put(0, -1);
    int sum = 0;
    for (int i = 0; i < nums.length; i++) {
        sum += nums[i];
        if (sumToIndex.containsKey(sum - k)) {
            maxLen = Math.max(maxLen, i - sumToIndex.get(sum - k));
        }
        if (!sumToIndex.containsKey(sum)) {
            sumToIndex.put(sum, i);
        }
    }
    return maxLen;
}
