// 49. Binary Search Tree Iterator

/* Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree. */

public class BSTIterator {
    Stack<TreeNode> inorder = new Stack<>();
    public BSTIterator(TreeNode root) {
        toLeft(root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !inorder.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode node = inorder.pop();
        if (node.right != null) {
            toLeft(node.right);
        }
        return node.val;
    }
    
    private void toLeft(TreeNode root) {
        while (root != null) {
            inorder.push(root);
            root = root.left;
        }
    }
}
