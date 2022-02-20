package com.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreePostorderTraversal {

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root != null) {
//            recursive(root, res);
            iterative(root, res);
//            morris(root, res);
        }

        return res;
    }

    public void iterative(TreeNode node, List<Integer> result) {
        Stack<TreeNode> s = new Stack<>();
        TreeNode current = node;
        TreeNode previousNode = null;
        while(!s.isEmpty() || current != null) {
            // traverse the left nodes
            while (current != null) {
                s.push(current);
                current = current.left;
            }

            // no right node, or right node was already visited previously
            // pop the topmost element, and mark this as previous node
            TreeNode right = s.peek().right;
            if (right == null || right == previousNode) {
                previousNode = s.pop();
                result.add(previousNode.val);
            }
            else {
                current = right;
            }
        }
    }

    // visit left, rigght, then middle
    // time: o(n), space: o(n)
    public void recursive(TreeNode node, List<Integer> result) {
        if (node.left != null) {
            recursive(node.left, result);
        }
        if (node.right != null) {
            recursive(node.right, result);
        }
        result.add(node.val);
    }

    public void morris(TreeNode node, List<Integer> result) {

    }

    public static void main(String[] a) {
        BinaryTreePostorderTraversal t = new BinaryTreePostorderTraversal();
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        t.postorderTraversal(root);
    }
}
