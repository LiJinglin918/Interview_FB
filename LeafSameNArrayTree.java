/*
 * If we are given two n-ary trees check if the leaf nodes are the same in the left to right direction.*/

/*
 * So basically we need to get a string of leaf nodes of both the trees and if both the strings are equal then return true
Now by definition, leaf node is a node who doesn't have any children so to get list of leaf nodes of a tree, 
while doing a DFS traversal if we find a node which has an empty list of children then append it's data to our result string. 
This way we can get string of leaf nodes

Code can something as below. Let me know if there is anything wrong in this solution.
Time complexity is O(|V|), as we need to traverse all nodes.
Space complexity - since it is a recursive implementation it would be O(h)

 */
import java.util.*;
public class LeafSameNArrayTree {
	class tNode {
		char val;
		ArrayList<tNode> children;
		tNode(char c) {
			val = c;
			children = new ArrayList<>();
		}
	}
	public boolean hasSameLeafNode(tNode root1, tNode root2) {
		StringBuilder s1 = helper(root1);
		StringBuilder s2 = helper(root2);
		return s1.equals(s2);
	}
	public StringBuilder helper(tNode root) {
		if (root == null)
			return null;
		StringBuilder sb = new StringBuilder();
		ArrayList<tNode> list = root.children;
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				sb.append(helper(list.get(i)));
			}
		}
		else {
			sb.append(root.val);
		}
		return sb;
	}
}
