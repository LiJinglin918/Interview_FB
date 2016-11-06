// 43. Print Binary Tree Vertical
// use a hashmap to record the column
// Time complexity: O(n) - n nodes, Space complexity: O(n)
public List<List<Integer>> verticalOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) {
        return result;
    }
    HashMap<Integer, List<Integer>> colToVertical = new HashMap<>();
    HashMap<TreeNode, Integer> nodeToCol = new HashMap<>();
    Queue<TreeNode> level = new LinkedList<>();
    int mostLeft = 0;
    level.offer(root);
    nodeToCol.put(root, 0);
    while (!level.isEmpty()) {
        TreeNode node = level.poll();
        int curCol = nodeToCol.get(node);
        mostLeft = Math.min(mostLeft, curCol);
        if (!colToVertical.containsKey(curCol)) {
            colToVertical.put(curCol, new ArrayList<Integer>());
        }
        colToVertical.get(curCol).add(node.val);
        if (node.left != null) {
            level.offer(node.left);
            nodeToCol.put(node.left, curCol - 1);
        }
        if (node.right != null) {
            level.offer(node.right);
            nodeToCol.put(node.right, curCol + 1);
        }
    }
    while (colToVertical.containsKey(mostLeft)) {
        result.add(colToVertical.get(mostLeft++));
    }
    return result;
}
