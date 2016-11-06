/*
 32. 给一个正数n, 打印所有相加的组合.
 例如： 1+1+1+1+...+1
		1+2+1+...+1
		9+1
		10
 */
 // DFS  'time complexity: O(k) -- k is the number of sums, space complexity: O(k * average Length) k == 2 ^ n'
 
 class AddSum {
    public List<List<Integer>> find(int input) {
        List<List<Integer>> result = new ArrayList<>();
        helper(result, new ArrayList<Integer>(), 1, input, 0);
        return result;
    }

    private void helper(List<List<Integer>> result, List<Integer> path, int pos, int target, int sum) {
        if (target == sum) {
            System.out.println(path);
            result.add(path);
            return;
        }
        for (int i = pos; i <= target; i++) {
            if (sum + i > target) {
                return;
            }
            sum += i;
            path.add(i);
            helper(result, path, i, target, sum);
            sum -= i;
            path.remove(path.size() - 1);
        }
    }
}
