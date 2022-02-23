package com.leetcode.linkedlist;

import java.util.HashSet;
import java.util.Set;

public class IntersectionTwoLinkedList {

    // intuition:
    // we will use a hashtable, iterate through each node and save the address of each node to the hashtable
    // we will then compare them, if they are equal, then they intersect
    // time: o(max(n,m)) worse, space o(n + m) worse
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        Set<ListNode> visited = new HashSet<>();
        ListNode ptr1 = headA;
        ListNode ptr2 = headB;

        while (ptr1 != null || ptr2 != null) {
            if (visited.contains(ptr1)) break;
            if (ptr1 != null) {
                visited.add(ptr1);
                ptr1 = ptr1.next;
            }
            if (visited.contains(ptr2)) break;
            if (ptr2 != null) {
                visited.add(ptr2);
                ptr2 = ptr2.next;
            }
        }
        if (visited.contains(ptr1)) return ptr1;
        if (visited.contains(ptr2)) return ptr2;
        return null;
    }

    // two pointer approach
    // time: o(2n + 2m) worse, space: o(1)
    // iterates through headA the first, and aligns it with headB the second iteration
    // same goes for headB
    // when they are both aligned, in the 2nd iteration, then they will get the intersection
    // or when theres no intersection, both will finish the headA,B with both equalling null
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        ListNode pt1 = headA, pt2 = headB;
        while (pt1 != pt2) {
            if (pt1 != null)
                pt1 = pt1.next;
            else
                pt1 = headB;
            if (pt2 != null)
                pt2 = pt2.next;
            else
                pt2 = headA;
        }
        return pt1;
    }

    private int getSize(ListNode node) {
        int n=0;
        while (node != null) {
            n++;
            node = node.next;
        }
        return n;
    }
}
