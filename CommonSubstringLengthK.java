/* 
44. given two strings, and an integer k
find whether there is common substring whose length >= k
例子： leetcode, codyabc, k = 3. 这两个string都有cod
*/
// Use a 2d array to record the length, and array[indexA][indexB] means that if 
// A.charAt(indexA) == B.charAt(indexB) the length of substring that ends with A.charAt(indexA)
// else it is 0
// So when we traverse the array, if A.charAt(indexA) == B.charAt(indexB)
// Then array[indexA][indexB] = array[indexA - 1][indexB - 1] + 1











/*
 * 找两个字符串是否有相同的substring长度大于k (boolean)
 * 1. Use dp method. Use a 2d array common[][]
 * 2. common[][] to record the length of the substring.
 * 3. Then traverse the array, if char in two arrays are same, dp[i][j] = dp[i - 1][j - 1] + 1.
 */

public class CommonSubstringLength {
	public boolean hasCommonThanK (String A, String B, int k) {
		if (k <= 1)
			return true;
		int[][] common = new int[A.length() + 1][B.length() + 1];
		for (int i = 1; i < A.length(); i++) {
			for (int j = 1; j <= B.length(); j++) {
				if (A.charAt(i - 1) == B.charAt(j - 1)) {
					common[i][j] = common[i - 1][j - 1] + 1;
				}
				if (common[i][j] >= k)
					return true;
			}
		}
		return false;
	}
}
