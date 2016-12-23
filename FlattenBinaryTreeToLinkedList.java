// 2. Flatten Binary Tree to Linked List
/*
Given a binary tree, flatten it to a linked list in-place.

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6
The flattened tree should look like:
   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6
*/

public class Solution {
    public void flatten(TreeNode root) {
        if (root == null)
            return;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            if (node.right != null) 
                stack.push(node.right);
            if (node.left != null) {
                node.right = node.left;
                node.left = null;
            }
            
            
            else if (!stack.isEmpty()) {
                node.right = stack.pop();
            }
            node = node.right;
        }
    }
}
