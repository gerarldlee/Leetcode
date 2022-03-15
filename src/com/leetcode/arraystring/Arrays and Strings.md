Arrays and Strings
====



Java dynamic arrays:

- Lists - ArrayLists, LinkedLists, Vector, Stack, Queue, PriorityQueue, Deque, ArrayDeque, ConcurrentLinkedQueue
- Sets - HashSet, LinkedHashSet, SortedSet, TreeSet
- Hashtable - Dictionary, HashMap, LinkedHashMap, SortedMap, TreeMap, ConcurrentHashMap
- Enums - EnumSet, EnumMap



Java 2D arrays:

- is a one dimensional array which contains M elements, each of which is an array of N integers.
- `a[]` is a pointer to `a[i..n-1]`, and each `a[i]` is also a pointer to `a[i][j..m-1]`
- Can we have different sizes of `j` in `a[i][j]`? Similarly, since `a[]` is a pointer, can we have a dynamic `a[i..n-1]`?



## String

- In Java, it is immutable, and cannot be modified
  - and will create additional memory for unique string literals since it is not garbage optimized
- use StringBuilder for mutability
- or convert it to `char[]` array



### Two Pointer

- can be used to reverse elements in the array

  ```java
  public static void reverse(int[] v, int N) {
      int i = 0;
      int j = N - 1;
      while (i < j) {
          swap(v, i, j);  // this is a self-defined function
          i++;
          j--;
      }
  }
  ```

- can be used to remove elements in the array - without sacrificing the space complexity

  ```java
  public int removeElement(int[] nums, int val) {
      int k = 0;
      for (int i = 0; i < nums.length; ++i) {
          if (nums[i] != val) {
              nums[k] = nums[i];
              k++;
          }
      }
      return k;
  }
  ```



#### Notes for Two-Pointer

- common scenario when using
- One slow-runner, and one fast-runner at the same time
- Solves this problem requires - you to determine the movement strategy for both pointers
- Might need to sort the array before using 2-pointer technique
- Might need a `greedy` thought to determine the movement strategy
- Can also be used for the Sliding Window problems



### Sliding Window

- is a sub-list that runs over an underlying collection i.e. [a, b, c, d, e, f, g, h]
- sliding window of 3 means: [a, b, c], [b, c, d], [c, d, e], [d, e, f], [e, f, g], [f, g, h]
- can assign two-pointers to the left, and right = left + 3
  - increasing the right + 1, and left + 1 at the same time.