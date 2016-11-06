// 46. Add and Search Word
// Use trie tree to do this
// Time complexity: Add - O(len(word)) search: O(len(word)), space complexity: O(number of words * averageLen(word))

public class WordDictionary {
    TrieNode root;
    public WordDictionary() {
        this.root = new TrieNode();
    }
    
    // Adds a word into the data structure.
    public void addWord(String word) {
        TrieNode mover = root;
        for (char letter : word.toCharArray()) {
            int index = letter - 'a';
            if (mover.children[index] == null) {
                mover.children[index] = new TrieNode();
            }
            mover = mover.children[index];
        }
        mover.isEnd = true;
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        return helper(word, root);        
    }
    
    private boolean helper(String word, TrieNode root) {
        TrieNode mover = root;
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            if (letter == '.') {
                boolean result = false;
                for (TrieNode node : mover.children) {
                    if (node != null) {
                        result = result || helper(word.substring(i + 1), node);
                    }
                }
                return result;
            }
            else {
                if (mover.children[letter - 'a'] != null) {
                    mover = mover.children[letter - 'a'];
                }
                else {
                    return false;
                }
            }
        }
        return mover.isEnd;
    }
    
    class TrieNode {
        public TrieNode[] children;
        boolean isEnd;
        public TrieNode() {
            this.children = new TrieNode[26];
            this.isEnd = false;
        }
    }
}
