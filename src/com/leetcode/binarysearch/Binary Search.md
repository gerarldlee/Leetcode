Binary Search
====

presumption - ordered collection.

It divides the search space in 2 after every comparison. 



3 parts of a successful binary search

1. pre-processing - sort if collection is unsorted
2. binary search - using loop or recursion to divide search space in half after every comparison
3. post-processing - determine viable candidate in the remaining space



## 3 templates for the binary search



### Template 1: Basic binary search 

- search for an element or condition which can be determined by accessing a single index in the array.

- search conditions can be determined without comparing the element's neighbors or elements around it
- no post processing required because each step, we are checking if the element has been found.

Distinguishing syntax:

- Initial condition - `left = 0, right = length-1`
- Termination: `left == right`
- searching the left of the pivot: `right = mid-1`
- searching the right of the pivot: `left = mid+1`

```java
int binarySearch(int[] nums, int target){
  if(nums == null || nums.length == 0)
    return -1;

  int left = 0, right = nums.length - 1;
  while(left <= right){
    // Prevent (left + right) overflow
    int mid = left + (right - left) / 2;
    if(nums[mid] == target){ return mid; }
    else if(nums[mid] < target) { left = mid + 1; }
    else { right = mid - 1; }
  }

  // End Condition: left > right
  return -1;
}
```



### Template 2: Advance binary search

- used to search an element or condition which requires accessing the current index and its immediate right neighbors index in the array
- need to compare element neighbor's right 
- use the element right neighbor to determine if the condition is met and decide whether to go left or right
- guarantees search space is at least 2 in size at each step
- post processing required - loop or recursion ends when we have 1 element left.  Needs to assess if the remaining element meets the condition

Distinguishing syntax:

- Initial condition: `left = 0, right = length`
- termination: `left == right`
- searching left from mid: `right = mid`
- searching right from mid: `left = mid+1`

```java
int binarySearch(int[] nums, int target){
  if(nums == null || nums.length == 0)
    return -1;

  int left = 0, right = nums.length;
  while(left < right){
    // Prevent (left + right) overflow
    int mid = left + (right - left) / 2;
    if(nums[mid] == target){ return mid; }
    else if(nums[mid] < target) { left = mid + 1; }
    else { right = mid; }
  }

  // Post-processing:
  // End Condition: left == right
  if(left != nums.length && nums[left] == target) return left;
  return -1;
}
```



### Template 3: Advance binary search

- used to search an element or condition which requires accessing the current index and its immediate left and right neighbor's index in the array
- need to compare and access the element's immediate left and right neighbors
- use elements neighbor to determine if the condition is met and decide whether to go left or right
- guarantees search space is at least 3 in size for each step
- post processing required - loop ends when we have 2 elements left.  Needs to assess if the remining elements meet the condition.

Determining syntax:

- Initial condition: `left=0, right = length-1`
- termination: `left + 1 == right`
- searching left: `right = mid`
- searching right: `left = mid`

```java
int binarySearch(int[] nums, int target) {
    if (nums == null || nums.length == 0)
        return -1;

    int left = 0, right = nums.length - 1;
    while (left + 1 < right){
        // Prevent (left + right) overflow
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] < target) {
            left = mid;
        } else {
            right = mid;
        }
    }

    // Post-processing:
    // End Condition: left + 1 == right
    if(nums[left] == target) return left;
    if(nums[right] == target) return right;
    return -1;
}
```





### Notes:

- templates 1 and 3 are the most commonly used.  Template 2 is a bit more advanced and used for certain types of problems
- They differ by
  - left, mid, right index assignments
  - loop or recursive terminal conditions
  - necessity of post processing
- Template 1: left <= right
- Template 2: left < right
- Template 3: left + 1 < right



Time complexity: o(log n)

- divide array by half - N -> N/2 -> N/4
- maximum number of iterations is log N (base 2)

Space complexity: o(1)