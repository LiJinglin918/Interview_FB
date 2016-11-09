/*
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

Tree To Double linked list: */












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
