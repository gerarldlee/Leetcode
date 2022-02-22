package com.leetcode.tree;

public class MaxDepthBinaryTree {

    public int maxDepth(TreeNode root) {

        if (root == null) return 0;

        int depth = 1;
        topDown(root, depth);
        depth = topDownAnswer;

//        depth = bottomsUp(root);

        return depth;
    }

    // good for unknown params that needs to be worked at
    // space: o(n), time: o(n)
    private int bottomsUp(TreeNode root) {
        if (root == null) return 0;

        int left = 0;
        int right = 0;
        if (root.left != null)
            left = bottomsUp(root.left);
        if (root.right != null)
            right = bottomsUp(root.right);

        return Math.max(left, right) + 1;
    }

    int topDownAnswer = 0;
    // good for known params that you foresaw. if we pass the depth, all the nodes will see their depth too
    // space: o(n) time: o(n)
    private void topDown(TreeNode root, int depth) {
        if (root == null) return;

        // nothing in them, just the value
        if (root.left == null && root.right == null) {
            topDownAnswer = Math.max(topDownAnswer, depth);
        }

        // this has to be done at a later time in a top-down approach
        if (root.left != null)
            topDown(root.left, depth + 1);
        if (root.right != null)
            topDown(root.right, depth + 1);
    }

}
