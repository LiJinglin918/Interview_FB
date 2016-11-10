// 53. Tasks With Cooldown with underscore   两种返回。一个string一个list
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
	
	
/*================================================================================================*/
	
	
	/*
	 * return string
	 * 1. use hashmap to map the task to the excute time.
	 * 2. When traverse the new char, time++
	 * 3. if the time of certain task is too far from the excute time (time - hm.get(missions[i]) > k), a new task was put into the hashmap
	 * 4. calculate the gap, then append '_' to the stringbuilder
	 * 5. calculate the new time for the new task. 
	 */
	
	public String Printer2(String input, int k) {
		if (input.length() <= 1)
			return input;
		StringBuilder res = new StringBuilder();
		
		// use a hashmap to map the tasks to the excute time
		HashMap<Character, Integer> missionToTime = new HashMap<>();
		
		// use variable time to store the current excute time (pointer)
		int time = 0;
		char[] missions = input.toCharArray();
		
		// traverse the missions array, one char increase one time
		for (int i = 0; i < missions.length; i++) {
			time++;
			
			// if the hashmap doesn't contains char || the certain task's excute time is too long, put a new task into the hashmap
			if (!missionToTime.containsKey(missions[i]) || time - missionToTime.get(missions[i]) > k) {
				missionToTime.put(missions[i], time);
			}
			
			// append the '_', according to the gap
			else {
				int gap = k - (time - missionToTime.get(missions[i]) - 1);
				while (gap > 0) {
					res.append('_');
					gap--;
				}
				
				// if the hashmap contains the certain char, recalculate the time and put it into hashmap
				time = k + missionToTime.get(missions[i]) + 1;
				missionToTime.put(missions[i], time);
			}
			res.append(missions[i]);
		}
		return res.toString();
	}
}

/*=========================================================================================================================================*/


// 只需返回一共多少时间。integer
//29. 工作的调度，是个面经题，有些变种，只要求出给定tasks的工作总时间，在小哥提示下做了优化到O(n). Follow up是如何schedule这些工作，这样最后的工作总时间最少。我说了一种greedy的算法，就是相同task相隔约长约好。但是不太对，小哥说其实是一旦数量最多的task cooldown时间到了，就schedule这个task。问了我大概怎么实现，就结束了。
// http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=198026&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3090%5D%5Bvalue%5D%3D1%26searchoption%5B3090%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
//mission order , same task cannot be called in a period  (missions, task, cd)

import java.util.*;
public class TasksMissionCooldownTotalTime {
	public int missionTotalTime (int[] mission, int n) {
		if (mission.length == 0)
			return 0;
		HashMap<Integer, Integer> hm = new HashMap<>();
		int time = 0;
		for (int i = 0; i < mission.length; i++) {
			time++;
			if (!hm.containsKey(mission[i])) {
				hm.put(mission[i], time);
			}
			else {
				if (time - hm.get(mission[i]) > n) {
					hm.put(mission[i], time);
				}
				else {
					time = n + hm.get(mission[i]) + 1;
					hm.put(mission[i], time);
				}
			}
		}
		return time;
	}
}
