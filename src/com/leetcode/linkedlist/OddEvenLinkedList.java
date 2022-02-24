package com.leetcode.linkedlist;

public class OddEvenLinkedList {

    // Intuition:
    // We create 1 general pointer, 1 odd pointer, 2 even pointers
    // We point them all at the head, except for the 2nd even pointer
    // We iterate the nodes, putting the odd pointer pointing to the next 2 nodes
    // same for even pointer
    // Once we reached the tail, we point the tail to the 2nd even pointer

    // time: o(n), space o(1)
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return head;
        // we should start this way because the head and head.next is already arranged
        ListNode odd = head;
        ListNode even = head.next, evenHead = head.next;
        ListNode generalPointer = head.next.next;
        int i = 3;
        while (generalPointer != null) {
            if (i % 2 == 0) {
                even.next = generalPointer;  // to remove cycle
                even = even.next;
            }
            else {
                odd.next = generalPointer; // we point the even head
                odd = odd.next;
            }
            if (generalPointer.next == null) {
                even.next = null;
                odd.next = evenHead;
                break;
            }
            generalPointer = generalPointer.next;
            i++;
        }
        return head;
    }


}
