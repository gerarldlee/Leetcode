package com.algomonster.bt;

import java.util.*;
import java.util.function.Function;

public class DFSWithStates {
    public static class Node<T> {
        public T val;
        public List<Node<T>> children;

        public Node(T val) {
            this(val, new ArrayList<>());
        }

        public Node(T val, List<Node<T>> children) {
            this.val = val;
            this.children = children;
        }
    }

    public static List<String> ternaryTreePaths(Node<Integer> root) {
        // WRITE YOUR BRILLIANT CODE HERE

        if (root == null) return new LinkedList();

        LinkedList<String> list = new LinkedList();
        LinkedList<Node> path = new LinkedList();
        dfs(root, list, path);

        return list;
    }

    private static void dfs(Node<Integer> node, LinkedList<String> results, LinkedList<Node> path) {
        // end state
        // when the last node in path is null, means its a leaf node
        if (!path.isEmpty() && path.get(path.size()-1).children.isEmpty()) {
            StringJoiner join = new StringJoiner("->");
            for (Node<Integer> i : path) {
                join.add(String.valueOf(i.val));
            }
            results.add(join.toString());
        }

        // add the root when its empty
        if (path.size() == 0) {
            path.add(node);
        }

        // permutation and backtracking
        for (Node<Integer> child : node.children) {
            // try a child, if it has subchild, then recursively perform dfs
            path.add(child);
            dfs(child, results, path);
            // backtrack and revert to this child
            path.remove(path.size()-1);
        }
    }

    public static <T> Node<T> buildTree(Iterator<String> iter, Function<String, T> f) {
        String val = iter.next();
        int num = Integer.parseInt(iter.next());
        ArrayList<Node<T>> children = new ArrayList<>();
        for (int i = 0; i < num; i++)
            children.add(buildTree(iter, f));
        return new Node<T>(f.apply(val), children);
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Node<Integer> root = buildTree(splitWords(scanner.nextLine()).iterator(), Integer::parseInt);
        scanner.close();
        List<String> res = ternaryTreePaths(root);
        for (String line : res) {
            System.out.println(line);
        }
    }
}
