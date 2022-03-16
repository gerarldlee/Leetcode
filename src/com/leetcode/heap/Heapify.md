
### Converting Heap into an Array

- parent node - `n / 2`
- left child - `n * 2`
  - in a zero-index array heap - `n * 2 + 1`

- right child - `n * 2 + 1`
  - in a zero-index array heap - `n * 2 + 2`

- why do we choose non-zero index array i.e. our array root value starts at `array[1]`
  - because we put the size of the entire array in the `array[0]`

- determine if a node is a leaf node from the array 
  - - any node `i`, that's greater than `n/2`, is a leaf node




Heapify
====


- is the process of re-arranging a heap to maintain the heap property i.e. the value of the root node is greater or less than or equal to the keys of its children nodes. If the root node's value is not more extreme, swap it with the most extreme child value, then recursively heapify that child subtree.  The child subtrees must be heaps to start

- simply convert group of data into Heap

```java
public void heapify(int[] array) {
    // we only heapify non-leaf nodes
    // it does not make sense for the leaf nodes to be "sifted down" 
    since theres no way to go down
    for (int i=array.length/2-1; i >= 0; i--) {		
        siftDown(array, i);
    }
}
```



### Sift Down

- to move the value down the tree by successively exchanging the value with the smaller of its two children.
- the operation continues until the value reaches a position where it is less than both its children or, until it reaches a leaf
- Used for deletions in Min Heap or Max Heap

```java
void siftDown(int[] a, int k) {
    int left_child_index = k * 2;		// left child of k = k * 2, right = left + 1
    
    // check if left child is valid 
    // (doesn't make sense for the right child since we are achieving complete binary tree)
    while (left_child_index > 0 && left_child_index < a.length) {		
        int smaller_child_index = left_child_index;
        int right_child_index = k * 2 + 1;
        // assign the smaller child if right or left
        if (right_child_index > 0 && a[right_child_index] < a[left_child_index]) {
            smaller_child_index = right_child_index;
        }
        // if k > smaller child, swap it
        if (a[smaller_child_index] < a[k]) {
            swap(smaller_child_index, k);
            k = smaller_child_index;
            left_child_index = k * 2;
        }
        else {	/
            break;
        }
    }
}
```



### Sift Up

- to move the value up the path towards the root by successively exchanging the value with the value in the node above.
- the operation continues until the value reaches a position where to is less than tis parent, or when it reach the root node
- used for insertions in Min Heap or Max Heap

```java
while (inserted node value < value of its parent node) {
	swap the value of the respective node
}
```

```java
void siftUp(iint k) {
	int parent = k / 2;
	while (parent > 0 && a[k] < a[parent]) {
        int tmp = a[parent];
        a[parent] = a[k];
        a[k] = tmp;
        k = parent;
        parent = k / 2;
	}
}
```
