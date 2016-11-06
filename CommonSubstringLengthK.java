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

class hasCommonSubstring {
    public boolean hasCommonThanK(String A, String B, int k) {
        if (k <= 1) {
            return true;
        }
        int[][] common = new int[A.length() + 1][B.length() + 1];
        for (int indexA = 1; indexA <= A.length(); indexA++) {
            for (int indexB = 1; indexB <= B.length(); indexB++) {
                if (A.charAt(indexA - 1) == B.charAt(indexB - 1)) {
                    common[indexA][indexB] = common[indexA - 1][indexB - 1] + 1;
                }
                if (common[indexA][indexB] >= k) {
                    return true;
                }
            }
        }
        return false;
    }
}
