package com.algomonster.bfs;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BinaryTreeLevelOrderTraversal {

    public static class Node<T> {
        public T val;
        public BinaryTreeMinDepth.Node<T> left;
        public BinaryTreeMinDepth.Node<T> right;

        public Node(T val) {
            this(val, null, null);
        }

        public Node(T val, BinaryTreeMinDepth.Node<T> left, BinaryTreeMinDepth.Node<T> right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static List<List<Integer>> levelOrderTraversal(BinaryTreeMinDepth.Node<Integer> root) {
        // WRITE YOUR BRILLIANT CODE HERE

        if (root == null) return null;

        ArrayDeque<BinaryTreeMinDepth.Node> queue = new ArrayDeque<>();
        queue.offer(root);

        List levels = new ArrayList();

        while(!queue.isEmpty()) {
            List<Integer> values = new ArrayList<>();
            int size = queue.size();

            while (size > 0) {
                BinaryTreeMinDepth.Node<Integer> curr = queue.poll();
                values.add(curr.val);
                size--;

                // add the left and right of curr to queue
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
            levels.add(values);
        }

        return levels;
    }

    public static <T> BinaryTreeMinDepth.Node<T> buildTree(Iterator<String> iter, Function<String, T> f) {
        String val = iter.next();
        if (val.equals("x")) return null;
        BinaryTreeMinDepth.Node<T> left = buildTree(iter, f);
        BinaryTreeMinDepth.Node<T> right = buildTree(iter, f);
        return new BinaryTreeMinDepth.Node<T>(f.apply(val), left, right);
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BinaryTreeMinDepth.Node<Integer> root = buildTree(splitWords(scanner.nextLine()).iterator(), Integer::parseInt);
        scanner.close();
        List<List<Integer>> res = levelOrderTraversal(root);
        for (List<Integer> row : res) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }
    }
}
