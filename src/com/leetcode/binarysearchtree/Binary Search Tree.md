BST
====

BST is a special form of binary tree that satisfies these properties

- the value of each nodes must be less than or equal to the value stored in its right subtree
- the value of each nodes must be greater than or equal to the value stored in its left subtree



BST can be traversed in pre-order, inorder, post order or level order; however inorder is the most common used traversal method because it produce a sorted BST in ascending order.



BST support 3 main operation:

- search

  - 1. return the node if the target value is equal to the value of the node
    2. continue searching in the left subtree if the target is less than the value of the node
    3. continue searching in the right subtree if the target is greater than the value of the node

    ```java
        public TreeNode searchBST(TreeNode root, int target) {
            if (root == null || root.val == target) {
                return root;
            }
            if (target < root.val) {
                return searchBST(root.left, target);
            }
            return searchBST(root.right, target);
        }
    ```

    - Time complexity: o(h) - height of the tree
    - Space complexity: o(h) - stack height of the tree

- insertion

  - 1. search the left or right subtree according to the relation of the value of the node and the value of our target node
    2. repeat Step 1 until reaching an external node
    3. add the new node as its left or right child depending on the relation of the value of the node and the value of our target node

    ```java
        public TreeNode insertIntoBST(TreeNode root, int val) {
            if (root == null) {
                return new TreeNode(val);   // return a new node if root is null
            }
            if (root.val < val) {           // insert to the right subtree if val > root->val
                root.right = insertIntoBST(root.right, val);
            } else {                        // insert to the left subtree if val <= root->val
                root.left = insertIntoBST(root.left, val);
            }
            return root;
        }
    ```

    - Time complexity: o(h) - height. o(n) in the worse, but o(log n) in the best
    - Space: o(h) - height. o(n) worse, o(log n) best

- deletion

  - 1. if the target node has no child, we simply remove the node
    2. if the target node has one child, we can use its child to replace itself
    3. if the target node has 2 children, replace the node with its in-order successor or predecessor node and delete that node

  - in-order successor - the leftmost child node the current node's right

    - 1. Determine which node to use - left or right
      2. when target value >= node's value, loop thru its right
      3. when target value < node's value, loop thru its left.  This node is a potential successor

  - predecessor - the rightmost child node in the current node's left

    - 1. determine which node to use - left or right
      2. when target value >= node's value, loop thru its right.  This node is a potential predecessor of the target node.
      3. when target value < node's value, loop thru its left

    ```java
    /**
         * findSuccesor - Helper function for finding successor
         * In BST, succesor of root is the leftmost child in root's right subtree
         */
        private TreeNode findSuccessor(TreeNode root) {
            TreeNode cur = root.right;
            while (cur != null && cur.left != null) {
                cur = cur.left;
            }
            return cur;
        }
        public TreeNode deleteNode(TreeNode root, int key) {
            // return null if root is null
            if (root == null) {
                return root;
            }
    
            // delete current node if root is the target node
            if (root.val == key) {
                // replace root with root->right if root->left is null	
                if (root.left == null) {
                    return root.right;
                }
    
                // replace root with root->left if root->right is null
                if (root.right == null) {
                    return root.left;
                }
    
                // replace root with its successor if root has two children
                TreeNode p = findSuccessor(root);
                root.val = p.val;
                root.right = deleteNode(root.right, p.val);
                return root;
            }
            if (root.val < key) {
                // find target in right subtree if root->val < key
                root.right = deleteNode(root.right, key);
            } else {
                // find target in left subtree if root->val > key
                root.left = deleteNode(root.left, key);
            }
            return root;
        }
    ```

    - Time complexity: o(h) - height of the tree; o(n) worse, o(log n) best
    - Space: o(h) - height of the tree; o(n) worse, o(log n) best





Example: Design a class to find the Kth largest element in the stream

- Solution 1: sort the array in a descending order and return the kth element
- But the array is a stream, and therefore we have to sort new elements as they come in.
- We could use BST as it guarantees that right subtree > node value, and left subtree < node value
- We have to create an insert function and search function
- Time complexity: if BST is well organized, o(h) = o(log n), height = log (total number of nodes).  Otherwise o(h) = o(n*h) or o(n^2) worse and o(n log(n)) best
- Space: o(h)

```java
class KthLargest {
    // insert a node into the BST
    private Node insertNode(Node root, int num) {
        if (root == null) {
            return new Node(num, 1);
        }
        if (root.val < num) {
            root.right = insertNode(root.right, num);
        } else {
            root.left = insertNode(root.left, num);
        }
        root.cnt++;
        return root;
    }

    private int searchKth(Node root, int k) {
        // m = the size of right subtree
        int m = root.right != null ? root.right.cnt : 0;
        // root is the m+1 largest node in the BST
        if (k == m + 1) {
            return root.val;
        }
        if (k <= m) {
            // find kth largest in the right subtree
            return searchKth(root.right, k);
        } else {
            // find (k-m-1)th largest in the left subtree
            return searchKth(root.left, k - m - 1);
        }
    } 
    
    private Node root;
    private int m_k;

    public KthLargest(int k, int[] nums) {
        root = null;
        for (int i = 0; i < nums.length; ++i) {
            root = insertNode(root, nums[i]);
        }
        m_k = k;
    }
    
    public int add(int val) {
        root = insertNode(root, val);
        return searchKth(root, m_k);
    }
}

class Node {    // the structure for the tree node
    Node left;
    Node right;
    int val;
    int cnt;    // the size of the subtree rooted at the node
    public Node (int v, int c) {
        left = null;
        right = null;
        val = v;
        cnt = c;
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
```





### Height balanced BST

or self-balancing BST - is a binary search tree that automatically keeps its height small, in the face of arbitrary item insertions and deletions. The height of a balanced BST is always log(N).  Also, the height of the two subtrees of every node never differs by more than 1.

Kinds of self-balancing BST

- Red Black Tree - https://en.wikipedia.org/wiki/Red%E2%80%93black_tree
- AVL Tree - https://en.wikipedia.org/wiki/AVL_tree
- Splay Tree - https://en.wikipedia.org/wiki/Splay_tree
- Treap - https://en.wikipedia.org/wiki/Treap

Properties of Self - Balancing BST

- o(log N) even in worse case

```java
class Solution {
  // Recursively obtain the height of a tree. An empty tree has -1 height
  private int height(TreeNode root) {
    // An empty tree has height -1
    if (root == null) {
      return -1;
    }
    return 1 + Math.max(height(root.left), height(root.right));
  }

  public boolean isBalanced(TreeNode root) {
    // An empty tree satisfies the definition of a balanced tree
    if (root == null) {
      return true;
    }

    // Check if subtrees have height within 1. If they do, check if the
    // subtrees are balanced
    return Math.abs(height(root.left) - height(root.right)) < 2
        && isBalanced(root.left)
        && isBalanced(root.right);
  }
};
```



#### Java implementation - TreeSet - search, insert, delete - O(log N)


It is combined with HashSet - unordered set - to gain performance.  When there are too many collisions in a hashtable, the search time would be o(N) - so it is better to use TreeSet in each bucket.
