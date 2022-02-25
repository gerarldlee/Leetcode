package com.leetcode.linkedlist;

public class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
    public Node random;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _next) {
        val = _val;
        next = _next;
    }

    public Node(int _val, Node _next, Node _prev) {
        val = _val;
        next = _next;
        prev = _prev;
    }
}
