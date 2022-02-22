package com.leetcode.linkedlist;


public class MyDoublyLinkList implements IFLinkList {

    // sentinels
    ListNode head, tail;
    int size;

    public MyDoublyLinkList() {
        size=0;
        head = new ListNode(0);
        tail = new ListNode(0);
        head.next = tail;
        tail.prev = head;
    }

    // time: o(1), space: o(1)
    private void addNode(ListNode previous, ListNode next, int val) {
        ListNode newNode = new ListNode(val);
        // P <-> New <-> N
        newNode.next = next;
        newNode.prev = previous;
        previous.next = newNode;
        next.prev = newNode;
        size++;
    }

    // time: o(1) space: o(1)
    private void deleteNode(ListNode node) {
        ListNode prev = node.prev;
        ListNode next = node.next;
        prev.next = next;
        next.prev = prev;
        size--;
    }

    // time: o(index) or o(size - index), space: o(1)
    private ListNode getNode(int index) {
        if (index == 0) return head;
        else if (index == size) return tail.prev;

        // forward from head or backwards from tail
        boolean direction = index <= size - index;
        ListNode node = direction ? head : tail;
        if (direction)
            for (int i=0; i<index; i++)
                node = node.next;
        else
            for (int i=0; i<(size-index)+1; i++)
                node = node.prev;
        return node;
    }

    @Override
    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    @Override
    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    @Override
    public void addAtIndex(int index, int val) {
        if (index > size || index < 0) return;
        ListNode current = getNode(index);
        addNode(current, current.next, val);
    }

    @Override
    public void deleteAtIndex(int index) {
        if (index >= size || index < 0) return;
        ListNode current = getNode(index+1);
        deleteNode(current);
    }

    @Override
    public int get(int index) {
        if (index >= size || index < 0) return -1;
        return getNode(index+1).value;
    }

    public void print() {
        ListNode curr = head;
        for (int i=0; i < size; i++) {
            curr = curr.next;
            System.out.printf("%d = %d, ", i, curr.value);
        }
        System.out.println();
    }

    public static void main(String[] a) {
        MyDoublyLinkList l = new MyDoublyLinkList();

        l.addAtTail(1); // 0 index
        l.addAtTail(2); // 1
        l.addAtTail(3); // 2
        System.out.println(l.get(3));   // should print -1, because index 3 is not valid
        System.out.println(l.get(2));       // should print 3
        l.deleteAtIndex(2);
        System.out.println(l.get(2));   // should print -1 because index 2 has beel deleted

        l = new MyDoublyLinkList();
        l.addAtHead(1); //1
        l.addAtTail(3); //1,3
        l.addAtIndex(1,2);  //1,2,3
        l.print();
        System.out.println(l.get(0));   // should print 1
        System.out.println(l.get(1));   // should print 2
        l.deleteAtIndex(1); //1,3
        l.print();
        System.out.println(l.get(1));   // should print 3

        l.addAtHead(4); //4,1,3
        System.out.println(l.get(1));   // should print 1
        System.out.println(l.get(10));  // should print -1
        l.print();

        l.deleteAtIndex(4); //4,1,3
        System.out.println(l.get(2));   // should print 3
        l.addAtIndex(4,5);
        System.out.println(l.get(4));   // should print -1
        l.addAtIndex(3,5);
        System.out.println(l.get(3));   // should print 5
        System.out.println(l.get(5));   // should print -1

        System.out.println("-----");
        MyDoublyLinkList l2 = new MyDoublyLinkList();
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
        l2.print();

        // 1
        // 1,3
        // 1,2,3
        // 1,2,3 -- 2
        // 2,3
        // 2,3 -- 2

        System.out.println("-------");
        l2 = new MyDoublyLinkList();
        l2.addAtIndex(1,0);
        System.out.println(l2.get(0));  // -1
        l2.print();
    }
}
