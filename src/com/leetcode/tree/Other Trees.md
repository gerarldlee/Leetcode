Other Self-Balancing BST
====


## Red Black Trees

A self-balancing BST where each node contains an extra bit for denoting the color of the node, either red or black

Use cases:

- to implement finite maps
- to implement Java packages: `java.util.TreeMap` and `java.util.TreeSet`
- in Linux kernel

Satisfies the following properties:

1. Red/Black property - every node is colored either red or black
2. Root property - the root is black
3. Leaf property - all leaf is black
4. Red property - if a red node has children, then, the children are always black
5. Depth property - for each node, any simple path from this node to any of its descendant leaf has the same black-depth (the number of black nodes)

![red-black tree](https://cdn.programiz.com/sites/tutorial2program/files/red-black-tree_0.png)

Each node has the following attributes:

- color
- key
- left_child
- right_child
- parent (except root node)

How does it maintain self-balancing?

- the red-black color is meant for balancing the tree
- the limitations put on the node color ensure that any simple path from the root to a leaf is not more than twice as long as any other such path.  It helps maintain the self-balancing property of the red-black tree.

https://www.programiz.com/dsa/red-black-tree



## AVL Trees

A self-balancing binary search tree in which each node maintains extra information called a balance factor whose value is either `-1, 0, or 1`

![avl tree](https://cdn.programiz.com/sites/tutorial2program/files/avl-tree-final-tree-1_0_2.png)

Use cases:

- for indexing large records in databases
- for searching in large databases

https://www.programiz.com/dsa/avl-tree





## B-Tree

A special type of self-balancing search tree in which each node can contain more than one key and can have more than two children.  This is a generalized form of BST i.e. height-balanced m-way tree.

![B-tree example](https://cdn.programiz.com/sites/tutorial2program/files/b-tree.png)

https://en.wikipedia.org/wiki/B-tree

Use Cases:

- databases and file systems
- multilevel indexing
- to store blocks of data (secondary storage media)





## B+ Tree

An advance form of a self-balancing tree in which all the values are present in the leaf level.

With the use of multilevel indexing, it makes accessing the data easier and faster

![Multilevel Indexing using B+ tree](https://cdn.programiz.com/sites/tutorial2program/files/multilevel-indexing.png)

https://en.wikipedia.org/wiki/B%2B_tree

Use Cases:

- multilevel indexing
- faster operations on the tree (insertion, deletion, search)
- database indexing
