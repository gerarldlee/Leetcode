package com.leetcode.narytree;

import java.util.*;

public class PreorderTraversal {

    public List<Integer> preorder(Node root) {
        List<Integer> array = new ArrayList<>();
        preorder(root, array);
        return array;
    }

    // recursive approach
    public void preorder(Node node, List<Integer> result) {
        if (node == null) return;
        result.add(node.val);
        if (node.children != null && !node.children.isEmpty()) {
            for (Node n : node.children) {
                preorder(n, result);
            }
        }
    }

    // iterative approach
    // time: o(n) space: o(n)
    public void preorderIterative(Node node, List<Integer> result) {
        if (node == null) return;

        LinkedList<Node> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            Node n = queue.pop();
            result.add(n.val);

            Collections.reverse(n.children);
            for (Node child : n.children) {
                queue.addFirst(child);
            }
        }
    }
}
