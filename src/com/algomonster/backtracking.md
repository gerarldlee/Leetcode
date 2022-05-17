## Backtracking

- is DFS with states

### Fundamentals

#### Combinatorial search problems

Combinatorial search problems involve finding groupings and assignments of objects that satisfy certain conditions. Finding all permutations/subsets, solving sudoku, and 8-queens are classic combinatorial problems.

#### Permutations

It's important to review basic high school combinatorics to get an intuition for combinatorial problems.

**Permutation** means arranging things with an order. For example, permutations of `[1, 2]` are `[1, 2]` and `[2, 1]`. Permutations are best visualized with trees.

![img](https://algomonster.s3.us-east-2.amazonaws.com/dfs_intro/arrangement.png)

The number of permutations is given by `n!` (we looked at factorial in [Recursion Review](https://algo.monster/problems/recursion_intro)). The way to think about permutation is to imagine you have a bag of 3 letters. Initially, you have 3 letters to choose from, you pick one out of the bag. Now you are left with 2 letters, you pick again now there's only 1 letter. The total number of choices is `3*2*1 = 6` (hence we have 6 leaf nodes in the above tree).

If you want to learn more about the math, you can find more details in the [Wikipedia entry](https://en.wikipedia.org/wiki/Permutation).

#### Complexity

The complexity of combinatorial problems often grows rapidly with the size of the problem. For example, as we have seen, the number of permutation of 3 objects is only 6. However, number of permutations of 10 objects is about 3 million. The number of permutations of 11 objects is about 40 million. The rapid growth of solution space with even a small increase in problem size is called [combinatorial explosion](https://en.wikipedia.org/wiki/Combinatorial_explosion).

#### Combinatorial search == DFS on a tree

In combinatorial search problems, search space is in the shape of a tree. The tree that represents all the possible states is called a **state-space Tree**.

Each node of the state-space tree represents a state we can reach in a combinatorial search (by making a particular combination). Leaf nodes are the solutions to the problem (permutations in the above example).

**Combinatorial search problems boil down to DFS/backtracking on the state-space tree**.

Since the search space can be quite large, we often have to "prune" the tree, i.e. discard branches.

#### Three steps to conquer combinatorial search problems

We summarized a three-step system to solve combinatorial search problems:

1. **Identify the state(s)**.
2. **Draw the state-space tree**.
3. **DFS/backtrack on the state-space tree**.

In step 1, we want to answer the following two questions to identify the states:

1. What state do we need to know whether we have reached a solution (and using it to construct a solution if the problem asks for it)?. In the above permutation example, we need to keep track of the letters we have already selected when we perform DFS.
2. What state do we need to decide which child nodes should be visited next and which ones should be pruned? In the above permutation example, we have to know what the letters are left that we can still use (since each letter can only be used once).

##### Draw the tree, draw the tree, draw the tree!!!

For step 2, you want to draw the tree (on a piece of paper if you have one). A small test case that's big enough to reach at least one solution (leaf node).

We can't stress how important this is. Once you draw the tree, the rest of the work becomes so much easier - simply traverse the tree depth-first. This is so important we want to stress it three times.

For step 3, apply the following backtracking template:

```
1function dfs(node, state):
2    if state is a solution:
3        report(state) # e.g. add state to final result list
4        return
5
6    for child in children:
7        if child is a part of a potential solution:
8            state.add(child) # make move
9            dfs(child, state)
10            state.remove(child) # backtrack
```

Notice how very similar this is to the Ternary Tree Path code we've seen in [DFS with States](https://algo.monster/problems/dfs_with_states) module. That problem has an explicit tree. For combinatorial search problems, we have to find our own tree.

This all may sound very abstract at the moment. It will be super clear once we apply the system to a couple of real problems.

In the next module, we will apply this system to solve concrete problems.



### Combinatorial search templates

#### DFS with states

##### Ternary Tree Paths

#### Permutations

#### Letter combinations of Phone numbers

### Memoization

#### Word break

#### Decode ways

decode number of ways

### Pruning

#### Palindrome partitioning

### Dedup

#### Combination sum

### Others

#### Subsets

