// https://segmentfault.com/a/1190000003894670
/*
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), 
determine if a person could attend all meetings.
For example, Given [[0, 30],[5, 10],[15, 20]], return false.
*/
/*
复杂度
时间 O(NlogN) 空间 O(1)
思路
这题和Merge Intervals很像，我们按开始时间把这些Interval都给排序后，就挨个检查是否有冲突就行了。
有冲突的定义是开始时间小于之前最晚的结束时间。这里之前最晚的结束时间不一定是上一个的结束时间，所以我们更新的时候要取最大值。
*/

public class Solution {
    public boolean canAttendMeetings(Interval[] intervals) {
        if(intervals == null || intervals.length == 0) return true;
        Arrays.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval i1, Interval i2){
                return i1.start - i2.start;
            }
        });
        int end = intervals[0].end;
        // 检查每一个Interval
        for(int i = 1; i < intervals.length; i++){
            // 如果Interval的开始时间小于之前最晚的结束时间，就返回假
            if(intervals[i].start < end) return false;
            end = Math.max(end, intervals[i].end);
        }
        return true;
    }
}

/*
Meeting Rooms II
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), 
find the minimum number of conference rooms required.
For example, Given [[0, 30],[5, 10],[15, 20]], return 2.
*/

/*
贪心法
复杂度
时间 O(NlogN) 空间 O(1)

思路
这题的思路和Rearrange array to certain distance很像，我们要用贪心法，即从第一个时间段开始，选择下一个最近不冲突的时间段，
再选择下一个最近不冲突的时间段，直到没有更多。然后如果有剩余时间段，开始为第二个房间安排，选择最早的时间段，再选择下一个最近不冲突的时间段，
直到没有更多，如果还有剩余时间段，则开辟第三个房间，以此类推。这里的技巧是我们不一定要遍历这么多遍，我们实际上可以一次遍历的时候就记录下，
比如第一个时间段我们放入房间1，然后第二个时间段，如果和房间1的结束时间不冲突，就放入房间1，否则开辟一个房间2。
然后第三个时间段，如果和房间1或者房间2的结束时间不冲突，就放入房间1或者2，否则开辟一个房间3，依次类推，最后统计开辟了多少房间。
对于每个房间，我们只要记录其结束时间就行了，这里我们查找不冲突房间时，只要找结束时间最早的那个房间。
这里还有一个技巧，如果我们把这些房间当作List来管理，每次查询需要O(N)时间，如果我们用堆来管理，可以用logN时间找到时间最早结束的房间。
*/






/*
 * 1. sort the intervals according to the start
 * 2. use priorityQueue to store the end times of the intervals
 * 3. If the current end time greater than the earliest end time (pq.peek())
 * update the earliest time (pq.poll()). 
 * 4. If smaller, 表示需要新的房间，则加入PQ, （因为最后记录pq.size()为房间数）
 */

import java.util.*;

public class MeetingRoomsII {
	public int minMeetingRooms(Interval[] intervals) {
		if (intervals == null || intervals.length == 0)
			return 0;
		Arrays.sort(intervals, new Comparator<Interval>() {
			public int compare(Interval o1, Interval o2) {
				return o1.start - o2.start;
			}
		});
		
		// Use the PriorityQueue to store the end times of the intervals
		PriorityQueue<Integer> endTime = new PriorityQueue<>();
		endTime.add(intervals[0].end);
		
		for (int i = 1; i < intervals.length; i++) {
			
			// If the current end time >= previous earliest end time, update
			if (intervals[i].start >= endTime.peek()) {
				endTime.poll();
			}
			endTime.add(intervals[i].end);
		}
		
		// pq.size() is the number of rooms needed. 
		return endTime.size();
	}
	
	public class Interval {
		int start;
		int end;
		Interval(int s, int e) {
			start = s;
			end = e;
		}
	}
}

