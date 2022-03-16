Heap Operations
====

### Construct Max and Min Heap

- Java Heap is PriorityQueue

- Time O(N)

- Space O(N)

  ```java
  // In Java, a Heap is represented by a Priority Queue
  import java.util.Collections;
  import java.util.PriorityQueue;
  import java.util.Arrays;
  
  // Construct an empty Min Heap
  PriorityQueue<Integer> minHeap = new PriorityQueue<>();
  
  // Construct an empty Max Heap
  PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
  
  // Construct a Heap with initial elements. 
  // This process is named "Heapify".
  // The Heap is a Min Heap
  PriorityQueue<Integer> heapWithValues= new PriorityQueue<>(Arrays.asList(3, 1, 2));
  ```



### Insert elements into a Heap

- Time: O(log N)

- Space: O(1)

  ```java
  // Insert an element to the Min Heap
  minHeap.add(5);
  // Insert an element to the Max Heap
  maxHeap.add(5);
  ```

  

### Get the top element of a Heap

- Time: O(1)

- Space: O(1)

  ```java
  // Get top element from the Min Heap
  // i.e. the smallest element
  minHeap.peek();
  // Get top element from the Max Heap
  // i.e. the largest element
  maxHeap.peek();
  ```

  

### Delete the top element from a Heap

- Time: O(log N)

- Space: O(1)

  ```java
  // Delete top element from the Min Heap
  minHeap.poll();
  // Delete top element from the Max Heap
  maxheap.poll();
  ```

  

### Get the length of a Heap

- Time: O(1)

- Space: O(1)

  ```java
  // Length of the Min Heap
  minHeap.size();
  // Length of the Max Heap
  maxHeap.size();
  
  // Note, in Java, apart from checking if the length of the Heap is 0, we can also use isEmpty()
  // If there are no elements in the Heap, isEmpty() will return true;
  // If there are still elements in the Heap, isEmpty() will return false;
  ```

  

### Complexity

| Heap method            | Time complexity | Space complexity |
| ---------------------- | --------------- | ---------------- |
| Construct a Heap       | *O*(*N*)        | *O*(*N*)         |
| Insert an element      | *O*(log *N*)    | *O*(1)           |
| Get the top element    | *O*(1)          | *O*(1)           |
| Delete the top element | *O*(log *N*)    | *O*(1)           |
| Get the size of a Heap | *O*(1)          | *O*(1)           |

*N* is the number of elements in the heap.


Applications of Heap
====

## Heap Sort

Min Heap - Ascending order

1. Heapify all elements into Min Heap
2. Record and delete top element
3. Put the top element into an array T that stores all sorted elements. Now, the Heap will remain a Min Heap
4. Repeat steps 2 and 3 until Heap is empty. The array T will contain all elements sorted in ascending order

```java
void heapSort(int[] array) {  
    int n = array.length;
    for (int i = n / 2 - 1; i >= 0; i--)  
        sift_down(a, n, i);  
  
    // One by one extract an element from heap  
    for (int i = n - 1; i >= 0; i--) {  
        /* Move current root element to end by swapping them */  
        swap(a, i, 0);
          
        sift_down(a, i, 0);  
    }  
}  

void sift_down(int a[], int n, int i)  
{  
    int largest = i; 			// Initialize largest as root  
    int left = 2 * i + 1; 		// left child  
    int right = 2 * i + 2; 		// right child  
    
    // If left child is larger than root  
    if (left < n && a[left] > a[largest])  
        largest = left;  
    
    // If right child is larger than root  
    if (right < n && a[right] > a[largest])  
        largest = right; 
    
    // If root is not largest  
    if (largest != i) {  
        swap(a, i, largest);
          
        sift_down(a, n, largest);  
    }  
}  

void swap(int[] a, int l, int r) {
    int tmp = a[r];
    a[r] = a[l];
    a[l] = tmp;
}
```



Max Heap - Descending order

1. Heapify all elements into Max Heap
2. Record and delete the top element
3. Put the top element into an array T that stores all sorted elements. Now, the Heap will remain a Max Heap
4. Repeat steps 2 and 3 until the Heap is empty. Array T will contain all elements sorted in descending order
5. Time: O(N log N)
6. Space O(N)



## Top K Problem

- Use Heap to obtain top K largest or smallest elements
- Solution to top K largest:
  1. Construct a Max Heap
  2. Add all elements into Max Heap
  3. Traverse and delete the top element using pop() and poll() and store the value into the resulting array T
  4. Repeat step 3 until we removed the K largest elements
- Solution to top K smallest
  1. Construct a Min Heap
  2. Add all elements into the Min Heap
  3. Traverse and delete the top element using pop() or poll() and store the value into the resulting array T
  4. Repeat step 3 until we have removed the Kth smallest elements
- Time: O( K log N + N)
  - Steps 1 and 2 require us to construct a Heap - O(N)
  - Each element removed from Heap - O(log N)
  - This process is repeated K times
- Space: O(N)

```java
```



#### Top K problem Template 2

- Solution to top K largest

  1. Construct Min Heap with size K

  2. Add elements to the Min Heap one by one

  3. When there are K elements in the Min Heap, compare the current element with the top element of the Heap

     - If the current element is no larger than `<=` the top element, drop it and proceed to the next element
     - If the current element is larger than `>` the Heap's top element, delete the Heap's top element and add the current element to the Min Heap

  4. Repeat steps 2 and 3 until all elements have been iterated

  5. Now the K elements in the Min Heap are the K largest elements

     

- Solution to the Top K smallest

  1. Construct Max Heap with size K
  2. Add elements to the Max Heap one by one
  3. When there are K elements in the Max Heap, compare the current element with the top element of the Heap
     - If the current element is no smaller than `>=` the top element of the Heap, drop it and proceed to the next
     - if the current element is smaller than `<` the top element of the Heap, delete the top element of Heap and add the current element to the Max Heap
  4. Repeat steps 2 and 3 until all elements have been iterated
  5. Now the K elements in the Max Heap are the K smallest

- Time: O(N log K)
- Space: O(K)

```java
```




## Kth element



#### Template 1

Solution of the K-th largest element:

1. Construct a Max Heap.
2. Add all elements into the Max Heap.
3. Traversing and deleting the top element (using pop() or poll() for instance).
4. Repeat Step 3 K times until we find the K-th largest element.

Solution of the K-th smallest element:

1. Construct a Min Heap.
2. Add all elements into the Min Heap.
3. Traversing and deleting the top element (using pop() or poll() for instance).
4. Repeat Step 3 K times until we find the K-th smallest element.

Time: O( K log N + N)

- Steps one and two require us to construct a Max Heap which requires O(N) time using the previously discussed heapify method. Each element removed from the heap requires O(log N) time; this process is repeated K times. Thus the total time complexity is O(K log N + N).

Space: O(N)

```java
    public int findKthLargest(int[] nums, int k) {
        // init heap by max-heap
        PriorityQueue<Integer> heap = 
            new PriorityQueue<>((a, b) -> (b - a));	// reverse order for max-heap

        // keep k largest elements in the heap
        for (int n: nums) {
          heap.add(n);
        }
        
        // since it is arranged at the largest, we remove the root node K times
        for (int i=k; i>1; i--) heap.poll();

        // finally the last poll is the Kth largest
        return heap.poll();        
  }
```



#### Template 2

Solution of the K-th largest element:

1. Construct a Min Heap with size K.
2. Add elements to the Min Heap one by one.
3. When there are K elements in the “Min Heap”, compare the current element with the top element of the Heap:
   - If the current element is not larger than the top element of the Heap, drop it and proceed to the next element.
   - If the current element is larger than the Heap’s top element, delete the Heap’s top element, and add the current element to the “Min Heap”.
4. Repeat Steps 2 and 3 until all elements have been iterated.  Now the top element in the Min Heap is the K-th largest element.

Solution of the K-th smallest element:

1. Construct a Max Heap with size K.
2. Add elements to the Max Heap one by one.
3. When there are K elements in the Max Heap, compare the current element with the top element of the Heap:
   - If the current element is not smaller than the top element of the Heap, drop it and proceed to the next element;
   - If the current element is smaller than the top element of the Heap, delete the top element of the Heap, and add the current element to the Max Heap.
4. Repeat Steps 2 and 3 until all elements have been iterated. Now the top element in the Max Heap is the K smallest element.

Time: *O*(*N* log *K*)

- Steps one and two will require *O*(*K* log *K*) time if the elements are added one by one to the heap, however using the heapify method, these two steps could be accomplished in *O*(*K*) time. Steps 3 and 4 will require *O*(log *K*) time each time an element must be replaced in the heap. In the worst-case scenario, this will be done N - K*N*−*K* times. Thus the total time complexity is *O*((*N*−*K*) log *K* + *K* log *K*) which simplifies to *O*(*N* log *K*).

Space: O(K)

```java
    public int findKthLargest(int[] nums, int k) {
        // init heap 'the smallest element first'
        PriorityQueue<Integer> heap =
            new PriorityQueue<>((a, b) -> (a - b));		// min-heap

        // keep k largest elements in the heap
        for (int n: nums) {
          heap.add(n);				// adds value in ascending order
          if (heap.size() > k)
            heap.poll();			// removes the mininum value / root node
        }

        // w
        return heap.poll();        
  }
```

