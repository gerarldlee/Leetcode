package com.leetcode.linkedlist;

public class RemoveNodeFromListEnd {

    // brute solution
    // get the size of the node
    // size - n = the node we have to delete
    // iterate over the node again to delete the node
    // return the head
    // time: o(2n) and o(1)

    // using two pointer approach,
    // we could get the size faster and we know the number of nodes by the 1-step pointer

    // if we advance the faster pointer by n+1 and maintain that distance with the slower pointer.
    // once we reach null for the faster pointer, that means the slower pointer's next is the node to be remove
    // time: o(head size) space: o(1)
    public ListNode removeNthFromEnd(ListNode head, int n) {

        // we also need to create a sentinel node when edge case n == sie of the head and the head needs to be removed
        ListNode h = new ListNode(0);
        h.next = head;

        ListNode fast = h;  // we need to assign this to h, for the case where n == head size
        ListNode slow = h;

        for (int i = 0; i < n + 1; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next; // this will be null when head size == n or skips the head node and returns the next

        return h.next;
    }
}
