/*
5. nearest Kth point
第一种：利用最大堆。新点距离小的话，就pop再加入新点。
第二种：Use quickSelect to find the Kth nearest point to the origin point	Time: O(n^2).
*/
// 题目要求距离某点第 Kth 近的点

// max heap
public class Solution {
	public List<Point> findNearestKPoints(Point[] points, int k) {
		List<Point> res = new ArrayList<>();
		if (k <= 0 || k > points.length)
			return res;
		PriorityQueue<Point> pq = new PriorityQueue<>(k, new Comparator<Point>() {
			public int compare(Point o1, Point o2) {
				return distance(o2) - distance(o1);
			}
		});
		for (Point point : points) {
			if (pq.size() < k)
				pq.add(point);
			else if (distance(pq.peek()) > distance(point)) {
				pq.poll();
				pq.add(point);
			}
		}
		while(!pq.isEmpty()) {
			res.add(pq.poll());
		}
		return res;
	}
	public int distance(Point point) {
        return point.x * point.x + point.y * point.y;
    }
}

// quickSelect
public class Solution {
	public class Point {
		int x;
		int y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public Point findNearestKthPoint(Point[] points, int k) {
		if (k > points.length || k == 0)
			throw new IllegalArgumentException("It is invalid");
		return quickSelect(points, 0, points.length - 1, k - 1);
	}
	public Point quickSelect(Point[] points, int left, int right, int k) {
		Random rand = new Random();
		int pivotIndex = 0;
		if (right != 0)
			pivotIndex = rand.nextInt(right) % (right - left + 1) + left;
		pivotIndex = partition(pivotIndex, points, left, right);
		if (pivotIndex == k)
			return points[k];
		else if (pivotIndex > k)
			return quickSelect(points, left, pivotIndex - 1, k);
		else
			return quickSelect(points, pivotIndex + 1, right, k);
	}
	public int partition(int pivotIndex, Point[] points, int left, int right) {
		int i = left;
		int j = right;
		Point temp = points[pivotIndex];
		int tempDistance = distance(temp);
		swap(points, left, pivotIndex);
		while (i < j) {
			while (j > i && distance(points[j]) >= tempDistance)
				j--;
			points[i] = points[j];
			while (j > i && distance(points[i]) <= tempDistance) 
				i++;
			points[j] = points[i];
		}
		points[i] = temp;
		return i;
	}
	public int distance(Point point) {
		return point.x * point.x + point.y * point.y;
	}
	public void swap(Point[] points, int i, int j) {
		Point temp = points[i];
		points[i] = points[j];
		points[j] = temp;
	}
}
