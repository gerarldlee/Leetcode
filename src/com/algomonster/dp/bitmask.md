# Bitmask and Dynamic Programming

## What is a bitmask?

### Binary Numbers

At the smallest scale in computers, data are stored as bits. A bit stores either 0 or 1. A binary number is a number expressed in the base-2 system. Each digit can be either 0 or 1. The integer types we use in programming languages are actually stored as binary numbers. Here is how binary numbers converts to decimal numbers:

![img](https://algomonster.s3.us-east-2.amazonaws.com/bitmask_intro/bitmask_intro_1.png)

You can check that in python:

```
1>>> bin(21)
2'0b10101'
```

Note that `0b` means it's a binary number. The leading 0s are omitted and that's why we have `10101` instead of `010101`.

### Bit-wise AND

We can `AND` two binary numbers by comparing each digits. If they are both 1 then the resulting digit is 1, otherwise it's 0.

![img](https://algomonster.s3.us-east-2.amazonaws.com/bitmask_intro/bitmask_intro_2.png)

### Bitmask

Now finally what's a mask? We can construct a binary number such that certain digit is set to 1 and other digits are all set to 0. This creates a "mask" that when we `AND` it to another binary it "turns off" (set to 0) all digits except the 1 digit in the mask.

![img](https://algomonster.s3.us-east-2.amazonaws.com/bitmask_intro/bitmask_intro_3.png)

## Encoding the state

Now how is this all useful to algorithms and dynamic programming? The answer is to encode states. Remember in [backtracking](https://algo.monster/problems/backtracking) problems like [permutation](https://algo.monster/problems/permutations), we used a `used` **boolean array** to keep track of state, i.e. which character has been used. If we were to [memoizing](https://algo.monster/problems/memoization_intro) the state, we need to somehow serialize the array, e.g. turning it into a string which has extra computational cost. With a bitmask, the serialization from boolean array to integer is already done for you - it's just an integer. This reduces memory consumption and makes the algorithm faster and simpler.

For instance, let us say that we are on a graph and the problem requires us to remember all the nodes that we visted. We can encode each node as a boolean bit value as part of our bitmask and use that value as our dp state.

![img](https://algomonster.s3.us-east-2.amazonaws.com/bitmask_intro/bitmask_intro.001.png)

In the example, we have visited nodes `1` and `4` nodes. We encode that as `1001` which is equal to `2^0+2^3=9`.

Bitmask questions can vary and additional dp states may be required so the code proviced will loosely follow what a bitmask type question will look like.

## Common bit manipulation operations

Typically, these problems use a recursive function as well in order to do dp. Below is some pseudocode to outline this idea. Here are some useful operations to keep in mind

1. `(1 << i)` -> simply to access the ith bit in the mask such as through a loop
2. `(bitmask | (1 << i))` -> this operation sets the ith bit in the bitmask to `1`
3. `(bitmask & ~(1 << i))` -> this operation sets the ith bit in the bitmask to `0`
4. `(bitmask & (1 << i))` -> this operation checks if the ith bit in the bitmask is set to `1` or not

Here's another example.

## Use bitmask to generate all subsets:

```
1>>> nums
2[1, 2, 3]
3>>> n = len(nums)
4>>> subsets = []
5>>> for mask in range(2 ** n):
6...     subset = []
7...     for i in range(n):
8...         if mask & (1 << i):
9...             subset.append(nums[i])
10...     subsets.append(subset[:])
11...
12>>> subsets
13[[], [1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3]]
```

We didn't need to use backtracking for this. Pretty cool, eh!

## Bitmask and Dynamic Programming

Now here is pseudocode for how a bitmask dp problem for dynamic programming.

```
1function f(int bitmask, int [] dp) {
2    if calculated bitmask {
3        return dp[bitmask];
4    }
5    for each state you want to keep track of {
6        if current state not in mask {
7            temp = new bitmask
8            dp[bitmask] = max(dp[bitmask], f(temp,dp) + transition_cost)
9        }
10    }
11    return dp[bitmask]
12}
```

Bitmask is helpful with problems that would normally require factorial complexity, something like `n!` but can instead reduce the computational complexity to `2^n` through storing the dp state. It can also act as an effective "upper-bound" on the number of computations that can take place.