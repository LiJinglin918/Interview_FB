// 16. check consecutive subarray duplicate numbers 给一个array, 然后给一个k, 让你check 连续的k个integer是否含有dulplicate, 很简单的，用窗口为K的hashset一直扫一遍就行了，很简单
// check duplicate numbers in window k

public class Solution {
	public boolean containsNearbyDuplicate(int[] nums, int k) {
		Set<Integer> window = new HashSet<>();
		int low = 0;
		int high = 0;
		while (high < nums.length) {
			if (window.size() >= k) {
				window.remove(nums[low]);
				low++;
			}
			if (window.contains(nums[high]))
				return true;
			else {
				window.add(nums[high]);
				high++;
			}
		}
		return false;
	}
}
