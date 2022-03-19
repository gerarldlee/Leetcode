Reverse Traversal
====



## Reverse Preorder

1. Visit the root
2. Traverse the right subtree
3. Traverse the left subtree

## Reverse Inorder

1. Traverse the right subtree
2. Then visit the root
3. Then traverse the left subtree

## Reverse Postorder

1. Traverse the right subtree
2. Traverse the left subtree
3. Visit the root

## Reverse Level-order

1. Maintain a stack and queue
2. Do regular level order traversal but put right first in the queue
3. Instead of printing, put the result in the stack
4. Finally print the contents of the stack

```java
public class ReverseLevelOrder {
 
    public static void reverseLevelOrder(Node root) {
        if (root == null) {
            System.out.println("Tree is empty");
            return;
        }
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(root);
        Stack <Node> stack = new Stack<Node>();
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            stack.push(node);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        while(!stack.isEmpty()) {
            System.out.printf("%d ",stack.pop().data);
        }
    }
}
```

