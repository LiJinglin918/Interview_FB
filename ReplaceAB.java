// 67. Replace
/*
这个题想不起来具体的例子了，大概是A和B分别代表两个不同的字符串。字符串由A,a,B,b组成。给定一个初始字符串（也是由A,a,B,b组成），
遍历这个初始字符串，如果遇到A或B，就用相应的字符串替代，这样就得到一个新字符串，然后对新字符串做替换操作。
问替代n次后第k项是什么字母。题目不难，但是不断要求优化，比如新字符串只需要保持size k。这个字符串具体例子很巧妙但记不起来了，
会形成一个repreated pattern，最后经面试官提示可以用binary search优化。这个面试官非常nice，好像是个东南亚裔的，基本上一步步给提示。
*/










/**
 * 此code是优化的结果（考虑最后形成重复的结果，一个circle。即改变完跟改变前一样）
 * 1. Use HashMap to map the replaced string to its index 
 * (When index reaches n, return)
 * 2. Use ArrayList to store all the replacement as a path
 * 3. Only consider the string at the length k
 * 4. Use a variable "last" to store the last string
 * 5. Check if forms a circle
 * 6. If forms, (hm.containKey(last)), calculate circle by hm.get(last).
 * 7. If not, add in the ArrayList path. 
 * 8. When calculate the char at "k". Calculate the index 
 * (when forms a circle, when not).
 * 9. from the path, get the string at index, then get char at k.
 */

import java.util.*;
public class ReplaceABwithInput {
	public char replace(String A, String B, String input, int n, int k) {
		
		// map the string to its index
		HashMap<String, Integer> strToIndex = new HashMap<>();
		
		// use ArrayList path to store the strings in each change
		List<String> path = new ArrayList<>();
		if (input.length() >= k + 1) {
			input = input.substring(0, k + 1);
		}
		strToIndex.put(input, 0);
		path.add(input);
		
		// store the last string
		String last = input;
		
		// count is count for the string in path. When it reaches n, return
		int count = 0;
		
		// circle is count for the number in the circle(loop)
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
			
			// update the last string
			last = curStr.toString();
			
			// whether there is circle (loop)
			if (strToIndex.containsKey(last)) {
				circle = count - strToIndex.get(last);
			}
			else {
				
				// store the last string into hm. count is the 遍数
				strToIndex.put(last, count);
				path.add(last);
			}
		}
		
		// reach the number n, return
		if (count == n) {
			return last.charAt(k);
		}
		
		// calculate the non-circle part number
		int nonCirclePart = path.size() - circle - 1;
		
		// calculate the index, should = noncirclepart it no loop
		int index = (n - nonCirclePart) % circle + nonCirclePart;
		
		// if n 在loop中，find the last string in path, at k.
		if (index == nonCirclePart) {
			return path.get(path.size() - 1).charAt(k);
		}
		else {
			return path.get(index).charAt(k);
		}
	}
}
