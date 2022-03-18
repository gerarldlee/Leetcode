Topological Sorting - Kahn's Algorithm
====

- deals with pre-requisite or requirements relationships
  - example: before taking CS101, we need to take MATH101. So MATH101 is a prerequisite to CS101
- topological sorting solves this kind of problems
  - school class prerequisites
  - program dependencies
  - event scheduling
  - assembly instructions

- provides linear sorting based on the required ordering between vertices in a directed acyclic graphs
  - example: given vertex u and v, to reach vertex v, we must reach vertex u first
  - in topological sorting, u has to appear before v in the ordering
- Kahn's algorithm is the most popular topological sorting algorithm

#### How it works?



#### Limitations

- topological sorting only works with graphs that are directed and acyclical
- there must be at least one vertex in the graph, with an in-degree of 0
- if all vertices in the graph have a non-zero in-degree, then all vertices need at least one vertex as a predecessor
  - in this case, no vertex can serve as the starting vertex

#### Complexity

V represents the vertices, E represents the edges

Time: O(V + E)

- We build an adjacent list. This allows us to efficiently check which courses depend on each prerequisite course. Building adjacent list = O(E) time since we iterate over all edges.
- Next, we repeatedly visit each course or vertex with an in-degree of zero and decrement the in-degree of all courses that have this course as a prerequisite (out-degree). In the worse case, we will visit every vertex and decrement every outgoing edge once. O(V + E) time
- Total complexity: O(E) + O(V + E) = O(V + E)

Space: O(V + E)

- Adjacent list uses O(E) space
- Storing the in-degree for each vertex requires O(V) space
- The queue can contain at most V nodes, so the queue also requires O(V) space



## Course Schedule 2 - Topological Sort using Khan's Algorithm

#### How it works?

- 



```java
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] result = new int[numCourses];
        if (numCourses == 0) {
            return result;
        }
        
        if (prerequisites == null || prerequisites.length == 0) {
            for (int i = 0; i < numCourses; i++) {
                result[i] = i;
            }
            return result;
        }
        
        int[] indegree = new int[numCourses];
        Queue<Integer> zeroDegree = new LinkedList<>();
        for (int[] pre : prerequisites) {
            indegree[pre[0]]++;
        }
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0){
                zeroDegree.add(i);
            }
        }
        if (zeroDegree.isEmpty()) {
            return new int[0];
        }
        int index = 0;
        while (!zeroDegree.isEmpty()) {
            int course = zeroDegree.poll();
            result[index] = course;
            index++;
            for (int[] pre : prerequisites) {
                if (pre[1] == course) {
                    indegree[pre[0]]--;
                    if (indegree[pre[0]] == 0) {
                        zeroDegree.add(pre[0]);
                    }
                }
            }
        }
        
        for (int in : indegree) {
            if (in != 0) {
                return new int[0];
            }
        }

        return result;
    }
}
```

