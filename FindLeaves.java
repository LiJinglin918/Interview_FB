/*
Given a binary tree, collect a tree's nodes as if you were doing this: 
Collect and remove all leaves, repeat until the tree is empty.

Example:
Given binary tree

          1
         / \
        2   3
       / \     
      4   5    
Returns [4, 5, 3], [2], [1].

*/

import java.util.*;
public class FindLeaves {
	public List<List<Integer>> findLeaves(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		helper(res, root);
		return res;
	}
	public int helper(List<List<Integer>> list, TreeNode root) {
		if (root == null)
			return -1;
		int left = helper(list, root.left);
		int right = helper(list, root.right);
		int currDepth = Math.max(left, right) + 1;

		if (list.size() <= currDepth) {
			list.add(new ArrayList<Integer>());
		}
		list.get(currDepth).add(root.val);
		
		return currDepth;
	}
}
