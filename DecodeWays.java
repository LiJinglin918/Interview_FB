/*
A message containing letters from A-Z is being encoded to numbers using the following mapping:
'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.
For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
The number of ways decoding "12" is 2.
*/
// 用int[] dp 记录valid的组合的数目。一个数字valid，则和前一个数字的数目一样；两个数字valid, 则像插入组合，即前两个数字相加。
// 即爬楼梯。最后一步可能从倒数第二步跨一步，也可能是倒数第三部跨两步。而解法是两种可能性相加。

// 第二个问题是，将所有的结果放在list中

public class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0)
            return 0;
        if (s.charAt(0) == '0')
            return 0;
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        
        if (isValid(s.substring(0, 1)))
            dp[1] = 1;
        else
            dp[1] = 0;                                                // this part could be replaced by "dp[1] = 1;"
            
        for (int i = 2; i <= s.length(); i++) {
            if (isValid(s.substring(i - 1, i)))
                dp[i] = dp[i - 1];
            if (isValid(s.substring(i - 2, i)))
                dp[i] = dp[i] + dp[i - 2];
        }
        return dp[s.length()];
    }
    private boolean isValid(String s) {
        if (s.charAt(0) == '0')                                       // for example, int the case of 101, 01 is not valid.
            return false;
        int num = Integer.parseInt(s);
        return num >= 1 && num <= 26;
    }
}


// Output the all result of decode ways (把所有ways都存在list里)
public class Solution {
	public List<String> decode(String num) {
		List<String> res = new ArrayList<>();
		helper(res, 0, num, "");
		return res;
	}
	public void helper(List<String> res, int pos, String num, String way) {
		if (pos == num.length()) {
			res.add(way);
			return;
		}
		String digits = num.substring(pos, pos + 1);
		int number = Integer.parseInt(digits);
		if (number == 0)
			return;
		helper(res, pos + 1, num, way + (char)(number + 'A' - 1));
		if (pos < num.length() - 1) {
			String digits2 = num.substring(pos, pos + 2);
			int number2 = Integer.parseInt(digits2);
			if (number <= 26)
				helper(res, pos + 2, num, way + (char)(number2 + 'A' - 1));
		}
	}
}
