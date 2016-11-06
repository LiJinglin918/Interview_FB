/*
Implement strStr().
Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
*/
// 先是用俩指针，然后用substring，然后改变为2d Array的haystack.
public class ImplementStrStr {
	public static void main(String[] args) {
		System.out.println(new ImplementStrStr().strStr2("abcabcd", "bcd"));
	}
	public int strStr(String haystack, String needle) {
		if (needle.length() == 0)
			return 0;
		if (haystack == null || needle == null || haystack.length() < needle.length())
			return -1;
		
		for (int i = 0; i <= haystack.length() - needle.length(); i++) {
			for (int j = 0; j < needle.length(); j++) {
				if (haystack.charAt(i + j) != needle.charAt(j))                         // haystack.charAt(i + j)
					break;
				if (j == needle.length() - 1)
					return i;
			}
		}
		return -1;
	}
	public int strStr2(String haystack, String needle) {
		if (needle.length() == 0)
			return 0;
		for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
			if (haystack.charAt(i) == needle.charAt(0)) {
				String sub = haystack.substring(i, needle.length() + i);		// subString
				if (sub.equals(needle))
					return i;
			}
		}
		return -1;
	}
}

// 2d array 的 haystack
public class Solution {
	public int strStr(List<List<Character>> haystack, String needle) {
	    int total = 0;
	    for (int row = 0; row < haystack.size(); row++) {
	        for (int col = 0; col < haystack.get(row).size(); col++) {
	            int k = 0;
	            int posRow = row;
	            int posCol = col;
	            while ((posRow < haystack.size() && posCol < haystack.get(posRow).size()) && k < needle.length() && haystack.get(posRow).get(posCol) == needle.charAt(k)) {
	                k++;
	                posCol++;
	                if (posCol == haystack.get(posRow).size()) {
	                    posCol = 0;
	                    posRow++;
	                }
	            }
	            if (k == needle.length()) {
	                return total + col - 1;
	            }
	        }
	        total += haystack.get(row).size();
	    }
	    return -1;
	}
}
