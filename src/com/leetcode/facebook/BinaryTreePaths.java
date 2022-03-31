package com.leetcode.facebook;

import com.leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePaths {

    static String ARROW = "->";
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) return new ArrayList<>();

        List<String> result = new ArrayList<>();
        traverse(result, "", root);
        return result;
    }

    private void traverse(List<String> result, String path, TreeNode node) {
        if (node.left == null && node.right == null) {
            result.add(path + String.valueOf(node.val));
        }

        if (node.left != null) {
            traverse(result, path + String.valueOf(node.val) + ARROW, node.left);
        }
        if (node.right != null) {
            traverse(result, path + String.valueOf(node.val) + ARROW, node.right);
        }
    }
}
