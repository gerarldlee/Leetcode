package com.algomonster.dfs;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class LowestCommonAncestor {
    public static Node lca(Node root, Node node1, Node node2) {
        // WRITE YOUR BRILLIANT CODE HERE

        if (root == null) return null;

        // if any of the target nodes is this node, return it
        if (root == node1 || root == node2) return root;

        // traverse using preorder
        // traverse the left, right subtree and return the target node found
        Node left = lca(root.left, node1, node2);
        Node right = lca(root.right, node1, node2);

        // when the left and right subtree has the target nodes, that means, this root is LCA
        if (left != null && right != null) return root;
        // but when theres only one found, either left or the right subtree has the target node
        if (left != null) return left;
        if (right != null) return right;

        return null;
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

        public static Node findNode(Node root, int target) {
            if (root == null) return null;
            if (root.val == target) return root;
            Node leftSearch = findNode(root.left, target);
            if (leftSearch != null) {
                return leftSearch;
            }
            return findNode(root.right, target);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Node root = Node.buildTree(Arrays.stream(scanner.nextLine().split(" ")).iterator());
        Node node1 = Node.findNode(root, Integer.parseInt(scanner.nextLine()));
        Node node2 = Node.findNode(root, Integer.parseInt(scanner.nextLine()));
        scanner.close();
        Node ans = lca(root, node1, node2);
        System.out.println(ans == null ? "null" : ans.val);
    }
}
