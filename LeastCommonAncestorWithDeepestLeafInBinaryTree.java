// 第一个方法，是最深的node的ancestor。第二个方法是给定两个node,求他们的ancestor。

// 1. Binary Search Tree Least Common Ancestor with deepest leaf. 二叉树求最深节点的ancestor

// recursive:	Time: O(n)	Space: O(h)
public class Solution {
	public TreeNode findLCARecursive(TreeNode root) {
		Pair res = findLCA(root);
		return res.node;
	}
	
	public Pair findLCA(TreeNode root) {
		if (root == null)
			return new Pair(null, 0);
		int depth = 0;
		Pair left = findLCA(root.left);
		Pair right = findLCA(root.right);
		depth = Math.max(left.depth, right.depth) + 1;
		if (left.depth == right.depth)
			return new Pair(root, depth);
		else if (left.depth > right.depth)
			return new Pair(left.node, depth);
		else
			return new Pair(right.node, depth);
	}
	
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) {
			val = x;
		}
	}
	
	public class Pair {
		public TreeNode node;
		public int depth;
		public Pair(TreeNode n, int d) {
			node = n;
			depth = d;
		}
	}
}

// Iterative: 	Time O(n)	Space: O(n)
import java.util.*;
public class Solution {
	public TreeNode findLCAIterative(TreeNode root) {
		if (root == null)
			return null;
		HashMap<TreeNode, TreeNode> childToParent = new HashMap<>();
		Queue<TreeNode> level = new LinkedList<>();
		TreeNode left = null;
		TreeNode right = null;
		level.add(root);
		while (!level.isEmpty()) {
			int size = level.size();
			for (int i = 0; i < size; i++) {
				TreeNode node = level.poll();
				if (i == 0)
					left = node;
				if (i == size - 1)
					right = node;
				if (node.left != null) {
					level.add(node.left);
					childToParent.put(node.left, node);
				}
				if (node.right != null) {
					level.add(node.right);
					childToParent.put(node.right, node);
				}
			}
		}
		while (left != right) {
			left = childToParent.get(left);
			right = childToParent.get(right);
		}
		return left;
	}
	
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) {
			val = x;
		}
	}
}


// multi-children
import java.util.*;

public class Solution {
	public TreeNodeMulti findLCA(TreeNodeMulti root) {
		return helper(root).node;
	}
	public Result helper(TreeNodeMulti root) {
		if (root == null)
			return new Result(null, 0);
		int depth = 0;
		List<Result> next = new ArrayList<>();
		for (TreeNodeMulti child : root.children) {
			next.add(helper(child));
		}
		Result deepest = new Result(null, 0);
		Result deepest2 = new Result(null, 0);
		for (Result result : next) {
			if (result.depth >= deepest.depth) {
				deepest2.node = deepest.node;
				deepest2.depth = deepest.depth;
				deepest.node = result.node;
				deepest.depth = result.depth;
			}
		}
		depth = 1 + deepest.depth;
		if (deepest.depth == deepest2.depth)
			return new Result(root, depth);
		return new Result(deepest.node, depth);
		
	}
	public class Result {
		TreeNodeMulti node;
		int depth;
		public Result(TreeNodeMulti n, int d) {
			node = n;
			depth = d;
		}
	}
	public class TreeNodeMulti {
		int val;
		List<TreeNodeMulti> children;
		TreeNodeMulti(int x) {
			val = x;
			children = new ArrayList<>();
		}
	}
}

// LCA in BST
/*
Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”
        _______6______
       /              \
    ___2__          ___8__
   /      \        /      \
   0      _4       7       9
         /  \
         3   5
For example, the lowest common ancestor (LCA) of nodes 2 and 8 is 6. Another example is LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null;
        if (p.val <= root.val && q.val >= root.val || (p.val >= root.val && q.val <= root.val))
            return root;
        if (p.val <= root.val && q.val <= root.val)
            return lowestCommonAncestor(root.left, p, q);
        if (p.val >= root.val && q.val >= root.val)
            return lowestCommonAncestor(root.right, p, q);
        return null;
    }
}
