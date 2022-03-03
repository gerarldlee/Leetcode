package com.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static TreeNode parser(Integer[] array) {
        if (array == null || array.length < 1) return null;

        TreeNode currentNode = new TreeNode(array[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(currentNode);

        for (int i=1; i<array.length; i += 2) {
            TreeNode oldest = queue.poll();
            if (array[i] != null) {
                oldest.left = new TreeNode(array[i]);
                queue.offer(oldest.left);
            }
            if (i+1 < array.length && array[i+1] != null) {
                oldest.right = new TreeNode(array[i+1]);
                queue.offer(oldest.right);
            }
        }
        return currentNode;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                ", left=" + left +
                ", right=" + right +
                "}\n";
    }
}
