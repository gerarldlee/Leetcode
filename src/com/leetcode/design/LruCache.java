package com.leetcode.design;

import java.util.HashMap;

public class LruCache {

    class Node {
        int key;
        int value;
        Node next;
        Node prev;
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    HashMap<Integer, Node> map = null;
    Node mru = null;
    Node lru = null;

    int size = 0;
    int capacity = 0;

    public LruCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap(capacity);

        this.mru = new Node(-1,-1);
        this.lru = new Node(-1,-1);
        this.mru.next = this.lru;
        this.lru.prev = this.mru;
    }

    private void moveToTop(Node v) {
        // delete it from the middle
        if (v.prev != null && v.next != null) {
            Node prev = v.prev;
            Node next = v.next;

            prev.next = next;
            next.prev = prev;
        }

        Node next = mru.next;
        next.prev = v;
        mru.next = v;

        v.prev = mru;
        v.next = next;
    }

    private void removeLru() {
        Node prev = lru.prev;
        map.remove(prev.key, prev);

        if (prev != null) {
            prev = prev.prev;
            prev.next = lru;
            lru.prev = prev;
        }
    }

    public int get(int key) {
        Node v = map.get(key);

        if (v != null) {
            // move this to mru top
            moveToTop(v);
            return v.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        Node v = map.get(key);
        if (v != null) {
            moveToTop(v);
            v.value = value;
        }
        else {
            Node newNode = new Node(key, value);
            moveToTop(newNode);
            map.put(key, newNode);
            size++;
            if (size > capacity) {
                removeLru();
            }
        }
    }

    public static void main(String[] argv) {
        LruCache lru = new LruCache(2);
        lru.put(1,0);
        lru.put(2,2);
        System.out.println(lru.get(1));
        lru.put(3,3);
        System.out.println(lru.get(2));
        lru.put(4,4);
        System.out.println(lru.get(1));
        System.out.println(lru.get(2));
        System.out.println(lru.get(3));
        System.out.println(lru.get(4));
    }
}
