Single Source Shortest Path
====

- the BFS shortest path can only be solved, under the assumption of <u>*unweighted graph*</u>
- For weighted graph, Dijkstra's algorithm or the Bellman-Ford is the most common
- use case:
	- route from your home to office, but the weight factor is time between bus stops, not distance
	- telephone network
	- IP routing
	- GPS

#### Edge Relaxation

A technique to minimize and update the connection from a vertex to another directly connected vertex by preferring lesser valued weights.

- If A -> B = 1, A -> C = 1, A -> D = 3, B -> D = 1
- Applying edge relaxation, instead of A -> D = 3, it will update it with 2 because D can be reached from A with traveling to B and then from B travelling to D.

## Dijkstra's Algorithm

- only applicable when all weights are **positive**

- works by edge relaxation technique

- Complexity:
  - Time: O(E + V log V) when fibonacci heap is used, O(V + E log V) for binary heap
  - Space: O(V)
  
  ```
  function dijkstra(G, S)
  	for each vertex V in G
  		distance[V] <- infinite
  		previous[V] <- NULL
  		if V != S, add V to Priority Queue Q
  	distance[S] <- 0
  	
  	while Q is not empty
  		U <- extract MIN from Q
  		for each univisted neighbor V of U
  			tempDistance <- distance[U] + edge_weight(U, V)
  			if tempDistance < distance[V]
  				distance[V] <- tempDistance
  				previous[V] <- U
  	
  	return distance[], previous[]
  ```
  
  


#### Network Delay Time - Dijkstra's Algorithm

```java
class Solution {
    // Adjacency list
    Map<Integer, List<Pair<Integer, Integer>>> adj = new HashMap<>();
    
    private void dijkstra(int[] signalReceivedAt, int source, int n) {
        Queue<Pair<Integer, Integer>> pq = new PriorityQueue<Pair<Integer,Integer>>
            (Comparator.comparing(Pair::getKey));
        pq.add(new Pair(0, source));
        
        // Time for starting node is 0
        signalReceivedAt[source] = 0;
        
        while (!pq.isEmpty()) {
            Pair<Integer, Integer> topPair = pq.remove();
            
            int currNode = topPair.getValue();
            int currNodeTime = topPair.getKey();
            
            if (currNodeTime > signalReceivedAt[currNode]) {
                continue;
            }
            
            if (!adj.containsKey(currNode)) {
                continue;
            }
            
            // Broadcast the signal to adjacent nodes
            for (Pair<Integer, Integer> edge : adj.get(currNode)) {
                int time = edge.getKey();
                int neighborNode = edge.getValue();
                
                // Fastest signal time for neighborNode so far
                // signalReceivedAt[currNode] + time : 
                // time when signal reaches neighborNode
                if (signalReceivedAt[neighborNode] > currNodeTime + time) {
                    signalReceivedAt[neighborNode] = currNodeTime + time;
                    pq.add(new Pair(signalReceivedAt[neighborNode], neighborNode));
                }
            }
        }
    }
    
    public int networkDelayTime(int[][] times, int n, int k) {
        // Build the adjacency list
        for (int[] time : times) {
            int source = time[0];
            int dest = time[1];
            int travelTime = time[2];
            
            adj.putIfAbsent(source, new ArrayList<>());
            adj.get(source).add(new Pair(travelTime, dest));
        }
        
        int[] signalReceivedAt = new int[n + 1];
        Arrays.fill(signalReceivedAt, Integer.MAX_VALUE);
        
        dijkstra(signalReceivedAt, k, n);
        
        int answer = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            answer = Math.max(answer, signalReceivedAt[i]);
        }
        
        // INT_MAX signifies atleat one node is unreachable
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}
```



## Bellman-Ford Algorithm

- can solve weighted directed graph, with **any weights** even if the weights are **negative**
- only applicable to "graphs" with no "negative weight cycles"
- even though BFA cannot find the shortest path in a graph with negative weight cycles, it can detect whether there exists a negative weight cycle in the graph
  - **Detection method:** After relaxing each edge `N-1` times, perform the `N`th relaxation. According to the “Bellman-Ford algorithm”, all distances must be the shortest after relaxing each edge `N-1` times. However, after the `N`th relaxation, if there exists `distances[u] + weight(u, v) < distances(v)` for any `edge(u, v)`, it means there is a shorter path . At this point, we can conclude that there exists a “negative weight cycle”
- https://www.youtube.com/watch?v=lyw4FaxrwHg
- https://www.programiz.com/dsa/bellman-ford-algorithm


#### Theorems

1. In a graph with "no negative weight cycles", with N vertices, the shortest path between any two vertices has at most N-1 edges
2. In a graph with "no negative weight cycles", there is no shortest path
3. Use dynamic programming to find the shortest path

   1. Time: O(V * E)
   2. Space: O(V^2)


### Algorithm

1. Start with the weighted graph

2. Choose a starting vertex and assign infinity path values to all other vertices

3. Visit each edge and relax the path distances if they are inaccurate

4. We need to do this V times because in the worse-case, a vertex path length might need to be readjusted V times

5. Notice how the vertex at the top right corner had its path length adjusted

6. After all the vertices have their path lengths, we check if a negative cycle is present

   ```
   function bellmanFord(G, S)
     for each vertex V in G
       distance[V] <- infinite
         previous[V] <- NULL
     distance[S] <- 0
   
     for each vertex V in G				
       for each edge (U,V) in G
         tempDistance <- distance[U] + edge_weight(U, V)
         if tempDistance < distance[V]
           distance[V] <- tempDistance
           previous[V] <- U
   
     for each edge (U,V) in G
       If distance[U] + edge_weight(U, V) < distance[V}
         Error: Negative Cycle Exists
   
     return distance[], previous[]
   ```



#### Bellman Ford vs Dijkstra

- very similar in structure
- dijkstra looks only to the immediate neighbors of a vertex, bellman goes through each edge in every iteration

```java
// Bellman Ford Algorithm in Java

class CreateGraph {

  // CreateGraph - it consists of edges
  class CreateEdge {
    int s, d, w;

    CreateEdge() {
      s = d = w = 0;
    }
  };

  int V, E;
  CreateEdge edge[];

  // Creates a graph with V vertices and E edges
  CreateGraph(int v, int e) {
    V = v;
    E = e;
    edge = new CreateEdge[e];
    for (int i = 0; i < e; ++i)
      edge[i] = new CreateEdge();
  }

  void BellmanFord(CreateGraph graph, int s) {
    int V = graph.V, E = graph.E;
    int dist[] = new int[V];

    // Step 1: fill the distance array and predecessor array
    for (int i = 0; i < V; ++i)
      dist[i] = Integer.MAX_VALUE;

    // Mark the source vertex
    dist[s] = 0;

    // Step 2: relax edges |V| - 1 times
    for (int i = 1; i < V; ++i) {
      for (int j = 0; j < E; ++j) {
        // Get the edge data
        int u = graph.edge[j].s;
        int v = graph.edge[j].d;
        int w = graph.edge[j].w;
        if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v])
          dist[v] = dist[u] + w;
      }
    }

    // Step 3: detect negative cycle
    // if value changes then we have a negative cycle in the graph
    // and we cannot find the shortest distances
    for (int j = 0; j < E; ++j) {
      int u = graph.edge[j].s;
      int v = graph.edge[j].d;
      int w = graph.edge[j].w;
      if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
        System.out.println("CreateGraph contains negative w cycle");
        return;
      }
    }

    // No negative w cycle found!
    // Print the distance and predecessor array
    printSolution(dist, V);
  }

  // Print the solution
  void printSolution(int dist[], int V) {
    System.out.println("Vertex Distance from Source");
    for (int i = 0; i < V; ++i)
      System.out.println(i + "\t\t" + dist[i]);
  }

  public static void main(String[] args) {
    int V = 5; // Total vertices
    int E = 8; // Total Edges

    CreateGraph graph = new CreateGraph(V, E);

    // edge 0 --> 1
    graph.edge[0].s = 0;
    graph.edge[0].d = 1;
    graph.edge[0].w = 5;

    // edge 0 --> 2
    graph.edge[1].s = 0;
    graph.edge[1].d = 2;
    graph.edge[1].w = 4;

    // edge 1 --> 3
    graph.edge[2].s = 1;
    graph.edge[2].d = 3;
    graph.edge[2].w = 3;

    // edge 2 --> 1
    graph.edge[3].s = 2;
    graph.edge[3].d = 1;
    graph.edge[3].w = 6;

    // edge 3 --> 2
    graph.edge[4].s = 3;
    graph.edge[4].d = 2;
    graph.edge[4].w = 2;

    graph.BellmanFord(graph, 0); // 0 is the source vertex
  }
}
```





## Improved Bellman-Ford Algorithm - Shortest Path Faster Algorithm

- Time: O(V * E)
- Space: O(V)
- SPFA - shortest path faster algorithm - is an improvement of the BFA algorithm that uses a queue
- reduces the number of relaxation steps that needed to be performed within each iteration


### Cheapest Flights within K Stops - Bellman Ford

```java
class Solution {
    // Bellman Ford Algorithm
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        if (src == dst) {
            return 0;
        }

        int[] previous = new int[n];
        int[] current = new int[n];
        for (int i = 0; i < n; i++) {
            previous[i] = Integer.MAX_VALUE;
            current[i] = Integer.MAX_VALUE;
        }
        previous[src] = 0;
        for (int i = 1; i < k + 2; i++) {
            current[src] = 0;
            for (int[] flight : flights) {
                int previous_flight = flight[0];
                int current_flight = flight[1];
                int cost = flight[2];

                if (previous[previous_flight] < Integer.MAX_VALUE) {
                    current[current_flight] = Math.min(current[current_flight],
                                                       previous[previous_flight] + cost);
                }
            }
            previous = current.clone();
        }
        return current[dst] == Integer.MAX_VALUE ? -1 : current[dst];
    }
}
```



## A* Algorithm

