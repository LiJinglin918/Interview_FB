/*
Serialization is the process of converting a data structure or object into a sequence of bits 
so that it can be stored in a file or memory buffer, 
or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. 
There is no restriction on how your serialization/deserialization algorithm should work. 
You just need to ensure that a binary tree can be serialized to a string 
and this string can be deserialized to the original tree structure.

For example, you may serialize the following tree
    1
   / \
  2   3
     / \
    4   5
as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. 
You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
Note: Do not use class member/global/static variables to store states. 
Your serialize and deserialize algorithms should be stateless.
*/
// 此题的变形： Serialize and deserialized Tree into LinkedList


class CodeTree {
    public ListNodeStr serialized(TreeNode root) {
        ListNodeStr head = helper(root).head;
        return head;
    }

    private Pair helper(TreeNode root) {
        if (root == null) {
            ListNodeStr node = new ListNodeStr("#");
            return new Pair(node, node);
        }
        ListNodeStr head = new ListNodeStr(Integer.toString(root.val));
        ListNodeStr tail = head;

        Pair left = helper(root.left);
        tail.next = left.head;
        tail = left.tail;

        Pair right = helper(root.right);
        tail.next = right.head;
        tail = right.tail;

        return new Pair(head, tail);
    }

    private ListNodeStr mover;
    public TreeNode deserialized(ListNodeStr head) {
        ListNodeStr fakeHead = new ListNodeStr("");
        fakeHead.next = head;
        mover = fakeHead;
        return helper();
    }
    private TreeNode helper() {
        mover = mover.next;
        if (mover.val.equals("#")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(mover.val));
        root.left = helper();
        root.right = helper();
        return root;
    }

    class Pair {
        ListNodeStr head;
        ListNodeStr tail;
        public Pair(ListNodeStr h, ListNodeStr t) {
            this.head = h;
            this.tail = t;
        }
    }

}
class ListNodeStr {
    public String val;
    public ListNodeStr next;
    public ListNodeStr(String val) {
        this.val = val;
    }
}
