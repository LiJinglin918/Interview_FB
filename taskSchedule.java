// 有两个方法，第一个是一般，第二个是follow up, 把时间减少。
/*
29. 工作的调度，是个面经题，有些变种，只要求出给定tasks的工作总时间，在小哥提示下做了优化到O(n). 
Follow up是如何schedule这些工作，这样最后的工作总时间最少。我说了一种greedy的算法，就是相同task相隔约长约好。
但是不太对，小哥说其实是一旦数量最多的task cooldown时间到了，就schedule这个task。问了我大概怎么实现，就结束了。
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=198026&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3090%5D%5Bvalue%5D%3D1%26searchoption%5B3090%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
*/
// mission order , same task cannot be called in a period  (missions, task, cd)

public static int missionOrder(int[] mission, int N) {
		if (mission.length == 0) {
			return 0;
		} 
		HashMap<Integer, Integer> map = new HashMap<>();
		int time = 0;
		for (int i = 0; i < mission.length; i++) {
			time++;
			if (!map.containsKey(mission[i])) {
				map.put(mission[i], time);
			}
			else {
				if (time - map.get(mission[i]) > N) {
					map.put(mission[i], time);
				}
				else {
					time = N + map.get(mission[i]) + 1;
					map.put(mission[i], time);
				}
			}
		}
		return time;
}

/* if can change the order of mission, how to minize the time (minize missions time) */
//
//always arrange the mission with the highest frequence
//if its time interval is smaller than k, find the second highest mission
//if all mission's time interval smaller than k, just add '*'
//using TreeSet to do this
'Time complexity: O(nlgn + n^2) --lgn is the add or remove operation of treeSet, Space complexity: O(n)'
public String minizTime(String input, int k) {
    if (input.length() <= 1) {
        return input;
    }
    StringBuilder result = new StringBuilder();
    TreeSet<Mission> missions = new TreeSet<>(new Comparator<Mission>() {
        @Override
        public int compare(Mission mis1, Mission mis2) {
        	// In treeSet, if the compare return 0 it will consider that they are same elements 
        	// and wont add this into set. So we need to consider the situation as two missions might have
        	// same frequence
            if (mis1.freq == mis2.freq) { 
                return mis1.name - mis2.name;
            }
            return mis2.freq - mis1.freq;
        }
    });
    HashMap<Character, Integer> missionToTime = new HashMap<>();
    HashMap<Character, Integer> missionToFreq = new HashMap<>();
    for (char mission : input.toCharArray()) {
        if (!missionToFreq.containsKey(mission)) {
            missionToFreq.put(mission, 1);
        }
        else {
            missionToFreq.put(mission, missionToFreq.get(mission) + 1);
        }
    }
    for (char mission : missionToFreq.keySet()) {
        missions.add(new Mission(mission, missionToFreq.get(mission)));
    }

    while (!missions.isEmpty()) {
        Mission selected = null;
        for (Mission mission : missions) {
            if (!missionToTime.containsKey(mission.name) || result.length() - missionToTime.get(mission.name) >= k) {
                selected = mission;
                missionToTime.put(mission.name, result.length() + 1);
                break;
            }
        }
        if (selected == null) {
            result.append('*');
        }
        else {
            result.append(selected.name);
            missions.remove(selected);
            selected.freq--;
            if (selected.freq > 0) {
                missions.add(selected);
            }
        }
    }
    return result.toString();
}

class Mission {
    public char name;
    public int freq;
    public Mission(char name, int freq) {
        this.name = name;
        this.freq = freq;
    }
}
