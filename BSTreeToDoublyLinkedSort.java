// 47. BST to Doubly LinkedList
// Time complexity O(nlgn) 

/* 71. Tree to Doubly LinkedList

     3
   /
1         
   \ 
    2
一棵树，返回一个list的头结点：
1《——》2《——》3（一三相连）
意思是返回一个递增list，1的right是2，left是3， 2的left是1，right是3,3的left是2，right是 1.
我用inorder获得递增序列然后设置left与right的node。返回1这个结点。. from: 1point3acres.com/bbs 
follow up如何不先获得序列，直接在traverse的时候获得结果。follow up没回答出来。
*/









/*
 * Convert the tree into double LinkedList, in the format of sort
 * 1. use the method "toLeft" to store the left branch (include the root) into the stack inorder
 * 2. while the inorder is not empty, pop the smallest one by one, add the right of each root into stack. (inorder process)
 * 3. link the end to the start
 */

import java.util.*;
public class TreeDoublyLinkedListSort {
	public DoubleLinkedList toDoubleLinkedList(TreeNode root) {
		if (root == null)
			return null;
		
		// initiate a stack that can be the parameter into the method "toLeft" to store
		Stack<TreeNode> inorder = new Stack<>();
		DoubleLinkedList fakeHead = new DoubleLinkedList(0);
		DoubleLinkedList previous = fakeHead;
		
		// store the left branch of the tree (including the root into the stack "inorder")
		toLeft(inorder, root);
		
		// while the stack is not empty, pop and link, and push the each root's right into the stack. 
		while(!inorder.isEmpty()) {
			TreeNode node = inorder.pop();
			DoubleLinkedList curNode = new DoubleLinkedList(node.value);
			curNode.pre = previous;
			previous.next = curNode;
			previous = curNode;
			if (node.right != null) {
				toLeft(inorder, node.right);
			}
		}
		
		// link the tail with the head
		DoubleLinkedList head = fakeHead.next;
		head.pre = previous;
		previous.next = head;
		return head;
	}
	
	public void toLeft(Stack<TreeNode> inorder, TreeNode root) {
		while (root != null) {
			inorder.push(root);
			root = root.left;
		}
	}

	
/*==============================================================================================================*/
	
	/*
	 * another method. Do not use extra space. Use the recursive
	 * 1. in the main function, connect the root. then find the left most node and right most node, link them
	 * 2. in the connect function, find the left branch connection, connect the left's root with the root
	 * 3. same to the right branch
	 */
	
	public TreeNode Tree2LinkedList(TreeNode root) {
		if (root == null)
			return root;
		
		ConnnectWithoutLinkedHeadTail(root);
		
		// find the left most node and right most node
		TreeNode head = root;
		TreeNode tail = root;
		while (head.left != null)
			head = head.left;
		while (tail.right != null)
			tail = tail.right;
		
		// connect the left most and right most nodes
		head.left = tail;
		tail.right = head;
		return head;
	}
	
	
	// use this method to link the nodes
	public void ConnnectWithoutLinkedHeadTail (TreeNode root) {
		if (root == null)
			return;
		if (root.left != null) {
			TreeNode left = root.left;
			
			// recursively. find the left branch node
			ConnnectWithoutLinkedHeadTail(left);
			while (left.right != null) {
				left = left.right;
			}
			
			// connect the left branch with the root
			left.right = root;
			root.left = left;
		}
		
		// same with the right branch
		if (root.right != null) {
			TreeNode right = root.right;
			ConnnectWithoutLinkedHeadTail(right);
			while (right.left != null) {
				right = right.left;
			}
			right.left = root;
			root.right = right;	
		}
	}
	
	public class TreeNode {
		int value;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) {
			value = x;
		}
	}
	public class DoubleLinkedList {
		int val;
		DoubleLinkedList pre;
		DoubleLinkedList next;
		public DoubleLinkedList(int val) {
			this.val = val;
		}
	}
}
