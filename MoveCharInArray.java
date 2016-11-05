/ Given char[], char, 移走array里面的所有此字符。in-place

public class Solution {
	public String removeChar(char[] input, char k) {
		int fast = 0; 
		int slow = 0;
		while (fast < input.length) {
			while (slow < input.length && input[slow] != k)
				slow++;
			fast = slow;
			while (fast < input.length && input[fast] == k)
				fast++;
			while (fast < input.length && input[fast] != k) {
				input[slow] = input[fast];
				input[fast] = k;
				fast++;
				slow++;
			}
		}
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < slow; i++)
			res.append(input[i]);
		return res.toString();
	}
}
