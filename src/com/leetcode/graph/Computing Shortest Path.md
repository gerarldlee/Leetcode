# Computing the shortest path

- single source shortest path (SSSP) - 
  - finding the shortest path between a given vertex V, and all other verteces in the graph
  - djikstra's algorithm
  - can also use BFS for unweighted graph
- single source shortest path with negative edge weights -
  - use bellman-ford algorithm
- single pair shortest path (SPSP) -
  - finding the shortest path between single pair of vertices
  - solved using djikstra's, but the single result is kept and the other shortest path is discarded
- all pairs shortest path (APSP) -
  - finding the shortest path between all pairs of vertices in the graph
  - floyd-warshall algorithm
  - or apply the djikstra's algorithm for each and every vertex in the graph