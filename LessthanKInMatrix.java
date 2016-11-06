// 94. Less than K in matrix
/*
矩阵中大小为m的正方形覆盖范围最大值不超过k的值。记得刷过相似的，动态规划，每个cell记一下左上方的所有和，简单解决。
*/
class MaxSumOfSquareNoLargerThanK {
    public int sum(int[][] matrix, int m, int k) {
        if (m > matrix.length || m > matrix[0].length) {
            return 0;
        }
        int maxSum = 0;
        int[][] sum = new int[matrix.length][matrix[0].length];
        for (int col = 0; col < matrix[0].length; col++) {
            int rectSum = 0;
            for (int row = 0; row < m; row++) {
                rectSum += matrix[row][col];
            }
            sum[0][col] = rectSum;
            for (int row = 1; row < matrix.length - m + 1; row++) {
                rectSum += matrix[row + m - 1][col] - matrix[row - 1][col];
                sum[row][col] = rectSum;
            }
        }
        for (int row = 0; row < matrix.length - m + 1; row++) {
            int squareSum = 0;
            for (int col = 0; col < m; col++) {
                squareSum += sum[row][col];
            }
            if (squareSum < k) {
                maxSum = Math.max(squareSum, maxSum);
            }
            for (int col = 1; col < matrix[0].length - m + 1; col++) {
                squareSum += sum[row][col + m - 1] - sum[row][col - 1];
                if (squareSum < k) {
                    maxSum = Math.max(squareSum, maxSum);
                }
            }
        }
        return maxSum;
    }
}


// 貌似是两种方法；方法之二：
// every cell store the sum of the sub-matrix from 0,0 to this cell.
// then we can get the sum of square on O(1)
public int findSum(int[][] matrix, int m, int k) {
    if (m > matrix.length || m > matrix[0].length) {
        return 0;
    }
    int maxSum = 0;
    int[][] sum = new int[matrix.length][matrix[0].length];
    buildSum(sum, matrix);
    for (int row = m - 1; row < matrix.length; row++) {
        for (int col = m - 1; col < matrix[0].length; col++) {
            int curSum = getSum(sum, row - m + 1, col - m + 1, row, col);
            if (curSum < k) {
                maxSum = Math.max(maxSum, curSum);
            }
        }
    }
    return maxSum;
}

public boolean findSquareEqualsK(int[][] matrix, int k) {
    int[][] sum = new int[matrix.length][matrix[0].length];
    buildSum(sum , matrix);
    for (int row = 0; row < matrix.length; row++) {
        for (int col = 0; col < matrix[0].length; col++) {
            int maxLen = Math.min(row, col);
            if (search(maxLen, sum, row, col, k)) {
                return true;
            }
        }
    }
    return false;
}

private boolean search(int maxLen, int[][] sum, int row, int col, int k) {
    int start = 0;
    int end = maxLen;
    while (start + 1 < end) {
        int mid = start + (end - start) / 2;
        int curSum = getSum(sum, row - mid, col - mid, row, col);
        if (curSum >= k) {
            end = mid;
        }
        else {
            start = mid;
        }
    }
    if (getSum(sum, row - end, col - end, row, col) == k ||
            getSum(sum, row - start, col - end, row, col) == k) {
        return true;
    }
    return false;
}

private int getSum(int[][] sum, int row1, int col1, int row2, int col2) {
    if (row1 == 0 && col1 == 0) {
        return sum[row2][col2];
    }
    else if (row1 == 0) {
        return sum[row2][col2] - sum[row2][col1 - 1];
    }
    else if (col1 == 0) {
        return sum[row2][col2] - sum[row1 - 1][col2];
    }
    else {
        return sum[row2][col2] - sum[row1 - 1][col2] - sum[row2][col1 - 1] + sum[row1 - 1][col1 - 1];
    }
}   
