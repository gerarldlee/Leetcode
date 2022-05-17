### Characteristics of DP

- problems can be divided into sub-problems and an optimal solution can be constructed
- subproblems overlap

DP = DFS + memoization (top-down)

## Patterns / Templates:

### Sequence

- The most common type of DP. 
- The recurrence relation `dp[i]` normally means max / min / best value for the sequence ending at index i.

#### house robber

find the *maximum* amount of loot

#### Coin change

find the *minimum* amount of coins needed to make up an amount

### Grid

- 2D version of sequence DP.
-  `dp[i][j]` means max / min / best value of matrix cell ending at index i and j.

#### Number of robot paths

*number of ways* for robot to move from top left to bottom right

#### Minimal path sum

find path in a grid with *minimum* cost

#### Maximal Square

find *maximal* square of 1s in a grid of 0s and 1s

#### Plumber

find *maximal* number of coins given 2D grid that contains obstacles and coins

### Dynamic number of subproblems

similar to sequence DP, except `dp[i]` depends on dynamic number of subproblems. e.g. `dp[i] = max(d[j]..) for j from 0 to i`

#### longest increasing subsequence

find the longest increasing subsequence of an array of numbers

#### Buy/Sell stock with at most K transactions

maximize profits by buying and selling stock using at most K transaction

#### Russian doll envelopes

#### largest divisible subsets

### Partition

This is a continuation of DFS + memoization problems. These problems are easier to reason and solve with a top-down approach. The key to solving this problem is to draw a state-space tree and then traverse it.

#### Triangle

find the smallest sum path to traverse a triangle of numbers from top to bottom 

#### Partition of 2 equal sum subsets

partition a set of numbers into 2 equal-sum subsets 

### Interval

The key to solving this problem involves finding subproblem defined on an interval `dp[i][j]`

#### Palindrome partitioning DP

#### Palindrome counting

#### Combine string

#### Festival game

### Two sequences

This type of problem has 2 sequences in their problem statement. `dp[i][j]` represents the max/min/best value for the first sequence ending in index i and second sequence ending in index j.

#### Edit distance

find the minimum distance to edit one string to another

#### Delete string

#### Longest common subsequence

find the longest common subsequence that is common in 2 sequences.

### Game theory

This type of problem asks for whether a player can win a decision game. The key to solving this type of problem is to identify winning state; and formulating a winning state as a state that returns a losing state to the opponent.

#### divisor game

### 0-1 knapsack

This type has a series of objects and usually asks for the maximum value that can be achieved from the objects without achieving a certain weight

#### 0-1 knapsack problem

find the maximum object value we can put in a knapsack without exceeding the weight

#### Perfect squares

find the smallest amount of perfect squares needed to sum to a particular number

#### Perfect Squares 2

#### Sequence check

### Bitmask

This type of problem use bitmasks to reduce factorial complexity (n!) to 2^n by encoding the dp state in bitmasks.

#### Longest path (in a DAG)

find the longest path in a directed acyclic graph

#### Min cost to visit every node

fund the minimum cost to traverse every node in a directed weighted graph

### Tree

#### DP in trees

#### Longest cycle
