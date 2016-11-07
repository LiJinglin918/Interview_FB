// 96. Find All Equivalent Pairs
/*
Given an array A of integers, find the index of values that satisfy A + B =C + D, where A,B,C & D are integers values in the array. Find all combinations of quadruples.
*/
// 'Time complexity: O(n^2)'







/**
 * 1. create a class "Indexs", which contains two elements: list of pairs in array format, and the pairs' index.
 * 2. use a HashMap. The key is sum, val is the "Indexs".  
 * 3. Traverse the array, using i and j to calculate sum
 * 4. create the HashMap, if keyset contains sum, add the new pair and new index into the hashmap. if not, new 
 * 5. Then iterate and traverse the keyset of hashmap, get the pairs of index
 * 6. If the index.size() is >= 2, then initiate an arraylist to add all the possibilities of the pairs. 
 * 7。 Time complexity: O(n^2)
 */
import java.util.*;
public class FourNumberPairs {
	public List<List<int[]>> find(int[] input) {
		
		// create the HashMap. The key is sum, val is the "indexs"
		HashMap<Integer, Indexs> sumToPair = new HashMap<>();
		for (int i = 0; i < input.length - 1; i++) {
			for (int j = i + 1; j < input.length; j++) {
				int sum = input[i] + input[j];
				if (!sumToPair.containsKey(sum)) {
					sumToPair.put(sum, new Indexs());
				}
				int[] newPair = new int[] {i, j};
				Set<Integer> index = sumToPair.get(sum).index;
				if (!index.contains(i) && !index.contains(j)) {
					sumToPair.get(sum).pairs.add(newPair);
					sumToPair.get(sum).index.add(i);
					sumToPair.get(sum).index.add(j);
				}
			}
		}
		
		// Find the candidate in HashMap and store in the result
		List<List<int[]>> res = new ArrayList<>();
		for (int sum : sumToPair.keySet()) {
			List<int[]> pairs = sumToPair.get(sum).pairs;
			if (pairs.size() >= 2) {						// If there is more than 1 candidate, 排列组合
				List<int[]> list = new ArrayList<>();
				for (int i = 0; i < pairs.size() - 1; i++) {
					for (int j = i + 1; j < pairs.size(); j++) {
						int[] pair1 = pairs.get(i);
						int[] pair2 = pairs.get(j);
						list.add(new int[] {input[pair1[0]], input[pair1[1]]});				// 排列组合
						list.add(new int[] {input[pair2[0]], input[pair2[1]]});
					}
				}
				if (list.size() != 0) {
					res.add(list);
				}
			}
		}
		return res;
	}
	public class Indexs {
		List<int[]> pairs = new ArrayList<>();
		Set<Integer> index = new HashSet<>();
	}
}
