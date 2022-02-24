package com.leetcode.queuestack;

import java.util.Arrays;

public class DesignCircularQueue {

    int capacity = 0;
    int[] array = null;

    // pointer to head, tail. assume 0-index
    int head_idx = 0;
    int tail_idx = 0;

    int size = 0;

    public DesignCircularQueue(int k) {
        this.capacity = k;
        array = new int[k];
    }

    // time: o(1), space o(1) for this method. but o(n) for all methods since we already allocated n capacity
    public boolean enQueue(int value) {
        if (size == capacity) return false;
        array[tail_idx] = value;
        tail_idx++;
        if (tail_idx > array.length-1) tail_idx = 0;
        size++;
        return true;
    }

    // time: o(1), space o(1) for this method. but o(n) for all methods since we already allocated n capacity
    public boolean deQueue() {
        if (size == 0) return false;
        head_idx++;
        if (head_idx > array.length-1) head_idx = 0;
        size--;
        return true;
    }

    // time: o(1) space: o(1)
    public int Front() {
        if (size == 0) return -1;
        return array[head_idx];
    }

    // time: o(1) space: o(1)
    public int Rear() {
        if (size == 0) return -1;
        int index = (tail_idx-1 < 0 ? array.length-1: tail_idx-1);
        return array[index];
    }

    // time: o(1) space: o(1)
    public boolean isEmpty() {
        return size == 0;
    }

    // time: o(1) space: o(1)
    public boolean isFull() {
        return size == capacity;
    }

    public void print() {
        System.out.println(Arrays.toString(array));
        System.out.println(capacity + ", size=" + size + ", head=" + head_idx + ", tail=" + tail_idx);
    }

    public static void main(String[] a) {
        DesignCircularQueue d = new DesignCircularQueue(3);
        d.enQueue(1);
        d.print();
        d.enQueue(2);
        d.print();
        d.enQueue(3);
        d.print();
        d.enQueue(4);
        d.print();
        System.out.println(d.Rear());
        System.out.println(d.isFull());
        d.deQueue();
        d.print();
        d.enQueue(4);
        d.print();
        System.out.println(d.Rear());
        System.out.println(d.Front());

        System.out.println("----------");
        d = new DesignCircularQueue(6);
        d.enQueue(6);
        System.out.println(d.Rear());
        System.out.println(d.Rear());
        d.print();
        d.deQueue();
        d.print();
        d.enQueue(5);
        d.print();
        System.out.println(d.Rear());
        d.deQueue();
        System.out.println(d.Front());
        d.deQueue();
        d.print();
        d.deQueue();
        d.print();
        d.deQueue();
        d.print();

    }
}
