/*
 * Give a string and pair of n swapping indice, generate a lexicographically largest string. 
 * Swapping indices can be reused any number times
 * 
 * i.e. String = "abdc"
 * 		Indices: (1,4)(3,4)
 * 		Answer: cdba, cbad, dbac, dbca. You should only return "dbca" which is lexicographically largest. 
 */
 
import java.util.*;
public class SwapReturnLexicographical {
	
	public class Pair {
		int x, y;
		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public String toString() {
			return "x = " + x + "y = " + y;
		}
	}
	
	public String getLargestLexicographicalString(String str, List<Pair> pairs) {
		return helper(str, pairs, 0, new HashSet<>());
	}
	public String helper(String str, List<Pair> pairs, int index, HashSet<String> cache) {
		
		// reset if index reaches pairs list size as we will
        // keep trying until we get the highest lexicographical
        // possible string (pair swapping can be reused).
		
		if (index == pairs.size())
			index = 0;
		Pair pair = pairs.get(index);
		
		// return if (string + pair) has been processed before
		if (cache.contains(str + pair.toString()))
			return str;
		
		String swappedString = swap(str, pair);
		
		// mark (string + pair) as visited
		cache.add(str + pair.toString());
		
		String withSwap = helper(swappedString, pairs, index + 1, cache);
		
		String withOutSwap = helper(str, pairs, index + 1, cache);
		
		return withSwap.compareTo(withOutSwap) > 0 ? withSwap : withOutSwap; 
	}
	
	public String swap(String s, Pair pair) {
		char[] str = s.toCharArray();
		char temp = str[pair.x];
		str[pair.x] = str[pair.y];
		str[pair.y] = temp;
		return new String(str);
	}
}
