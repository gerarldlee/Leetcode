Knapsack Problem
====

- A weighted array with item value. 

- Which of those array item can be carried by a knapsack that has a limit of a certain amount



## 0-1 Knapsack



```java
int[][] memo = null;
void init() {
    if (memo == null) memo = new int[weighted_array.lengh][capacity];
}

int knapsack(int[] weights, int[] values, int index, int capacity) {
	// memo
    if (memo[index][capacity] != Integer.MIN_VALUE)
        return memo[index][capacity];

    // base case
    if (index == 0 || capacity == 0) return 0;
    
    int result = Integer.MIN_VALUE;
    if (weights[index] > capacity) {
        result = knapsack(weights, values, index - 1, capacity);
    }
    else {
        int tmp1 = knapsack(weights, values, index - 1, capacity);
        int tmp2 = values[knapsack(weights, values, index - 1, capacity - weights[index]);
        return Math.max(tmp1, tmp2);
    }
    memo[index][capacity] = result;
    return result;
}
```

