// 25. Product of the array
// 给定一个array, 返回里面元素乘积的所有可能值。例如给定array:[1,2,3,4]   应该返回 [1, 2, 3, 4, 6, 8, 12, 24]












/*
 * backtracking. 
 * 1. use a flag to indicate the first element
 * 2. consider the replicates
 * 3. If one of the elements is 0, all 0. return. Find the next index
 */

import java.util.*;
public class ProductOfTheArray {
	public List<Integer> product(int[] input) {
		List<Integer> res = new ArrayList<>();
		Arrays.sort(input);
		helper(res, 1, 0, input, true);
		return res;
	}

	public void helper(List<Integer> res, int curProduct, int pos, int[] input, boolean flag) {
		
		// use a flag to represent the first element or not
		if (!flag) {
			res.add(curProduct);
		}
		flag = false;
		
		// traverse the remaining input
		for (int i = pos; i < input.length; i++) {
			
			// consider the replicates
			if (i != pos & input[i] == input[i - 1])
				continue;
			
			// consider one of the elements is 0, just return
			if (input[i] == 0) {
				res.add(0);
				continue;
			}
			
			// backtracking
			curProduct *= input[i];
			helper(res, curProduct, i + 1, input, flag);
			curProduct /= input[i];
		}
	}
}
