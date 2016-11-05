public int strStrPermutation(String haystack, String needle) {
    HashMap<Character, Integer> letterToCount = new HashMap<>();
    for (char letter : needle.toCharArray()) {
        if (letterToCount.containsKey(letter)) {
            letterToCount.put(letter, letterToCount.get(letter) + 1);
        }
        else {
            letterToCount.put(letter, 1);
        }
    }
    for (int i = 0; i < haystack.length(); i++) {
        if (letterToCount.containsKey(haystack.charAt(i))) {
            HashMap<Character, Integer> temp = new HashMap<>(letterToCount);
            int j = i;
            while (j < haystack.length()) {
                char letter = haystack.charAt(j);
                if (temp.containsKey(letter)) {
                    temp.put(letter, temp.get(letter) - 1);
                }
                else {
                    break;
                }
                if (temp.get(letter) == 0) {
                    temp.remove(letter);
                }
                if (temp.size() == 0) {
                    return i;
                }
                j++;
            }
            if (j == haystack.length()) {
                return -1;
            }
        }
    }
    return -1;
}
