Graph
====

#### Types:

- **Undirected graphs**

  - edges between 2 vertices do not have direction, indicating a 2-way relationship

    ![img](https://assets.leetcode.com/static_assets/explore/The_basic_of_graph_1.png)

- **Directed graphs**

  - edges between 2 vertices are directional

    ![img](https://assets.leetcode.com/static_assets/explore/The_basic_of_graph_2.png)

- **Weighted graphs**

  - edges between 2 vertices have weight in it. 

  - it can be metric, i.e. time, distance, size, etc

    ![img](https://assets.leetcode.com/static_assets/explore/The_basic_of_graph_3.png)



#### Terminologies:

- graph is a non-linear data structure consisting of vertices and edges

- nodes are called vertices

- edges are the connection between 2 nodes / vertices

- path are the sequence of vertices to go through, from one vertex to another. 

  - i.e. path from A to C is [A, B, C],  or [A, G, B, C], or [A, E, F, D, B, C]
  - there can be multiple path between 2 vertices

- path length is the number of edges in a path.

  - i.e. path length from A to C are 2, 3, 5 respectively for the above path

- cycle - a path where starting point and endpoint are the same vertex

  - [A, B, D, F, E] forms a cycle.  Similarly, [A, G, B] forms another cycle

- negative weight cycle - in a weighted graph, if the sum of the edges of a cycle is negative, then its a negative weight cycle

  - i.e. the sum of weights is -3

    ![img](https://assets.leetcode.com/static_assets/explore/4._Negative_Cycle.png)

- degree of a vertex - applies to unweighted graphs. This is the number of edges connecting the vertex.

  - ie. the degree of vertex A is 3 because 3 edges are connecting it
  - the number of nodes connecting that's connected to this node

- in-degree - in a directed graph, the in-degree of a vertex is *d* are the number of directional edges directed ***to*** the vertex *d*

  - i.e. the in-degree of A is 1 because only F connects directly to A. 

- out-degree - in a directed graph, the out-degree of a vertex *d* are the number of directional edges directed ***from*** the vertex *d*

  - i.e. the out-degree of vertex A is 3 because A outwardly points to vertices G, B, C



## Disjoint Set

- is a data structure that addresses the connectivity between the components of a network such as graph, computer network, or social network. 

- use cases:

  - we can use disjoint set to determine if 2 people share a common ancestor
  - use to detect cycles in a graph
  - use in maze generation problems

- we can use to checks if 2 vertices are connected.

  ![img](https://leetcode.com/explore/learn/card/Figures/Graph_Explore/Disjoint_Set_1_edited.png)

#### How does it work?

- the `find` function - finds the root node of a given vertex
  - in the above graph, the output of the `find(3)`  = 0
- the `union` function - unions 2 vertices and makes their root nodes the same
  - in the above graph, the union of vertex 4 and 5 - the root node will become the same, which means that the union function will modify the root node of the vertex 4 and 5 to the same root node



#### Implementation

- Implementation with *Quick Find* - 
  - time complexity `find()` = O(1)
  - `union()` = O(N)
- Implementation with *Quick Union*
  - union() will better than the Quick Find solution, but find() will be worse



### Quick Find Implementation

Time complexity: 

- `union()` function - O(N) - we need to traverse all array to find all `rootY`
- `find()` function - O(1)
- `connected()` function - O(1)
- `UnionFind()` constructor - O(N) - we need to create N array size

Space complexity

- O(N) - the size of the array

```java
class UnionFind {
    private int[] root;	// contains the root values of each vertex represented as index

    public UnionFind(int size) {
        root = new int[size];
        for (int i = 0; i < size; i++) {
            root[i] = i;		// assign the vertex as their own root i.e. disconnected
        }
    }

    public int find(int x) {
        return root[x];
    }
		
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            // if they are not in the same set, assign all rootY to rootX
            for (int i = 0; i < root.length; i++) {
                if (root[i] == rootY) {
                    root[i] = rootX;
                }
            }
        }
    }

    public boolean connected(int x, int y) {
        return find(x) == find(y);	// when they have the same root
    }
}

// App.java
// Test Case
public class App {
    public static void main(String[] args) throws Exception {
        UnionFind uf = new UnionFind(10);
        // 1-2-5-6-7 3-8-9 4
        uf.union(1, 2);
        uf.union(2, 5);
        uf.union(5, 6);
        uf.union(6, 7);
        uf.union(3, 8);
        uf.union(8, 9);
        System.out.println(uf.connected(1, 5)); // true
        System.out.println(uf.connected(5, 7)); // true
        System.out.println(uf.connected(4, 9)); // false
        // 1-2-5-6-7 3-8-9-4
        uf.union(9, 4);
        System.out.println(uf.connected(4, 9)); // true
    }
}
```



### Quick Union Implementation

- more efficient than Quick Find if and only if we want to connect all the N elements
- uses the path compression technique
- the more depth connection the graph has, the more time it will take `find()`
- is more efficient in the `union()` because it just gets the root of Y and assigns root of X to it.

Time complexity:

- `find()` - O(N) - worse case, we need to traverse every vertex to find the root
- `union()` - O(N) - still needs to use the `find()` calls, but once the root is found, the assignment is O(1)
- `connected()` - O(N) - involves 2 `find()` calls
- `UnionFind()` constructor - O(N) - create N array

Space complexity:

- O(N) - create N array

```java
class UnionFind {
    private int[] root;

    public UnionFind(int size) {
        root = new int[size];
        for (int i = 0; i < size; i++) {
            root[i] = i;
        }
    }

    public int find(int x) {
        while (x != root[x]) {		// traverse the vertex to find root if x is not root
            x = root[x];
        }
        return x;
    }

    public void union(int x, int y) {
        int rootX = find(x);		// find the root of X
        int rootY = find(y);		// find the root of Y
        if (rootX != rootY) {		
            root[rootY] = rootX;	// assign the root of Y to rootX
        }
    }

    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
}
```



### Union by Rank Implementation

- more efficient than Quick Union, because of ranks, which allows the height of the graph to be minimal

Time complexity:
- constructor: O(N)
- `find()`: O(log N)
- `union()`: O(log N)
- `connected()`: O(log N)

Space: O(N)

```java
class UnionFind {
    private int[] root;
    private int[] rank;

    public UnionFind(int size) {
        root = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            root[i] = i;	// assign the root to themselves
            rank[i] = 1; 	// assign the rank to 1 since they have themselves as root
        }
    }

    public int find(int x) {
        while (x != root[x]) {
            x = root[x];
        }
        return x;
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            if (rank[rootX] > rank[rootY]) {	// checks the rank, w/e is higher
                root[rootY] = rootX;	// since rootX is higher, rootX becomes root of Y
            } else if (rank[rootX] < rank[rootY]) {
                root[rootX] = rootY;
            } else {					// they are equal, so choose any
                root[rootY] = rootX;	// rootX becomes root of Y
                rank[rootX] += 1;		// increment the rootX rank
            }
        }
    }

    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
}
```



### Path Compression Optimization

- is another alternative to optimize Quick Union
- As we `find(x)`, the root of x is updated, thereby when we search it next time, it will be O(1)

Time: 

- constructor: O(N)
- Find: O(log N) - average case, O(1), best case, O(N) worse case
- Union: O(log N)
- Connected: O(log N)

```java
class UnionFind {
    private int[] root;

    public UnionFind(int size) {
        root = new int[size];
        for (int i = 0; i < size; i++) {
            root[i] = i;
        }
    }

    public int find(int x) {	// as we use find(), we consequently update root[x]
        if (x == root[x]) {
            return x;
        }
        return root[x] = find(root[x]);
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            root[rootY] = rootX;
        }
    }

    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
}
```



### Optimized with both Path Compression and Union by Rank

Time:

- constructor: O(N)
- Find: O($\alpha$(N)) 
- Union: O($\alpha$(N)) 
- Connected: O($\alpha$(N)) 

```java
class UnionFind {
    private int[] root;
    // Use a rank array to record the height of each vertex, i.e., the "rank" of each vertex.
    private int[] rank;

    public UnionFind(int size) {
        root = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            root[i] = i;
            rank[i] = 1; // The initial "rank" of each vertex is 1, because each of them is
                         // a standalone vertex with no connection to other vertices.
        }
    }

	// The find function here is the same as that in the disjoint set with path compression.
    public int find(int x) {
        if (x == root[x]) {
            return x;
        }
        return root[x] = find(root[x]);
    }

	// The union function with union by rank
    public void union(int x, int y) {
        int rootX = find(x);	// the x is already optimzed by using find(x)
        int rootY = find(y);	// the y is already optimzed by using find(y)
        if (rootX != rootY) {
            // we still need to do the ranking because there might be some Y where it has 			  // not yet been set a root
            if (rank[rootX] > rank[rootY]) {
                root[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                root[rootX] = rootY;
            } else {
                root[rootY] = rootX;
                rank[rootX] += 1;
            }
        }
    }

    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
}
```



### Notes

- the main idea of disjoint set, is to have all connected vertices have the same parent node
- to check if the 2 vertices are connected, we only need to check if they have the same root / parent node



## Number of Provinces - Disjoint Sets

```java
// Union Find
    public int findCircleNum(int[][] isConnected) {
        if (isConnected == null || isConnected.length == 0) {
            return 0;
        }

        int n = isConnected.length;
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }

        return uf.getCount();
    }

    class UnionFind {
        private int[] root;
        private int[] rank;
        private int count;

        UnionFind(int size) {
            root = new int[size];
            rank = new int[size];
            count = size;
            for (int i = 0; i < size; i++) {
                root[i] = i;
                rank[i] = 1;
            }
        }

        int find(int x) {
            if (x == root[x]) {
                return x;
            }
            return root[x] = find(root[x]);
        }

        void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                if (rank[rootX] > rank[rootY]) {
                    root[rootY] = rootX;
                } else if (rank[rootX] < rank[rootY]) {
                    root[rootX] = rootY;
                } else {
                    root[rootY] = rootX;
                    rank[rootX] += 1;
                }
                count--;
            }
        }

        int getCount() {
            return count;
        }
    }
```

