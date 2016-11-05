// 12. 在一个nonnegative array里找continuous sequence 加起来等于特定值的一道题 Equals k
public boolean minSubArrayLen(int s, int[] nums) {
    int fast = 0;
    int slow = 0;
    int sum = 0;
    while (fast < nums.length) {
        sum += nums[fast];
        while (sum > s) {
            sum -= nums[slow];
            slow++;
        }
        if (sum == s) {
            return true;
        }
        fast++;
    }
    return false;
}
