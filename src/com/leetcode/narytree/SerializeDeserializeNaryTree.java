package com.leetcode.narytree;

import java.util.LinkedList;
import java.util.Stack;

public class SerializeDeserializeNaryTree {
    // Encodes a tree to a single string.
    // it will yield to 1,B,2,3,B,4,5,6,7,C,8,9,C
    // B signifies begin of children, which also means the previous is a root node
    // C signifies close of the children, and the next is the sibling of the previous
    public String serialize(Node root) {
        if (root == null) return "";
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.deleteCharAt(sb.length()-1).toString();
    }

    // Time: O(N), Space: O(N) - O(2N) from the stack (each children calls) and the string produced
    private void serialize(Node node, StringBuilder sb) {
        sb.append(node.val).append(",");
        if (node.children != null && node.children.size() > 0) {
            sb.append("B,");
            for (Node n : node.children) serialize(n, sb);
            sb.append("C,");
        }
    }

    // Decodes your encoded data to tree.
    // 1,B,2,3,B,4,5,6,7,C,8,9,C
    // Time: O(N), Space: O(N) - Should be O(N + K), for the stack (level of children) we use
    public Node deserialize(String data) {
        if (data == null || data.length() < 1) return null;
        String[] s = data.split(",");
        Stack<Node> stack = new Stack<>();
        Node curr = null, head = null;
        for (String node : s) {
            switch (node) {
                case "B": { stack.push(curr);   break; }
                case "C": { stack.pop();        break; }
                default: {
                    Node n = new Node(Integer.parseInt(node), new LinkedList<>());
                    if (!stack.isEmpty()) {
                        Node currentNode = stack.peek();
                        currentNode.children.add(n);
                    }
                    if (head == null) head = n;
                    curr = n;
                    break;
                }
            }
        }
        return head;
    }


    public static void main(String[] argv) {
        SerializeDeserializeNaryTree s = new SerializeDeserializeNaryTree();
        Node root = new Node(5);
        root.children = new LinkedList<>();
        Node child = new Node(6, new LinkedList<>());
        root.children.add(child);
        root.children.add(new Node(9));
        child.children.add(new Node(7));
        child.children.add(new Node(8));

        String one = s.serialize(root);
        System.out.println(one);
        System.out.println(s.deserialize(one));

        System.out.println("---------------------");
        String data = "1,B,2,3,B,4,5,6,7,C,8,9,C";
        Node tree = s.deserialize(data);
        System.out.println(tree);

        System.out.println(s.serialize(tree));
    }
}

// Your Codec object will be instantiated and called as such:
// SerializeDeserializeNaryTree codec = new SerializeDeserializeNaryTree();
// codec.deserialize(codec.serialize(root));