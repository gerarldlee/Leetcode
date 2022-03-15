N-nary Tree
====



A rooted tree in which each node has no more than N children.

![img](https://s3-us-west-1.amazonaws.com/s3-lc-upload/explore/cards/n-ary-tree/nary_tree_example.png)



- A Trie is also an N-nary tree.
- A binary tree is a special form of N-nary tree



## Traversal

### Preorder

- visit the root node, traverse the children subtree one by one.  A->B->C->E->F->D->G.

- time: o(n)

- space: o(n)

  ```java
    public List<Integer> preorder(Node root) {
      LinkedList<Node> stack = new LinkedList<>();
      LinkedList<Integer> output = new LinkedList<>();
      if (root == null) {
        return output;
      }
  
      stack.add(root);
      while (!stack.isEmpty()) {
        Node node = stack.pollLast();
        output.add(node.val);
        Collections.reverse(node.children);
        for (Node item : node.children) {
          stack.add(item);
        }
      }
      return output;
    }
  ```

  

### Inorder

- there are no in-order traversal with n-nary tree

### Postorder

- traverse the children subtree one by one, then visit the root node. B->E->F->C->G->D->A.

- BFS: top->bottom, left->right

- DFS: bottom->top, left->right

- time: o(n)

- space: o(n)

  ```java
    public List<Integer> postorder(Node root) {
      LinkedList<Node> stack = new LinkedList<>();
      LinkedList<Integer> output = new LinkedList<>();
      if (root == null) {
        return output;
      }
  
      stack.add(root);
      while (!stack.isEmpty()) {
        Node node = stack.pollLast();
        output.addFirst(node.val);
        for (Node item : node.children) {
          if (item != null) {
            stack.add(item);    
          } 
        }
      }
      return output;
    }
  ```

  

### Level order

- traverse the tree, level by level, using queue / BFS. A->B->C->D->E->F->G.

- BFS: top->bottom, left->right

- time: o(n)

- space: o(n)

  ```java
    public List<Integer> postorder(Node root) {
      LinkedList<Node> stack = new LinkedList<>();
      LinkedList<Integer> output = new LinkedList<>();
      if (root == null) {
        return output;
      }
  
      stack.add(root);
      while (!stack.isEmpty()) {
        Node node = stack.pollLast();
        output.addLast(node.val);
        for (Node item : node.children) {
          if (item != null) {
            stack.add(item);    
          } 
        }
      }
      return output;
    }
  ```

  

## Solutions to N-nary Tree

### Top-down

Top-down means that in each recursion level, we will visit the node first to come up with some values and pass these values to its children when calling the function recursively.

```
function top_down(root, params) {
    return specifc value for null node
    update the answer if needed
    for each child node in root.children[k] {
        ans[k] = top_down(root.children[k], new_params[k])
    }
    return ans[k]
}
```



### Bottoms-up

Bottoms-up means that in each recursion level, we will first call the function recursively for all children nodes and then come up with the answer according to the return values and the value o the root node itself.

```
return specific value for null node
for each child node root.children[k]:
     ans[k] = bottom_up(root.children[k])    // call function recursively for all children
return answer                                // answer <- root.val, all ans[k]
```

