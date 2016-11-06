// 64. Given k sorted lists of O(n) integers each, implement an iterator that will yield all elements in sorted order。
// http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=190778&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3090%5D%5Bvalue%5D%3D1%26searchoption%5B3090%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

class MergeToIterator implements Iterator<Integer>{
    PriorityQueue<Number> minNumber;
    List<List<Integer>> lists;
    public MergeToIterator(List<List<Integer>> input) {
        this.minNumber = new PriorityQueue<>(input.size(), new Comparator<Number>() {
            @Override
            public int compare(Number num1, Number num2) {
                return num1.value - num2.value;
            }
        });
        this.lists = input;
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).size() == 0) {
                continue;
            }
            minNumber.add(new Number(input.get(i).get(0), 0, i));
        }
    }

    @Override
    public boolean hasNext() {
        return !minNumber.isEmpty();
    }

    @Override
    public Integer next() {
        Number min = minNumber.poll();
        if (lists.get(min.listIndex).size() - 1 > min.index) {
            minNumber.add(new Number(lists.get(min.listIndex).get(min.index + 1), min.index + 1, min.listIndex));
        }
        return min.value;
    }

    class Number {
        int value;
        int index;
        int listIndex;
        public Number(int value, int index, int listIndex) {
            this.value = value;
            this.index = index;
            this.listIndex = listIndex;
        }
    }
}
