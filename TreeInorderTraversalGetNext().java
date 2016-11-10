// 第一个是创建getNext()。第二个方法是LC原题。
/* 
27. Binary Tree Inorder Traversal
class Node {Node left, Node right, Node parent}

Node getNext (Node current) {
	
} 
*/

// Inorder --visite left -> root -> right
// First check right child, if it's not null, then the most left child of this right child is the answer
// Else check parent, if it is null, return null --because this is the root and it is the last node in inorder
// if not, if node.parent.left = node (node is the left child of its parent), return parent
// if node.parent.right = node(node is the right child of its parent), go up and 
// search ancester if any ancester is the left child of its parent, return parent
// else return null
// Time complexity:O(1) in average, worst O(h), space complexity: O(1)' (BT Inorder parent)

class BTInroder {
    public TreeNodeWithParent findNext(TreeNodeWithParent node) {
        if (node == null) {
            return null;
        }
        if (node.right != null) {
            TreeNodeWithParent result = node.right;
            while (result.left != null) {
                result = result.left;
            }
            return result;
        }
        else if (node.parent == null) {
            return null;
        }
        else if (node.parent.left == node) {
            return node.parent;
        }
        else {
            TreeNodeWithParent result = node.parent;
            while (result.parent != null && result.parent.right == result) {
                result = result.parent;
            }
            return result.parent;
        }
    }
}

class TreeNodeWithParent {
    public TreeNodeWithParent parent;
    public TreeNodeWithParent left;
    public TreeNodeWithParent right;
    public int val;
    public TreeNodeWithParent(int val, TreeNodeWithParent parent) {
        this.val = val;
        this.parent = parent;
        this.left = null;
        this.right = null;
    }

}


/*======================================================================================================================*/


/*
Given a binary tree, return the inorder traversal of its nodes' values.
For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,3,2].
Note: Recursive solution is trivial, could you do it iteratively?
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
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;                                                 // Note if return null, the result is "null" but not [].
        helper(root, res);
        return res;
    }
    
    public void helper(TreeNode root, List<Integer> res) {              // Note the usage of List<Integer> which can add in the function
        if (root != null) {
            helper(root.left, res);
            res.add(root.val);
            helper(root.right, res);
        }
    }
    
    // the second method, use the stack.
    public List<Integer> inorderTraversal2(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		if (root == null)
			return res;
		Stack<TreeNode> stack = new Stack<>();
		TreeNode node = root;
		while (!stack.isEmpty() || node != null) {
			while (node != null) {
				stack.push(node);
				node = node.left;
			}
			node = stack.pop();
			res.add(node.val);
			node = node.right;
		}
		return res;
	}
}
