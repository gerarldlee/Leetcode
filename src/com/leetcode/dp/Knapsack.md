Knapsack Problem
====

- A weighted array with item value. 

- Which of those array item can be carried by a knapsack that has a limit of a certain amount

### Types of Knapsack problems

1. Fractional Knapsack - this is a greedy type that allows items to be divided if the bag's capacity does not allow the entire item. Example: if weight capacity if the bag is 10KG, and bag is already filled by 9KG, a 2KG item can still be added by dividing it into half and just adding the 1KG of the item
2. 0-1 Knapsack - As opposed to the fractional knapsack, with 0-1 knapsack, if we have a 10KG capacity, and a remaining 2KG item, we cannot add partial item. We can only add it whole or not at all. 
3. Unbounded Knapsack - A 0-1 Knapsack, where we can only take items as a whole and not in parts or fractions, however, an item first picked can still be chosen again in the next iteration.

## Fractional Knapsack

1. calculate the ratio of value to weight for each item
2. sort the items based on this ratio
3. take the item with highest ratio and add them until we cant add another item as a whole
4. at the end add the next item as much fraction as we can

[Fractional Knapsack Problem | GeeksforGeeks - YouTube](https://www.youtube.com/watch?v=m1p-eWxrt6g)

```java

// Greedy approach
public class FractionalKnapSack {
    // function to get maximum value
    private static double getMaxValue(int[] wt, int[] val,
                                      int capacity)
    {
        ItemValue[] iVal = new ItemValue[wt.length];
  
        for (int i = 0; i < wt.length; i++) {
            iVal[i] = new ItemValue(wt[i], val[i], i);
        }
  
        // sorting items by value;
        Arrays.sort(iVal, new Comparator<ItemValue>() {
            @Override
            public int compare(ItemValue o1, ItemValue o2)
            {
                return o2.cost.compareTo(o1.cost);
            }
        });
  
        double totalValue = 0d;
  
        for (ItemValue i : iVal) {
  
            int curWt = (int)i.wt;
            int curVal = (int)i.val;
  
            if (capacity - curWt >= 0) {
                // this weight can be picked while
                capacity = capacity - curWt;
                totalValue += curVal;
            }
            else {
                // item cant be picked whole
                double fraction
                    = ((double)capacity / (double)curWt);
                totalValue += (curVal * fraction);
                capacity
                    = (int)(capacity - (curWt * fraction));
                break;
            }
        }
  
        return totalValue;
    }
  
    // item value class
    static class ItemValue {
        Double cost;
        double wt, val, ind;
  
        // item value function
        public ItemValue(int wt, int val, int ind)
        {
            this.wt = wt;
            this.val = val;
            this.ind = ind;
            cost = new Double((double)val / (double)wt);
        }
    }
  
    // Driver code
    public static void main(String[] args)
    {
        int[] wt = { 10, 40, 20, 30 };
        int[] val = { 60, 40, 100, 120 };
        int capacity = 50;
  
        double maxValue = getMaxValue(wt, val, capacity);
  
        // Function call
        System.out.println("Maximum value we can obtain = "
                           + maxValue);
    }
}
```





## 0-1 Knapsack

https://www.youtube.com/watch?v=T4bY72lCQac&t=0s

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



## Unbounded Knapsack - 0-1-Infinity

- coin change 2
- top 5 dynamic programming patterns