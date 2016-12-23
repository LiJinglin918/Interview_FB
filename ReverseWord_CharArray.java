/*
 * [ 'p', 'e', 'r', 'f', 'e', 'c', 't', ' ', 
 * 'm', 'a', 'k', 'e', 's', ' ', '
 * p', 'r', 'a', 'c', 't', 'i', 'c', 'e' ]

would turn into:
[ 'p', 'r', 'a', 'c', 't', 'i', 'c', 'e', ' ', 
'm', 'a', 'k', 'e', 's', ' ', 
'p', 'e', 'r', 'f', 'e', 'c', 't' ]
*/
import java.util.*;
public class ReverseWords_CharArray {
	public static void main(String[] args) {
		char[] arr = {'p', 'e', 'r', 'f', 'e', 'c', 't', ' ', 
				'm', 'a', 'k', 'e', 's', ' ', 
				'p', 'r', 'a', 'c', 't', 'i', 'c', 'e'};
		char[] res = new ReverseWords_CharArray().reverseWord(arr);
		for (int i = 0; i < res.length; i++) {
			System.out.println(res[i]);
		}
	}
	public char[] reverseWord(char[] arr) {
		StringBuilder temp = new StringBuilder();
		StringBuilder res = new StringBuilder();
		int i = arr.length - 1;
		
		// 从右到左，加入到temp中最后再reverse回来
		for (; i >= 0; i--) {
			if (arr[i] != ' ') {
				temp.append(arr[i]);
			}
			if (arr[i] == ' ') {
				res.append(temp.reverse());
				res.append(" ");
				temp = new StringBuilder();
			}
		}
		if (i == -1) {
			res.append(temp.reverse());
		}
		return res.toString().toCharArray();
	}
}
