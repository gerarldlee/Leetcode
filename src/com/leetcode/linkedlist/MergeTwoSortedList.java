package com.leetcode.linkedlist;

public class MergeTwoSortedList {

    // Intuition
    // Create a sentinel that we will return, this is the proper list
    // We will create 2 pointers for the 2 list, and compare them one by one.
    // We attach the least value to the sentinel, and move the corresponding pointer of L1 and L2

    // time: o(n + m) space o(1)
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode sentinel = new ListNode(0);
        ListNode head = sentinel;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                head.next = l1;
                l1 = l1.next;
            }
            else {
                head.next = l2;
                l2 = l2.next;
            }
            head = head.next;
        }

        if (l1 != null) head.next = l1;
        else head.next = l2;

        return sentinel.next;
    }
}
