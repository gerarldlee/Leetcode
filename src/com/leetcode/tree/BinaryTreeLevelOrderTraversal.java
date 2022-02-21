package com.leetcode.tree;

import java.util.*;

public class BinaryTreeLevelOrderTraversal {

    // time: o(n), space: o(n)
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

//        recursive(root, 0, result);
        iterative(root, result);

        return result;
    }

    // time: o(n), space o(n)
    // recursive is always more intuitive approach than iteration
    // be cafeful of those stack overflow
    public void recursive(TreeNode node, int level, List<List<Integer>> result) {
        List<Integer> currentLevel = null;

        // when it fulfills the level, its time to create a new level
        if (result.size() == level) {
            currentLevel = new ArrayList<>();
            result.add(currentLevel);
        }

        // the level is actually the index of the list
        currentLevel = result.get(level);
        currentLevel.add(node.val);

        if (node.left != null) recursive(node.left, level + 1, result);
        if (node.right != null) recursive(node.right, level + 1, result);
    }

    // time: o(n) space o(n)
    public void iterative(TreeNode node, List<List<Integer>> result) {
        // we need to use this too, for BFS, so it does not really matter
        // its better for us to just use Queue since we are not inserting in front!
        Deque<TreeNode> levelsQueue = new LinkedList<>();
        levelsQueue.addLast(node);

        while (!levelsQueue.isEmpty()) {
            List<Integer> currentLevel = new ArrayList<>();

            // like recursion, we get and compare the level, but from the deque size
            // and add those elements in the current level
            int queue_size = levelsQueue.size();
            while (queue_size > 0) {
                // we need to pop the first element in the stack
                TreeNode currentNode = levelsQueue.removeFirst();

                currentLevel.add(currentNode.val);

                // add the child nodes of the current nodes to the stack
                if (currentNode.left != null) levelsQueue.addLast(currentNode.left);
                if (currentNode.right != null) levelsQueue.addLast(currentNode.right);

                queue_size--;
            }
            result.add(currentLevel);   // add the current stack level to the result
        }
    }
}
