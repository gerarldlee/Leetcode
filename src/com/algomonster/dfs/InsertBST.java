package com.algomonster.dfs;

import java.util.*;
import java.util.function.Function;

public class InsertBST {
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

    public static Node<Integer> insertBst(Node<Integer> bst, int val) {
        // WRITE YOUR BRILLIANT CODE HERE

        if (bst == null) return new Node(val);

        // search to the left
        if (val < bst.val) {
            // if there are childs, then recurse
            if (bst.left != null) {
                insertBst(bst.left, val);
            }
            // if not, then insert
            else {
                Node newNode = new Node(val);
                bst.left = newNode;
            }
        }
        // search to the right
        else if (val > bst.val) {
            if (bst.right != null) {
                insertBst(bst.right, val);
            }
            else {
                Node newNode = new Node(val);
                bst.right = newNode;
            }
        }
        // else they are equal, so just return the bst

        return bst;
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

    public static <T> void formatTree(Node<T> node, List<String> out) {
        if (node == null) {
            out.add("x");
            return;
        }
        out.add(node.val.toString());
        formatTree(node.left, out);
        formatTree(node.right, out);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Node<Integer> bst = buildTree(splitWords(scanner.nextLine()).iterator(), Integer::parseInt);
        int val = Integer.parseInt(scanner.nextLine());
        scanner.close();
        Node<Integer> res = insertBst(bst, val);
        ArrayList<String> resArr = new ArrayList<>();
        formatTree(res, resArr);
        System.out.println(String.join(" ", resArr));
    }
}
