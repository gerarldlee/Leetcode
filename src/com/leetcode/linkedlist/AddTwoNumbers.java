package com.leetcode.linkedlist;

public class AddTwoNumbers {


    // Intuition

//    We will iterate over l1 and l2 nodes at the same time.  We will add their values together.
    // If we divide the value by 10, we will get the carry over for the next iteration.
    // This remaining value will the be put and created / added as a next of a sentinel node.
    // It does not matter if l1 or l2 is finished; whichever has elements, we will add the carry to the
    // current value, step by step.

    // time : o(max(l1, l2)) space: o(max(l1,l2)) maybe + 1 node for the carry
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode sentinel = new ListNode(0);
        ListNode curr = sentinel;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int val = carry;
            carry = 0;
            if (l1 != null) { val += l1.val; l1 = l1.next; }
            if (l2 != null) { val += l2.val; l2 = l2.next; }
            if (val > 9) {
                carry = val / 10;
                val -= 10;
            }
            curr.next = new ListNode(val);
            curr = curr.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return sentinel.next;
    }
}
