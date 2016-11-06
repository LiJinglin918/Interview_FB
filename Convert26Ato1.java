// 60. 字母和数字的转换 A = 1 B = 2 AA = 27 基本是26进制的转换
public int titleToNumber(String s) {
    int result = 0;
    for (char letter : s.toCharArray()) {
        result = result * 26 + letter - 'A' + 1;
    }
    return result;
}

public String convertToTitle(int n) {
    StringBuilder result = new StringBuilder();
    while (n > 0) {
        char letter = (char)((n - 1) % 26 + 'A');
        result.append(letter);
        n = (n - 1) / 26;
    }
    return result.reverse().toString();
}
