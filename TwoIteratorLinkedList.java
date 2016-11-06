// 78. 有序双链表合并，不过给的是两个iterator，让实现一个类，生成下一个.
class SortedIterator{
    public SortedIterator(Iterator a, Iterator b);
    public boolean hasNext();
    public int next();
}


class SortedIterator {
    private Pair pairA;
    private Pair pairB;
    public SortedIterator(Iterator<Integer> a, Iterator<Integer> b) {
        if (a.hasNext()) {
            pairA = new Pair(a.next(), a);
        }
        else {
            pairA = new Pair(null, a);
        }
        if (b.hasNext()) {
            pairB = new Pair(b.next(), b);
        }
        else {
            pairB = new Pair(null, b);
        }
    }
    public boolean hasNext() {
        return pairA.value != null || pairB.value != null;
    }

    public int next() {
        if (pairA.value == null) {
            return helper(pairB);
        }
        else if (pairB.value == null) {
            return helper(pairA);
        }
        Integer result = null;
        if (pairA.value > pairB.value) {
            result = helper(pairB);
        }
        else {
            result = helper(pairA);
        }
        return result;
    }

    private Integer helper(Pair pair) {
        int result = pair.value;
        if (pair.iterator.hasNext()) {
            pair.value = pair.iterator.next();
        }
        else {
            pair.value = null;
        }
        return result;
    }

    private class Pair {
        public Integer value;
        public Iterator<Integer> iterator;
        public Pair(Integer val, Iterator<Integer> iterator) {
            this.value = val;
            this.iterator = iterator;
        }
    }
}
