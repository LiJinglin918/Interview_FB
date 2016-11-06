// 93. Find the position of name
/*
在一个post里面找出所有的name分别出现的位置, post会很长。 unordered_map<string, vector<int>>   findPosition (vector<string> names, string post) {}. names如果很多很多怎么办。

// Use TrieTree, 
// word.length = k, post.length = n
'Time complexity: O(nk), space complexity: total number of the node in trie tree'
*/

class FindIndex {
    TrieNode root = new TrieNode();
    public HashMap<String, List<Integer>> find(List<String> input, String post) {
        HashMap<String, List<Integer>> result = new HashMap<>();
        if (input.size() == 0) {
            return result;
        }
        for (String word : input) {
            insert(word);
        }
        for (int i = 0; i < post.length(); i++) {
            if (i == 3) {
                System.out.print("s");
            }
            List<String> words = search(post, i);
            for (String word : words) {
                if (!result.containsKey(word)) {
                    result.put(word, new ArrayList<>());
                }
                result.get(word).add(i);
            }
        }
        return result;
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
    private List<String> search(String post, int index) {
        List<String> result = new ArrayList<>();
        StringBuilder word = new StringBuilder();
        TrieNode mover = root;
        while (index < post.length()) {
            if (mover.isEnd) {
                result.add(word.toString());
            }
            char letter = post.charAt(index);
            if (mover.children[letter - 'a'] == null) {
                return result;
            }
            mover = mover.children[letter - 'a'];
            word.append(letter);
            index++;
        }
        if (mover.isEnd) {
            result.add(word.toString());
        }
        return result;
    }
}
