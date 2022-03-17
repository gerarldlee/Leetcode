Minimum Spanning Tree
====

- a spanning tree is a connected sub-graph in a undirected graph where *<u>all vertices</u>* are connected with the *<u>minimum number</u>* of edges

- use cases:

  - telephone or cable networks, to minimize the cost of cabling
  - traveling salesman problem
  - cluster analysis
  - real-time face tracking and verification
  - protocols in computer science to avoid network cycles
  - max bottleneck paths
  - dithering - adding white noise to digital recording to reduce distortion
  - entropy based image registration

- the pink edges `[(A, B), (A, C), (A, D), (A, E)]` form a spanning tree

  ![img](https://leetcode.com/explore/learn/card/Figures/Graph_Explore/Spanning_Tree.png)

- a minimum spanning tree is a spanning tree with the minimum possible total edge weight in a weighted undirected graph

- the green edges `[(A, E), (A, B), (B, C), (C, D)]` and `[(A, E), (E, D), (A, B), (B, C)]`

- a weighted undirected graph can have multiple minimum spanning tree

  ![img](https://leetcode.com/explore/learn/card/Figures/Graph_Explore/Minimum_Spanning_Tree.png)

## Cut Property

- in graph, a "cut" is a partition of vertices in a graph into two disjoin subsets
- `B, A, E` forms one subset and `C, D` forms another
- a crossing edge is an edge that connects a vertex in one set with a vertex in the other set
- `[B, C], [A, C], [A, D], [E, D]` are all crossing edges

![img](https://leetcode.com/explore/learn/card/Figures/Graph_Explore/Cut_Property.png)

#### The "cut property" definition

For any cut `C` of the graph, if the weight of an edge `E` in the cut-set of `C` is strictly smaller than the weights of all other edges of the cut-set of `C`, then this edge belongs to all MSTs of the graph.

## Kruskal's Algorithm

- is an algorithm to construct a minimum spanning tree of a weighted undirected graph
- How it works?
  1. Sort all the edges in non-decreasing order of their weight
  2. Pick the smallest edge. Check if it forms a cycle with the spanning tree formed so far. 
     - if cycle is not formed, include this edge
     - if cycle is formed, discard it
  3. Repeat step 2 until there are V-1 edges in the spanning 

#### Min Cost to Connect All Points - Kruskal

- Time: O(E log E)
- Space: O(E)

```java
class Solution {
    // Kruskal's Algorithm
    public int minCostConnectPoints(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        int size = points.length;
        PriorityQueue<Edge> pq = new PriorityQueue<>((x, y) -> x.cost - y.cost);
        UnionFind uf = new UnionFind(size);

        for (int i = 0; i < size; i++) {
            int[] coordinate1 = points[i];
            for (int j = i+1; j < size; j++) {
                int[] coordinate2 = points[j];
                // Calculate the distance between two coordinates.
                int cost = Math.abs(coordinate1[0] - coordinate2[0]) + 
                           Math.abs(coordinate1[1] - coordinate2[1]);
                Edge edge = new Edge(i, j, cost);
                pq.add(edge);
            }
        }

        int result = 0;
        int count = size - 1;
        while (!pq.isEmpty() && count > 0) {
            Edge edge = pq.poll();
            if (!uf.connected(edge.point1, edge.point2)) {
                uf.union(edge.point1, edge.point2);
                result += edge.cost;
                count--;
            }
        }
        return result;
    }

    class Edge {
        int point1;
        int point2;
        int cost;

        Edge(int point1, int point2, int cost) {
            this.point1 = point1;
            this.point2 = point2;
            this.cost = cost;
        }
    }

    class UnionFind {
        int root[];
        int rank[];

        public UnionFind(int size) {
            root = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                root[i] = i;
                rank[i] = 1; 
            }
        }

        public int find(int x) {
            if (x == root[x]) {
                return x;
            }
            return root[x] = find(root[x]);
        }

        public void union(int x, int y) {
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
            }
        }

        public boolean connected(int x, int y) {
            return find(x) == find(y);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        int[][] points = {{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}};
        Solution solution = new Solution();
        System.out.print("Minimum Cost to Connect Points = "); 
        System.out.println(solution.minCostConnectPoints(points)); 
    }
}
```





## Prim's Algorithm

- is also an algorithm that's alternative to Kruskal's
- used to construct the minimum spanning tree in a weighted undirected graph
- How it works?
  - Create an empty array of visited set, and a non-visited set. Put all the vertices of the graph into the non-visited set
  - Select an arbitrary vertex from non-visited set and move it to the visited set. 
  - Among all the edges coming out from **all vertices** of the visited set, select the next vertex with the minimum weighted edge and add this to the visited set
    - when comparing, disregard the vertices that has been added to the visited set already
    - select only from those vertices that's in the non-visited set
- the difference between Kruskal's and Prim's is that Kruskal's expands the minimum spanning tree by adding edges, whereas Prim's expands the MST by adding vertices

#### Min Cost to Connect All Points - Prims



```java
class Solution {
    // Prim Algorithm
    public int minCostConnectPoints(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        int size = points.length;
        PriorityQueue<Edge> pq = new PriorityQueue<>((x, y) -> x.cost - y.cost);
        boolean[] visited = new boolean[size];
        int result = 0;
        int count = size - 1;
        // Add all edges from points[0] vertexs
        int[] coordinate1 = points[0];
        for (int j = 1; j < size; j++) {
            // Calculate the distance between two coordinates.
            int[] coordinate2 = points[j];
            int cost = Math.abs(coordinate1[0] - coordinate2[0]) + 
                       Math.abs(coordinate1[1] - coordinate2[1]);
            Edge edge = new Edge(0, j, cost);
            pq.add(edge);
        }
        visited[0] = true;

        while (!pq.isEmpty() && count > 0) {
            Edge edge = pq.poll();
            int point1 = edge.point1;
            int point2 = edge.point2;
            int cost = edge.cost;
            if (!visited[point2]) {
                result += cost;
                visited[point2] = true;
                for (int j = 0; j < size; j++) {
                    if (!visited[j]) {
                        int distance = Math.abs(points[point2][0] - points[j][0]) + 
                                       Math.abs(points[point2][1] - points[j][1]);
                        pq.add(new Edge(point2, j, distance));
                    }
                }
                count--;
            }
        }
        return result;
    }

    class Edge {
        int point1;
        int point2;
        int cost;

        Edge(int point1, int point2, int cost) {
            this.point1 = point1;
            this.point2 = point2;
            this.cost = cost;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        int[][] points = {{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}};
        Solution solution = new Solution();
        System.out.print("Minimum Cost to Connect Points = "); 
        System.out.println(solution.minCostConnectPoints(points)); 
    }
}
```

