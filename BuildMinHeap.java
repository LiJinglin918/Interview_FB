/*
89. BuildMinHeap
input是一个array，要求生成一个树，有以下三个属性
1）二叉树
2）Min Heap，表示node的每个子节点的值都大于或者等于这个node的值
3）做inorder traversal的时候要保持array的顺序

举个例子input是 5, 2, 10, 7
       2
      / \
    5   7
        /
        10

follow up 是一个addNode的函数，输入是root node，还有个int value，保持原有的属性，返回root
// Find the smallest value in the array and make it as root
// left part as left branch, right part as right brandh
'Time complexity: O(nlgn), Space complexity: O(lgn)'
*/








/**
 * 1. find the smallest value in the array, make it as root
 * 2. left part is left branch. right part is right branch.
 * 3. Time complexity: O(n log n), Space complexity: O(log n)
 */
public class BuildMinHeap {
	public TreeNode build(int[] input) {
		return helper(input, 0, input.length - 1);
	}
	public TreeNode helper(int[] input, int start, int end) {
		if (start > end)
			return null;
		int index = -1;
		int val = Integer.MAX_VALUE;
		
		// find the smallest value in the array, make it as root
		for (int i = start; i <= end; i++) {
			if (input[i] <= val) {
				index = i;
				val = input[i];
			}
		}
		TreeNode root = new TreeNode(val);
		
		// left part is left branch, right part is right branch
		root.left = helper(input, start, index - 1);
		root.right = helper(input, index + 1, end);
		return root;
		
	}
	
	/*
	 * follow up. 输入是root,和一个int。 保持原有的属性。
	 * If the input is smaller than root.value, 
	 * this input should be current's parent. 
	 * To maintain the inorder traverse order, the current node should be the left child of input node, else go into the right child.
	 */
	public TreeNode addOne(TreeNode root, int value) {
		TreeNode mover = root;
		TreeNode prev = null;
		while (true) {
			if (value > mover.val) {
				if (mover.right == null) {
					TreeNode node = new TreeNode(value);
					mover.right = node;
					break;
				}
				prev = mover;
				mover = mover.right;
			}
			else {
				TreeNode node = new TreeNode(value);
				if (prev != null)
					prev.right = node;
				else {
					root = node;
				}
				node.left = mover;
				break;
			}
		}
		return root;
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
