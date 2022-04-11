package com.leetcode.facebook;

import com.leetcode.tree.TreeNode;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
public class DiameterBinaryTree {

    int diameter = 0;

    // time: O(N) - it will visit all the nodes
    // space: O(N) - since it will visit all the nodes, then it will also create a stack entry for all the nodes it visit
    public int diameterOfBinaryTree(TreeNode root) {
        largestDiameter(root);
        return diameter;
    }

    private int largestDiameter(TreeNode node) {

        if (node == null) return 0;

        int left = largestDiameter(node.left);
        int right = largestDiameter(node.right);
        this.diameter = Math.max(diameter, left + right);

        return Math.max(left, right) + 1;
    }
}
