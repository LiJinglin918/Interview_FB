// 37. Merge K Sorted LinkedList
/*
Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
// PQ: 一个基于优先级堆的无界优先级队列。优先级队列的元素按照其自然顺序进行排序，或者根据构造队列时提供的 Comparator 进行排序
// follow up: 给的不是list, 而是每个list的iterator.

public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;
        if (lists.length == 1)
            return lists[0];
        PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {
            public int compare(ListNode node1, ListNode node2) {
                return node1.val - node2.val;
            }
        });
        ListNode dummyHead = new ListNode(0);
        ListNode p = dummyHead;
        for (ListNode node : lists) {
            if (node != null)
                pq.add(node);
        }
        while (!pq.isEmpty()) {
            ListNode listNext = pq.poll();          // the smallest element
            p.next = listNext;                      
            p = p.next;                             
            listNext = listNext.next;               // point to the ListNode next to the smallest element
            if (listNext != null)
                pq.add(listNext);
        }
        p.next = null;
        return dummyHead.next;
    }
}

class Merge {
    public List<Integer> mergeTwoList(List<Integer> list1, List<Integer> list2) {
        List<Integer> result = new ArrayList<>();
        int index1 = 0;
        int index2 = 0;
        while (index1 < list1.size() && index2 < list2.size()) {
            int number1 = list1.get(index1);
            int number2 = list2.get(index2);
            if (number1 <= number2) {
                result.add(number1);
                index1++;
            }
            else {
                result.add(number2);
                index2++;
            }
        }
        while (index1 < list1.size()) {
            result.add(list1.get(index1++));
        }
        while (index2 < list2.size()) {
            result.add(list2.get(index2++));
        }
        return result;
    }

    public List<Integer> mergeKList(List<List<Integer>> input) {
        PriorityQueue<Number> minNumber = new PriorityQueue<>(input.size(), new Comparator<Number>() {
            @Override
            public int compare(Number num1, Number num2) {
                return num1.value - num2.value;
            }
        });
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).size() == 0) {
                continue;
            }
            minNumber.add(new Number(input.get(i).get(0), 0, i));
        }
        while (!minNumber.isEmpty()) {
            Number min = minNumber.poll();
            result.add(min.value);
            if (min.index < input.get(min.listIndex).size() - 1) {
                minNumber.add(new Number(input.get(min.listIndex).get(min.index + 1), min.index + 1, min.listIndex));
            }
        }
        return result;

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

	// 传入的是每个list的iterator
    public List<Integer> mergeKListWithIterator(List<Iterator<Integer>> input) {
        PriorityQueue<Number> minNumber = new PriorityQueue<>(input.size(), new Comparator<Number>() {
            @Override
            public int compare(Number num1, Number num2) {
                return num1.value - num2.value;
            }
        });
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).hasNext()) {
                minNumber.add(new Number(input.get(i).next(), 0, i));
            }
        }
        while (!minNumber.isEmpty()) {
            Number min = minNumber.poll();
            if (input.get(min.listIndex).hasNext()) {
                minNumber.add(new Number(input.get(min.listIndex).next(), 0, min.listIndex));
            }
            result.add(min.value);
        }
        return result;
    }
}
