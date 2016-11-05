/*
Given a binary tree, find its minimum depth.
The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
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
    public int minDepth(TreeNode root) {
        if (root == null)
            return 0;
        if (root.left == null)
            return minDepth(root.right) + 1;
        if (root.right == null)
            return minDepth(root.left) + 1;
        else 
            return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }
}


// iterative method
private int findMinDepth(TreeNode root) {
	if (root == null) {
        return 0;
    }
    Queue<TreeNode> nodes = new LinkedList<>();
    int level = 0;
    int result = 0;
    nodes.offer(root);
    while (result == 0) {
        level++;
        int size = nodes.size();
        for (int i = 0; i < size; i++) {
            TreeNode node = nodes.poll();
            if (node.left == null && node.right == null) {
                result = level;
            }
            if (node.left != null) {
                nodes.offer(node.left);
            }
            if (node.right != null) {
                nodes.offer(node.right);
            }
        }
    }
    return result;
}
