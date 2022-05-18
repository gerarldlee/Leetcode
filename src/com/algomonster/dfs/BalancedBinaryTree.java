package com.algomonster.dfs;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class BalancedBinaryTree {
    public static class Node<T> {
        public T val;
        public Node<T> left;
        public Node<T> right;

        public Node(T val) {
            this(val, null, null);
        }

        public Node(T val, Node<T> left, Node<T> right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static boolean isBalanced(Node<Integer> tree) {
        // WRITE YOUR BRILLIANT CODE HERE

        if (tree == null) return true;

        int height = height(tree);
//         System.out.println(height);

        // height should be not not balanced
        return height != -1;
    }

    private static int height(Node<Integer> tree) {
        // a null tree is a balanced tree
        if (tree == null) return 0;

        // gets the maximum height of this tree
        int left = height(tree.left);
        int right = height(tree.right);

        // if either left or right subtree is not balanced, then its not balanced
        if (left == -1 || right == -1) {
            return -1;
        }

        // if the left subtree or the right subtree's height difference more than 1, then not balanced
        if (Math.abs(left - right) > 1) {
            return -1;
        }

        // the height of this tree is the current tree + either left height or right height w/c higher
        int height = Math.max(left, right) + 1;
        return height;
    }

    public static <T> Node<T> buildTree(Iterator<String> iter, Function<String, T> f) {
        String val = iter.next();
        if (val.equals("x")) return null;
        Node<T> left = buildTree(iter, f);
        Node<T> right = buildTree(iter, f);
        return new Node<T>(f.apply(val), left, right);
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Node<Integer> tree = buildTree(splitWords(scanner.nextLine()).iterator(), Integer::parseInt);
        scanner.close();
        boolean res = isBalanced(tree);
        System.out.println(res);
    }
}
