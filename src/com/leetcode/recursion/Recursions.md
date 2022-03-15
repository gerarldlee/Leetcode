Recursions
====

1. When in doubt, write down the recurrence relationship and base case
2. Whenever possible, apply memoization - to avoid duplicate calculation
3. When stack overflows, tail recursion might help



The problems with recursion

- risk of stack overflow 
- memory consumption
- additional cost of function calls or may result in duplicate calculation

Pros

- more intuitive
- less complex unless abused



Steps to convert:

1. Use stack or queue data structure within the function to replace the role of the system call stack.  At each occurrence of recursion, we simply push the parameters as a new element into the stack or queue instead of invoking a recursion
2. We create a loop over the stack or queue. The chain of invocation of recursion would then be replaced with the iteration within the loop.



Divide and Conquer vs Backtracking

----

1. DC problem has 1 definite solution, and backtracking has unknown number of solutions.
2. Each step in DC is required and will be used to build the final solution, while steps in backtracking merely to try and test things out if the solution works.
3. When building solution in DC, we have clear and predefined path.  In backtracking, one does not know in advance which exact path to take to the solution. 



## Tail Recursion


Is a recursion where recursive call is the final instruction in the recursion function and there should only be one recursive call in the function.

Example: Tail recursion

```java
  private static int helper_tail_recursion(int start, int [] ls, int acc) {
    if (start >= ls.length) {
      return acc;
    }
    // this is a tail recursion because the final instruction is the recursive call.
    return helper_tail_recursion(start+1, ls, acc+ls[start]);
  }
```



Example: Non-tail recursion

```java
  private static int helper_non_tail_recursion(int start, int [] ls) {
    if (start >= ls.length) {
      return 0;
    }
    // not a tail recursion because it does some computation after the recursive call returned.
    return ls[start] + helper_non_tail_recursion(start+1, ls);
  }
```



Java and Python does not optimize tail recursion and does not have tail call optimization.  However it is possible to implement with lamdas.

https://stackoverflow.com/questions/13591970/does-python-optimize-tail-recursion



3. 