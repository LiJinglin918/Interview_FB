// 40. merge Interval from sorted arrays
// Use minHeap to poll out the interval with smallest start time
// Check if it has next interval to push into heap in its list
// Then check heap.peek(), which should always with the smallest start time
// check if heap.peek().start <= curInterval.end
// Then we need to merge. poll out this interval and merge
// curInterval.end = Math.max(curInterval.end, heap.peek().end)
// Keep doing the operation above util heap.peek().start > curInterval.end
// Add curInterval to result.
// Time complexity: O(nlgk) -- k lists of interval, total number of interval is n
// Space complexity: O(k) -- space of min heap'
class MergeKthSortedIntervals {
    public List<Interval> merge(Interval[][] intervals) {
        List<Interval> result = new ArrayList<>();
        if (intervals.length == 0) {
            return result;
        }
        PriorityQueue<IntervalContainer> minInterval = new PriorityQueue<>(intervals.length, new Comparator<IntervalContainer>() {
            @Override
            public int compare(IntervalContainer inter1, IntervalContainer inter2) {
                return inter1.interval.start - inter2.interval.start;
            }
        });
        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i].length > 0) {
                minInterval.add(new IntervalContainer(intervals[i][0], 0, i));
            }
        }
        while (!minInterval.isEmpty()) {
            IntervalContainer curContainer = minInterval.poll();
            addNextToMinInterval(minInterval, curContainer, intervals);
            while (!minInterval.isEmpty() && minInterval.peek().interval.start <= curContainer.interval.end) {
                IntervalContainer nextContainer = minInterval.poll();
                addNextToMinInterval(minInterval, nextContainer, intervals);
                curContainer.interval.end = Math.max(curContainer.interval.end, nextContainer.interval.end);
            }
            result.add(new Interval(curContainer.interval.start, curContainer.interval.end));
        }
        return result;
    }
    private void addNextToMinInterval(PriorityQueue<IntervalContainer> minInterval, IntervalContainer container, Interval[][] intervals) {
        if (intervals[container.listIndex].length - 1 > container.index) {
            minInterval.add(
                    new IntervalContainer(
                            intervals[container.listIndex][container.index + 1],
                            container.index + 1,
                            container.listIndex));
        }
    }

}

class IntervalContainer {
    public Interval interval;
    public int index;
    public int listIndex;
    public IntervalContainer(Interval interval, int index, int listIndex) {
        this.interval = interval;
        this.index = index;
        this.listIndex = listIndex;
    }
}

class Interval {
    public int start;
    public int end;
    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
