// 54. 给一个partial sorted的数组比如1，3，5，2，4，6，8，10，20，30，11，12，13，数组有N个数，分为M个segment，N>>>M，要求输出排序后的数组。第一反应就是merge k sorted list，按照那个写了下，问了问time 和space 的complexity
merge k list(use M pointer to record the position of the begin of the sorted segment)
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=192644&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3090%5D%5Bvalue%5D%3D1%26searchoption%5B3090%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

// Use a class Number to record the cur number and index and end index of a segment
// For instance, we have {1, 3, 9, 2, 6, 8}
// Then we have a Number(value = 1, index = 0, endIndex = 2) and Number(value = 2, index = 3, endIndex = 5)
// Traverse through the array find the start and end of every segment
// store the start number of every segment into a min heap.
// So every time we use heap, which pop out the samllest number, and we check its index
// if the index is samller than the endIndex, which means this segment is not over, we 
// need to put the next number of the segment into heap.
/* Time complexity: O(nlgm) -n is the number of numbers in array -m is the number of segment
 Space complexity: O(n) */

class SortPartialSortedArray {
    public int[] sortArray(int[] input) {
        if (input.length == 0) {
            return new int[]{};
        }
        int[] sortedResult = new int[input.length];
        int mover = 0;
        int start = 0;
        PriorityQueue<Number> minNumber = new PriorityQueue<>(new Comparator<Number>() {
            @Override
            public int compare(Number num1, Number num2) {
                return num1.value - num2.value;
            }
        });
        for (int i = 0; i < input.length; i++) {
            if (i > 0 && input[i] < input[i - 1]) {
                minNumber.add(new Number(input[start], start, i - 1));
                start = i;
            }
        }
        minNumber.add(new Number(input[start], start, input.length - 1));
        while (!minNumber.isEmpty()) {
            Number min = minNumber.poll();
            sortedResult[mover++] = min.value;
            if (min.index < min.endIndex) {
                minNumber.add(new Number(input[min.index + 1], min.index + 1, min.endIndex));
            }
        }
        return sortedResult;
    }

    class Number {
        int value;
        int index;
        int endIndex;
        public Number(int value, int index, int endIndex) {
            this.value = value;
            this.index = index;
            this.endIndex = endIndex;
        }
    }
}
