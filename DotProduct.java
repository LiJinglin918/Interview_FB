/*
   A={a1, a2, a3,...}
   B={b1, b2, b3,...}
   dot_product = a1 * b1 + a2 * b2 + a3 * b3 ＋。。。
   
   例子: A=[[1, a1], [300, a300], [5000, a5000]]
        B=[[100, b100], [300, b300], [1000, b1000]]
        返回 a300 * b300
        
   A={a1, ...., a300, ...., a5000}
   B={...., b100, ..., b300, ..., b1000, ...} 里面有很多0
   
   输入就是只把非0的数列出来了，A=[[1, a1], [300, a300], [5000, a5000]]的意思就是A中第1个数是a1,第300个是a300,第5000个是a5000，其他都是0.
   
   Follow up:
   如果length(B) >>> length(A)，即B非常长，怎么做能减少时间复杂度。
   对A里面的每个数，用binary search找B中相对应的值，这样时间复杂度是O(nlogm) (n = len(A), m =len(B))
*/

import java.util.*;

// Pair[0] = index, Pair[1] = value;

public class Solution {
	public static void main(String[] args) {
		int[][]A = {{1, 1}, {300, 2}, {500, 6}};
		int[][]B = {{1, 3}, {500, 3}, {900, 3}};
		System.out.println(new Solution().docProduct2(A, B));
	}
	
	// two pointers
	public int dotProduct(int[][] A, int[][] B) {
		int res = 0;
		int i = 0;
		int j = 0;
		while (i < A.length && j < B.length) {
			if (A[i][0] == B[j][0]) {
				res += A[i][1] * B[j][1];
				i++;
				j++;
			}
			else if (A[i][0] > B[j][0])
				i++;
			else
				j++;
		}
		return res;
	}
	
	
	// Binary Search
	public int docProduct2(int[][] A, int[][] B) {
		int res = 0;
		for (int[] pair : A) {
			int index = pair[0];
			int indexB = binarySearch(B, index);
			if (indexB != -1)
				res += pair[1] * B[indexB][1];
		}
		return res;
	}
	public int binarySearch(int[][] B, int index) {
		int low = 0;
		int high = B.length - 1;
		while (low + 1 < high) {
			int mid = (low + high) / 2;
			if (B[mid][1] >= index)
				high = mid;
			else
				low = mid;
		}
		if (B[low][0] == index)
			return low;
		else if (B[high][0] == index)
			return high;
		return -1;
	}
}


