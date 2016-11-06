// 67. Replace
/*
这个题想不起来具体的例子了，大概是A和B分别代表两个不同的字符串。字符串由A,a,B,b组成。给定一个初始字符串（也是由A,a,B,b组成），
遍历这个初始字符串，如果遇到A或B，就用相应的字符串替代，这样就得到一个新字符串，然后对新字符串做替换操作。
问替代n次后第k项是什么字母。题目不难，但是不断要求优化，比如新字符串只需要保持size k。这个字符串具体例子很巧妙但记不起来了，
会形成一个repreated pattern，最后经面试官提示可以用binary search优化。这个面试官非常nice，好像是个东南亚裔的，基本上一步步给提示。
*/
class Replace {
    public char replace(String A, String B, String input, int n, int k) {
        HashMap<String, Integer> strToIndex = new HashMap<>();
        List<String> path = new ArrayList<>();
        if (input.length() >= k + 1) {
            input = input.substring(0, k + 1);
        }
        strToIndex.put(input, 0);
        path.add(input);
        String last = input;
        int count = 0;
        int circle = 0;
        while (count < n && circle == 0) {
            count++;
            StringBuilder curStr = new StringBuilder();
            for (char letter : last.toCharArray()) {
                if (letter == 'A') {
                    curStr.append(A);
                }
                else if (letter == 'B') {
                    curStr.append(B);
                }
                else {
                    curStr.append(letter);
                }
                if (curStr.length() >= k + 1) {
                    curStr = curStr.delete(k + 1, curStr.length());
                    break;
                }
            }
            last = curStr.toString();
            if (strToIndex.containsKey(last)) {
                circle = count - strToIndex.get(last);
            }
            else {
                strToIndex.put(last, count);
                path.add(last);
            }
        }
        if (count == n) {
            return last.charAt(k);
        }
        int nonCircle = path.size() - circle - 1;
        int index = (n - nonCircle) % circle + nonCircle;
        System.out.println(path);
        if (index == nonCircle) {
            return path.get(path.size() - 1).charAt(k);
        }
        else {
            return path.get(index).charAt(k);
        }
    }
}
