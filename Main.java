package bsthash;

/**
 * Main class to test the program
 *
 * @author Agata Porwit
 */
public class Main {

    public static void main(String[] args) {
        BSTHashTable hashHash = new BSTHashTable(10);

        hashHash.insert(9);
        hashHash.insert(8);
        hashHash.insert(2);
        hashHash.insert(3);
        hashHash.insert(1);
        hashHash.insert(7);
        hashHash.insert(6);
        hashHash.insert(5);
        hashHash.insert(4);
        hashHash.insert(10);
        hashHash.insert(15);
        hashHash.insert(19);
        hashHash.insert(19);

        System.out.println(hashHash.toString());

        System.out.println("Searching for elements: ");

        hashHash.search(4);
        hashHash.search(12);

        System.out.println();

        hashHash.remove(6);
        hashHash.remove(19);
        System.out.println(hashHash);

    }

}
