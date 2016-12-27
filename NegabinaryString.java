// Given a decimal number, write a function that return its negabinary (i.e. negative 2-base) representation as a string.
// For example: solution(-15) == "110001", solution(2) == "110", solution(13) == "11101".

// https://www.careercup.com/question?id=5728108897370112

/*
(-2)^0 = 1
(-2)^1 = -2
(-2)^2 = 4
(-2)^3 = -8
(-2)^4 = 16
(-2)^5 = -32 and so on

i. e. for 2 = 1 * 4 + 1 * (-2) + 0 * 1 => 110
-15 = 1 * -32 + 1 * 16 + 0 * -8 + 0 * 4 + 0 * -2 + 1 * 1 => 110001
 */

public class NumberToNegabinaryString {
	public String nega(int x) {
		StringBuilder sb = new StringBuilder();
		while (x != 0) {
			int rem = x % -2;
			x = x / -2;
			if (rem < 0) {
				rem += 2;
				x++;
			}
			sb.append(rem);
		}
		return sb.reverse().toString();
	}
}

