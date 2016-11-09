import java.util.*;




/*
 * 返回所有两个数相加等于target的一组数的index (考虑input中重复的元素)
 * 1. use a hashmap to map the interger in input to its index
 * 2. use a set to check whether the position is visited or not. 
 * 3. traverse the input, map the value and index into hashmap
 * 4. traverse the input again, if the hashmap containsKey(target - input[i])
 * indicating this index i and the previous index can be a pair of result.
 * 5. Find all the index of the value (target - input[i]) because of the replicates. 
 * 6. if this pair is not visited, add it. 
 */


public class TwoSum {
	public List<List<Integer>> twoSumIndex(int[] input, int target) {
		
		// store the index of the two candidate (allows replicates, so if replicate, there are two indexes)
		List<List<Integer>> res = new ArrayList<>();
		HashMap<Integer, List<Integer>> valueToIndex = new HashMap<>();
		Set<List<Integer>> visited = new HashSet<>();
		for (int i = 0; i < input.length; i++) {
			if (!valueToIndex.containsKey(input[i])) {
				valueToIndex.put(input[i], new ArrayList<Integer>());
			}
			valueToIndex.get(input[i]).add(i);
		}
		for (int i = 0; i < input.length; i++) {
			if (valueToIndex.containsKey(target - input[i])) {
				for (int index : valueToIndex.get(target - input[i])) {
					
					// to avoid the replicates
					if (index > i) {
						
						// store the pair into the list
						List<Integer> pair = new ArrayList<>(Arrays.asList(i, index));
						
						// if it is not visited
						if (!visited.contains(pair)) {
							visited.add(pair);
							res.add(pair);
						}
					}
				}
			}
		}
		return res;
	}
    
 /*========================================================================================================================*/

    
    /*
	 * store the two integers, whose sum is the target
	 * 1. allows the replicates. However, just need to store the integer value. So need to care about it
	 * 2. use the hashmap to map the integer value to its index
	 * 3. traverse the input array, map the value in the array to its index into hashmap
	 * 4. traverse the input array again, if the hashmap containsKey(target - input[i]), indicating that pair is the candidate
	 * 5. replicate does not matter, so just break
	 * 6. if found, put the pair into list. 
	 */
	
	public List<List<Integer>> twoSumValue(int[] input, int target) {
		
		// store the two integers whose sum is the target into the list
		List<List<Integer>> res = new ArrayList<>();
		
		// map the integer into the index
		HashMap<Integer, List<Integer>> valueToIndex = new HashMap<>();
		Set<List<Integer>> visited = new HashSet<>();
		
		for (int i = 0; i < input.length; i++) {
			if (!valueToIndex.containsKey(input[i])) {
				valueToIndex.put(input[i], new ArrayList<Integer>());
			}
			valueToIndex.get(input[i]).add(i);
		}
		
		for (int i = 0; i < input.length; i++) {
			if (valueToIndex.containsKey(target - input[i])) {
				int idx = -1;				// flag
				for (int index : valueToIndex.get(target - input[i])) {
					if (index != i) {
						idx = index;
						break;				// replicates does not matter, just break
					}
				}
				
				// If found the pair
				if (idx != -1) {
					List<Integer> pair = new ArrayList<>(Arrays.asList(input[i], input[idx]));
					Collections.sort(pair);
					if (!visited.contains(pair)) {
						res.add(pair);
						visited.add(pair);
					}
				}
			}
		}
		return res;
	}
	
	
    
    /*==============================================================================================================*/
    
    
	/*
	 * If the array is already sorted, or could be sorted.
	 * 1. sort the array input
	 * 2. use two pointers, left and right to traverse the array input
	 * 3. if sum > target, right--; if sum < target, left++;
	 * 4. If these is replicate, left++ and right--.
	 */
	public List<List<Integer>> twoSumSort(int[] input, int target) {
		List<List<Integer>> res = new ArrayList<>();
		Arrays.sort(input);
		int left = 0;
		int right = input.length - 1;
		while (left < right) {
			if (input[left] + input[right] > target) {
				right--;
			}
			else if (input[left] + input[right] < target) {
				left++;
			}
			else {
				res.add(new ArrayList<Integer>(Arrays.asList(input[left], input[right])));
				left++;
				right--;
				while (left < input.length && input[left] == input[left - 1]) {
					left++;
				}
				while (right >= 0 && input[right] == input[right + 1]) {
					right--;
				}
			}
		}
		return res;
	}
}
