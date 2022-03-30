package com.leetcode.binarysearchtree;

import com.leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinarySearchTreeIterator {
    List<Integer> sortedList;
    int currentIndex;
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
        Stack<TreeNode> stack = new Stack();
        TreeNode n = node;
        while (!stack.isEmpty() || n != null) {
            // traverse the left subtree of the node and push it into the stack
            while (n != null) {
                stack.push(n);
                n = n.left;
            }
            // visit the node and remove it from the stack
            n = stack.pop();
            this.sortedList.add(n.val);
            this.size++;

            n = n.right;
        }
    }

    public static void main(String[] a) {
        TreeNode t = new TreeNode(2);
        t.left = new TreeNode(1);
        t.right = new TreeNode(3);

        BinarySearchTreeIterator b = new BinarySearchTreeIterator(t);
    }
}
