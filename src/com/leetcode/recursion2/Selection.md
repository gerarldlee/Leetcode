Quickselect
====

- a selection algorithm to find the kth smallest element in an unordered list
- closely related to Quicksort algorithm
- good average-case, but poor worse-case performance



### How it works?

1. choose one element as a pivot, and partition the data in 2, based on the pivot; accordingly as less than the pivot, the pivot, and greater than the pivot.
2. instead of recursing into both sizes as in Quicksort, Quickselect only recurs into one side with its searching element
3. since the pivot is in its final sorted position, all those preceding it is unsorted, and all those following it is also unsorted. This reduces the average-case complexity from O(N log N) to O(N) with a worse case of O(N^2)

```java
class Main {
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
 
    // Partition using Lomuto partition scheme
    public int partition(int[] nums, int left, int right, int pIndex) {
        // pick `pIndex` as a pivot from the array
        int pivot = nums[pIndex];
 
        // Move pivot to end
        swap(nums, pIndex, right);
 
        // elements less than the pivot will be pushed to the left of `pIndex`;
        // elements more than the pivot will be pushed to the right of `pIndex`;
        // equal elements can go either way
        pIndex = left;
 
        // each time we find an element less than or equal to the pivot, `pIndex`
        // is incremented, and that element would be placed before the pivot.
        for (int i = left; i < right; i++) {
            if (nums[i] <= pivot) {
                swap(nums, i, pIndex);
                pIndex++;
            }
        }
 
        // move pivot to its final place
        swap(nums, pIndex, right);
 
        // return `pIndex` (index of the pivot element)
        return pIndex;
    }
 
    // Returns the k'th smallest element in the list within `left…right`
    // (i.e., left <= k <= right). The search space within the array is
    // changing for each round – but the list is still the same size.
    // Thus, `k` does not need to be updated with each round.

    // Returns the k'th smallest element in the list within `left…right` (inclusive)
	public int quickselect(int nums[], int left, int right, int k) {
        while (true) {
            // If the array contains only one element, return that element
            if (left == right) {
                return nums[left];
            }

            // select `pIndex` between left and right
            int pIndex = left + rand() % (right - left + 1);

            pIndex = partition(nums, left, right, pIndex);

            // The pivot is in its final sorted position
            if (k == pIndex) {
                return nums[k];
            }

            // if `k` is less than the pivot index
            else if (k < pIndex) {
                right = pIndex - 1;
            }

            // if `k` is more than the pivot index
            else {
                left = pIndex + 1;
            }
        }
    }
 
    public static void main(String[] args) {
        int[] nums = { 7, 4, 6, 3, 9, 1 };
        int k = 2;

        System.out.print("k'th smallest element is " +
                quickSelect(nums, 0, nums.length - 1, k - 1));
    }
}
```



