Greedy Algorithms
====

### Types:

- Selection Sort
- Knapsack Problems
- Minimum Spanning Tree
- Singel Source Shortest Path
- Job Scheduling Problem
- Prims Minimal Spanning Tree
- Kruskals Minimal Spanning Tree
- Dijsktras Minimal Spanning Tree
- Huffman Coding
- Ford-Fulkerson Algorithm

## Ford Fulkerson Algorithm

- calculates the maximum possible flow in a network or graph

- flow network - network of vertices and edges with a source "S" and a sink "T". Each vertex except S and T can receive and send an equal amount of stuff through it. S can only send and T can only receive stuff.

  ![Flow network](https://cdn.programiz.com/sites/tutorial2program/files/flow-network.png)

- use cases:

  - water distribution pipeline
  - bipartite matching problem
  - circulation with demands

- algorithm:

  1. initialize the flow in all the edges to 0
  2. while there is an augmenting path between the source and the sink, add this path to the flow
  3. update the residual graph

  ```java
  // Ford-Fulkerson algorith in Java
  
  import java.util.LinkedList;
  
  class FordFulkerson {
    static final int V = 6;
  
    // Using BFS as a searching algorithm 
    boolean bfs(int Graph[][], int s, int t, int p[]) {
      boolean visited[] = new boolean[V];
      for (int i = 0; i < V; ++i)
        visited[i] = false;
  
      LinkedList<Integer> queue = new LinkedList<Integer>();
      queue.add(s);
      visited[s] = true;
      p[s] = -1;
  
      while (queue.size() != 0) {
        int u = queue.poll();
  
        for (int v = 0; v < V; v++) {
          if (visited[v] == false && Graph[u][v] > 0) {
            queue.add(v);
            p[v] = u;
            visited[v] = true;
          }
        }
      }
  
      return (visited[t] == true);
    }
  
    // Applying fordfulkerson algorithm
    int fordFulkerson(int graph[][], int s, int t) {
      int u, v;
      int Graph[][] = new int[V][V];
  
      for (u = 0; u < V; u++)
        for (v = 0; v < V; v++)
          Graph[u][v] = graph[u][v];
  
      int p[] = new int[V];
  
      int max_flow = 0;
  
      # Updating the residual calues of edges
      while (bfs(Graph, s, t, p)) {
        int path_flow = Integer.MAX_VALUE;
        for (v = t; v != s; v = p[v]) {
          u = p[v];
          path_flow = Math.min(path_flow, Graph[u][v]);
        }
  
        for (v = t; v != s; v = p[v]) {
          u = p[v];
          Graph[u][v] -= path_flow;
          Graph[v][u] += path_flow;
        }
  
        // Adding the path flows
        max_flow += path_flow;
      }
  
      return max_flow;
    }
  
    public static void main(String[] args) throws java.lang.Exception {
      int graph[][] = new int[][] { { 0, 8, 0, 0, 3, 0 }, { 0, 0, 9, 0, 0, 0 }, { 0, 0, 0, 0, 7, 2 },
          { 0, 0, 0, 0, 0, 5 }, { 0, 0, 7, 4, 0, 0 }, { 0, 0, 0, 0, 0, 0 } };
      FordFulkerson m = new FordFulkerson();
  
      System.out.println("Max Flow: " + m.fordFulkerson(graph, 0, 5));
  
    }
  }
  ```




## Huffman Coding

- technique for compressing data to reduce its size without losing any of the details.

- used to compress data in which there are frequently occuring characters

- used in GZIP, BZIP2, PKZIP, etc. for text and fax transmissions

- given a string `"BCAADDDCCACACAC"`

  - each character is 8 bits, and total of 15 chars, so 120 bits are required to send this string
  - huffman creates a tree using the frequencies of the character and then generates code for each character
  - decoding is also done using the same tree
  - uses **prefix code** in the decoding process to prevent ambiguity - a code associated with a character should not be present in the prefix of any other code. 

- Steps:

  1. calculate the frequency of each charcater in the string i.e. `B = 1, C = 6, A = 5, D = 3`

  2. sort the characters in an increasing order of the frequency.  these are sorted in a priority queue. `Q = B=1, D=3, A=5, C=6`

  3. make each unique character as a leaf node

  4. create an empty node `Z` and assign the minimum frequency to the left child of Z and assign the second minimum frequency to the right child of Z. set the value of the Z as the sum of the above 2 minimum frequencies.

     ![huffman coding](https://cdn.programiz.com/sites/tutorial2program/files/hf-encoding-1.png)

  5. remove these 2 minimum frequencies from Q and add the sum into the list of frequencies

  6. insert node Z into the tree

  7. repeat steps 3 to 5 for all the characters

  8. for each non-leaf node, assign 0 to the left edge and 1 to the right edge

     ![huffman coding](https://cdn.programiz.com/sites/tutorial2program/files/hf-encoding-4.png)

     - for sending the above string over a network, we have to send the tree as well as the above compressed code. the total size is given below:

     | Character       | Frequency | Code | Size     |
     | :-------------- | :-------- | :--- | :------- |
     | A               | 5         | 11   | 5*2 = 10 |
     | B               | 1         | 100  | 1*3 = 3  |
     | C               | 6         | 0    | 6*1 = 6  |
     | D               | 3         | 101  | 3*3 = 9  |
     | 4 * 8 = 32 bits | 15 bits   |      | 28 bits  |

		- without encoding, the total size would be 120 bits. after encoding, the size is reduced to 32 + 15 + 28 = 75 bits.

#### Decoding the code

- traverse through the tree to find the character. `101` is to be decoded, we can traverse from the root as shown below

![huffman coding](https://cdn.programiz.com/sites/tutorial2program/files/hf-decoding.png)

- Complexity
  - time: O(N log N)

```java
class HuffmanNode {
  int item;
  char c;
  HuffmanNode left;
  HuffmanNode right;
}

// For comparing the nodes
class ImplementComparator implements Comparator<HuffmanNode> {
  public int compare(HuffmanNode x, HuffmanNode y) {
    return x.item - y.item;
  }
}

// IMplementing the huffman algorithm
public class Huffman {
  public static void printCode(HuffmanNode root, String s) {
    if (root.left == null && root.right == null && Character.isLetter(root.c)) {

      System.out.println(root.c + "   |  " + s);

      return;
    }
    printCode(root.left, s + "0");
    printCode(root.right, s + "1");
  }

  public static void main(String[] args) {

    int n = 4;
    char[] charArray = { 'A', 'B', 'C', 'D' };
    int[] charfreq = { 5, 1, 6, 3 };

    PriorityQueue<HuffmanNode> q = new PriorityQueue<HuffmanNode>(n, new ImplementComparator());

    for (int i = 0; i < n; i++) {
      HuffmanNode hn = new HuffmanNode();

      hn.c = charArray[i];
      hn.item = charfreq[i];

      hn.left = null;
      hn.right = null;

      q.add(hn);
    }

    HuffmanNode root = null;

    while (q.size() > 1) {

      HuffmanNode x = q.peek();
      q.poll();

      HuffmanNode y = q.peek();
      q.poll();

      HuffmanNode f = new HuffmanNode();

      f.item = x.item + y.item;
      f.c = '-';
      f.left = x;
      f.right = y;
      root = f;

      q.add(f);
    }
    System.out.println(" Char | Huffman code ");
    System.out.println("--------------------");
    printCode(root, "");
  }
}
```



