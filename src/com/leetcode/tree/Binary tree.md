Binary Tree
====

## Traversal

Strategies for scanning a tree:

BFS - we scan through the tree level by level, from left to right, top to bottom.  Nodes on higher level will be visited before the lower levels

DFS - we adopt depth as the priority, so we would start from a root, and reach all the way down to a certain leaf, and then back to the root to reach another branch. It can be further segmented into preorder, inorder, postorder.

### Preorder

- is a DFS method, that scans from top -> bottom, left -> right

- using recursive approach

  - visit the root node, traverse the left subtree, then traverse the right subtree

  ```java
  public void preorder(Node root) {
      // visit the root node
      root.val;
      if (root.left != null) preorder(root.left);
      if (root.right != null) preorder(root.right);
  }
  ```

- using iterative approach

  - visit the root node, traverse the right subtree, traverse the left subtree

  ```java
  Stack<TreeNode> stack = new Stack();
  stack.push(root);
  while(!stack.isEmpty()) {
  	TreeNode curr = stack.pop(); // remove the first entry
  	// visit value curr.val
  	if (curr.right != null) stack.push(curr.right);	// right first, because FILO, so when it pops left first
  	if (curr.left != null) stack.push(curr.left); // before left
  }
  ```

  

### Inorder

- is a DFS method  that scans from left subtree node, bottom -> top, visit the node, then right subtree node, bottom -> top

- this is typically used in binary search tree BST. we can retrieve all the data in sorted order using this traversal.

- using recursive approach

  - traverse the left subtree, then visit the root node, then traverse the right subtree

  ```java
  public void inroder(Node node) {
      if (node.left != null) inorder(node.left);
      // visit the node
      node.val;
      if (node.right != null) inorder(node.right);
  }
  ```

- using iterative approach

  - traverse the left subtree, visit the node, then the right of the node (do not traverse the subtree)

  ```java
  Stack<TreeNode> stack = new Stack();
  TreeNode n = root;
  while (!stack.isEmpty() || n != null) {
      // traverse the left subtree of the node and push it into the stack
      while (n != null) {
          stack.push(n);
          n = n.left;
      }
      // visit the node and remove it from the stack
      n = stack.pop();
      n.val;
      
      n = n.right;
  }
  ```



### Postorder

- is a DFS method that scans from bottom -> top, left -> right by:

- when deleting a node in a tree, the deletion process will be in post-order. ie. delete its left child and its right child before deleting the node itself.

- widely used in mathematical expression to parse 

- using recursive approach:

  - traversing the left subtree first, then traverse the right subtree, then visit the root node

  ```java
  public void postorder(Node node) {
      if (node.left != null) postorder(node.left);
      if (node.right != null) postorder(node.right);
      // visit the node
      node.val;
  }
  ```

- using iterative approach

  - traverse the left subtree of the node, visit the right node, then the middle node

  ```java
  Stack<TreeNode> stack = new Stack();
  TreeNode current = root;
  TreeNode previous = null;
  while (!stack.isEmpty() || current != null) {
      // traverse the left subtree of the node and push it to the stack
      while (current != null) {
          stack.push(current);
          current = current.left;
      }
      // no right node, or right node was visited previously
      TreeNode right = s.peek().right;
      if (right == null || right == previous) {
          previous = stack.pop();		// this is the current node
          previous.val;	        // visit the current node
      }
      else {
          current = right;	// this is the right node of the current
      }
  }
  ```
  
  



### Level order

- is a BFS method that scans left -> right, top -> bottom by:

- using the BFS, iterative approach

  - traverse the tree, level by level, using queue - FIFO

  ```java
  Queue<TreeNode> queue = new Queue();
  queue.offer(root);
  while (!queue.isEmpty()) {
      TreeNode curr = queue.poll();
      if (curr.left != null) queue.offer(curr.left);
      if (curr.right != null) queue.offer(curr.right);
  }
  ```


- another case of BFS, iterative approach

  - traverse the tree, level by level, using queue - FIFO

  ```java
  Queue<TreeNode> queue = new Queue();
  queue.offer(root);
  while (!queue.isEmpty()) {
      int size = queue.size();
      List<Integer> subAns = new LinkedList();
      for (int i = 0; i < size; ++i) {        // traverse nodes in the same level
          TreeNode cur = queue.poll();
          subAns.add(cur.val);                // visit the root
          if (cur.left != null) {
              q.offer(cur.left);              // push left child to queue if it is not null
          }
          if (cur.right != null) {
              q.offer(cur.right);             // push right child to queue if it is not null
          }
      }
      ans.add(subAns);
  }
  ```

- using recursive approach
  - given a root node, add its children to the queue

```java
public void bfs(Queue<TreeNode> queue) {
    if (queue == null || queue.isEmpty()) return;
    
    int size = queue.size();
    while (size > 0) {
	    TreeNode c = queue.poll();
        // process the children's node value
        
        if (c.left != null) queue.offer(c.left);
        if (c.right != null) queue.offer(c.right);
        size--;
    }
    // this queue will hold the next level of depth
	bfs(queue);
}
```





### Difference between top-down and bottom-up 

Top-down is a recursive call in which we pass params to the function as we go in depth further. This is *like* pre-order traversal where we visit the node and come up with some values and pass this values to the children nodes recursively.

- When to use?
  - Can you determine some parameters to help the node know its answer?
  - Can you use these parameters and the value of the node itself to determine what should be the parameters passed to its children?

`top_down(root, params)`:

```JAVA
1. return specific value for null node
2. update the answer if needed                      // answer <-- params
3. left_ans = top_down(root.left, left_params)      // left_params <-- root.val, params
4. right_ans = top_down(root.right, right_params)   // right_params <-- root.val, params
5. return the answer if needed                      // answer <-- left_ans, right_ans
```

Example:

```java
private int answer; // don't forget to initialize answer before call maximum_depth
private void maximum_depth(TreeNode root, int depth) {
    if (root == null) {
        return;
    }
    if (root.left == null && root.right == null) {
        answer = Math.max(answer, depth);
    }
    maximum_depth(root.left, depth + 1);
    maximum_depth(root.right, depth + 1);
}
```





Bottom-up is also a recursive call in which we first call the function recursively for all the child nodes and then after, come up with the answer according to the returned values of the child nodes and the current node itself. This is *like* postorder traversal.

- When to use? 
  - For a node in a tree, if you know the answer of its children, can you calculate the answer of that node?

`bottom_up(root)`:

```java
1. return specific value for null node
2. left_ans = bottom_up(root.left)      // call function recursively for left child
3. right_ans = bottom_up(root.right)    // call function recursively for right child
4. // process the answer
4. return answers                       // answer <-- left_ans, right_ans, root.val
```

Example:

```java
public int maximum_depth(TreeNode root) {
    if (root == null) {
        return 0;                                   // return 0 for null node
    }
    int left_depth = maximum_depth(root.left);
    int right_depth = maximum_depth(root.right);
    return Math.max(left_depth, right_depth) + 1;   // return depth of the subtree rooted at root
}
```

