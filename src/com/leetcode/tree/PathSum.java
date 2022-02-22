package com.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

public class PathSum {

    // intuition
    // we iterate over the nodes from root to leaf, adding each node value
    // if we surpassed the targetSum, but the path is not yet finished, we terminate the path and its subsequent childrens

    // a better strategy here is still the preorder DFS, top-down approach, since we have to make
    // an optimal decision along the way, disregarding the path in excess of targetSum
    // Doesn't really matter if we choose iterative or recursive

    // POSSIBLE SOLUTION:
    // 1. another solution to this, is the prefix sum method. but we have to navigate the TreeNode, so thats not possible
    // unless we converted the TreeNode to an array.
    // 2. Backtracking approach


    // time: O(N) we have to traverse every element at worse
    // space: O(N) we have to create a stack of N size at worse
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        boolean result = recursive(root, targetSum, 0);
//        boolean result = backtrack_peudocode(root, targetSum);
        return result;
    }

    public boolean backtrack_peudocode(TreeNode node, int targetsum) {
        /*
        1. Check if the move is valid i.e. if the running sum is within the target sum
        2. If it is, explore that node
        2.5 Get the node.val
        3. Call recursive function to explore that node
        4. In the chance that it does not go well, revert the running sum
         */
        return false;
    }

    public boolean iterative(TreeNode node, int targetsum) {
        Queue<TreeNode> queue = new LinkedList<>();
        LinkedList<Integer> runningSum = new LinkedList<>();
        queue.offer(node);
        runningSum.add(node.val);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            int currentSum = runningSum.poll();
            if (current.left == null && current.right == null && currentSum == targetsum) return true;

            if (current.right != null) {
                queue.offer(current.right);
                runningSum.add(currentSum + current.right.val);
            }
            if (current.left != null) {
                queue.offer(current.left);
                runningSum.add(currentSum + current.left.val);
            }
        }
        return false;
    }

    public boolean recursive(TreeNode node, int targetSum, int runningSum) {
        if (node == null) return false;

        runningSum += node.val;

        if ((node.left == null && node.right == null))
            return (runningSum == targetSum);

        return recursive(node.left, targetSum, runningSum) || recursive(node.right, targetSum, runningSum);
    }

    public static void main(String[] a) {
        TreeNode root = TreeNode.parser(new Integer[] {5,4,8,11,null,13,4,7,2,null,null,null,1});
        TreeNode root1 = TreeNode.parser(new Integer[] {1,2,3});
        TreeNode root2 = TreeNode.parser(new Integer[] {});
        TreeNode root4 = TreeNode.parser(new Integer[] {-2,null,-5});
        TreeNode root3 = TreeNode.parser(new Integer[] {1,2});

        PathSum p = new PathSum();
        System.out.println(p.hasPathSum(root4, -5));
        System.out.println(p.hasPathSum(root3, 1));
        System.out.println(p.hasPathSum(root, 22));
        System.out.println(p.hasPathSum(root1, 5));
        System.out.println(p.hasPathSum(root2, 0));
    }
}
