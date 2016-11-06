// 38. 题目是给定一个list of sorted integer arrays，要求找所有的数的median
/* e.g. [1,3,6,7,9], [2,4,8], [5], return 5
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=193898&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3090%5D%5Bvalue%5D%3D1%26searchoption%5B3090%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
*/
// Use a min Head which is priorityQueue to pop out the minimum number
// each time pop one, counter plus one util counter equals to the totalNumber / 2
// Time complexity: O(nlgK) -- lgk for sort in the priotiyqueue and pop out n /2 times, space complexity: O(k)
class MedianOfKSorted {
    public double findMedian(List<double[]> input) {
        PriorityQueue<Number> minNumber = new PriorityQueue<>(input.size(), new Comparator<Number>() {
            @Override
            public int compare(Number num1, Number num2) {
                if (num1.value > num2.value) {
                    return 1;
                }
                return -1;
            }
        });
        int total = 0;
        for (int i = 0; i < input.size(); i++) {
            minNumber.add(new Number(input.get(i)[0], i, 0));
            total += input.get(i).length;
        }
        double median = 0;
        double previous = 0;
        int count = 0;
        while (count <= total / 2) {
            Number min = minNumber.poll();
            count++;
            previous = median;
            median = min.value;
            if (input.get(min.arrayIndex).length - 1 > min.index ) {
                minNumber.add(new Number(input.get(min.arrayIndex)[min.index + 1], min.arrayIndex, min.index + 1));
            }
        }
        if (total % 2 == 0) {
            return (previous + median) / 2.0;
        }
        return median;
    }

    class Number {
        double value;
        int arrayIndex;
        int index;
        public Number(double value, int arrayIndex, int index) {
            this.value = value;
            this.arrayIndex = arrayIndex;
            this.index = index;
        }
    }
}
