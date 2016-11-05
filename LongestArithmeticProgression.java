/* 22. Longest arithmetic Progression in an unsorted array
在没排序的数组里，找最长的等差数列（index不能变）
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=199553&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3090%5D%5Bvalue%5D%3D1%26searchoption%5B3090%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
 */


public class Solution {
	public int findLongestArithmeticProgression(int[] input) {
        if (input.length <= 2) {
            return 2;
        }
        int maxLen = 2;
        int[][] length = new int[input.length][input.length];
        HashMap<Integer, List<Integer>> valueToIndex = new HashMap<>();
        for (int i = 0; i < input.length; i++) {
            if (!valueToIndex.containsKey(input[i])) {
                valueToIndex.put(input[i], new ArrayList<Integer>());
            }
            valueToIndex.get(input[i]).add(i);
        }
        for (int index = 1; index < input.length; index++) {
            for (int secondLast = index - 1; secondLast >= 0; secondLast--) {
                int gap = input[index] - input[secondLast];
                int next = input[secondLast] - gap;
                if (valueToIndex.containsKey(next)) {
                    int nextIndex = -1;
                    for (int j = valueToIndex.get(next).size() - 1; j >= 0; j--) {
                        if (valueToIndex.get(next).get(j) < secondLast) {
                            nextIndex = valueToIndex.get(next).get(j);
                            break;
                        }
                    }
                    if (nextIndex != -1) {
                        length[secondLast][index] = length[nextIndex][secondLast] + 1;
                        maxLen = Math.max(maxLen, length[secondLast][index]);
                    }
                }
                if (length[secondLast][index] == 0) {
                    length[secondLast][index] = 2;
                }
            }
        }
        return maxLen;
    }
}
