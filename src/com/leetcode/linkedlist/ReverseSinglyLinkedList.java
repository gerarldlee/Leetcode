package com.leetcode.linkedlist;

public class ReverseSinglyLinkedList {

    // iterative approach
    // time: o(n), space o(1)
    public ListNode reverseList(ListNode head) {
        ListNode currNode = head;
        ListNode prevNode = null;

        while(currNode != null) {
            ListNode nextNode = currNode.next;
            currNode.next = prevNode;
            prevNode = currNode;
            currNode = nextNode;
        }
        return prevNode;
    }

    // recursive approach
    public ListNode reverseListRecursive(ListNode head) {
        if (head == null) return null;
        recursive(head);
        head.next = null;   // terminal, tail
        return newHead;
    }
    ListNode newHead = null;
    private ListNode recursive(ListNode node) {
        if (node != null) newHead = node;
        if (node.next != null) {
            ListNode next = recursive(node.next);
            next.next = node;
        }
        return node;
    }

}
