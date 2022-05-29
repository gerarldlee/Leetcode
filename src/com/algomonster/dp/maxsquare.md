# Maximal Square

Given a binary matrix, find out the largest size square sub-matrix with all 1's and return its area.

### Input

- `matrix`: a binary matrix

### Output

the area of the largest square in the input matrix

### Examples

#### Example 1:

**Input**:

```
1matrix = 
2[[1, 0, 1, 0, 0],
3 [1, 0, 1, 1, 1],
4 [1, 1, 1, 1, 0],
5 [1, 0, 0, 1, 0]]
6
```

**Output**: `4`

**Explanation**:

The largest square is of size 2x2 and area 4.

## Try it

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Solution {
    public static int maximalSquare(List<List<Integer>> matrix) {
        // WRITE YOUR BRILLIANT CODE HERE
        return 0;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int matrixLength = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> matrix = new ArrayList<>();
        for (int i = 0; i < matrixLength; i++) {
            matrix.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        scanner.close();
        int res = maximalSquare(matrix);
        System.out.println(res);
    }
}
```

