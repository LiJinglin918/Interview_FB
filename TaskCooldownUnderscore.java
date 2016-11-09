/*
# Tasks: 1, 1, 2, 1. 
# Recovery interva (cooldown): 2
# Output: 7  (order is 1 _ _ 1 2 _ 1). 

# Example 2

# Tasks: 1, 2, 3, 1, 2, 3
# Recovery interval (cooldown): 3
# Output: 7  (order is 1 2 3 _ 1 2 3)

# Example 3

# Tasks: 1, 2, 3 ,4, 5, 6, 2, 4, 6, 1, 2, 4
# Recovery interval (cooldown): 6
# Output: 18  (1 2 3 4 5 6 _ _ 2 _ 4 _ 6 1 _ 2 _ 4)
*/ 







/*
 * 此题挺巧妙的。return a string list. The cooldown is the circle number
 * 1. Use hashmap to map the string to the index of the task[i]
 * 2. use a pointer j to store the index of the task[i].
 * 3. use another pointer i to traverse the tasks array
 * 4. It the distance >= cooldown, add "_".  (hm.get(tasks[i]) + cooldown .= j)
 */

import java.util.*;
public class TasksCircleCooldown {
	public List<String> Printer(String[] tasks, int cooldown) {
		List<String> res = new ArrayList<>();
		if (tasks == null || tasks.length == 0)
			return res;
		
		HashMap<String, Integer> hm = new HashMap<>();
		
		// use the pointer to store the index of tasks[i]
		int j = 0;
		for (int i = 0; i < tasks.length; i++) {
			while (hm.containsKey(tasks[i]) && hm.get(tasks[i]) + cooldown >= j) {
				res.add("_");
				j++;
			}
			res.add(tasks[i]);
			hm.put(tasks[i], j);
			j++;
		}
		return res;
	}
}
