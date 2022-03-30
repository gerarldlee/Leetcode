package com.leetcode.binarysearchtree;

import com.leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class UniqueBinarySearchTrees2 {

    public List<TreeNode> generateTrees(int n) {
        return generateTree(1, n);
    }

    private List<TreeNode> generateTree(int start, int end) {
        if (start > end) {
            List<TreeNode> output = new ArrayList<>();
            output.add(null);
            return output;
        }

        List<TreeNode> output = new ArrayList<>();
        for (int root = start; root <= end; root++) {

            // attach any lesser value node to its left and create a tree sub left
            List<TreeNode> lefts = generateTree(start, root - 1);

            // attach any greater value to its right and create a right sub tree
            List<TreeNode> rights = generateTree(root + 1, end);

            for (TreeNode left : lefts) {
                for (TreeNode right : rights) {
                    TreeNode node = new TreeNode(root);
                    node.left = left;
                    node.right = right;
                    output.add(node);
                }
            }
        }
        return output;
    }

    public static void main(String[] a) {

        UniqueBinarySearchTrees2 u = new UniqueBinarySearchTrees2();
        u.generateTrees(3);

    }
}
