package com.leetcode.linkedlist;

/**
 * A singly link list
 */
public class MyLinkedList implements IFLinkList {

    ListNode sentinel;
    int size = 0;

    public MyLinkedList() {
        this.sentinel = new ListNode(0);
    }

    private ListNode getNode(int index) {
        ListNode curr = sentinel.next;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr;
    }

    // space o(1) time o(n)
    public int get(int index) {
        if (index >= size || index < 0) return -1;
        ListNode node = getNode(index);
        return node == null ? -1 : node.val;
    }

    // time: o(1) space o(1)
    public void addAtHead(int val) {
        ListNode node = new ListNode(val);
        node.next = sentinel.next;
        sentinel.next = node;
        size++;
    }

    // time: o(n) space: o(1)
    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    // time: o(n) worse space: o(1)
    public void addAtIndex(int index, int val) {
        if (index == 0) {
            addAtHead(val);
            return;
        }

        ListNode node = getNode(index-1);
        if (node != null) {
            ListNode newNode = new ListNode(val);
            newNode.next = node.next;
            node.next = newNode;
        }
        size++;
    }

    // time: o(n) worse, space: i(1)
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) return;
        ListNode node = sentinel;
        for (int i=0; i<index; i++) {
            node = node.next;
        }
        node.next = (node.next != null ? node.next.next : null);
        size--;
    }

    public void print() {
        ListNode curr = sentinel;
        for (int i=0; i < size; i++) {
            curr = curr.next;
            System.out.printf("%d = %d, ", i, curr.val);
        }
        System.out.println();
    }

    public static void main(String[] a) {
        MyLinkedList l = new MyLinkedList();
        l.addAtHead(1); //1
        l.addAtTail(3); //1,3
        l.addAtIndex(1,2);  //1,2,3
        l.print();
        System.out.println(l.get(1));   // should print 2
        l.deleteAtIndex(1); //1,3
        l.print();
        System.out.println(l.get(1));   // should print 3

        l.addAtHead(4); //4,1,3
        System.out.println(l.get(1));   // should print 1
        System.out.println(l.get(10));  // should print -1

        l.deleteAtIndex(4); //4,1,3
        System.out.println(l.get(2));   // should print 3
        l.addAtIndex(4,5);
        System.out.println(l.get(4));   // should print -1
        l.addAtIndex(3,5);
        System.out.println(l.get(3));   // should print 5
        System.out.println(l.get(5));   // should print -1
//
        System.out.println("-----");
        MyLinkedList l2 = new MyLinkedList();
        l2.addAtHead(7);    // 7
        l2.addAtHead(2);    // 2, 7
        l2.addAtHead(1);    //1,2,7
        l2.addAtIndex(3,0); //1,2,7,0
        l2.deleteAtIndex(2);    //1,2,0
        l2.addAtHead(6);    //6,1,2,0
        l2.addAtTail(4);    //6,1,2,0,4
        System.out.println(l2.get(4));  // 6,1,2,0,4   should print 4
        l2.addAtHead(4);    // 4,6,1,2,0,4
        l2.addAtIndex(5,0); //4,6,1,2,0,0,4
        l2.addAtHead(6);    // 6,4,6,1,2,0,0,4

        System.out.println("-----");
        MyLinkedList l3 = new MyLinkedList();
        l3.addAtIndex(1,0);
        System.out.println(l3.get(0));  // -1
    }

}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */