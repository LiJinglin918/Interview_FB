// merge intervals (one list/two list)
import java.util.*;
public class MergeIntervals {
	public List<Interval> merge(List<Interval> intervals) {
		List<Interval> res = new ArrayList<>();
		if (intervals == null || intervals.size() == 0) {
			return res;
		}
		Collections.sort(intervals, new Comparator<Interval>() {
			public int compare(Interval o1, Interval o2) {
				return o1.start - o2.start;
			}
		});
		
		Interval a = intervals.get(0);
		for (int i = 1; i < intervals.size(); i++) {
			Interval b = intervals.get(i);
			if (a.end >= b.start) {
				a = new Interval(Math.min(a.start, b.start), Math.max(a.end, b.end));
			}
			else {
				res.add(a);
				a = b;
			}
		}
		res.add(a);
		return res;
	}
	
/*---- merge 两个interval ----------------------------------------------------------------*/
	
	/*
	 * 把两个interval分别sort
	 * 利用两个指针，小的先存在结果中。
	 */
	
	public List<Interval> mergeTwoInterval(List<Interval> list1, List<Interval> list2) {
		if (list1 == null || list1.size() == 0)
			return list2;
		if (list2 == null || list2.size() == 0) 
			return list1;
		Collections.sort(list1, new myComparator());
		Collections.sort(list2, new myComparator());
		
		List<Interval> res = new ArrayList<>();
		int i = 0; 
		int j = 0;
		
		// Get the first interval in the res (smallest)
		Interval prev = null;
		if (list1.get(0).start < list2.get(0).start) {
			prev = list1.get(0);
			i++;
		}
		else {
			prev = list2.get(0);
			j++;
		}
		
		while (i < list1.size() || j < list2.size()) {
			if (j == list2.size() || i < list1.size() && list1.get(i).start < list2.get(j).start) {
				
				// merge prev with i
				if (prev.end < list1.get(i).start) {
					res.add(prev);
					prev = list1.get(i);
				}
				else {
					prev.end = Math.max(prev.end, list1.get(i).end);
				}
				i++;
			}
			else {
				
				// merge prev with j
				if (prev.end < list2.get(j).start) {
					
					// the prev has been updated(max of end), so add it
					res.add(prev);
					prev = list2.get(j);
				}
				else {
					prev.end = Math.max(prev.end, list2.get(j).end);
				}
				j++;
			}
		}
		
		// add the last interval
		res.add(prev);
		return res;
	}
	public class myComparator implements Comparator<Interval> {
		public int compare(Interval o1, Interval o2) {
			return o1.start - o2.start;
		}
	}
}
