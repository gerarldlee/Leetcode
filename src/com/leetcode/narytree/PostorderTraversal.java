package com.leetcode.narytree;

import java.util.LinkedList;
import java.util.List;

public class PostorderTraversal {

    // iterative
    public List<Integer> postorder(Node root) {
        LinkedList<Integer> result = new LinkedList<>();
        if (root == null) return result;

        LinkedList<Node> stack = new LinkedList<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            Node n = stack.pollLast();
            result.addFirst(n.val);

            if (n.children != null) stack.addAll(n.children);
        }
        return result;
    }

    // recursive
    public List<Integer> postorderR(Node root) {
        List<Integer> result = new LinkedList<>();
        postorder(root, result);
        return result;
    }

    public void postorder(Node node, List<Integer> result) {
        if (node == null) return;

        for (Node n : node.children) {
            postorder(n, result);
        }

        result.add(node.val);
    }
}
