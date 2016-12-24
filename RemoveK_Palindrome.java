/*
 * A k-palindrome is a string which transforms into a palindrome on removing at most k characters.

Given a string S, and an integer K, print "YES" if S is a k-palindrome; otherwise print "NO".

Constraints:

S has at most 20,000 characters.
0 <= k <= 30

Sample Test Cases:

Input - abxa 1 
Output - YES 

Input - abdxa 1 
Output - NO

 */

public class Palindrome_RemoveK {
	public boolean RemoveK_Palindrome(String s, int k) {
		if (s.length() <= 1) {
			return true;
		}
		while (s.charAt(0) == s.charAt(s.length() - 1)) {
			s = s.substring(1,  s.length() - 1);
			if (s.length() <= 1) {
				return true;
			}
		}
		if (k == 0) {
			return false;
		}
		return RemoveK_Palindrome(s.substring(0, s.length() - 1), k - 1) || RemoveK_Palindrome(s.substring(1, s.length()), k - 1);
	}
}


