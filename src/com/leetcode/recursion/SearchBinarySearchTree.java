package com.leetcode.recursion;

import com.leetcode.tree.TreeNode;

public class SearchBinarySearchTree {

    // recursive
    // time: o(log n) space o(log n) since its a binary search tree, and every node is divided into its half
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return null;
        if (val < root.val) {
            return searchBST(root.left, val);
        }
        else if (val > root.val) {
            return searchBST(root.right, val);
        }
        return root;
    }
}
