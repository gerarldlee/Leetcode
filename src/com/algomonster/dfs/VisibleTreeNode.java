package com.algomonster.dfs;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class VisibleTreeNode {
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

    public static int visibleTreeNode(Node<Integer> node) {
        // WRITE YOUR BRILLIANT CODE HERE
        // the root node is always visible, hence +1
        return 1 + visibleTreeNode(node, node.val);
    }

    public static int visibleTreeNode(Node<Integer> node, int max) {
        if (node == null) return 0;

        int count = 0;
        if (node.val > max) count++;

        count += visibleTreeNode(node.left, Math.max(max, node.val));
        count += visibleTreeNode(node.right, Math.max(max, node.val));
        return count;
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
        int res = visibleTreeNode(root);
        System.out.println(res);
    }
}
