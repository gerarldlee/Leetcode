package com.grind75;


  class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }


public class BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return Math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    public int height(TreeNode node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public static TreeNode treeParser(int[] array, int index) {
        if (index >= array.length || array[index] == -1) return null;

        TreeNode tree = new TreeNode(array[index]);
        TreeNode current = tree;
        int i = 1;
        while (i < array.length) {

        }
        return tree;
    }

    public static void main(String[] argv) {
        TreeNode tree = treeParser(new int[] {1,2,2,3,-1,-1,3,4,-1,-1,4}, 0);

        BalancedBinaryTree bt = new BalancedBinaryTree();
        System.out.println(bt.isBalanced(tree));
    }
}
