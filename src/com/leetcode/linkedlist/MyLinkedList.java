package com.leetcode.linkedlist;

/**
 * A singly link list
 */
public class MyLinkedList implements IFLinkList {

    int val = 0;
    MyLinkedList next;
    MyLinkedList head;

    public MyLinkedList() {
        this.next = null;
        this.head = null;
    }

    public MyLinkedList(int val) {
        super();
        this.val = val;
    }

    // space o(1) time o(n)
    public int get(int index) {
        if (this.head == null) return -1;
        int i=0;
        MyLinkedList curr = this.head;
        while (i < index && curr != null) {
            curr = curr.next;
            i++;
        }
        return (curr != null ? curr.val : -1);
    }

    // time: o(1) space o(1)
    public void addAtHead(int val) {
        MyLinkedList newHead = new MyLinkedList(val);
        if (this.head != null) {
            newHead.next = this.head;
        }
        this.head = newHead;
    }

    // time: o(n) space: o(1)
    public void addAtTail(int val) {
        if (this.head == null) {
            addAtHead(val);
            return;
        }

        MyLinkedList curr = this.head, prev = null;
        while (curr != null) {
            prev = curr;
            curr = curr.next;
        }
        MyLinkedList newItem = new MyLinkedList(val);
        prev.next = newItem;
    }

    // time: o(n) worse space: o(1)
    public void addAtIndex(int index, int val) {
        if (index == 0) {
            addAtHead(val);
        }

        int i=0;
        MyLinkedList next = this.head, curr = null;
        while (i < index && next != null) {
            curr = next;
            next = next.next;
            i++;
        }
        // even when curr == null, add it as the last index when i == index
        if (i == index) {
            MyLinkedList item = new MyLinkedList(val);
            if (curr != null) {
                item.next = curr.next;
                curr.next = item;
            }
        }
    }

    // time: o(n) worse, space: i(1)
    public void deleteAtIndex(int index) {
        if (this.head == null) return;
        if (index == 0) {
            this.head = this.head.next;
        }

        int i=0;
        MyLinkedList next = this.head, curr = null;
        while (i < index && next != null) {
            curr = next;
            next = next.next;
            i++;
        }
        if (i == index && curr != null && next != null) {
            curr.next = next.next;
        }
    }

    public static void main(String[] a) {
//        MyLinkedList l = new MyLinkedList();
//        l.addAtHead(1); //1
//        l.addAtTail(3); //1,3
//        l.addAtIndex(1,2);  //1,2,3
//        System.out.println(l.get(1));   // should print 2
//        l.deleteAtIndex(1); //1,3
//        System.out.println(l.get(1));   // should print 3
//
//        l.addAtHead(4); //4,1,3
//        System.out.println(l.get(1));   // should print 1
//        System.out.println(l.get(10));  // should print -1
//
//        l.deleteAtIndex(4); //4,1,3
//        System.out.println(l.get(2));   // should print 3
//        l.addAtIndex(4,5);
//        System.out.println(l.get(4));   // should print -1
//        l.addAtIndex(3,5);
//        System.out.println(l.get(3));   // should print 5
//        System.out.println(l.get(5));   // should print -1
//
//        System.out.println("-----");
//        MyLinkedList l2 = new MyLinkedList();
//        l2.addAtHead(7);    // 7
//        l2.addAtHead(2);    // 2, 7
//        l2.addAtHead(1);    //1,2,7
//        l2.addAtIndex(3,0); //1,2,7,0
//        l2.deleteAtIndex(2);    //1,2,0
//        l2.addAtHead(6);    //6,1,2,0
//        l2.addAtTail(4);    //6,1,2,0,4
//        System.out.println(l2.get(4));  // 6,1,2,0,4   should print 4
//        l2.addAtHead(4);    // 4,6,1,2,0,4
//        l2.addAtIndex(5,0); //4,6,1,2,0,0,4
//        l2.addAtHead(6);    // 6,4,6,1,2,0,0,4


        // 1
        // 1,3
        // 1,2,3
        // 1,2,3 -- 2
        // 2,3
        // 2,3 -- 2


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