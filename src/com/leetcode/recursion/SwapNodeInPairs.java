package com.leetcode.recursion;

import com.leetcode.linkedlist.ListNode;

public class SwapNodeInPairs {

    // Intuition
    // Recursive approach - given the head, determine for head size by 2's, head and head.next
    // anything less than this is invalid and cannot be swapped, so just return it
    // otherwise swap them. And then call the recursive function again giving the head.next.next
    // and head.next.next.next
    // time: n(n / 2) space: o(n/2)

    // We could achieve o(n/2) time and o(1) with iterative approach but recursion is the topic
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode sentinel = new ListNode(0);
        sentinel.next = recurse(head, head.next);
        return sentinel.next;
    }

    // The idea is to point node2.next <-- node1, and
    // node1.next <-- prev's node2.next || prev's node2.next.next
    private ListNode recurse(ListNode node1, ListNode node2) {
        ListNode tempNext = node2.next;
        node2.next = node1;
        if (tempNext != null && tempNext.next != null) {
            node1.next = recurse(tempNext, tempNext.next);
        }
        else {
            node1.next = tempNext;
        }
        return node2;
    }
}
