package com.algomonster.dfs;

import java.util.*;

public class SerializeDeserializeBinaryTree {
    public static String serialize(Node root) {
        // WRITE YOUR BRILLIANT CODE HERE
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    private static void serialize(Node node, StringBuilder str) {
        if (node == null) {
            str.append("x ");
            return;
        }

        str.append(node.val).append(" ");

        serialize(node.left, str);
        serialize(node.right, str);
    }

    public static Node deserialize(String root) {
        // AND HERE
        return deserialize(Arrays.stream(root.split(" ")).iterator());
    }

    private static Node deserialize(Iterator<String> nodes) {
        String val = nodes.next();
        if (val.equals("x")) return null;

        Node cur = new Node(Integer.parseInt(val));
        cur.left = deserialize(nodes);
        cur.right = deserialize(nodes);
        return cur;
    }

    /** Driver class, do not change **/
    static class Node {
        int val;
        Node left, right;

        public Node(int val) {
            this.val = val;
        }

        public static Node buildTree(Iterator<String> iter) {
            String nxt = iter.next();
            if (nxt.equals("x")) return null;
            Node node = new Node(Integer.parseInt(nxt));
            node.left = buildTree(iter);
            node.right = buildTree(iter);
            return node;
        }

        public static void printTree(Node root, List<String> out) {
            if (root == null) {
                out.add("x");
            } else {
                out.add(String.valueOf(root.val));
                printTree(root.left, out);
                printTree(root.right, out);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Node root = Node.buildTree(Arrays.stream(scanner.nextLine().split(" ")).iterator());
        scanner.close();
        Node newRoot = deserialize(serialize(root));
        ArrayList<String> out = new ArrayList<>();
        Node.printTree(newRoot, out);
        System.out.println(String.join(" ", out));
    }
}
