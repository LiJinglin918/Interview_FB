// 72. Find Pivot
/*
给定一个array 返回一个partition point可以返回该index左边的和和右边的和一样，没有就返回-1
往右扫一遍求所有的和,再往左扫,求当前扫过的和,二者相减得左边的和.
*/

class PartitionSum {
    public int findPivot(int[] input) {
        int sum = 0;
        for (int number : input) {
            sum += number;
        }
        int rightSum = 0;
        for (int i = input.length - 1; i >= 0; i--) {
            sum -= input[i];
            rightSum += input[i];
            if (rightSum == sum) {
                return i;
            }
        }
        return -1;
    }
}
