/*
82. WalkMatrix          打印斜着走的array[][] 
一个矩阵斜着走的list例子如下：
123
456
789
输出：{1}{42}{753}{86}{9} 
*/

class WalkMatrix {
    public void print(int[][] matrix) {
        int height = matrix.length;
        
        // 打印对角线上部分： 1， 42， 753
        for (int i = 0; i < matrix.length; i++) {
            helper(i, 0, matrix);
        }
        
        // 打印对角线下部分：  86， 9
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
