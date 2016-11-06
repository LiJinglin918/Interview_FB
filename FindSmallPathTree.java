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
class FindSmallPath {
    public List<Integer> find(TreeNode root) {
        Result result = helper(root);
        return result.path;
    }

    private Result helper(TreeNode root) {
        if (root == null) {
            Result result = new Result();
            result.path.add(Integer.MAX_VALUE);
            return result;
        }
        if (root.left == null && root.right == null) {
            Result result = new Result();
            result.min = root.val;
            result.path.add(root.val);
            return result;
        }
        Result left = helper(root.left);
        Result right = helper(root.right);
        Result result = new Result();
        result.path.add(root.val);
        if (left.min > right.min) {
            result.path.addAll(right.path);
        }
        else {
            result.path.addAll(left.path);
        }
        result.min = Math.min(root.val, Math.min(left.min, right.min));
        return result;
    }
    class Result {
        public int min = Integer.MAX_VALUE;
        public List<Integer> path = new ArrayList<>();
        public Result() {
        }
    }
}
