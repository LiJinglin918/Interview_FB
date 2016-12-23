/*
 * Given a matrix containing either 0 or 1 in each cell, 
 * find the square with the longest side containing all 1s in its boundary. 
 * Cells inside the square may contain either 0 or 1.

For example, given matrix

[
  [0, 1, 0, 1, 1]
  [0, 1, 1, 1, 0]
  [0, 1, 0, 1, 1]
  [1, 1, 1, 1, 1]
  [1, 1, 1, 1, 1]
]

The square with the maximum size containing all 1s in its boundary has top-left corner at (1,1) and bottom-right corner at (3, 3).

Note: You only need to return the length of each side of the square, In the above example, the length of each side of the square is 3.
 */

public class LargestSquare_All1Boundary {
	public int getLargetestSquare(int[][] matrix) {
		int res = 0;
		int m = matrix.length;
		int n = matrix[0].length;
	
		// left to right, top to bottom
		int[][] lToRLength = new int[m][n];
		int[][] tToBLength = new int[m][n];
		
		for (int i = 0; i < n; i++) {
			tToBLength[0][i] = matrix[0][i];
			if (matrix[0][i] == 1) {
				lToRLength[0][i] = (i > 0)? lToRLength[0][i - 1] + 1 : 1;
				res = 1;
			}
			else {
				lToRLength[0][i] = 0;
			}
		}
		
		if (n == 1)
			return res;
		
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if (matrix[i][j] != 1) {
					// break
					tToBLength[i][j] = 0;
					lToRLength[i][j] = 0;
				}
				else {
					tToBLength[i][j] = tToBLength[i - 1][j] + 1;
					lToRLength[i][j] = lToRLength[i][j - 1] + 1;
					
					
					// 
					int sideLength = 1;
					
					for (int maxSide = Math.min(tToBLength[i][j], lToRLength[i][j]); maxSide > 1; maxSide--) {
						if(tToBLength[i][j - maxSide + 1] >= maxSide && lToRLength[i - maxSide + 1][j] >= maxSide) {
							sideLength = maxSide;
							break;
						}
					}
					res = Math.max(res, sideLength);
				}
			}
		}
		return res;
	}
}
