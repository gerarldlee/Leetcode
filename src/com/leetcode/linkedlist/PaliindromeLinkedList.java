package com.leetcode.linkedlist;

public class PaliindromeLinkedList {

    // intuition
    // get the size to get the middle, and odd or even, this will also get the tail, do not loose this pointer
    // reverse the node starting from middle up to the tail
    // compare the nodes one step from the tail and the head

    // time: o(2 1/2 n) or o(3n) worse, space : o(1)
    public boolean isPalindrome(ListNode head) {
        if (head == null) return false;
        int size = getSize(head);
        if (size < 2) return true;

        int mid = size / 2;
        ListNode midNode = head;
        System.out.printf("mid = %d, size = %d, max = %d\n", mid, size, size % 2 == 0 ? mid : mid + 1);
        for (int i=0; i < (size % 2 == 0 ? mid : mid + 1); i++) {
            midNode = midNode.next;
        }
        // now reverse it
        ListNode prev = null;   // make the midNode have a null tail too
        while (midNode != null) {
            ListNode nextNode = midNode.next;
            midNode.next = prev;
            prev = midNode;
            midNode = nextNode;
        }
        System.out.printf("%d\n", prev.val);
        // now the prev will be the head of the reversed 1/2 node, and there will be a tail 1/2 of the original size
        for (int i=0; i< mid; i++) {
            if (prev == null) break;
            System.out.printf("%d = %d\n", head.val, prev.val);
            if (head.val != prev.val) return false;
            head = head.next;
            prev = prev.next;
        }
        return true;
    }

    public int getSize(ListNode node) {
        int i=0;
        while (node != null) {
            node = node.next;
            i++;
        }
        return i;
    }
}
