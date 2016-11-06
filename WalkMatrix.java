// 
82. WalkMatrix
/* 一个矩阵斜着走的list例子如下：
123
456
789
输出：{1}{42}{753}{86}{9} 
*/

class WalkMatrix {
    public void print(int[][] matrix) {
        int height = matrix.length;
        for (int i = 0; i < matrix.length; i++) {
            helper(i, 0, matrix);
        }
        for (int i = 1; i < matrix[0].length; i++) {
            helper(height - 1, i, matrix);
        }
    }

    private void helper(int row, int col, int[][] matrix) {
        while (row >= 0 && col < matrix[0].length) {
            System.out.printf("%d ", matrix[row--][col++]);
        }
        System.out.println();
    }
}
