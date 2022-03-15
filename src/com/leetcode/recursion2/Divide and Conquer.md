Divide and Conquer
====


- works recursively
- breaks down a problem into 2 or more subproblems
- combines the result

Template:
1. divide problem into subproblems
2. solve the subproblem, create base case
3. combine the result from the subproblems



Example 1: Merge sort 
[1,5,3,2,8,7,6,4]

1. divide into subproblems
[1,5,3,2] [8,7,6,4]
[1,5][3,2] [8,7][6,4]
[1][5][3][2] [8][7][6][4] <-- base case when null or single element!

2. conquer / sort the subproblems
[1,5][2,3] [7,8][4,6]	<-- we merge sort the result of 1 and 5; 2 and 3, 7 and 8, 4 and 6
[1,2,3,5] [4,6,7,8]		<-- same process goes as we get out of recursion, we merge from the result of [1,5] and [2,3]

3. merge / combine them
[1,2,3,4,5,6,7,8]

Time: O(N log N)
Space: O(N)

### Notes:

1. Tree related problems can be solved using divide and conquer i.e. binary tree, binary search tree

Binary Search Tree 
1. All values on the left subtree of a node should be less than the value of the node.
2. All values on the right subtree of a node should be greater than the value of the node.
3. Both the left and right subtrees must also be binary search trees. (Recursively!)

Step 1 (divide): we divide the tree into 2 subtrees, left and right child
Step 2 (conquer): we recursively validate each subtree if its indeed a binary search tree
Step 3 (combine): upon the results of the subproblems from Step 2, we return true if and only if both subtrees are valid BST

Step 2 (combine): would reach a base case where the subtree is either empty or contains single node, which is a valid BST itself
	- if it contains a single node, then that node should be less than the previous nodes value if its in the left of the previous node, or higher than the previous node value if its in the right of the previous node



Example 2: Search a 2D Matrix 2

Given a 2D matrix [m x n], that has properties:
- sorted integers for each row, from left to right
- sorted integers for each column, from top to bottom

| 1    | 2    | 3    | 4    | 5    | 6    | 7    | 8    |
| 9    | 10   | 11   | 12   | 13   | 14   | 15   | 16   |
| 17   | 18   | 19   | 20   | 21   | 22   | 23   | 24   |
| 25   | 26   | 27   | 28   | 29   | 30   | 31   | 32   |
| 33   | 34   | 35   | 36   | 37   | 38   | 39   | 40   |
| 41   | 42   | 43   | 44   | 45   | 46   | 47   | 48   |
| 49   | 50   | 51   | 52   | 53   | 54   | 55   | 56   |
| 57   | 58   | 59   | 60   | 61   | 62   | 63   | 64   |

Find: 35

Step 1 (divide): divide matrix into 4 sub-matrix, choose a pivot point based on a row and column
Step 2 (conquer): recursively look into each sub-matrix, and search for the desired target
Step 3 (combine): if we find the target in each of the sub-matrix, we stop the search and return result immediately

Base case: 

- when the input matrix is empty or it contains single element. In the single element, means its found already.
- One can choose the middle point of row and column as pivot points to divide the matrix.

Since the smallest and largest element is located top left and bottom right (matrix is sorted), this applies to the sub-matrix as well. 
1. if our target = pivot, we found our target and return result immediately
2. If our target < pivot, we can discard the bottom right sub-matrix, as all element in that region must be greater than the pivot or equal to the edge of it.
3. In the same way, if our target is greater than the pivot, we can discard the top-left sub-matrix.



Example 3: Quick Sort

Divide: choose a pivot, and partition or divide the list into 2. one contains values less than pivot, the other contains values > pivot.

Conquer: recursively sort the two sublist

Combine: We are sure that the 2 sublist are sorted, so combine the result obtained in Conquer to get the final sorted list.

Base case: either input list is empty or contains a single element. In this case, its considered sorted already.








## Master Theorem


This is used to deriving the time-complexity of DP algorithms. This does not apply to recursion algorithms.

t(n) = a t(n/b) + f(n)

