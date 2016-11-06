// 23. Longest Path in binary tree (could be path from leaf to leaf)

class LongestPathInBT {
    public int findLongest(TreeNode root) {
        Pair length = checkNode(root);
        return length.longestPath;
    }

    private Pair checkNode(TreeNode root) {
        if (root == null) {
            return new Pair(0, 0);
        }
        Pair left = checkNode(root.left);
        Pair right = checkNode(root.right);
        int across = 1 + left.depth + right.depth;
        int longestPath = Math.max(across, Math.max(left.longestPath, right.longestPath));
        int depth = Math.max(left.depth, right.depth) + 1;
        return new Pair(depth, longestPath);
    }

    class Pair {
        int depth;
        int longestPath;
        public Pair(int depth, int longestPath) {
            this.depth = depth;
            this.longestPath = longestPath;
        }
    }
}
