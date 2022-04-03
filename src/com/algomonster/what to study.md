highest roi - dfs, bfs, two pointers, binary search
linked list, stack and queue
priority queue, heap
greedy and dp
trie, union find,

almost never used

minimal spanning tree - kruskal and prims
minimum cut - ford fulkerson
shortest path in weighted graph - bellman ford
string search - boyer moore

knuth-morris-pratt vs bug free brute force coding
dijkstra - shortest path in weighted graph, uses priority queue
robin-karp rolling hash - solving 2 pointers

## keywords:

top k... - heap (k closest)

how many ways... - dp (robot path) - dfs (decode ways)

substring... - sliding window (longest substring)

palindrome... - 2 pointers (valid) - dfs (partitioning) - dp (partitioning)

tree... - shortest... or level-order... - bfs - dfs i.e. max depth

parenthesis... - stack

subarray... - prefix sum (subarray sum) - hashmap (continuous subarray sum)

x sum... - 2 pointers

max / longest sequence... - dp - dfs (longest increasing subsequence) - mono deque (sliding window maximum)

minimum / shortest... - dp, dfs (minimal path sum) - bfs (shortest path)

partitioning / split ... array / string... - dfs (decode ways)

subsequence... - dp, dfs (longest increasing subsequence) - sliding window

matrix... - dfs, bfs (flood fill) - dp (maximal square)

jump... - greedy, dp (jump game)

game - dp (divisor game, stone game)

connected component, cut/remove, regions, groups, connections... - union find (number of connected components, redundant connections)

transitive relationship - bfs (word ladder) - bfs, union find (sentence similarity) - bfs, union find (evaluate division)

interval - greedy (interval pattern)

## Templates

#### Backtracking

```java
backtrack(List result, int[] array, int state) {
    if (isvalid(state)) {
        result.add(state);
        return;
    }

    for (int choice : array) {
        // try a choice
        state.process(choice)

        backtrack(result, array, state);

        // remove
        state.remove(choice);
    }
}
```

#### Binary Search

```java
int l = 0;
int r = array.length;
while (l <= r) {
    int m = (l + r) / 2;
    if (array[m] == target)
        return m;
    else if (array[m] < target) {
        l = mid + 1;
    }
    else {
        r = mid + 1;
    }
}
```

#### BFS on a Tree

```java
void bfs(Node root) {
    Deque queue = new ArrayDeque();
    queue.offer(root);
    while (!queue.isEmpty()) {
        Node n = queue.poll();
        // process node

        for (Node child : n.children) {
            queue.offer(child);
        }
    }
}
```

#### BFS on a Graph

```java
void bfs(Node root) {
    Deque queue = new ArrayDeque();
    queue.offer(root);
    Set<Node> visitedNodes = new HashSet<>();
    while (!queue.isEmpty()) {
        Node n = queue.poll();
        // process node
        for (Node neighbor : n.neighbors) {

            if (!visitedNodes.contains(neighbor)) {
            queue.offer(neighbor);
            visitedNodes.add(n);
            }
        }
    }
}
```

#### DFS on a Tree

```java
void dfs(Node root) {
    if (root == null) return;

    // process
    root.val;
    dfs(root.left);
    dfs(root.right);
}
```

#### DFS on a Graph

```java
void dfs(Node root, Set<Node> visited) {
    if (root == null) return;

    // process
    root.val;

    for (Node neighbor : root.neighbors) {
        if (!visited.contains(neighbor)) {
            visited.add(neighbor);
            dfs(neighbor, visited);
        }
    }
}
```

#### Trie

```java
class Trie {
    char value;
    HashMap<Character, Trie> children = new HashMap();
    Trie(char value) {
        this.value = value;
    }
    void insert(String s, int index) {
        if (index == s.length()) return;
        if (children.contains(s.charAt(index))) {
            children.get(s.charAt(index))
                .insert(s, index+1);
        }
        else {
            children.put(s.charAt(index), new Trie(s.charAt(index)));
            children.get(s.charAt(index))
                .insert(s, index+1);
        }
    }
}
```

#### Union Find / disjoint set

```java
class UnionFind {
    Map<Integer, Integer> map = new HashMap<>();
    Integer find(Integer x) {
        Integer y = map.getOrDefault(x, x);
        if (y != x) {
            y = this.find(y);
            map.put(x, y);
        }
        return y;
    }
    void union(Integer x, Integer y) {
        map.put(find(x), find(y));
    }
    boolean connected(Integer x, Integer y) {
        return find(x) == find(y);
    }
}
```

#### Topological Sort

```java
class LinkedNode {
    int indegrees = 0;
    List<LinkedNode> children = new LinkedList<>();
}
Map<Integer, LinkedNode> counts(Map<Integer, List<Integer>> dependencies) {
    Map<T, Integer> counts = new HashMap<>();
    graph.keySet().forEach(node -> {
        counts.put(node, 0);
    });
    // loop through every node and add to the child node 1 parent
    graph.entrySet().forEach(entry -> {
        for (T node : entry.getValue()) {
            counts.put(node, counts.get(node) + 1);
        }
    });
    return counts;
}
List<Integer> topologicalSort(Map<Integer, List<Integer>> adjacentMatrix) {

    List<Integer> result = new ArrayList<>();
    Queue<Integer> queue = new ArrayDeque<>();

    Map<Integer, LinkedNode> counts = counts(adjacentMatrix);
    // add the 0 in-degree to queue
    counts.entrySet().forEach(entry -> {
        if (entry.getValue() == 0) {
            queue.add(entry.getKey());
        }
    });

    // do a BFS and process nodes one by one that does not have in-degrees, as well as decrease the count
    // of the nodes that have in-degrees > 0
    while(!queue.isEmpty()) {
        Integer node = queue.poll();
        result.add(node);

        for (Integer child : adjacentMatrix.get(node)) {
            // decrease the dependency of each child to the "processed parent"
            counts.put(child, counts.get(child) - 1);
            if (counts.get(child) == 0) {
                queue.add(child);
            }
        }
    }
}
```

#### Heap
