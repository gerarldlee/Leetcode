package com.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

public class SerializeDeserializeBinaryTree {

    // traverse the node, then convert them to BFS array, then convert it to string
    // time: o(n) space o(1)
    public String serialize(TreeNode node) {
        if (node == null) return "";

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr == null) {
                sb.append("null,");
                continue;
            }
            else {
                sb.append(curr.val).append(",");
            }
            // push the left and the right values even if its null
            queue.offer(curr.left);
            queue.offer(curr.right);
        }
        return sb.substring(0, sb.length()-1).toString();
    }

    // separate the array string comma separated for the values
    // inflate them into array, and inflate it into TreeNode
    public TreeNode deserialize(String datum) {
        if (datum == null) return null;
        String d = datum.replaceAll("\\[\\]", "");
        if (d.length() <= 0) return null;

        String[] data = d.split(",");
        TreeNode head = new TreeNode(Integer.parseInt(data[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(head);
        for (int i=1; i<data.length; i += 2) {
            TreeNode top = queue.poll();
            if (data[i] != null && !data[i].equals("null")) {
                top.left = new TreeNode(Integer.parseInt(data[i]));
                queue.offer(top.left);
            }
            if (i+1 < data.length && data[i+1] != null && !data[i+1].equals("null")) {
                top.right = new TreeNode(Integer.parseInt(data[i+1]));
                queue.offer(top.right);
            }
        }
        return head;
    }

    public static void main(String[] g) {
        String d = "[]";

        SerializeDeserializeBinaryTree s = new SerializeDeserializeBinaryTree();
        TreeNode n = s.deserialize(d);
    }
}
