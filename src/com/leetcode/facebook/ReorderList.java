package com.leetcode.facebook;

public class ReorderList {

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) {this.val = val; }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void reorderList(ListNode head) {

        // get the size of the list node by iterating
        int n = 1;
        ListNode cur = head;
        while (cur.next != null) {
            n++;
            cur = cur.next;
        }

        // form an array wiht all the singly list next addresses
        ListNode[] arr = new ListNode[n];
        arr[0] = head;
        for (int i=1; i<n; i++) {
            arr[i] = arr[i-1].next;
        }

        ListNode current = head;
        for (int i=0; i < (n/2)+1; i++) {
            // weve reached the limit
            if (i+1 >= (n/2)+1) {
                current.next = null;
                break;
            }

            ListNode tmp = arr[i].next;
            current.next = arr[n-i-1];
            arr[n-i-1].next = tmp;
            current = tmp;
        }

    }

    public static void main(String[] a) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
//        head.next.next.next.next = new ListNode(5);

        reorderList(head);
    }
}
