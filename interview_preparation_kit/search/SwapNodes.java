
/**
 * Name: Swap Nodes
 * 
 * Problem: A binary tree is a tree which is characterized by one of the following properties:
 *   - It can be empty (null).
 *   - It contains a root node only.
 *   - It contains a root node with a left subtree, a right subtree, or both. These subtrees are 
 *      also binary trees.
 *  
 *  In-order traversal is performed as:
 *    1. Traverse the left subtree.
 *    2. Visit root.
 *    3. Traverse the right subtree.
 * 
 *  For this in-order traversal, start from the left child of the root node and keep exploring the 
 *  left subtree until you reach a leaf. When you reach a leaf, back up to its parent, check for a 
 *  right child and visit it if there is one. If there is not a child, you've explored its left and 
 *  right subtrees fully. If there is a right child, traverse its left subtree then its right in 
 *  the same manner. Keep doing this until you have traversed the entire tree. You will only store 
 *  the values of a node as you visit when one of the following is true:
 *   - it is the first node visited, the first time visited
 *   - it is a leaf, should only be visited once
 *   - all of its subtrees have been explored, should only be visited once while this is true
 *   - it is the root of the tree, the first time visited
 *   
 *  Swapping: Swapping subtrees of a node means that if initially node has left subtree L and 
 *      right subtree R, then after swapping, the left subtree will be R and the right subtree, L.
 *      For example, in the following tree, we swap children of node 1.
 *      
 *                                      Depth
 *          1               1            [1]
 *         / \             / \
 *        2   3     ->    3   2          [2]
 *         \   \           \   \
 *          4   5           5   4        [3]
 *      In-order traversal of left tree is 2 4 1 3 5 and of right tree is 3 5 1 2 4.
 * 
 *  Swap operation: We define depth of a node as follows:
 *    - The root node is at depth 1.
 *    - If the depth of the parent node is d, then the depth of current node will be d+1.
 *      
 *      Given a tree and an integer, k, in one operation, we need to swap the subtrees of all the 
 *      nodes at each depth h, where h ∈ [k, 2k, 3k,...]. In other words, if h is a multiple of k, 
 *      swap the left and right subtrees of that level.
 * 
 *      You are given a tree of n nodes where nodes are indexed from [1..n] and it is rooted at 1. 
 *      You have to perform t swap operations on it, and after each swap operation print the 
 *      in-order traversal of the current state of the tree.
 * 
 * Input Format: The first line contains n, number of nodes in the tree.

Each of the next n lines contains two integers, a b, where a is the index of left child, and b is the index of right child of ith node.
Note: -1 is used to represent a null node.

The next line contains an integer, t, the size of . 
Each of the next t lines contains an integer , each being a value .
 * 
 * Constraints: 
 *     1 <= n <= 1024
 *     1 <= t <= 100
 *     1 <= k <= n
 *     Either a = -1 or 2 <= a <= n
 *     Either b = -1 or 2 <= b <= n
 *     The index of a non-null child will always be greater than of its parent
 *     
 * Output Format: For each k, perform the swap operation and store the indices of your in-order 
 *  traversal to your result array. After all swap operations have been performed, return your 
 *  result array for printing.
 * 
 * Sample:
 *     Input:
 *         3
 *         2 3
 *         -1 -1
 *         -1 -1
 *         2
 *         1
 *         1

 *     Output:
 *         3 1 2
 *         2 1 3
 * 
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SwapNodes {
    static class Node {
        int val;
        Node left, right;

        Node(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }

        @Override
        public String toString() {
            return String.valueOf(val);
        }

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        short n = Short.parseShort(in.nextLine());
        ArrayList<ArrayList<Node>> levelsList = new ArrayList<ArrayList<Node>>();

        levelsList.add(new ArrayList<Node>() {
            private static final long serialVersionUID = 1L;
            {
                add(new Node(1));
            }
        });

        int parentsIndex = 0;
        for (short i = 0; i < n;) {
            ArrayList<Node> parents = levelsList.get(parentsIndex++);

            ArrayList<Node> children = new ArrayList<Node>();
            for (int index = 0; index < parents.size(); index++) {
                String line = in.nextLine();

                //The last nodes input line are only nulls (-1)
                if (i++ == n) break;

                String data[] = line.split(" ");

                short a = Short.parseShort(data[0]);
                short b = Short.parseShort(data[1]);
                
                Node left = a == -1 ? null : new Node(a);
                Node right = b == -1 ? null : new Node(b);
                
                parents.get(index).left = left;
                parents.get(index).right = right;
                
                if (left != null) {
                    children.add(left);
                }
                if (right != null) {
                    children.add(right);
                }
                
            }
            if (children.size() > 0) {
                levelsList.add(children);
            }
        }

        byte t = Byte.parseByte(in.nextLine());
        short[] swaps = new short[t];
        for (int i = 0; i < t; i++) {
            swaps[i] = Short.parseShort(in.nextLine());
        }

        printSwaps(levelsList, swaps);
        
        in.close();
    }

    private static void printSwaps(ArrayList<ArrayList<Node>> levelsList, short[] swaps) {
        Node root = levelsList.get(0).get(0);
        int maxDepth = levelsList.size();
        for (int i = 0; i < swaps.length; i++) {
            int mul = 1;
            int curDepth = swaps[i];
            while (curDepth <= maxDepth) {
                for (Node parent : levelsList.get(curDepth - 1)) {
                    Node tmp = parent.left;
                    parent.left = parent.right;
                    parent.right = tmp;
                }
                curDepth = swaps[i] * ++mul;
            }

            String res = inOrderTraversal(root);
            System.out.println(res);
        }
    }

    private static String inOrderTraversal(Node root) {
        StringBuilder sb = inOrderTraversal(root, new StringBuilder());
        return sb.substring(0, sb.length() - 1);
    }

    private static StringBuilder inOrderTraversal(Node root, StringBuilder res) {
        if (root == null) {
            return res;
        }
        res = inOrderTraversal(root.left, res);
        res.append(root.toString());
        res.append(" ");
        res = inOrderTraversal(root.right, res);
        return res;
    }
}