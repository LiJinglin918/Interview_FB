// 83. find path between two nodes
/*
Find path from one node to another node in a binary tree
node has parent pointer 每一个node有父亲指针
*/





/*
 * 1. calculate the length from the node1(node2) to the root
 * 2. use the ArrayList "left" to store the path from node1 to root
 * 3. use the ArrayList "right" to store the path from node2 to root
 * (both are from the node to root, so one is ordered the other one is reverse)
 * 4. Add the root.
 * 5. store the path in order into res. 
 */

import java.util.*;
public class FindPathBetweenTwoNodes {
	public List<Integer> findPath(TreeNodeWithParent node1, TreeNodeWithParent node2) {
		List<Integer> res = new ArrayList<>();
		List<Integer> left = new ArrayList<>();
		List<Integer> right = new ArrayList<>();
		
		if (node1 == null || node2 == null)
			return res;
		
		TreeNodeWithParent mover1 = node1;
		TreeNodeWithParent mover2 = node2;
		
		int len1 = findLength(mover1);
		int len2 = findLength(mover2);
		
		while (len1 > len2) {
			left.add(mover1.val);
			mover1 = mover1.parent;
			len1--;
		}
		
		while (len2 > len1) {
			right.add(mover2.val);
			mover2 = mover2.parent;
			len2--;
		}
		
		// when len1 == len2, add the left and right together, util to the root 
		while (mover1 != mover2) {
			left.add(mover1.val);
			right.add(mover2.val);
			mover1 = mover1.parent;
			mover2 = mover2.parent;
		}
		
		// add the root 
		left.add(mover1.val);
		
		// restore the left inorderly, and restore the right reversely. 
		res.addAll(left);
		for (int i = right.size() - 1; i>= 0; i--) {
			res.add(right.get(i));
		}
		return res;
	}
	public int findLength(TreeNodeWithParent node) {
		int len = 0;
		while (node != null) {
			node = node.parent;
			len++;
		}
		return len;
	}
	public class TreeNodeWithParent {
		int val;
		TreeNodeWithParent left;
		TreeNodeWithParent right;
		TreeNodeWithParent parent;
		TreeNodeWithParent (int x) {
			val = x;
		}
	}
}
