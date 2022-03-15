Stack
====

Used in LIFO processing order
- the newest element will be the first to be processed
- adds at the end of the stack
- removes at the end of the stack too
- operations: push, pop

```java
// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        // 1. Initialize a stack.
        Stack<Integer> s = new Stack<>();
        // 2. Push new element.
        s.push(5);
        s.push(13);
        s.push(8);
        s.push(6);
        // 3. Check if stack is empty.
        if (s.empty() == true) {
            System.out.println("Stack is empty!");
            return;
        }
        // 4. Pop an element.
        s.pop();
        // 5. Get the top element.
        System.out.println("The top element is: " + s.peek());
        // 6. Get the size of the stack.
        System.out.println("The size is: " + s.size());
    }
}
```



#### Stack and DFS Notes

- can be used to find path from the root to the target
  - starts from the root node, traverse the children nodes and trace back when its no way to go deeper.
  - choose the second child / path, and so on, until the target node is found
  - we only traceback and try another path when after we reached the deepest node
  - the first path found, is *not always the shortest path*
- the processing order is the exact opposite order in which they are added



### DFS Template 1

- we can use DFS instead of BFS, difference is the traversal order

- the nodes you visit earlier might not be the nodes which are closer to the root node

  - as a result, the first path you found in DFS, might not be the shortest path

- 2 ways to implement DFS

  - recursion - using call stack

  - the stack is the depth of the nodes

    ```java
    /*
     * Return true if there is a path from cur to target.
     */
    boolean DFS(Node cur, Node target, Set<Node> visited) {
        return true if cur is target;
        for (next : each neighbor of cur) {
            if (next is not in visited) {
                add next to visted;
                return true if DFS(next, target, visited) == true;
            }
        }
        return false;
    }
    ```

    

  - iteration - prevents the stack overflow problem for huge depth nodes

    ```java
    /*
     * Return true if there is a path from cur to target.
     */
    boolean DFS(int root, int target) {
        Set<Node> visited;
        Stack<Node> stack;
        add root to stack;
        while (stack is not empty) {
            Node cur = the top element in stack;
            remove the cur from the stack;
            return true if cur is target;
            for (Node next : the neighbors of cur) {
                if (next is not in visited) {
                    add next to visited;
                    add next to stack;
                }
            }
        }
        return false;
    }
    ```

    
