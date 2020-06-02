package bsthash;

import java.util.Stack;

/**
 * Main class to test the program
 *
 * @author Agata Porwit
 * used online source for the iterator leetcode
 */

public class BSTIterator {
    Stack<BST.Node> stack;

    public BSTIterator(BST.Node root) {

        this.stack = new Stack<>();
        this.leftmostInorder(root);
    }

    /**
     * Moves to the next smallest number in the BST
     *
     * @return the next smallest number
     */
    public int next() {
        //Node at the top of the stack has next smallest element
        BST.Node topmostNode = this.stack.pop();
        //If node has a right child, calling function on the right side
        if (topmostNode.right != null) {
            this.leftmostInorder(topmostNode.right);
        }
        return topmostNode.value;
    }

    /**
     * checking  item on the stack
     *
     * @return what is left on the stack
     */
    public boolean hasNext() {
        return this.stack.size() > 0;
    }

    /**
     * find the left most child node
     */
    private void leftmostInorder(BST.Node root) {
        while (root != null) {
            this.stack.push(root);
            root = root.left;
        }
    }
}

