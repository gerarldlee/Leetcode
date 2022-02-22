package com.leetcode.tree;

import com.sun.source.tree.Tree;

import java.util.*;

public class SymmetricTree {

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return false;

//        return iterative(root);
//        return recursive(root);
        return anotherIterativeSolution(root);
    }

    // intuition:
    // compare both the left and the right at the same time, maintaining a single combination of stack o(n)
    // but returning as soon as it encounters differences
    // this is more efficient than the previous one since,
    // this will end early and result in fewer loop on the average
    public boolean anotherIterativeSolution(TreeNode node) {
        Queue<TreeNode> tree = new LinkedList<>();
        tree.offer(node.left);
        tree.offer(node.right);

        while (!tree.isEmpty()) {
            TreeNode l = tree.poll();
            TreeNode r = tree.poll();

            if (l == null && r == null) continue;
            if (l == null || r == null) return false;
            if (l.val != r.val) return false;

            tree.offer(l.left);
            tree.offer(r.right);
            tree.offer(l.right);
            tree.offer(r.left);
        }

        return true;
    }

    // space: o(n), time: o(n)
    public boolean iterative(TreeNode node) {
        TreeNode leftNode = node.left;
        List<Integer> leftValues = new ArrayList<>();
        Queue<TreeNode> leftQueue = new LinkedList<>();
        leftQueue.offer(leftNode);

        while (!leftQueue.isEmpty()) {
            TreeNode left = leftQueue.poll();
            if (left != null) {
                leftValues.add(left.val);
                leftQueue.offer(left.left);
                leftQueue.offer(left.right);
            }
            else
                leftValues.add(null);
        }

        TreeNode rightNode = node.right;
        List<Integer> rightValues = new ArrayList<>();
        Queue<TreeNode> rightQueue = new LinkedList<>();
        rightQueue.offer(rightNode);

        while (!rightQueue.isEmpty()) {
            TreeNode right = rightQueue.poll();
            if (right != null) {
                rightValues.add(right.val);
                rightQueue.offer(right.right);
                rightQueue.offer(right.left);
            }
            else rightValues.add(null);
        }

        return leftValues.equals(rightValues);
    }

    // time: o(N), space: o(N)
    public boolean recursive(TreeNode node) {
        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;

        List<Integer> leftValues = new ArrayList<>();
        List<Integer> rightValues = new ArrayList<>();
        preorder(leftNode, leftValues);
        reverse_preorder(rightNode, rightValues);

        return leftValues.equals(rightValues);
    }

    // top-down
    private void preorder(TreeNode leftNode,List<Integer> leftValues) {
        if (leftNode == null) leftValues.add(null);
        else {
            leftValues.add(leftNode.val);
            // search from left, to right
            preorder(leftNode.left, leftValues);
            preorder(leftNode.right, leftValues);
        }
    }

    // top-down
    private void reverse_preorder(TreeNode rightNode,List<Integer> rightValues) {
        if (rightNode == null) rightValues.add(null);
        else {
            rightValues.add(rightNode.val);
            // search from right, to left
            reverse_preorder(rightNode.right, rightValues);
            reverse_preorder(rightNode.left, rightValues);
        }
    }

    public boolean another_recursive(TreeNode node) {
        return mirror(node.left, node.right);
    }

    private boolean mirror(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;

        if (left.val == right.val && mirror(left.right, right.left) && mirror(left.left, right.right)) {
            return true;
        }
        return false;
    }

    public static void main(String[] ar) {
        SymmetricTree s = new SymmetricTree();
        TreeNode example1 = TreeNode.parser(new Integer[] {1,2,2,3,4,4,3});
        TreeNode example2 = TreeNode.parser(new Integer[] {1,2,2,null,3,null,3});
        TreeNode example3 = TreeNode.parser(new Integer[] {2,3,3,4,5,5,4,null,null,8,9,9,8});

        System.out.println(s.isSymmetric(example1));
        System.out.println(s.isSymmetric(example2));
        System.out.println(s.isSymmetric(example3));
    }
}
