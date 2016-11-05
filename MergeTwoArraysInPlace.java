// MERGE two arrays in-place a = [2,4,6,8, _, _, _, _]
// b = [1,3,5,7]
/*
Note：_代表空的位置，用以merge。
a的actual length与b一样，并且a的空余位置足够放入b。a, b都是sorted array，要求in-place merge两个array。
从两个数列的尾部（这个例子中对于a是从8开始）比较，哪个更大就放在a的末端。
需要注意到当a被遍历完后，要将b剩下的数字照搬到a的前端。
*/


public class Solution {
	public void merge(int[] nums1, int m, int[] nums2, int n) {
		int len = m + n - 1;
		int i = m - 1;
		int j = n - 1;
		while (i >= 0 && j >= 0) {
			if (nums1[i] >= nums2[j]) {
				nums1[len] = nums1[i];
				i--;
			}
			else {
				nums1[len] = nums2[j];
				j--;
			}
			len--;
		}
		while (j >= 0) {
			nums1[len--] = nums2[j--];
		}
	}
}
