// 91. find if a tree is subtree of another
// 'Time complexity: O(nk) -k nodes in subtree'





/**
 * 题意是check第二个tree是不是第一个tree的subtree
 * 1. recursive
 * 2. note the two identical tree are also true
 * 3. node the null tree are also true.
 * 4. Time complexity: O(nk) -k nodes in subtree
 *
 */
public class SubtreeCheck {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode (int x) {
			val = x;
		}
	}
	public boolean subtree(TreeNode root, TreeNode node) {
		if (root == null && node == null)
			return true;
		boolean res = false;
		if (root != null && node != null) {
			if (root.val == node.val) {				// check if they are identical. 
				res = helper(root, node);
			}
			if (!res) {
				res = subtree(root.right, node);
			}
			if (!res) {
				res = subtree(root.left, node);
			}
		}
		return res;
	}
	
	// helper method is to check whether these two tree are identical. 
	public boolean helper(TreeNode root, TreeNode node) {
		if (root == null && node == null) {
			return true;
		}
		if ((root == null && node != null) || (root != null && node == null))
			return false;
		if (root.val == node.val) {
			return helper(root.right, node.right) && helper(root.left, node.left);
		}
		return false;
	}
}
