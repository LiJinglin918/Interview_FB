// 74. check if a binary tree is mirrored

//Recursive way
private boolean helper(TreeNode left, TreeNode right) {
    if ((left == null && right != null) || (left != null && right == null)) {
        return false;
    }
    if (left == null && right == null) {
        return true;
    }
    if (left.val != right.val) {
        return false;
    }
    boolean subResult = helper(left.right, right.left) && helper(left.left, right.right);
    return subResult;
}

//iterative way
public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        Stack<TreeNode> left = new Stack<>();
        Stack<TreeNode> right = new Stack<>();
        left.push(root.left);
        right.push(root.right);
        while(!left.isEmpty() && !right.isEmpty()) {
            TreeNode leftTemp = left.pop();
            TreeNode rightTemp = right.pop();
            if (leftTemp == null ^ rightTemp == null 
            || (leftTemp != null && rightTemp != null && leftTemp.val != rightTemp.val)) {
                return false;
            }
            if (leftTemp == null && rightTemp == null) {
                continue;
            }
            left.push(leftTemp.left);
            right.push(rightTemp.right);
            left.push(leftTemp.right);
            right.push(rightTemp.left);
        }
        return true;
    }
