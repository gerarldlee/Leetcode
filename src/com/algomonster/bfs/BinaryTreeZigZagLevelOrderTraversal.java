package com.algomonster.bfs;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BinaryTreeZigZagLevelOrderTraversal {
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

    public static List<List<Integer>> zigZagTraversal(Node<Integer> root) {
        // WRITE YOUR BRILLIANT CODE HERE

        if (root == null) return null;

        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.offer(root);

        LinkedList ziglevels = new LinkedList();

        boolean reverse = false;

        while (!queue.isEmpty()) {
            LinkedList zagvalues = new LinkedList();

            int size = queue.size();
            while (size > 0) {
                Node curr = queue.poll();
                zagvalues.add(curr.val);
                size--;

                // add children to queue
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }

            if (reverse) {
                Collections.reverse(zagvalues);
            }

            ziglevels.add(zagvalues);

            // reverse it for the next step
            reverse = !reverse;
        }

        return ziglevels;
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
        List<List<Integer>> res = zigZagTraversal(root);
        for (List<Integer> row : res) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }
    }
}
