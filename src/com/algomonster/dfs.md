# Overview

Recursion is one of the most important concepts in computer science. Simply speaking, recursion is the process of a function calling itself. 

This example highlights the key components in writing a correct recursive function:

1. **Base case/exit**, e.g. Carly doesn't call anyone else, she just says yes and hangs up.
2. **Recursive call, i.e. calling the function itself with different argument**, e.g. Ben calling Sheldon.

## Recursion and Stack

How does the computer accomplish such a feat as calling a function itself? The answer is quite simple - it uses a stack behind the scene to keep track of where things are. For this particular problem, the factorial recursive function roughly translates to this when executed:

```python
1def factorial_stack(n):
2    stack = []
3    # push each call to a stack
4    # top of the stack is base case
5    while n > 0:
6        stack.append(n)
7        n -= 1
8
9    res = 1
10    # pop and use return value until stack is empty
11    while stack is not None:
12        res *= stack.pop()
13
14    return res
```

A computer's internal stack is called **"call stack"** and the data it pushes onto a call stack are called "stack frame"s. Strictly speaking, stack frames on a call stack represent the function you are calling and its arguments. 

# Depth First Search

With a solid understanding of recursion under our belt, we are now ready to tackle one of the most useful technique in coding interviews - Depth First Search (DFS). As the name suggests, DFS is a bold search. We go as deep as we can to look for a value, and when there is nothing new to discover, we retrace our steps to find something new. To put it in a term we already know, the **pre-order traversal** of a tree is DFS. Let's look at a simple problem of searching for a node in a binary tree whose value is equal to `target`.

```java
public static Node dfs(Node root, int target) {
2    if (root == null) {
3      return null;
4    }
5    if (root.val == target) {
6      return root;
7    }
8    // return non-null return value from the recursive calls
9    Node left = dfs(root.left, target)
10    if (left != null) {
11      return left;
12    }
13    // at this point, we know left is null, and right could be null or non-null
14    // we return right child's recursive call result directly because
15    // - if it's non-null we should return it
16    // - if it's null, then both left and right are null, we want to return null
17    return dfs(root.right, target)
18}
```

Being able to visualize recursion and call stack of a DFS function in your mind is **extremely important**. Please take a minute to make sure you internalize it and come back to this page any time you need to look at it.

------

The example above also introduces two other concepts, backtracking and divide and conquer. The action of retracing steps (e.g. from `2` we first visited `3` depth first and retraced back and visit the other child `4`) is called [**backtracking**](https://en.wikipedia.org/wiki/Backtracking). Backtracking and DFS are similar concept and essentially the same thing since in DFS you always "backtrack" after exploring a deeper node. It's like saying I *program* computers by doing *coding*. If we really want to make the distinction, then backtracking is the concept of retracing and DFS is the algorithm that implements it. In computer science textbooks [1,2,3], backtracking is often mentioned and associated with combinatorial search problems. We will do the same in the course.

We have two recursive calls `dfs(root.left)` and `dfs(root.right)`, and return based on results from the recursive calls. This is also a [**divide and conquer**](https://en.wikipedia.org/wiki/Divide-and-conquer_algorithm) algorithm, i.e. splitting into subproblems of the same type (search in left and right children) until they are simple enough to be solved directly (null nodes or found target) and combine the results from these subproblems (return non-null node). We'll investigate divide and conquer more in a later module.

## When to use DFS

### Tree

DFS is essentially pre-order tree traversal.

- Traverse and find/create/modify/delete node
- Traverse with return value (finding max subtree, detect balanced tree)

### Combinatorial problems

DFS/backtracking and combinatorial problems are a match made in heaven (or silver bullet and werewolf 😅). As we will see in the [Combinatorial Search](https://algo.monster/problems/backtracking) module, combinatorial search problems boil down to searching in trees.

- How many ways are there to arrange something
- Find all possible combinations of ...
- Find all solutions to a puzzle

### Graph

Trees are special graphs that have no cycle. We can still use DFS in graphs with cycles. We just have to record the nodes we have visited and avoiding re-visiting them and going into an infinite loop.

- Find a path from point A to B
- Find connected components
- Detect cycles

## DFS on tree

## Think like a node

The key to solving tree problems using DFS is to think from the perspective of a node instead of looking at the whole tree. This is in line with how recursion is written. Reason from a node's perspective, decide how the current node should be proceeded, then recurse on children and let recursion takes care of the rest.

When you are a node, the only things you know are 1) your value and 2) how to get to your children. So the recursive function you write manipulates these things.

The template for DFS on tree is:

```python
1function dfs(node, state):
2    if node is null:
3        ...
4        return
5
6    left = dfs(node.left, state)
7    right = dfs(node.right, state)
8
9        ...
10
11    return ...
```

## Defining the recursive function

Two things we need to decide to define the function:

### 1. Return value (passing value up from child to parent)

What do we want to return after visiting a node. For example, for the max depth problem this is the max depth for the current node's subtree. If we are looking for a node in the tree, we'd want to return that node if found, else return null. Use the return value to pass information from children to parent.

### 2. Identify state(s) (passing value down from parent to child)

What states do we need to maintain to compute the return value for the current node. For example, to know if the current node's value is larger than its parent we have to maintain the parent's value as a state. State becomes DFS's function arguments. Use states to pass information from parent to children.

Consider the problem of pretty-print a binary tree. Given directory tree

![img](https://algomonster.s3.us-east-2.amazonaws.com/dfs_on_trees_intro/dfs_on_trees_intro_1.png)

We want to "pretty-print" the directory structure with indents like this:

```
1/
2  foo
3    baz
4  bar
```

We can pass the current indent level as a state of the recursive call.

```
1indent_per_level = '  '
2function dfs(node, indent_level):
3  ...
4  current_indent_level = indent_level + indent_per_level
5  print(current_indent_level + node.val)
6  dfs(node, current_indent_level)
```

## Using return value vs. global variable

Consider the problem of finding the maximum value in a binary tree.

![img](https://algomonster.s3.us-east-2.amazonaws.com/dfs_on_trees_intro/dfs_on_trees_intro_2.png)

`11` is the large value.

### Using return value (divide and conquer)

One way to solve it is to use return value to pass the maximum value we have encountered back to parent node, and let the parent node compare it with the return value from the other child. This is more of a divide and conquer approach.

```python
1function dfs(node):
2  if node is null:
3    return MIN_VALUE
4
5  left_max_val = dfs(node.left)
6  right_max_val = dfs(node.right)
7return max(node.val, left_max_val, right_max_val)
```

### Using global variable

Another way to solve it is to traverse the tree while keeping a global variable that keeps track of the maximum value we have encountered. After the dfs, we return the global variable.

```python
1...
2# global variable to record current max value
3# initialize to minimum value possible so any node will be larger
4max_val = MIN_VALUE
5
6function dfs(node):
7  if node is null:
8    return
9
10  if node.val > max_val: # update the global variable if current value is larger
11    max_val = node.val
12
13  # recurse
14  dfs(node.left)
15  dfs(node.right)
16
17function get_max_val(root)
18  dfs(root) # kick off dfs from root node
19  return max_val
```

It's more of a personal preference which one you use. One could argue global variables are bad and therefore the divide and conquer. However, sometimes it's easier to use a global variable. Recall that divide and conquer has two steps - partition and merge. If the merge step is complex, then using a global variable might simplify things.

## Templates:

#### Max depth of a tree

count the maximum depth of a tree

#### Visible Tree node

count the number of visible nodes

#### Balanced binary tree

#### Serializing and deserializing binary tree

#### Lowest common ancestor



### Binary search tree

#### Validate BST

#### Insert into BST

#### Invert Binary Tree

#### Kth smallest number in BST

#### Flatten Binary Tree into Linked List
