package bsthash;

import java.util.Objects;

/**
 * Binary Search Tree
 *
 * @author Agata
 */


public class BST {

    /**
     * The Node class stores an item and a reference for two child nodes.
     */

    public class Node {
        Integer value;
        Node left;
        Node right;

        /**
         * Constructor.
         *
         * @param valueX The element to store in the node.
         * @param left   The reference to the successor node.
         */
        Node(Integer valueX, Node left, Node right) {
            value = valueX;
            this.left = left;
            this.right = right;
        }

        /**
         * Constructor.
         *
         * @param val The element to store in the node.
         */
        Node(Integer valueX) {
            // Call the other (sister) constructor.
            this(valueX, null, null);
        }

    }

    //BST node
    Node root;   //root node of tree
    private int size;    //number of elements in BST

    /**
     * Constructor
     */

    public BST() {
        root = null;
        this.size = 0;
    }

    public int getSize() {
        return this.size;
    }


    public Integer BSTSearch(Integer blocks) {
        Node current = this.root;
        while (current != null) {

            if (Objects.equals(blocks, current.value)) {
                return current.value; //found
            } else if (blocks < current.value) {
                //traverse left side
                current = current.left;
            } else {
                //traverse right side
                current = current.right;
            }
        }

        return null;
    }

    /**
     * Insert a node into BST
     *
     * @param blocks generic value to insert into BST
     */
    public void BSTInsert(Integer blocks) {
        //create new node to insert
        Node item = new Node(blocks);
        if (this.root == null) {
            this.root = item;
            this.size++;
        } else {
            //reference to root tree
            Node current = this.root;
            while (current != null) {
                //if item is less than current value, set to left of current
                if (item.value < current.value) {
                    if (current.left == null) {
                        current.left = item;
                        this.size++;
                        current = null;
                    } else {
                        current = current.left;
                    }
                } else {
                    if (current.right == null) {
                        current.right = item;
                        this.size++;
                        current = null;
                    } else {
                        current = current.right;
                    }
                }
            }
        }

    }

    /**
     * @param blocks
     */
    public void BSTRemove(Integer blocks) {
        Node parent = null;
        Node current = this.root;
        // Search for node
        while (current != null) {
            // Node found
            if (Objects.equals(current.value, blocks)) {
                // Remove leaf
                if (current.left == null && current.right == null) {
                    if (parent == null) {
                        Node found = this.root;
                        this.root = null;
                        return;
                    } else if (parent.left == current)
                        parent.left = null;
                    else
                        parent.right = null;
                    // Remove node with only left child
                } else if (current.left != null && current.right == null) {
                    if (parent == null)
                        this.root = current.left;
                    else if (parent.left == current)
                        parent.left = current.left;
                    else
                        parent.right = current.left;
                    // Remove node with only right child
                } else if (current.left == null && current.right != null) {
                    if (parent == null)  // Node is root
                        this.root = current.right;
                    else if (parent.left == current)
                        parent.left = current.right;
                    else
                        parent.right = current.right;
                    // Remove node with two children
                } else {
                    // Find successor (leftmost child of right subtree)
                    Node successor = current.right;
                    while (successor.left != null) {
                        successor = successor.left;
                    }
                    Node successorSearch = new Node(successor.value, null, successor.right);
                    // Remove successor
                    BSTRemove(successor.value);
                    current.value = successorSearch.value;
                }
                return;// Node found and removed
                // Search right
            } else if (current.value < blocks) {
                parent = current;
                current = current.right;
                // Search left
            } else {
                parent = current;
                current = current.left;
            }
        }

    }

    public void PrintTreeInOrder() {
        Node current = this.root;
        PrintTreeInOrder();
        if (current == null) {
            System.out.println();
            return;
        }
        Node left = current.left;
        Node right = current.right;

        PrintTreeInOrder();
        System.out.print(current.value);
        PrintTreeInOrder();
    }
}
