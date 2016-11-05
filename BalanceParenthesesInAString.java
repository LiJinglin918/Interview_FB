/*
4. Balance parentheses in a string
例子：
"(a)()" -> "(a)()"
"((bc)" -> "(bc)"
")))a((" -> "a"
"(a(b)" ->"(ab)" or "a(b)"
*/
// 解法就是，从左到右删invalid‘)’, 再从右到左删invalid‘(’。也可以用stack记录‘(’的index。然后删除。 

public class Solution {
	public String findBalanceParentheses(String input) {
		String temp = deleteCloseParenthes(input);
		return deleteOpenParenthes(temp);
	}
	public String deleteCloseParenthes(String input) {
		int count = 0;
		StringBuilder res = new StringBuilder();
		for (char c : input.toCharArray()) {
			res.append(c);
			if (c == '(')
				count++;
			else if (c == ')') {
				if (count > 0)
					count--;
				else
					res.deleteCharAt(res.length() - 1);
			}
		}
		return res.toString();
	}
	public String deleteOpenParenthes(String input) {
		int count = 0;
		StringBuilder res = new StringBuilder();
		for (int i = input.length() - 1; i >= 0; i--) {
			char c = input.charAt(i);
			res.append(c);
			if (c == ')')
				count++;
			else if (c == '(') {
				if (count > 0)
					count--;
				else
					res.deleteCharAt(res.length() - 1);
			}
		}
		return res.reverse().toString();
	}
}
