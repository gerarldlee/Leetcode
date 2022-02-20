package com.leetcode.tree;

import java.util.*;

public class BinaryTreeLevelOrderTraversal {

    // time: o(n), space: o(n)
    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();

        iterative(root, result);

        return result;
    }

    public void iterative(TreeNode node, List<List<Integer>> result) {
        Deque<TreeNode> levels = new LinkedList<>();
        levels.push(node);

        while (!levels.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            TreeNode lvl = levels.pop();
            level.add(lvl.val);
            result.add(level);

            if (lvl.left != null) {
                levels.add(lvl.left);
            }
            if (lvl.right != null) {
                levels.add(lvl.right);
            }
        }


    }
}
