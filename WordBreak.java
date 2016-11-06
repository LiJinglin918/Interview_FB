// 第一个方法返回Boolean，第二个返回一个值。
/*
Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
For example, given
s = "leetcode",
dict = ["leet", "code"].
Return true because "leetcode" can be segmented as "leet code".
*/
// two pointer, store the true part using boolean[i], finally to the end of string.

public class Solution {
    public boolean wordBreak(String s, Set<String> wordDict) {
        boolean[] index = new boolean[s.length() + 1];
        index[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (index[j] && wordDict.contains(s.substring(j, i))) {
                    index[i] = true;
                    break;
                }
            }
        }
        return index[s.length()];
    }
}


// Output only one result
// Use DFS
// Time complexity: O(2^(n/k))
class WordBreak {
    public String wordBreak(Set<String> dict, String input) {
        List<String> path = new ArrayList<>();
        helper(path, dict, input, 0);
        StringBuilder result = new StringBuilder();
        for (String word : path) {
            result.append(word + " ");
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    private boolean helper(List<String> path, Set<String> dict, String input, int index) {
        if (index == input.length()) {
            return true;
        }
        for (int i = index; i < input.length(); i++) {
            String word = input.substring(index, i + 1);
            if (dict.contains(word)) {
                path.add(word);
                if (helper(path, dict, input, i + 1)) {
                    return true;
                }
                path.remove(path.size() - 1);
            }
        }
        return false;
    }
}
