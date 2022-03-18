Dynamic Programming
====

1. is a programming paradigm that systematically explore all possible solutions to a problem

   - problems that can be broken down into <u>overlapping subproblems</u> and are re-used multiple times
   - problem has an <u>optimal substructure</u> solution that can be found from solutions to overlapping subproblems of the original problem

2. use cases

   - fibonacci sequence 
   - graph i.e. bellman-ford, single source shortest path
   - game theory
   - ai and machine learning
   - economics and finance
   - bioinformatics
   - shortest path
   - longest common subsequence
   - optimal search binary tree
   - travelling salesman

3. two-ways to implement DP

   - bottom-up - tabulation - iteration that starts as base case

     - example: 

       ```
       // Pseudocode example for bottom-up
       
       F = array of length (n + 1)
       F[0] = 0
       F[1] = 1
       for i from 2 to n:
           F[i] = F[i - 1] + F[i - 2]
       ```

       

   - top-down - memoization - recursion with memoization

     - example:

       ```
       // Pseudocode example for top-down
       
       memo = hashmap
       Function F(integer i):
           if i is 0 or 1: 
               return i
           if i doesn't exist in memo:
               memo[i] = F(i - 1) + F(i - 2)
           return memo[i]
       ```

   - Which is better?

     - bottom-up runtime is faster and does not have call-stack
     - top-down is easier to write and intuitive.  also with recursion, the ordering of sub-problems does not matter, whereas with tabulation, we need to go through a logical ordering of solving sub-problems

4. characteristics? when to use DP?

   1. it asks for an optimum value of something i.e. maximum, minimum, or number of ways to do something
      - minimum cost of doing ...
      - maximum profit from ...
      - how many ways are there to do ...
      - what is the longest possible ...
      - is it possible to reach a certain point ...
   2. the future decision depends on earlier decisions. when we need to factor in results from previous decisions 
      1. house robber example - houses have certain amount of money in them, but a robber can only rob every other house without getting the police attention if two adjacent house were broken on the same night
      2. longest increasing subsequence - need to determine the length of the longest subsequence that is strictly increasing

5. when to use greedy algorithms, vs dp?

   1. greedy algorithms deal with the maximization, minimization i.e. the first characteristics, but does not deal with the future decisions

6. dp's top-down recursion vs divide and conquer?

   1. divide and conquer can usually be parallelize, and dp cannot. 
   2. dp can still be parallelize, but its too complex

7. to convert DP's top-down to bottom-up - we need to find the correct order in which to iterate over the states

   - we still reuse base cases and the recurrence relation



## Framework to Solve DP Problems

1. define **state** variables - that can describe the scenario. common things to look out for:
   - an index along some input
     - usually used if input is given as array or string
   - a second index along some input
     - sometimes you need 2 index state variables, i and j
     - maximum slice
   - explicit numerical constraint given in the problem
     - example: "you are only allowed to complete K transactions" or "you are allowed to break up to K obstacles", etc.
   - variables that describe status in a given state
     - example: "true, if currently holding a key, false if not", "currently holding K packages", etc.
   - some sort of data like a tuple, or bitmask to indicate "visited" or "used"
     - example: "bitmask where ith but indicates if the ith city has been visited"
     - mutable data like array cannot be used. only immutable data like numbers, strings can be hashed and therefore memoized
2. a function or data structure that will compute or contain the answer to the problem for every given state
   1. example: climbing stairs problem, we define `dp(i)` as a function to contain the answer to the i-th step. similarly, `dp(n)` will contain the n-th step
   2. top-down is implemented recursive function with hashmap as memo
   3. bottom-up is implemented via nested loops and array
3. a recurrence relation to transition between states
   1. equation that relates one state to another. i.e. dp(i) = dp(i -1) + dp(i - 2)
4. base cases so that our recurrence relation does not go infinitely
   1. example: dp(1) = 1 and dp(2) = 2



#### Example: House Robber

1. function
   1. state variable - if you're a robber, you have to line up the houses, an array that holds the value of money for each house, and the house as an index would make sense. `nums[houses] = money`
   2. `dp(i)` - the function that returns the maximum money you can rob up to, and including house i.
2. recurrence relation
   1. we can either rob this house, or choose not to.  if we decide to rob this house, we gain `nums[i]` money, and is only possible if we did not rob the previous house. i.e. `nums[i] + dp(i - 2)`
   2. if we decide not to rob this house, then the money we have from the previous house is what we will have when we arrive at this house i.e. `dp(i-1)`
   3. from this choices we want to pick the one that gives the maximum profits. i.e. `dp(i) = max(dp(i-1), nums[i] + dp(i-2))`. This is our recurrence relation
3. base case
   1. if there's only one house, then the most money we can make is by robbing the house. `dp(0) = nums[0]`
   2. if there are 2 houses to rob, then the most money we can make is by robbing the house with more money. `dp(1) = max(nums[0], nums[1])`
4. Complexity
   1. Time: o(N) for both approaches, as we iterate all houses
   2. Space: O(N) for both approaches. memo and call-stack for top-down, and the maximum value array for bottom-up

```java
// Top-down / recursion approach
class TopDownSolution {
    private HashMap<Integer, Integer> memo = new HashMap<Integer, Integer>();
    private int[] nums;
    
    private int dp(int i) {
        // Base cases
        if (i == 0) return nums[0];
        if (i == 1) return Math.max(nums[0], nums[1]);
        if (!memo.containsKey(i)) {
            memo.put(i, Math.max(dp(i - 1), dp(i - 2) + nums[i])); // Recurrence relation
        }
        return memo.get(i);
    }
    
    public int rob(int[] nums) {
        this.nums = nums;
        return dp(nums.length - 1);
    }
}

// bottom-up approach
class BottomUpSolution {
    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        
        int[] dp = new int[nums.length];
        
        // Base cases
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]); // Recurrence relation
        }
        
        return dp[nums.length - 1];
    }
}
```



## Top-Down to Bottom-Up Tips

1. Complete a top-down implementation first
2. Initialize an array that is sized according to your state variables
3. Set base cases, same as the one from the top-down approach
4. Write for-loops that iterate over the state variables. Multiple for-loop for multiple state variables
   1. **Start iterating from the base cases**
5. Each iteration of the inner-most loop represents a given state and is equivalent to a function call (to the same state in the top-down approach).
   1. Copy the logic from your top-down into the for-loop and change the function calls to accessing your array instead.
   2. *Sometimes the recurrence relation is dynamic, and therefore need iteration if the state variables*
6. were done! the array is populated with the answer to the original problem for all possible states. Return the answer to the original problem by `return dp[...]`

#### Example: Maximum Score from Performing Multiple Operations

1. function





#### Example: Minimum Difficulty of a Job Schedule

1. function
   1. 

#### Example: Word Break

1. function



## State Transition by Inaction

- by doing nothing, sometimes the best course of action is to do nothing
- 2 states having the same value

## State Reduction

- if we could derive the information from the states we declare, then we could reduce the state
- will reduce time and space complexity
- can also be achieve in recurrence relation, if it only involves a fixed number of states



#### Example: Best Time to Buy and Sell Stock IV

1. function
   1. 
   2. 

#### Example: Minimum Cost Climbing Stairs

1. function



## Counting DP

- number of distinct ways of doing something
- the recurrence relation of min/max problems typically involves the min() max() function
- the recurrence relation of counting problems typically sums the results of multiple states together
- the base case of min/max problems often starts with 0. the base case for longest common subsequence also starts with 0
- the base case of counting problems does not start from 0. maybe from 1 or 2.



#### Example: Coin Change 2

1. function

#### Example: Decode Ways

1. function



## Path Problems DP

- matrix input

- gives rules for "moving" through the matrix in the problem description

  ![img](https://leetcode.com/explore/featured/card/Figures/1293/1293_next_steps.png)

- if we are allowed to move in all 4 directions - it is a graph/BFS problem instead

- this pattern is often combined with Counting DP pattern

  

#### Example: Unique Path

1. function
