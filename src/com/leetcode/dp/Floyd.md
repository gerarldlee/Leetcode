Floyd-Warshall Algorithm
====

- for finding the shortest path between all the pair of vertices in a weighted graph.

- works for both directed and undirected weighted graphs

- does not work for the graph with negative cycles

- time complexity: O(N^3)

- space: O(N^2)

  ![graph](https://cdn.programiz.com/sites/tutorial2program/files/fw-Graph.png)

### Steps:
1. Create a matrix A0 of dimension n*n where n is the number of verices. 

   1. each cell `A0[i][j]` is filled with the distance from the ith vertex to the jth vertex. if there is no path from i to j, then cell = infinity

      ![matrix floyd warshall algorithm](https://cdn.programiz.com/sites/tutorial2program/files/fw-Matrix-1.png)

   2. create a matrix A1 using A0. the elements in the first column and the first row are left as they are. the remaining cells are filled in the following way:

      1. let K be the intermediate vertex in the shortest path from source to destination. in this step, K is the first vertex. `A0[i][j]` is filled with `(A[i][k] + A[k][j]) if (A[i][j] > A[i][k] + A[k][j])`

      2. i.e if the direct distance from the source to the destination is greater than the path through the vertex K, then the cell is filled with `A[i][k] + A[k][j]`

      3. K is vertex 1. we calculate the distance from source vertex to destination vertex through this vertex K

         ![matrix floyd warshall algorithm](https://cdn.programiz.com/sites/tutorial2program/files/fw-Matrix-2.png)

   3. similarly, A2 is created using A1. the elements in the second column and the second row are left as they are. In this step, K is the second vertex i.e. vertex 2. the remaining steps are the same as step 2.

   4. similarly, A3 and A4 is also created

   5. A4 gives the shortest path between each pair of vertices

   ```java
   class FloydWarshall {
     final static int INF = 9999, nV = 4;
   
     // Implementing floyd warshall algorithm
     void floydWarshall(int graph[][]) {
       int matrix[][] = new int[nV][nV];
       int i, j, k;
   
       for (i = 0; i < nV; i++)
         for (j = 0; j < nV; j++)
           matrix[i][j] = graph[i][j];
   
       // Adding vertices individually
       for (k = 0; k < nV; k++) {
         for (i = 0; i < nV; i++) {
           for (j = 0; j < nV; j++) {
             if (matrix[i][k] + matrix[k][j] < matrix[i][j])
               matrix[i][j] = matrix[i][k] + matrix[k][j];
           }
         }
       }
       printMatrix(matrix);
     }
   
     void printMatrix(int matrix[][]) {
       for (int i = 0; i < nV; ++i) {
         for (int j = 0; j < nV; ++j) {
           if (matrix[i][j] == INF)
             System.out.print("INF ");
           else
             System.out.print(matrix[i][j] + "  ");
         }
         System.out.println();
       }
     }
   
     public static void main(String[] args) {
       int graph[][] = { { 0, 3, INF, 5 }, { 2, 0, INF, 4 }, { INF, 1, 0, INF }, { INF, INF, 2, 0 } };
       FloydWarshall a = new FloydWarshall();
       a.floydWarshall(graph);
     }
   }
   ```

   