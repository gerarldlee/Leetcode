package com.leetcode.linkedlist;

import java.util.HashSet;
import java.util.Set;

public class Cycle {

    // hash set
    // time: o(n), space: o(n)
    public boolean hasCycle(ListNode head) {
        Set<ListNode> visited = new HashSet<>();
        while (head != null) {
            if (visited.contains(head)) return true;
            visited.add(head);
            head = head.next;
        }
        return false;
    }

    // time: o(n) or o(n + k), space o(1)
    public boolean hasCycleTwoPointers(ListNode node) {
        if (node == null) return false;

        ListNode slowPointer = node;
        ListNode fastPointer = node.next;

        while (slowPointer != fastPointer) {
            if (fastPointer == null || fastPointer.next == null) return false;

            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
        }
        return true;
    }

    // Gets the node where the cycle first begin
    // o(N + K) time, o(1) space
    public ListNode detectCycle(ListNode node) {
        ListNode slowPointer = node;
        ListNode fastPointer = node;
        while (fastPointer != null && fastPointer.next != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
            if (slowPointer == fastPointer) break;
        }
        // just to check if we got a non-cycle
        if (fastPointer == null || fastPointer.next == null) return null;

        ListNode headPointer = node;
        ListNode intersectionPointer = slowPointer;
        while (headPointer != intersectionPointer) {
            headPointer = headPointer.next;
            intersectionPointer = intersectionPointer.next;
        }
        return intersectionPointer;
    }

}
