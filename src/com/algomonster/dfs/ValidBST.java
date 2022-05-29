package com.algomonster.dfs;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class ValidBST {
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

    public static boolean validBst(Node<Integer> root) {
        // WRITE YOUR BRILLIANT CODE HERE

        // TODO remember this for BST
        // the key, is to put both state for left (max) and right (min) -- why?

        // the recursion is checking the left and right values if it is > or < node.val
        // at the same time, since the min or max is carried over from the previous recursion, the previous
        // node.val border is also carried over

        return isValidBst(root, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }

    private static boolean isValidBst(Node<Integer> node, int max, int min) {

        if (node == null) return true;

        if (node.val < min || node.val > max)
            return false;

        return isValidBst(node.left, node.val, min) && isValidBst(node.right, max, node.val);
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
        Node<Integer> root = buildTree(splitWords(scanner.nextLine()).iterator(), Integer::parseInt);
        scanner.close();
        boolean res = validBst(root);
        System.out.println(res);
    }
}
