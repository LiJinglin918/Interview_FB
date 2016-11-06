// 47. BST to Doubly LinkedList
// Time complexity O(nlgn) 
public static TreeNode Tree2LinkedList(TreeNode root) {
	if (root == null) {
		return root;
	}
	Connect(root);
	TreeNode head = root;
	TreeNode tail = root;
	while (head.left != null) {
		head = head.left;
	}
	while (tail.right != null) {
		tail = tail.right;
	}
	head.left = tail;
	tail.right = head;
	return head;
}
private static void Connect(TreeNode root) {
	if (root == null) {
		return;
	}
	if (root.left != null) {
		TreeNode left = root.left;
		Connect(left);
		while (left.right != null) {
			left = left.right;
		}
		left.right = root;
		root.left = left;
	}
	if (root.right != null) {
		TreeNode right =root.right;
		Connect(right);
		while (right.left != null) {
			right = right.left;
		}
		right.left = root;
		root.right = right;
	}
}
