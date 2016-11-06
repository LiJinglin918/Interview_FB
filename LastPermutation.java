// 81. lastPermutation
/* 就是lc next permutation的反着来的previous permutation，做法一样 */
// The idea is that find the last two adjacent number that the first one is beigger than the second one
// Then the question come to that find the previous permutation of the nums[first-end]
// Then sequence after second must be acending, so the previous permutation must comes from 
// the number that is samller than the nums[first] at the position first with a decending sequence after it
// Exp: 5, 4, 1, 2, 3 previous -> 5, 3, 4, 2, 1 
// the num[first] = 4, nums[second] = 1, nums[smaller] = 3 

class PreviousPermutation {
    public int[] previous(int[] input) {
        if (input.length <= 1) {
            return input;
        }
        int first = 0;
        int second = 0;
        for (int i = input.length - 1; i >= 1; i--) {
            if (input[i] < input[i - 1]) {
                first = i - 1;
                second = i;
                break;
            }
        }
        int smaller = 0;
        for (int i = input.length - 1; i >= second; i--) {
            if (input[i] < input[first]) {
                smaller = i;
                break;
            }
        }
        swap(input, first, smaller);
        reverse(input, second, input.length - 1);
        return input;
    }
    private void swap(int[] input, int index1, int index2) {
        int temp = input[index1];
        input[index1] = input[index2];
        input[index2] = temp;
    }
    private void reverse(int[] input, int start, int end) {
        while (start <= end) {
            swap(input, start++, end--);
        }
    }
}
