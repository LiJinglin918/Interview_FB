/*
Given a 2D board and a word, find if the word exists in the grid.
The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
For example,
Given board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.
*/

public class Solution {
    public boolean exist(char[][] board, String word) {
        if (board == null || word == null)
            return false;
        if (word.length() == 0)
            return true;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0))
                    if (dfs (board, word, i, j, 0))
                        return true;
            }
        }
        return false;
    }
    private boolean dfs(char[][] board, String word, int i, int j, int index) {
        if (word.length() == index)                                                  // Note the usage of index and length() == index        
            return true;
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(index))
            return false;
        board[i][j] = '~';                                                           // visited
        boolean res = dfs(board, word, i - 1, j, index + 1) 
                   || dfs(board, word, i + 1, j, index + 1)
                   || dfs(board, word, i, j - 1, index + 1) 
                   || dfs(board, word, i, j + 1, index + 1);
        board[i][j] = word.charAt(index);
        return res;
    }
}

// word search 2:
// Use trie tree to store the words in the dictionary
// So we dont have to look into the array to find the character match any words
// We only need to look into the trieNode root to see children[ch - 'a'] == null
// if not, then we can start search
'Time complexity: O(n^2*len), space complexity:O(len*k) there are k words in the arrays'
public class Solution {
    private static final int[] X = {0, 0, 1, -1};
    private static final int[] Y = {1, -1, 0, 0};
    private TrieNode root = new TrieNode();
    
    public List<String> findWords(char[][] board, String[] words) {
        for (String word : words) {
            insert(word);
        }
        List<String> result = new ArrayList<>();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (root.children[board[row][col] - 'a'] != null) {
                    String word = board[row][col];
                    helper(board, new boolean[board.length][board[0].length], root.children[board[row][col] - 'a'], row, col, word, result);
                }
            }
        }
        return result;
    }
    
    private void helper(char[][] board, boolean[][] visited, TrieNode node, int row, int col, String word, 
                        List<String> result) {
        visited[row][col] = true;
        if (node.isEnd && !result.contains(word)) {
            result.add(word);
        }
        for (int k = 0; k < 4; k++) {
            int nextX = row + X[k];
            int nextY = col + Y[k];
            if (nextX < 0 || nextX >= board.length || nextY < 0 || nextY >= board[0].length || visited[nextX][nextY]
                || node.children[board[nextX][nextY] - 'a'] == null) {
                continue;
            }
            helper(board, visited, node.children[board[nextX][nextY] - 'a'], nextX, nextY, word + board[nextX][nextY], result);
        }
        visited[row][col] = false;
    }
    
    private void insert(String word) {
        TrieNode mover = root;
        for (char letter : word.toCharArray()) {
            if (mover.children[letter - 'a'] == null) {
                mover.children[letter - 'a'] = new TrieNode();
            }
            mover = mover.children[letter - 'a'];
        }
        mover.isEnd = true;
    }
    
    class TrieNode {
        public TrieNode[] children;
        public boolean isEnd;
        public TrieNode() {
            this.children = new TrieNode[26];
            this.isEnd = false;
        }
    }
}
