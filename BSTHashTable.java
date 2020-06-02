package bsthash;

/**
 
 *
 * @author Agata 

 * hashValue = item % hashArray.length
 */

public class BSTHashTable {

    private int numberOfElements;
    private BST[] bucketsForBST;

    /**
     * Constructor
     * Initial size of the array stored in a hash
     */

    BSTHashTable(int size) {
        this.bucketsForBST = new BST[size];
        numberOfElements = 0;
    }

    //modulo my hash ;)
    public int hash(Integer item) {
        return item.hashCode() % bucketsForBST.length;
    }

    public void insert(Integer item) {
        //load factor 1 or more, call rehash
        if (this.loadFactor() >= 1.0) {
            rehash();
        }
        int hashIndex = hash(item);
        //no collision, create new BST at hash index
        if (bucketsForBST[hashIndex] == null) {
            bucketsForBST[hashIndex] = new BST();
        }
        bucketsForBST[hashIndex].BSTInsert(item);
        numberOfElements++;
    }

    public void remove(Integer item) {
        int hashIndex = hash(item);
        BST bucket = bucketsForBST[hashIndex];
        if (bucket == null) {
            return;
        }
        bucket.BSTRemove(item);
        numberOfElements--;
    }

    //Searches for an item in the hash table and if found, return it.

    public Integer search(Integer item) {
        BST bucket = this.bucketsForBST[hash(item)];
        //if bucket is null, item not in bucket
        if (bucket == null) {
            System.out.println("not found");
            return null;
        }
        if (bucket.BSTSearch(item).equals(item)) {
            System.out.println("found");
            return item;

        } else {
            System.out.println("not found");
            return null;
        }
    }

    /**
     * @return String representation of a hash table
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (BST treeBucket : bucketsForBST) {
            if (treeBucket != null) {
                BSTIterator mover = new BSTIterator(treeBucket.root);
                while (mover.hasNext()) {
                    str.append(mover.next()).append(" ");
                }
                str.append("\n");
            }
        }
        return str.toString();

    }

    /**
     * Get the number of elements in a specific tree bucket
     *
     * @param index which bucket to get size of
     * @return the number of elements in the tree bucket
     */
    public int size(int index) {
        return bucketsForBST[index].getSize();
    }

    /**
     * a method that calculates and returns the loading factor to the caller
     * Toms code
     *
     * @return load factor of hash table
     */
    private double loadFactor() {
        return (double) numberOfElements / (double) bucketsForBST.length;
    }

    private void rehash() {
        //double the size of a current array
        int biggerSize = bucketsForBST.length * 2;
        //find next prime
        while (!isPrime(biggerSize)) {
            biggerSize++;
        }
        //new larger hash table
        BSTHashTable larger = new BSTHashTable(biggerSize);
        Integer item;
        //copy data into a stack
        for (BST bucketsForBST : bucketsForBST) {
            if (bucketsForBST != null) {
                BSTIterator mover = new BSTIterator(bucketsForBST.root);
                while (mover.hasNext()) {
                    item = mover.next();

                    int newIndex = item.hashCode() % biggerSize;

                    if (larger.bucketsForBST[newIndex] == null) {
                        larger.bucketsForBST[newIndex] = new BST();
                    }
                    larger.insert(item);
                }
            }
        }
        this.bucketsForBST = larger.bucketsForBST;
        this.numberOfElements = larger.numberOfElements;
    }

    //from Toms code
    private static boolean isPrime(int num) {
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

}
