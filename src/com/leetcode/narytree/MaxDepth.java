package com.leetcode.narytree;

public class MaxDepth {

    // The idea is to form a recursion for the nodes children and children children, increasing the maximum
    // depth as we go along

    // Time: o(n) space: o(1)
    public int maxDepth(Node root) {
            if (root == null) return 0;
            int cur = 0;
            for (Node node : root.children) {
                cur = Math.max(cur, maxDepth(node));
            }
            return 1 + cur;
    }
}
