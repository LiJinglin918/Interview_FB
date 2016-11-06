// 91. find if a tree is subtree of another
// 'Time complexity: O(nk) -k nodes in subtree'

class FindSubTree {
    public boolean isValid(TreeNode root, TreeNode node) {
        if (root == null && node == null) {
            return true;
        }
        boolean result = false;
        if (root != null && node != null) {
            if (root.val == node.val) {
                result = helper(root, node);
            }
            if (!result) {
                result = isValid(root.right, node);
            }
            if (!result) {
                result = isValid(root.left, node);
            }
        }
        return result;
    }

    private boolean helper(TreeNode root, TreeNode node) {
        if (root == null && node == null) {
            return true;
        }
        if ((root == null && node != null) || (root != null && node == null)) {
            return false;
        }
        if (root.val == node.val) {
            return helper(root.right, node.right) && helper(root.left, node.left);
        }
        return false;
    }
}
