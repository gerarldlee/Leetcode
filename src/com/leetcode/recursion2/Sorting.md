Sorting
====

Stability - if the value is equal, then the input's relative order will be preserved in the output.


## Popular sorts by average case in time complexity

### time: o(n^2), space: o(1)

#### Bubble sort 
- compares the value to the next value. if the next value is less than the value, then swap them. repeat until the array size is finished, and repeat until there's nothing to sort and everything is in order.

worse case: o(n^2) - it will still traverse every value and compare them to sort things out.

best case: o(n) - if the array is already sorted

stability: yes

```java
for (int i=0; i < array.length; i++) {
    for (int j=1; j < array.length-i; j++) {
        if (array[j] < array[j-1]) {
            int tmp = array[j-1];
            array[j-1] = array[j];
            array[j] = tmp;
        }
    }
}
```



#### Insertion sort 
- iterates and takes the current element in the list one at a time. comparing and inserting them to the already sorted array one by one. swapping or moving the elements greater than its value.

worse case: o(n^2) - if its sorted descending, because it needs to be shifted to the right for every item element

best case: o(n) - if its already sorted, so it does not need to be shifted to the right

stability: yes

```java
for (int i=1; i < array.length; i++) {
    int j = i-1;
    int tmp = array[i];
    // swap values for those array[j] > tmp, so that we can insert the tmp (current position),
	while (j >= 0 && array[j] > tmp) {
        array[j+1] = array[j];
        j--;
    }
    // lastly, insert the array[i]
    array[j+1] = tmp;
}
```



#### Selection sort 
- selects the smallest value in each iteration and swaps them to the current starting point in each iteration.

worse case: o(n^2)

best case: o(n^2)

stability: no - because it swaps the minimum element to the current starting point position, and if the value of the current starting point is non-unique, then this starting point may have been the place of the previous minimum element

```java
for (int start=0; start < array.length; start++) {
    // select the smallest
    int min = start;
    for (int j=start+1; j < array.length; j++) {
        if (array[j] < array[min]) {
            min = j;
        }
    }
    // swap them to the current starting position
    if (min != start) {
        array[j] = array[start];
        array[start] = min;
    }
}
```





### time: o(n log(n))

#### Merge sort 
- is a divide and conquer algorithm that recursively divides the unsorted array into 2.  when there's nothing more to divide ie. it reaches its base case of array is null, it sorts, merge and combines the result.
- can be easily extended to handle data sets that cannot fit in the RAM, where bottleneck cost is reading and writing the input on disk, not comparing and swapping them

worse case: o(n log(n))

best case: o(n log(n)) - even when the array is sorted, it will still divide the array and sort them at least n log(n) times.

space: o(n) - because of the stack that's created

stability: yes

notes: since its a divide and conquer, it is highly parallelizable - up to o(log n) with using 

[Three-Hungarian]: https://en.wikipedia.org/wiki/Hungarian_algorithm

```java
public int[] merge(int[] array) {
    // base case
    if (array.length <= 1) return array;
    
    // divide in half, create left and right array
    int mid = array.length / 2;
    int[] left = merge(Arrays.copyOfRange(array, 0, mid));
    int[] right = merge(Arrays.copyOfRange(array, mid, array.length));
    
    int[] merge_result = new int[left.length + right.length];
	// merge sort them
    int l, r, c;
    while (l < left.length && r < right.length) {
        if (left[l] < right[r]) {
	        merge_result[c++] = left[l++];
        }
        else {
	        merge_result[c++] = right[r++];
        }
    }
    // copy the remaining values
    while (l < left.length) {
        merge_result[c++] = left[l++];
    }
	while (r < right.length) {
        merge_result[c++] = right[r++];
    }
    return merge_result;
}
```



#### Quick sort 
- is a divide and conquer algorithm that takes a pivot, usually the last value in the array, and this ultimately divide the array into 2 partitions by swapping the left and right pointer value to the left or right of the pivot 
- everything less than pivot, pivot, and everything higher than pivot. The pivot is always in its right place, then recursively do this in the left, and the right partition. 
- The base case is when there's no more element in the array.

worse case: o(n^2)

best case: o(n log(n))

space: o(log n) - this is an in-place sorting algorithm where we don't touch what is already sorted

stability: no - 

notes: this is the one used in Java standard library. be careful of stack overflow.  Faster than merge sort, and 2-3x faster than heap sort.

```java
public void quicksort(int[] array, int left, int right) {
    // base case
    if (left < 0 || right < 0 || left > right) return;
    
    // the pivot is where the partition iteration finished its job
    int pivot = partition(array, left, right);
    // sort left and right of the pivot
    quicksort(array, left, pivot-1);
    quicksort(array, pivot+1, right);
}

private int partition(int[] array, int left, int right) {
	int pivot = array[right];
    int i = left;
    
    for (int j=left; j<right; j++) {
        // anything thats less than pivot should be in its left
        if (array[j] < pivot) {
            swap(array, i, j);
	        i++;
        }
    }
    // swap the pivot (right) to its rightful place
    swap(array, i, right);
    return i;
}

private void swap(int[] array, int i, int j) {
    int tmp = array[i];
    array[i] = array[j];
    array[j] = tmp;
}
```



#### Heap sort 
- more efficient than selection sort.  works by determining largest number in the list, and placing it at the end of the list, and continuing with the list.  The difference with selection sort is that this works via heap data structure (a special case of binary tree)
- if you can't tolerate the worse-case time complexity of quicksort o(n^2) or need low space cost
- Linux kernel uses heapsort instead of quicksort because of these reasons
- better than merge sort's O(N) overhead

worse case: o(n log(n))

best case: o(n log(n))

space: o(1)

stability: no - 

```java
public void heapsort(int[] array) {
    heapify(array);				// make the array a max-heap
    int size = array.length;
    while(size > 0) {
        int largestValue = removeMax(array, size);	// remove the largest from the max-heap
        size--;
        array[size] = largestValue;		// update the array[] from largest to smallest
    }
}

private int removeMax(int[] heap, int size) {
    int maxValue = heap[1];		// non-zero index array
    // move the last item in the heap to the root partition
    heap[1] = heap[heap.length-1];
    siftDown(heap, size-1, 1);
    
}
```





### time: o(n log(n)) - but hybrid sorting



#### Timsort 
- a hybrid of merge sort and insertion sort

worse case: o(n log(n))

best case: o(n)

space: o(n)

stability: yes



#### Introsort 
- a hybrid of quick sort, heap sort, and insertion sort

worse case: o(n log(n))

best case: o(n log(n))

space: o(log n)

stability: no - 

notes: this is the one used in several standard libraries. i.e. C++, C#, .NET, Go



### Other sorting algorithms



#### Shell sort 

- is a generalized version of insertion sort. 
- it first sort elements that are far apart from each other and successively reduces the interval between the elements to be sorted.
- <u>the performance of shell sort depends on the type of sequence used for a given input array</u>
  - original sequence: `N/2 , N/4 , …, 1`
  - knuth's increments: `1, 4, 13, …, (3^k – 1) / 2`
  - sedgewick's increments: `1, 8, 23, 77, 281, 1073, 4193, 16577...4j+1+ 3·2j+ 1`
  - hibbard's increments: `1, 3, 7, 15, 31, 63, 127, 255, 511…`
  - papernov and stasevich increments: `1, 3, 5, 9, 17, 33, 65,...`
  - pratt's: `1, 2, 3, 4, 6, 9, 8, 12, 18, 27, 16, 24, 36, 54, 81....`
- time: o(N log N)m,  space: O(1)

```java
class ShellSort {

// Rearrange elements at each n/2, n/4, n/8, ... intervals
void shellSort(int array[], int n) {
	for (int interval = n / 2; interval > 0; interval /= 2) {
    	for (int i = interval; i < n; i += 1) {
        	int temp = array[i];
	        int j;
    	    for (j = i; j >= interval && array[j - interval] > temp; j -= interval) {
        		array[j] = array[j - interval];
	        }
    	    array[j] = temp;
        }
    }
}

    public static void main(String args[]) {
      	int[] data = { 9, 8, 3, 7, 5, 6, 4, 1 };
	    int size = data.length;
    	ShellSort ss = new ShellSort();
	    ss.shellSort(data, size);
    	System.out.println("Sorted Array in Ascending Order: ");
	    System.out.println(Arrays.toString(data));
    }
}
```







#### Counting sort 

- is a good choice scenarios where there are ***small number of distinct values to be sorted***. But this is pretty rare in practice, so counting sort does not get much use.

- algorithm

  1. get the maximum value in the unsorted array - `3` in `[1, 0, 3, 1, 3, 1]` and create a new count array of size `3 + 1`.  initialize them to `0`.

  2. count the number of occurrences 

     - the occurences of `[0, 1, 2, 3]` is `[1, 3, 0, 2]` respectively

  3. perform prefix-sum of the count array

     - the prefix sum of `[1, 3, 0, 2]` is `[1, 4, 4, 6]`

  4. shift the count array to the right

     - becomes `[0, 1, 4, 4]` which means the number 3 is in the 4th position in the resulting sorted array

  5. find the index of each element of the original array from the count array

     - the first `1` in  `[1, 0, 3, 1, 3, 1]` will be located in `count[1] = 1` index of the resulting sorted array. 
     - the second element `0` from the unsorted array, will be located in `count[0] = 0`, so `result[count[0]] = 0`
     - etc

  6. time - o(n + k), space: o(n + k)

     ```java
     class CountingSort {
       void countSort(int array[], int size) {
         int[] output = new int[size + 1];
     
         // Find the largest element of the array
         int max = array[0];
         for (int i = 1; i < size; i++) {
           if (array[i] > max)
             max = array[i];
         }
     
         // Initialize count array with all zeros.
         int[] count = new int[max + 1];
         Arrays.fill(count, 0);
     
         // Store the count of each element
         for (int i = 0; i < size; i++) {
           count[array[i]]++;
         }
     
         // Store the cummulative count of each array
         for (int i = 1; i <= max; i++) {
           count[i] += count[i - 1];
         }
     
         // Find the index of each element of the original array in count array, and
         // place the elements in output array
         for (int i = size - 1; i >= 0; i--) {
           output[count[array[i]] - 1] = array[i];
           count[array[i]]--;
         }
     
         // Copy the sorted elements into original array
         for (int i = 0; i < size; i++) {
           array[i] = output[i];
         }
       }
     
       public static void main(String args[]) {
         int[] data = { 4, 2, 2, 8, 3, 3, 1 };
         int size = data.length;
         CountingSort cs = new CountingSort();
         cs.countSort(data, size);
         System.out.println("Sorted Array in Ascending Order: ");
         System.out.println(Arrays.toString(data));
       }
     }
     ```

     



#### Radix sort 

- sort elements by first grouping the individual digits of the same **place value**, then sort the elements according to their increasing/decreasing order
- fast, O(N) in its worst case time complexity, theoretically

- If you're using it to sort binary numbers, then there's hidden constant factor that's usually 32 or 64 (depending on how many bits your numbers are).  This is often way bigger than o(log N)

- <u>It tends to be slow in practice</u>

- algorithm:

  1. Find the largest element. `[121, 432, 564, 23, 1, 45, 788]` is `788`. It has 3 digits, therefore loop should go up to the hundredth place (3 times)
  2. the radixes are:
     1. single digit:  `[1, 2, 4, 3, 1, 5, 8]`, but lets disregard the tens and hundredth's radixes for now as it will change later based on sorting each.
  3. Now, go through each significant place one by one. Any stable sort will do, but the Counting Sort is prefered.
     1. use the **Counting Sort** to sort the single digit radix.  The resulting array becomes `[121, 001, 432, 023, 564, 045, 788]` respectively
     2. Get the ten's digit radix of the sorted array: `[2, 0, 3, 2, 6, 4, 8]` and sort them using Counting Sort again. The resulting array will be `[001, 121, 023, 432, 045, 564, 788]`
     3. Get the hundredth's digit radix of the sorted array: `[0, 1, 0, 4, 0, 5, 7]` respectively and sort them using Counting Sort again. The resulting sorted array will be `[001, 023, 045, 121, 432, 564, 788]`
     4. Done!
  4. time: o(d(n + k)) - linear time complexity, which is theoretically better than other sorting algorithms that has O(N log N)
  5. space: O(n + k) - takes huge space, and also because of it uses another sorting algorithm as intermediary

  ```java
  class RadixSort {
  
    // Using counting sort to sort the elements in the basis of significant places
    void countingSort(int array[], int size, int place) {
      int[] output = new int[size + 1];
      int max = array[0];
      for (int i = 1; i < size; i++) {
        if (array[i] > max)
          max = array[i];
      }
      int[] count = new int[max + 1];
  
      for (int i = 0; i < max; ++i)
        count[i] = 0;
  
      // Calculate count of elements
      for (int i = 0; i < size; i++)
        count[(array[i] / place) % 10]++;
  
      // Calculate cumulative count
      for (int i = 1; i < 10; i++)
        count[i] += count[i - 1];
  
      // Place the elements in sorted order
      for (int i = size - 1; i >= 0; i--) {
        output[count[(array[i] / place) % 10] - 1] = array[i];
        count[(array[i] / place) % 10]--;
      }
  
      for (int i = 0; i < size; i++)
        array[i] = output[i];
    }
  
    // Function to get the largest element from an array
    int getMax(int array[], int n) {
      int max = array[0];
      for (int i = 1; i < n; i++)
        if (array[i] > max)
          max = array[i];
      return max;
    }
  
    // Main function to implement radix sort
    void radixSort(int array[], int size) {
      // Get maximum element
      int max = getMax(array, size);
  
      // Apply counting sort to sort elements based on place value.
      for (int place = 1; max / place > 0; place *= 10)
        countingSort(array, size, place);
    }
  
    public static void main(String args[]) {
      int[] data = { 121, 432, 564, 23, 1, 45, 788 };
      int size = data.length;
      RadixSort rs = new RadixSort();
      rs.radixSort(data, size);
      System.out.println("Sorted Array in Ascending Order: ");
      System.out.println(Arrays.toString(data));
    }
  }
  ```

  