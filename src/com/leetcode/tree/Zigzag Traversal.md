Level Order Traversals
====

## Zigzag Level Order Traversal

We can use a queue just like we used in Level Order Traversal. But in this case, we can also maintain a flag variable which keeps track of alternate level to reverse the order of the corresponding level traversal.flag==true implies we have to insert from left to right and flag==false means we have to insert element from right to left our answer arraylist.

- Time Complexity: O(N)
- Space: O(N) for queue data structure

```java
// Java implementation of a O(n) time method for
// Zigzag order traversal
import java.util.*;
public class Main {
    // Class containing left and
    // right child of current
    // node and key value
    static class Node {
 
        public int data;
        public Node left, right;
 
        public Node(int data)
        {
            this.data = data;
            left = right = null;
        }
    }
 
    // A utility function to create a new node
    static Node newNode(int data)
    {
        Node node = new Node(data);
        return node;
    }
 
    // Function to print the zigzag traversal
    static ArrayList<Integer> zigZagTraversal(Node root)
    {
 
        ArrayList<Integer> ans = new ArrayList<Integer>();
        // if there is no element in the tree,return empty
        // arraylist
        if (root == null)
            return ans;
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);
        // this variable helps to check if elements are to
        // be added from left to right or right to left
        boolean leftToRight = true;
        while (q.size() > 0) {
            int size = q.size();
            // this arraylist is used to store element at
            // current level
            ArrayList<Integer> temp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node curr = q.poll();
                if (curr.left != null)
                    q.add(curr.left);
                if (curr.right != null)
                    q.add(curr.right);
                temp.add(curr.data);
            }
            if (leftToRight) // at current level,add element
                             // fom left to right to our
                             // answer
            {
                // do nothing
            }
            // we have to add element from to right to left
            // and this can be done by reversing our temp
            // arraylist
            else {
                Collections.reverse(temp);
            }
            // add element form temp arraylist to our ans
            // arraylist
            for (int i = 0; i < temp.size(); i++) {
                ans.add(temp.get(i));
            }
            // change the value of leftToRight from true to
            // false or false to true for next iteration.
            leftToRight = !(leftToRight);
        }
        // return our ans arraylist
        return ans;
    }
 
    public static void main(String[] args)
    {
 
        // Arraylist to store the traversal order.
        ArrayList<Integer> ans;
 
        // create tree
        Node root = newNode(1);
        root.left = newNode(2);
        root.right = newNode(3);
        root.left.left = newNode(7);
        root.left.right = newNode(6);
        root.right.left = newNode(5);
        root.right.right = newNode(4);
        System.out.println(
            "ZigZag Order traversal of binary tree is");
 
        ans = zigZagTraversal(root);
 
        for (int i = 0; i < ans.size();
             i++) { // to print the order
            System.out.print(ans.get(i) + " ");
        }
    }
}
```



## Spiral Level Order Traversal

- Iterative, using Double Ended Queue

The idea is to use a deque. While travelling left to right we can poll and print the elements from the front and insert their children(**left child first followed by the right child**) at the back. While travelling right to left we can poll and print the elements from the back and insert their children(**right child first followed by the left child**) at the front of the deque.

- Time: O(N)
- Space: O(N)

```java

class GFG {
   
  //Defining Node class
  static class Node {
 
    int key;
    Node left;
    Node right;
 
    public Node(int key) {
        this.key = key;
    }
}
   
  //Class to construct the tree
  static class MyTree {
 
    public MyTree(){};
 
    public Node root;
 
}
   
  //Function that prints the tree in spiral fashion
  public static void spiralPrint(Node root){
 
        //Declare a deque
        Deque<Node> dq = new ArrayDeque<>();
         
        //Insert the root of the tree into the deque
        dq.offer(root);
         
        //Create a  variable that will switch in each iteration
        boolean reverse = true;
 
        //Start iteration
        while (!dq.isEmpty()){
             
              //Save the size of the deque here itself, as in further steps the size
              //of deque will frequently change
            int n = dq.size();
             
              //If we are printing left to right
            if(!reverse){
               
              //Iterate from left to right
                for (int i =0; i < n; i++){
                         
                  //Insert the child from the back of the deque
                  //Left child first
                    if (dq.peekFirst().left  != null)
                        dq.offerLast(dq.peekFirst().left);
                   
                    if (dq.peekFirst().right != null)
                        dq.offerLast(dq.peekFirst().right);
                   
                  //Print the current processed element
                    System.out.print(dq.pollFirst().key + "  ");
                   
                   
                }
                //Switch reverse for next traversal
                reverse = !reverse;
               
            }else{
 
              //If we are printing right to left
              //Iterate the deque in reverse order and insert the children
              //from the front
                while (n-- >0){
                    //Insert the child in the front of the deque
                    //Right child first
                    if (dq.peekLast().right != null)
                        dq.offerFirst(dq.peekLast().right);
                   
                    if (dq.peekLast().left != null)
                        dq.offerFirst(dq.peekLast().left);
 
                  //Print the current processed element
                    System.out.print(dq.pollLast().key + "  ");
 
                }
                //Switch reverse for next traversal
                reverse = !reverse;
                 
            }
        }
    }
   
   
    public static void main (String[] args) {
        MyTree mt = new MyTree();
        mt.root = new Node(1);
        mt.root.left = new Node(2);
        mt.root.right = new Node(3);
        mt.root.left.left = new Node(7);
        mt.root.left.right = new Node(6);
        mt.root.right.left = new Node(5);
        mt.root.right.right = new Node(4);
         
 
      System.out.println("Spiral Order Traversal Of The Tree Is :");
        spiralPrint(mt.root);
    }
}
```

