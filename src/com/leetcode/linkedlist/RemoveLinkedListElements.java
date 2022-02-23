package com.leetcode.linkedlist;

public class RemoveLinkedListElements {

    // time: o(n) space: o(1)
    public ListNode removeElements(ListNode head, int val) {
        if (val == 0) return head;

        ListNode sentinel = new ListNode(0);
        sentinel.next = head;

        ListNode prev = sentinel;
        ListNode curr = sentinel.next;

        while (curr != null) {
            if (curr.val == val) {
                // delete ptr2
                prev.next = curr.next;
                curr = prev.next;
            }
            else {
                prev = prev.next;
                curr = curr.next;
            }
        }

        return sentinel.next;
    }
}
