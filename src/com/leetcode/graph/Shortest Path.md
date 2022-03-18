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


#### Theorems

1. In a graph with "no negative weight cycles", with N vertices, the shortest path between any two vertices has at most N-1 edges

2. In a graph with "no negative weight cycles", there is no shortest path

3. Use dynamic programming to find the shortest path

   1. Time: O(V * E)
   2. Space: O(V^2)

   

## Improved Bellman-Ford Algorithm

- Time: O(V * E)
- Space: O(V)
- SPFA - shortest path faster algorithm - is an improvement of the BFA algorithm that uses a queue


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

