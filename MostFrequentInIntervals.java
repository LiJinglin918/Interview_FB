// 84. Most Frequent In Intervals
/*
interval [startTime, stoptime)
给这样的一串区间 I1, I2......In  
找出 一个 time stamp  出现在interval的次数最多。
startTime <= t< stopTime 代表这个数在区间里面出现过。

example：  [1,3),  [2, 7),   [4,  8),   [5, 9)
5和6各出现了三次， 所以答案返回5，6。
*/

class MaxOverpal {
    public List<Integer> findMaxOverLapTime (Interval[] intervals) {
        List<Integer> result = new ArrayList<>();
        if (intervals.length == 0) {
            return result;
        }
        List<Point> points = new ArrayList<>();
        for (Interval interval : intervals) {
            points.add(new Point(interval.start, true));
            points.add(new Point(interval.end, false));
        }
        Collections.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                if (p1.time == p2.time) {
                    return p1.isStart ? 1 : 0;
                }
                return p1.time - p2.time;
            }
        });
        int max = 0;
        int number = 0;
        int start = 0;
        int end = 0;
        for (Point point : points) {
            if (point.isStart) {
                number++;
                if (number > max) {
                    max = number;
                    start = point.time;
                    end = point.time;
                }
            }
            else {
                if (number == max) {
                    end = point.time;
                }
                number--;
            }
        }
        for (int i = start; i < end; i++) {
            result.add(i);
        }
        return result;
    }

    private class Point {
        public int time;
        public boolean isStart;
        public Point(int time, boolean isStart) {
            this.time = time;
            this.isStart = isStart;
        }
    }
}
