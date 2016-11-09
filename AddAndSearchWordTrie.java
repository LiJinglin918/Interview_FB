// 46. Add and Search Word
// Use trie tree to do this
// Time complexity: Add - O(len(word)) search: O(len(word)), space complexity: O(number of words * averageLen(word))








// Leetcode 原题。




/*
Design a data structure that supports the following two operations:
void addWord(word)
bool search(word)
search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.
For example:
addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true
Note:
You may assume that all words are consist of lowercase letters a-z.
*/
// Reference: https://discuss.leetcode.com/topic/38674/clean-java-trie-solution-23ms

public class AddAndSearchWordDataStructureDesign {
	class TrieNode {
		TrieNode[] children = new TrieNode[26];
		boolean flag = false;
	}
	TrieNode root = new TrieNode();
	public void addWord(String word) {
		TrieNode t1 = root;                             // 像树一样，node指向children
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (t1.children[c - 'a'] == null) {
				t1.children[c - 'a'] = new TrieNode();
			}
			t1 = t1.children[c - 'a'];
		}
		t1.flag = true;
	}
	public boolean search(String word) {
		return search(root, word);
	}
	private boolean search(TrieNode node, String word) {
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (c == '.') {
				for (TrieNode x : node.children) {
					if (x != null && search(x, word.substring(i + 1)))		// 判断null很重要，因为前面没写
						return true;
				}
				return false;					// 为‘.’的情况要判断FALSE的时候
			}
			else {
				node = node.children[c - 'a'];                  // 很重要，记得要将node指向children
				if (node == null) {
					return false;
				}
			}
		}
		return node.flag;						// 判断trie树里面结束
	}
}




/*=========================================================================================================== */




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
