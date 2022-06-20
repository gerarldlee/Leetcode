Maximum Subarray Problem
====

Kadane's algorithm is an algorithm that can find the maximum sum subarray given an array of numbers in O(n) time and O(1) space.
- very efficient
- very simple

## How it works?

- the maximum sum subarray if the current index, is the value of the current index or the maximum sum subarray of the previous index + the value of the current index.

- involves iterating through the array using an integer variable `current` and at each index `i` determines if elements before index `i` are worth keeping, or should be discarded.

- useful when array can contain negative numbers

- if `current` becomes negative, it is reset, and we start considering a new subarray starting at the current index

  ```
  // Given an input array of numbers "nums",
  1. best = negative infinity
  2. current = 0
  3. for num in nums:
      3.1. current = Max(current + num, num)
      3.2. best = Max(best, current)
  
  4. return best
  ```

  

  #### Example: Maximum Sum Circular Subarray

  
