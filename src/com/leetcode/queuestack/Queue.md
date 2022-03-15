Queue
====

used for processing orders - FIFO, LIFO

- the first element added to the queue will be processed first

- operations: offer, poll

- Enqueue - add element at the end of the queue

- Dequeue - remove element at the top of the queue

- Java Queue interface - 

  - Queue <- PriorityQueue, PriorityBlockingQueue, 
  - Deque <- LinkedList, ArrayDeque - allows to add and

- Can be efficiently implemented with a Circular Queue

  - a fixed size circular array and two pointers

  - reuse the wasted space

    ```java
    class MyCircularQueue {
        
        private int[] data;
        private int head;
        private int tail;
        private int size;
    
        /** Initialize your data structure here. Set the size of the queue to be k. */
        public MyCircularQueue(int k) {
            data = new int[k];
            head = -1;
            tail = -1;
            size = k;
        }
        
        /** Insert an element into the circular queue. Return true if the operation is successful. */
        public boolean enQueue(int value) {
            if (isFull() == true) {
                return false;
            }
            if (isEmpty() == true) {
                head = 0;
            }
            tail = (tail + 1) % size;
            data[tail] = value;
            return true;
        }
        
        /** Delete an element from the circular queue. Return true if the operation is successful. */
        public boolean deQueue() {
            if (isEmpty() == true) {
                return false;
            }
            if (head == tail) {
                head = -1;
                tail = -1;
                return true;
            }
            head = (head + 1) % size;
            return true;
        }
        
        /** Get the front item from the queue. */
        public int Front() {
            if (isEmpty() == true) {
                return -1;
            }
            return data[head];
        }
        
        /** Get the last item from the queue. */
        public int Rear() {
            if (isEmpty() == true) {
                return -1;
            }
            return data[tail];
        }
        
        /** Checks whether the circular queue is empty or not. */
        public boolean isEmpty() {
            return head == -1;
        }
        
        /** Checks whether the circular queue is full or not. */
        public boolean isFull() {
            return ((tail + 1) % size) == head;
        }
    }
    
    /**
     * Your MyCircularQueue object will be instantiated and called as such:
     * MyCircularQueue obj = new MyCircularQueue(k);
     * boolean param_1 = obj.enQueue(value);
     * boolean param_2 = obj.deQueue();
     * int param_3 = obj.Front();
     * int param_4 = obj.Rear();
     * boolean param_5 = obj.isEmpty();
     * boolean param_6 = obj.isFull();
     */
    ```



#### Notes

- the nodes closest, or next to the root, or child nodes will be traversed / processed earlier
- the processing order of the nodes is exactly the same order as how they are added to the queue
- the processing is always level order - BFS



### BFS Template 1

- can be used to find shortest path

- can be used to traverse a graph, level by level

- can be used to find the length of the shortest path between root and target node

  - in each round, the nodes in the queue are the nodes *waiting to be processed*
  - after each outer while loop, we are one *one step farther from the root node.* The variable `step` indicates the distance from the root node and the current node we are visiting

  ```java
  /**
   * Return the length of the shortest path between root and target node.
   */
  int BFS(Node root, Node target) {
      Queue<Node> queue;  // store all nodes which are waiting to be processed
      int step = 0;       // number of steps neeeded from root to current node
      // initialize
      add root to queue;
      // BFS
      while (queue is not empty) {
          // iterate the nodes which are already in the queue
          int size = queue.size();
          for (int i = 0; i < size; ++i) {
              Node cur = the first node in queue;
              return step if cur is target;
              for (Node next : the neighbors of cur) {
                  add next to queue;
              }
              remove the first node from queue;
          }
          step = step + 1;
      }
      return -1;          // there is no path from root to target
  }
  ```



### BFS Template 2

- never visit a node twice

- handles a graph with cycle

- we can add a hashset to the code in Template 1 to solve this problem

  ```java
  /**
   * Return the length of the shortest path between root and target node.
   */
  int BFS(Node root, Node target) {
      Queue<Node> queue;  // store all nodes which are waiting to be processed
      Set<Node> visited;  // store all the nodes that we've visited
      int step = 0;       // number of steps neeeded from root to current node
      // initialize
      add root to queue;
      add root to visited;
      // BFS
      while (queue is not empty) {
          // iterate the nodes which are already in the queue
          int size = queue.size();
          for (int i = 0; i < size; ++i) {
              Node cur = the first node in queue;
              return step if cur is target;
              for (Node next : the neighbors of cur) {
                  if (next is not in visited) {
                      add next to queue;
                      add next to visited;
                  }
              }
              remove the first node from queue;
          }
          step = step + 1;
      }
      return -1;          // there is no path from root to target
  }
  ```

- Cases where we do not need to keep the `visited` hashset

  - when we are sure there is no cycle i.e. tree traversal
  - when we want to add the node to the queue multiple times
