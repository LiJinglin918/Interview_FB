// 2Sum
class TwoSum {
    public List<List<Integer>> twoSumIndex(int[] input, int target) {
        List<List<Integer>> result = new ArrayList<>();
        HashMap<Integer, List<Integer>> valueToIndex = new HashMap<>();
        Set<List<Integer>> visited = new HashSet<>();
        for (int i = 0; i < input.length; i++) {
            if (!valueToIndex.containsKey(input[i])) {
                valueToIndex.put(input[i], new ArrayList<Integer>());
            }
            valueToIndex.get(input[i]).add(i);
        }
        for (int i = 0; i < input.length; i++) {
            if (valueToIndex.containsKey(target - input[i])) {
                for (int index : valueToIndex.get(target - input[i])) {
                    if (index > i) {
                        List<Integer> pair = new ArrayList<>(Arrays.asList(i, index));
                        if (!visited.contains(pair)) {
                            visited.add(pair);
                            result.add(pair);
                        }
                    }
                }
            }
        }
        return result;
    }
    public List<List<Integer>> twoSumValue(int[] input, int target) {
        List<List<Integer>> result = new ArrayList<>();
        HashMap<Integer, List<Integer>> valueToIndex = new HashMap<>();
        Set<List<Integer>> visited = new HashSet<>();
        for (int i = 0; i < input.length; i++) {
            if (!valueToIndex.containsKey(input[i])) {
                valueToIndex.put(input[i], new ArrayList<Integer>());
            }
            valueToIndex.get(input[i]).add(i);
        }
        for (int i = 0; i < input.length; i++) {
            if (valueToIndex.containsKey(target - input[i])) {
                int idx = -1;
                for (int index : valueToIndex.get(target - input[i])) {
                    if (index != i) {
                        idx = index;
                        break;
                    }
                }
                if (idx != -1) {
                    List<Integer> pair = new ArrayList<>(Arrays.asList(input[i], input[idx]));
                    Collections.sort(pair);
                    if (!visited.contains(pair)) {
                        result.add(pair);
                        visited.add(pair);
                    }
                }

            }
        }
        return result;
    }
    public List<List<Integer>> twoSumSort(int[] input, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(input);
        int left = 0;
        int right = input.length - 1;
        while (left < right) {
            if (input[left] + input[right] > target) {
                right--;
            }
            else if (input[left] + input[right] < target) {
                left++;
            }
            else {
                result.add(new ArrayList<Integer>(Arrays.asList(input[left], input[right])));
                left++;
                right--;
                while (left < input.length && input[left] == input[left - 1]) {
                    left++;
                }
                while (right >= 0 && input[right] == input[right + 1]) {
                    right--;
                }
            }
        }
        return result;
    }
}
