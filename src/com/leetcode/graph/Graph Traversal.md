DFS
===

- ideal for finding all vertices, and paths between 2 vertices
- DFS can explore all paths from starting vertex to all other vertex
- it is mainly used to:
  - traverse all vertices in a graph
  - traverse all paths between any two vertices in a graph
- Time complexity: O(V + E)
- Space: O(V)
- Uses a Stack

### Traversing all paths between vertices

- Time Complexity: O((V - 1)!) The above example is for an undirected graph. The worst-case scenario, when trying to find all paths, is a complete graph. A complete graph is a graph where every vertex is connected to every other vertex.

- Space Complexity: O(V^3)

  . The space used is by the stack which will contain:

  - (V - 1) paths after adding first V - 1 paths to the stack.
  - (V - 1) - 1 + (V - 2) paths after popping one path and adding second set of paths.
  - (V - 1) - 1 + (V - 2) - 1 + (V - 3) - 1 + ...
  - ≈*V*⋅(*V*−1)/2+1 paths will be at most in the stack, and each path added to the stack will take O(V) space.

### Find if Path Exists in Graph - DFS

Complexity:

- Time Complexity: O(V + E). Here, V*V* represents the number of vertices, and E represents the number of edges.
  - To create the adjacency list, we must iterate over each of the E edges.
  - In the while loop, at most, we will visit vertex once.
  - The for loop inside the while loop will have a cumulative sum of at most E iterations since it will iterate over all of the node's neighbors for each node.
- Space Complexity: O(V + E)
  - The adjacency list will contain O(V + E) elements.
  - The stack will also contain O(E) elements. However, this can be reduced to O(V) by checking whether a `neighbor` node has been seen before adding it to the stack.
  - The `seen` set will use O(V) space to store the visited nodes.

```java
public boolean validPath(int n, int[][] edges, int start, int end) {
    List<List<Integer>> adjacency_list = new ArrayList<>();        
    for (int i = 0; i < n; i++) {
        adjacency_list.add(new ArrayList<>());
    }

    // represent the edges into the array
    for (int[] edge : edges) {
        adjacency_list.get(edge[0]).add(edge[1]); // connect u to v
        adjacency_list.get(edge[1]).add(edge[0]); // connect v to u in the same way
    }

    Deque<Integer> stack = new ArrayDeque<>();
    stack.push(start);
    boolean seen[] = new boolean[n];
    Arrays.fill(seen, false);

    while (!stack.isEmpty()) {
        // Get the current node.
        int node = stack.pop();

        // Check if we have reached the target node.
        if (node == end) {
            return true;
        }

        // Check if we've already visited this node.
        if (seen[node]) {
            continue;
        }
        seen[node] = true;

        // Add all neighbors to the stack.
        for (int neighbor : adjacency_list.get(node)) {
            stack.push(neighbor);
        }
    }

    return false;
}
```



### All Paths From Source to Target

Complexity:

- Time Complexity: O*(2*V*⋅*V). Here, V represents the number of vertices.
  - For a directed acyclic graph (DAG) with V*V* vertices, there could be at most 2^{V - 1} −1 possible paths to go from the starting vertex to the target vertex. We need O(V) time to build each such path.
  - Therefore, a loose upper bound on the time complexity would be (2^(*V*−1)−1)⋅*O*(*V*)=*O*(2^*V*⋅*V*).
  - Since we have overlap between the paths, the actual time spent on the traversal will be lower to some extent.
- Space Complexity: O(V). The recursion depth can be no more than V, and we need O(V) space to store all the previously visited vertices while recursively traversing deeper with the current path. Please note that we don't count the space usage for the output, i.e., to store all the paths we obtained.

```java
class Solution {
    // DFS
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> paths = new ArrayList<>();
        if (graph == null || graph.length == 0) {
            return paths;
        }

        dfs(graph, 0, new ArrayList<>(), paths);
        return paths;
    }

    void dfs(int[][] graph, int node, List<Integer> path, List<List<Integer>> paths) {
        path.add(node);
        if (node == graph.length - 1) {
            paths.add(new ArrayList<>(path));
            return;
        }
        int[] nextNodes = graph[node];
        for (int nextNode: nextNodes) {
            dfs(graph, nextNode, path, paths);
            path.remove(path.size() - 1);
        }
    }
}
```



BFS
===

- BFS can traverse all vertices of a graph and traverse all paths between two vertices
- The most advantageous use case for BFS is to efficiently find the shortest path between two vertices in a graph, where **all edges have equal and positive weights** ie. <u>***unweighted graphs***</u>
- DFS can also find the shortest path between 2 vertices in a graph with equal and positive weights, but it must traverse all paths between 2 vertices before finding the shortest one.
- BFS can find the shortest path without traversing all the paths.  This is because as soon as a path between the source vertex and target vertex is found, it is guaranteed to be the shortest path between the 2 nodes.
- It is mainly used to:
  - traverse all vertices in a graph
  - finding the shortest path between 2 vertices in a graph, where *all edges have equal and positive weights*.
- Complexity:
  - time: O(V + E)
  - Space: O(V)
- Uses Queue

### Shortest Path

- Time Complexity: O(V + E). Here, V represents the number of vertices, and E represents the number of edges. In the worst case, when the distance between the starting vertex and the target vertex is the maximum possible, we need to check every vertex and traverse through every edge in the graph.
- Space Complexity: O(V). The queue will take up to O(V) space to store all the graph vertices in the worst-case scenario. We must also use O(V) space to keep track of which vertices have been visited.

### Find if Path Exist in Graph - BFS

Complexity:

- Time Complexity: O(V + E)*O*(*V*+*E*). Here, V*V* represents the number of vertices and E represents the number of edges.
  - To create the adjacency list, we must iterate over each of the E edges.
  - In the while loop, at most we will visit vertex once.
  - The for loop inside the while loop will have a cumulative sum of at most E  iterations since it will iterate over all of the node's neighbors for each node.
- Space Complexity: O(V + E) 
  - The adjacency list, will contain O(V + E) elements.
  - The queue will also contain O(V)  elements.
  - The `seen` set will use O(V)  space to store the visited nodes.

```java
public boolean validPath(int n, int[][] edges, int start, int end) {
    List<List<Integer>> adjacency_list = new ArrayList<>();        
    for (int i = 0; i < n; i++) {
        adjacency_list.add(new ArrayList<>());
    }

    for (int[] edge : edges) {
        adjacency_list.get(edge[0]).add(edge[1]);
        adjacency_list.get(edge[1]).add(edge[0]);
    }

    Queue<Integer> queue = new LinkedList<>();
    queue.add(start);
    boolean seen[] = new boolean[n];
    Arrays.fill(seen, false);
    seen[start] = true;

    while (!queue.isEmpty()) {
        // Get the current node.
        int node = queue.poll();

        // Check if we have reached the target node.
        if (node == end) {
            return true;
        }

        // Add all neighbors to the stack.
        for (int neighbor : adjacency_list.get(node)) {
            // Check if neighbor has been added to the queue before.
            if (!seen[neighbor]) {
                seen[neighbor] = true;
                queue.add(neighbor);
            }
        }
    }

    return false;
}
```



### All Paths From Source to Target - BFS

Complexity:

- Time Complexity: O*(2*^V*⋅*V). Here, V  represents the number of vertices.
  - For a graph with V vertices, there could be at most  2^*V*−1−1 possible paths to go from the starting vertex to the target vertex. We need O(V) time to build each such path.
  - Therefore, a loose upper bound on the time complexity would be (2^(*V*−1)−1)⋅*O*(*V*)=*O*(2^*V*⋅*V*).
  - Since we have overlapping between the paths, the actual time spent on the traversal will be lower to some extent.
- Space Complexity: O*(2*^V*⋅*V*). The queue can contain O(2^V) paths and each path will take O(V) space. Therefore, the overall space complexity is  O*(2*^V*⋅*V).

```java
public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> paths = new ArrayList<>();
        if (graph == null || graph.length == 0) {
            return paths;
        }

        Queue<List<Integer>> queue = new LinkedList<>();
        List<Integer> path = new ArrayList<>();
        path.add(0);
        queue.add(path);

        while (!queue.isEmpty()) {
            List<Integer> currentPath = queue.poll();
            int node = currentPath.get(currentPath.size() - 1);
            for (int nextNode: graph[node]) {
                List<Integer> tmpPath = new ArrayList<>(currentPath);
                tmpPath.add(nextNode);
                if (nextNode == graph.length - 1) {
                    paths.add(new ArrayList<>(tmpPath));
                } else {
                    queue.add(new ArrayList<>(tmpPath));
                } 
            }
        }
        return paths;
    }
```

