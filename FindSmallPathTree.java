// 75. FindSmallPathTree		
/*
第二道题是 给个Tree 不一定是平衡的， 要求 把所有路径排序后  按字符串那样的比较大小方法 找出最小的路径  时间要求线性的。 比如  
         5
      /     \
    10      3
   /   \   /
  1    7  8
路径有  5 10 1 ； 5 10 7 ； 5 3 8
排序后  1 5 10 ； 5 7 10 ； 3 5 8；
所以按字符串类型排序 为  1 5 10 < 3 5 8 < 5 7 10；
*/

import java.util.*;
public class FindSmallPathTree {
	public List<Integer> findSmallPath(TreeNode root) {
		Result res = helper(root);
		Collections.sort(res.path);
		return res.path;
	}
	
	public Result helper(TreeNode root) {
		if (root == null) {
			
			// new 一个，然后填属性
			Result res = new Result();
			res.path.add(null);
			return res;
		}
		if (root.left == null && root.right == null) {
			
			// new 一个，然后填属性。
			Result res = new Result();
			res.min = root.val;
			res.path.add(root.val);
			return res;
		}
		
		// new 一个，然后填属性。先不管path是否排序，先添加再说。
		Result res = new Result();
		res.path.add(root.val);
		
		Result left = helper(root.left);
		Result right = helper(root.right);
		
		// 把左右子树含有最小的元素的那一部分path再添加进来。
		if (left.min > right.min) {
			res.path.addAll(right.path);
		}
		else {
			res.path.addAll(left.path);
		}
		
		// 最后min属性
		res.min = Math.min(root.val, Math.min(left.min, right.min));
		return res;
	}
	
	// 属性是最小值，和路径。
	public class Result {
		int min = Integer.MAX_VALUE;
		List<Integer> path = new ArrayList<>();
	}
}
