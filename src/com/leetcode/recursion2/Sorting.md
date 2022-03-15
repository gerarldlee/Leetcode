Sorting
====

Stability - if the value is equal, then the input's relative order will be preserved in the output.


## Popular sorts by average case in time complexity

### time: o(n^2), space: o(1)

Bubble sort - compares the value to the next value. if the next value is less than the value, then swap them. repeat until the array size is finished, and repeat until there's nothing to sort and everything is in order.

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



Insertion sort - iterates and takes the current element in the list one at a time. comparing and inserting them to the already sorted array one by one. swapping or moving the elements greater than its value.

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



Selection sort - selects the smallest value in each iteration and swaps them to the current starting point in each iteration.

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

Merge sort - is a divide and conquer algorithm that recursively divides the unsorted array into 2.  when there's nothing more to divide ie. it reaches its base case of array is null, it sorts, merge and combines the result.

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



Quick sort - is a divide and conquer algorithm that takes a pivot, usually the last value in the array, and this ultimately divide the array into 2 partitions by swapping the left and right pointer value to the left or right of the pivot - everything less than pivot, pivot, and everything higher than pivot. The pivot is always in its right place, then recursively do this in the left, and the right partition. The base case is when there's no more element in the array.

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



Heap sort - more efficient than selection sort.  works by determining largest number in the list, and placing it at the end of the list, and continuing with the list.  The difference with selection sort is that this works via heap data structure (a special case of binary tree)

worse case: o(n log(n))

best case: o(n log(n))

space: o(1)

stability: no - 

```java
```





### time: o(n log(n)) - but hybrid sorting



Timsort - a hybrid of merge sort and insertion sort

worse case: o(n log(n))

best case: o(n)

space: o(n)

stability: yes



Introsort - a hybrid of quick sort, heap sort, and insertion sort

worse case: o(n log(n))

best case: o(n log(n))

space: o(log n)

stability: no - 

notes: this is the one used in several standard libraries. i.e. C++, C#, .NET, Go



### Other sorting algorithms



Shell sort - 

Counting sort - 

Radix sort - 