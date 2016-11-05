/* 22. Longest arithmetic Progression in an unsorted array
在没排序的数组里，找最长的等差数列（index不能变）
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=199553&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3090%5D%5Bvalue%5D%3D1%26searchoption%5B3090%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
 */
// 第一个方法是返回等差数列的长度。第二个方法是返回整个等差数列。

// 返回等差数列的长度。
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

// 如果要输出这个等差数列结果。
public List<Integer> printLongest(int[] input) {
        List<Integer> result = new ArrayList<>();
        if (input.length <= 2) {
            for (int num : input) {
                result.add(num);
            }
            return result;
        }
        int maxLen = 0;
        List<Integer>[][] length = new ArrayList[input.length][input.length];
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
                        length[secondLast][index] = new ArrayList<Integer>(length[nextIndex][secondLast]);
                        length[secondLast][index].add(input[index]);
                        if (maxLen <= length[secondLast][index].size()) {
                            result = length[secondLast][index];
                            maxLen = length[secondLast][index].size();
                        }
                    }
                }
                if (length[secondLast][index] == null) {
                    length[secondLast][index] = new ArrayList<>();
                    length[secondLast][index].add(input[secondLast]);
                    length[secondLast][index].add(input[index]);
                }
            }
        }
        return result;
    }
