package com.leetcode.narytree;

import java.util.List;

/**
 * N-nary Tree Node
 * Contains a list of its children nodes
 */
public class Node {

    public int val;
    public List<Node> children;

    public Node() {

    }

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, List<Node> children) {
        this.val = val;
        this.children = children;
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                ", children=" + children +
                '}';
    }
}
