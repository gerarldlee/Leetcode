package com.leetcode.linkedlist;

public class RotateList {

    /*
    Intuition:

    Approach 1
    1. Count the list size
    2. Iterate the list up to size-k
    3. Cut it, this size-k+1 will become the new head. Assign the tail of the size-k 's next to null to terminate
    4. Assign the tail since size-k+1 to the head

    Time: O(N) Space: O(N)

    Approach 2
    1. Iterate the list until the tail is reached, while counting the size at the same time
    2. Assign the tail;s next to the head, making it circular
    3. and continue iterating until the size-k is reached
    4. Assign null to the size-k's next. Return the size-k+1 as head

    This would work in case 1 where size > k
    What if K > size?

    5. Since the list is circular, we compute for the head's position by repeatedly subtracting the size from k.
    The remainder is the size-k'th position, and will become our tail. The next of that is our head.

    6. In the case of head == null, we just return null

    Time: O(N) Space: O(N)
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) return null;

        int count = 0;
        ListNode curr = head, prev = null;
        while (curr != null) {
            count++;
            prev = curr;
            curr = curr.next;
        }
        prev.next = head;

        if (k > count) k = (k % count);
        int newHeadCount = count - k;

        curr = head;
        while (newHeadCount > 0) {
            prev = curr;
            curr = curr.next;
            newHeadCount--;
        }
        prev.next = null;

        return curr;
    }
}
