package com.leetcode.design;

import com.leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTreeIterator {

    List<Integer> sortedList;
    int currentIndex = Integer.MIN_VALUE;
    int size = 0;

    public BinarySearchTreeIterator(TreeNode root) {
        this.sortedList = new ArrayList<>();
        this.currentIndex = 0;
        this.traverseInorder(root);
    }

    public int next() {
        return sortedList.get(this.currentIndex++);
    }

    public boolean hasNext() {
        return this.currentIndex < size;
    }

    private void traverseInorder(TreeNode node) {
        if (node == null) return;

        traverseInorder(node.left);
        this.sortedList.add(node.val);
        this.size++;
        traverseInorder(node.right);
    }
}
