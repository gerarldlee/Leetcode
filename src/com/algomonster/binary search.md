# Binary Search Templates

### Vanilla binary search

#### Intuition

Binary search is an efficient array search algorithm. It works by narrowing down the search range by half each time. If you have looked up a word in a physical dictionary, then you've already used binary search in real life. Let's look at a simple example:

*Given a sorted array of integers and an integer called target, find the element that equals to the target and return its index. If the element is not found, return -1.*

The key observation here is that the array is sorted. Let's say we pick a random element in the array and compare it to the target.

- If we happen to pick the element that equals to the target (how lucky!), then bingo. We don't need to do any more work, just return its index.
- If the element is smaller than the target, then we know the target cannot be found in the section to the *left* of the current element since everything to the left is even smaller. So we discard the current element and everything on the left from the search range.
- If the element is larger than the target, then we know the target cannot be found in the section to the *right* of the current element since everything to the right is even larger. So we discard the current element and everything on the right from the search range.

We repeat this process until we find the target. Instead of picking a random element, we always pick the **middle** element in the current search range. This way we can discard half of the options and shrink the search range by half each time. This gives us `O(log(N))` runtime.

#### Implementation
The search range is represented by the left and right indices that start from both ends of the array and move towards each other as we search. When we move the index, we discard elements and shrink the search range.

Time Complexity: O(log(n))

##### Calculating mid
Note that when calculating mid, if the number of elements is even, there are two elements in the middle. We normally follow the convention of picking the first one, which is equivalent to doing integer division (left + right) / 2.

#### Deducing binary search
It's important to understand and be able to deduce the algorithm yourself instead of memorizing it. In a real interview, interviewers may ask you additional questions to test your understanding, so simply memorizing the algorithm may not be enough to convince the interviewer.

Key elements in writing a correct binary search:

1. When to terminate the loop
Make sure while loop has equality comparison. Otherwise, for the edge case of a one-element array, we'd skip the loop and miss the potential match.

2. Whether/how to update left and right boundary in the if conditions
Consider which side to discard. If arr[mid] is already smaller than the target, then we should discard everything on the left by making left = mid + 1.

3. Should I discard the current element?
For vanilla binary search, we can discard it since it can't be the final answer if it is not equal to the target. There might be situations where you would want to think twice before discarding the current element. We'll discuss this in the next module.

#### When to use binary search
Interestingly, binary search works beyond sorted arrays. You can use binary search whenever you can make a binary decision to shrink the search range. We will see this in the following modules.


### Finding the boundary
An array of boolean values is divided into two sections; the left section consists of all false and the right section consists of all true. Find the boundary of the right section, i.e. the index of the first true element. If there is no true element, return -1.

Input: arr = [false, false, true, true, true]

Output: 2

Explanation: first true's index is 2.

#### Explanation
The binary decision we have to make when we look at an element is

if the element is false, we discard everything to the left and the current element itself.
if the element is true, the current element could be the first true although there may be other true to the left. We discard everything to the right but what about the current element?
We can either keep the current element in the range or record it somewhere and then discard it. Here we choose the latter. We'll discuss the other approach in the alternative solution section.

We keep a variable boundary_index that represents the leftmost true's index currently recorded. If the current element is true, then we update boundary_index with its index and discard everything to the right including the current element itself since its index has been recorded by the variable.

Time Complexity: O(log(n))

The good thing with this approach is that we don't have to modify the while loop logic in the vanilla binary search from the last module, besides introducing a variable.

#### Alternative approach
Another approach to handle case 2 above is to keep the current element in the search range instead of discarding it, i.e. if arr[mid]: right = mid instead of right = mid - 1. However, doing this without modifying the while condition will result in an infinite loop. This is because when left == right, right = mid will not modify right and thus, not shrink search range and we will be stuck in the while loop forever. To make this work we have to remove the equality in the while condition. In addition, as mentioned in the last module, a while loop without equality will miss the single-element edge case so we have to add an additional check after the loop to handle this case. Overall, we have to make three modifications to the vanilla binary search to make it work.

Side note: how to not get stuck in an infinite loop
make progress in each step
have an exit strategy

#### Summary
This problem is a major 🔑 in solving future binary search-related problems. As we will see in the following modules, many problems boil down to finding the boundary in a boolean array.

## Sorted Array

### First element not smaller than target

### First occurance

Given a sorted array of integers and a target integer, find the first occurrence of the target and return its index. Return -1 if the target is not in the array.

Input:

arr = [1, 3, 3, 3, 3, 6, 10, 10, 10, 100]
target = 3
Output: 1

Explanation: First occurrence of 3 is at index 1.

Input:

arr = [2, 3, 5, 7, 11, 13, 17, 19]
target = 6
Output: -1

Explanation: 6 does not exists in the array.



### Square root

## Implicit sorted array

### Minimum in rotated sorted array

Find the minimum in rotated sorted array

A sorted array of unique integers was rotated at an unknown pivot. For example, `[10, 20, 30, 40, 50]` becomes `[30, 40, 50, 10, 20]`. Find the index of the minimum element in this array. All the numbers are unique.

Input: `[30, 40, 50, 10, 20]`

Output: `3`

Explanation: the smallest element is 10 and its index is 3.

Input: `[3, 5, 7, 11, 13, 17, 19, 2]`

Output: `7`

Explanation: the smallest element is 2 and its index is 7.

### Peak of mountain array

## Advanced

### Capacity to ship packages within D days

There are `n` packages that needs to be transported from one city to another, and you need to transport them there within `d` days. For the `i`th package, the weight of the package is `weights[i]`. You are required to deliver them in order with a rental truck. The rental trucks come in different sizes. The bigger trucks have greater weight capacity but cost more to rent. To minimize cost, you want to deliver the packages in one truck once per day, with a weight capacity as small as possible to save on rental cost. What is the minimum weight capacity of the truck that is required to deliver all packages within `d` days?

Input
- `weights`: A list of packages and their weights.
- `d`: The number of days to deliver all packages.

Output:
The minimum weight capacity of the truck.

Example 1:

**Input**:

```
1weights = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
2d = 5
```

**Output**: `15`

**Explanation**:

The first day we deliver the first `5` package. The second day we deliver the next `2`, and for each following days, we deliver `1`. The maximum weight delivered on each day is `15`, so we can have a truck with a capacity of `15`. This value is the minimum.

Constraints

- `1 <= len(weights) <= 5 * 10^4`
- `1 <= d <= len(weights)`
- `1 <= weights[i] <= 500`

### Ugly numbers 3

Write a program to find the `n`-th ugly number.

Ugly numbers are positive numbers that are divisible by either `a`, `b`, or `c`.

Since the number can be too large, return the actual answer modulo `10^9 + 7`

Example 1:

Input:`n = 10`, `a = 2`, `b = 3`, `c = 5`

Output: `14`

Explanation: `2, 3, 4, 5, 6, 8, 9, 10, 12, 14` is the sequence of the first `10` ugly numbers.

Example 2:

Input:`n = 2`, `a = 3`, `b = 4`, `c = 5`

Output: `4`

Explanation: `3, 4` is the sequence of the first `2` ugly numbers.

Constraints

- `1 <= n, a, b, c <= 10^9`
- `1 <= a * b * c <= 10^18`

### Newspapers

You've begun working in the one and only Umbristan. As part of your job working for the government you are asked to organize newspapers. Each morning your fellow coworkers will dilligently read through the newspapers carefully examining its contents. It is your job to organize the newspapers into piles and hand them out to your coworkers to read through. Each newspaper is assigned a time based on how much time it would take to read through its contents. The newspapers are carefully layed out in a line in a particular order that must not be broken when assigning the newspapers. That is you cannot pick and choose newspapers to make a pile to assign to a co-worker to read through. Instead you must take a particular subsection of the line of newspapers, make a pile and give that to a co-worker.

What is the minimum amount of time it would take to have your coworkers go through all the newspapers?

Constraints

```
1 <= newspapers.length <= 10^5
1 <= newspaper <= 10^5
1 <= coworkers <= 10^5
```

Examples

Example 1:

Input: `newspapers = [7,2,5,10,8], coworkers = 2`

Output: `18`

Explanation:

Assign first `3` newspapers to one coworker then assign the rest to another. The time it takes for the first `3` newspapers is `7 + 2 + 5 = 14` and for the last `2` is `10 + 8 = 18`.

Example 2:

Input: `newspapers = [2,3,5,7], coworkers = 3`

Output: `7`

Explanation:

Assign `[2, 3]`, `[5]`, and `[7]` separately to workers. The minimum time is `7`.
