// 30. Reverse Print LinkedList
public ListNode reverseList(ListNode head) {
    if (head == null) {
        return head;
    }
    ListNode mover = head;
    ListNode last = null;
    while (mover != null) {
        ListNode next = mover.next;
        mover.next =last;
        last = mover;
        mover = next;
    }
    return last;
}

public ListNode reverseListRecursive(ListNode head) {
	if (head == null || head.next == null) {
        return head;
    }
    ListNode newHead = reverseList(head.next);
    head.next.next = head;
    head.next = null;
    return newHead;
}
