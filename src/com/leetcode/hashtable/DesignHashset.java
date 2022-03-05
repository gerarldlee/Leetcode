package com.leetcode.hashtable;

/*
Intuition

Designing a hash set is trivial. We need to create a bucket storage of n numbers, and each one an array for collision.

We also need to define a hash function from its key, and directly store this to the storage of n numbers.

In order to optimize handling of collision, we can use BST to maintain a sorted list.

So this entails a bucket search of O(1) due to direct access using hash function, and because we are maintaining a sorted
list, we can perform a binary search in them, which is o(log n), rather than maybe a linear o(n) if we maintained a linkedlist

Space will depend on the number of buckets, and the number of collisions.

As a constraint, key = [0, 10^6] 1,000,000
 */
public class DesignHashset {

    private BstBucket[] bucketArray;
    private int keyRange; // a prime number for key range, using modulo.

    public DesignHashset() {
        this.keyRange = 7919; // prime number, closer to 10000, which is 1/10th of the theoretical maximum
        this.bucketArray = new BstBucket[this.keyRange];
    }

    public void add(int key) {
        int hashKey = hash(key);
        if (this.bucketArray[hashKey] == null) {
            this.bucketArray[hashKey] = new BstBucket();
        }
        this.bucketArray[hashKey].add(key);
    }

    public void remove(int key) {
        int hashKey = hash(key);
        if (this.bucketArray[hashKey] != null) {
            this.bucketArray[hashKey].remove(key);
        }
    }

    public boolean contains(int key) {
        int hashKey = hash(key);
        if (this.bucketArray[hashKey] != null) {
            return this.bucketArray[hashKey].contains(key);
        }
        return false;
    }

    private int hash(int key) {
        return key % this.keyRange;     // the hash function, which is just a %
    }
}

// our bucket is a binary search tree
class BstBucket {

    private TreeNode treeRoot;  // root node of the BST

    public BstBucket() {
    }

    public void add(int key) {
        this.treeRoot = _insert(treeRoot, key);
        System.out.println("Added: " + treeRoot);
    }
    private TreeNode _insert(TreeNode root, int key) {
        if (root == null) return new TreeNode(key);
        if (key < root.val) {
            root.left = _insert(root.left, key);
        }
        else if (key > root.val) {
            root.right = _insert(root.right, key);
        }
        return root;
    }

    public void remove(int key) {
        if (treeRoot == null) return;   // nothing to remove
        this.treeRoot = _remove(treeRoot, key);
        System.out.println("Remove: " + treeRoot);
    }
    private TreeNode _remove(TreeNode root, int key) {
        if (root == null) return null;
        if (key < root.val) {
            root.left = _remove(root.left, key);
        }
        else if (key > root.val) {
            root.right = _remove(root.right, key);
        }
        else {
            // the node has left
            if (root.left != null) {
                root.val = _predecessor(root);
                root.left = _remove(root.left, root.val);
            }
            // the node has a right child
            else if (root.right != null) {
                root.val = _successor(root);
                root.right = _remove(root.right, root.val);
            }
            // node no right or left, is a leaf
            else {
                root = null;
            }
        }
        return root;
    }
    /*
     * One step right and then always left
     */
    private int _successor(TreeNode root) {
        root = root.right;
        while (root.left != null)
            root = root.left;
        return root.val;
    }

    /*
     * One step left and then always right
     */
    private int _predecessor(TreeNode root) {
        root = root.left;
        while (root.right != null)
            root = root.right;
        return root.val;
    }

    public boolean contains(int key) {
        System.out.println("Contains: " + treeRoot);
        if (treeRoot == null) return false;
        return _search(treeRoot, key) != null;
    }
    private TreeNode _search(TreeNode root, int key) {   // binary search the BST
        if (root == null || key == root.val) return root;
        return key < root.val ? _search(root.left, key) : _search(root.right, key);
    }
}

class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */