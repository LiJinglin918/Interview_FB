// 77. Print reverse LinkedList
/*
逆序输出一个单链表，要求空间复杂度为O(log n)，不能修改链表结构（也就是不可以reverse链表，然后再reverse回去）
最后做的大概就是每次走到终点，递归右半段，然后再递归左半段，最后给他画颗递归树证明下复杂度就行了，他也认可了。
不过看起来这个做法相对奇葩一点，他看我画的还想了一会，似乎这不是他原来设想的方案吧，但也确实没毛病。
'Time complexity: O(nlgn) space complexity: O(lgn)'
*/

class ReversePrint {
    public void print(ListNode head) {
        ListNode mover = head;
        int length = 0;
        while (mover != null) {
            mover = mover.next;
            length++;
        }
        helper(head, length);
    }

    private void helper(ListNode head, int length) {
        if (head == null) {
            return;
        }
        if (length == 1) {
            System.out.println(head.val);
            return;
        }
        int count = 0;
        ListNode mover = head;
        while (count < length / 2) {
            mover = mover.next;
            count++;
        }
        helper(mover, length - length / 2);
        helper(head, length / 2);
    }
}
