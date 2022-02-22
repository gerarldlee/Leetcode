package com.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
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
            // if the first i is null, continue on
//            if (array[i] == null) {
//                i--;
//                continue;
//            }
            // since i develops by 2 increments, we can assert a non-null here
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

}
