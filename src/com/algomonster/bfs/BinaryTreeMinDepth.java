package com.algomonster.bfs;

import java.util.*;
import java.util.function.Function;

public class BinaryTreeMinDepth {
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

    public static int binaryTreeMinDepth(Node<Integer> root) {
        // WRITE YOUR BRILLIANT CODE HERE

        if (root == null) return 0;

        ArrayDeque<Node> queue = new ArrayDeque();
        queue.offer(root);

        int depth = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();

            while (size > 0) {
                size--;
                Node curr = queue.poll();
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
                if (curr.left == null && curr.right == null)
                    return depth;
            }
            depth++;
        }

        return depth;
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
        int res = binaryTreeMinDepth(root);
        System.out.println(res);
    }
}
