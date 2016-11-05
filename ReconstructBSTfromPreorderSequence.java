public class Solution {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) {
			val = x;
		}
	}
	public TreeNode constructFromPreorder(int[] preorder) {
		if (preorder.length == 0)
			return null;
		return helper(preorder, 0, preorder.length - 1);
	}
	public TreeNode helper(int[] preorder, int start, int end) {
		TreeNode root = new TreeNode(preorder[start]);
		if (start == end)
			return root;
		start++;
		int rightIndex = start;
		while (rightIndex <= end && preorder[rightIndex] < root.val)          // 找到右子树的部分（因为是preorder）
			rightIndex++;
		if (rightIndex > start)
			root.left = helper(preorder, start, rightIndex - 1);
		if (rightIndex <= end)
			root.right = helper(preorder, rightIndex, end);
		return root;
	}
}
