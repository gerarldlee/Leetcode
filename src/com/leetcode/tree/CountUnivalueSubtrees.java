package com.leetcode.tree;

/**
 * [5,1,5,5,5,null,5] - 4
 * [5,1,5,5,5,null,2] - 3
 * [] - 0
 * [1,2,3,4,5,6,7] - 4 ?
 * [1,2,2,2] - 3 - the group of 2's
 * [-1,-2,null,-3,4,4,4] - 3 explain: the group of 4's
 * [-1,4,-2,null,-3,4,4,4] - 3 exp: the individual 4's
 * [4,4,4,4,4,4,4] - 7 exp: all of them are 4's and so treat as whole subtree
 * [4,1,4,4,4,4,4,4] - 6 exp: the 4's except for the first node
 */

/**
 * A univalue subtree is a subtree whos values are the same from the node, to the childs.
 * 1. If the node is a child i.e. does not have children, its a univalue.
 * 2. If the node's value is the same as the childrens value, and their childrens children.
 *
 */
public class CountUnivalueSubtrees {

    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) return 0;

        return unival(root, root.val);
    }

    public int unival(TreeNode node, int value) {
        return 0;
    }
}
