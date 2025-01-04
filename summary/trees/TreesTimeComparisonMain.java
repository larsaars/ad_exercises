import avltree.AVLTree;
import bst.BST;
import rbtree.RBTree;

import java.util.Random;

public class TreesTimeComparisonMain {

    private static final Random random = new Random();

    public static void main(String[] args) {

        // compare time taken to insert n random numbers in AVL tree and BST
        int points = 1000000;
        int[] pointsArray = new int[points];

        for (int i = 0; i < points; i++) {
            pointsArray[i] = random.nextInt(points * 10);
        }

        // INSERTION
        // AVL
        long start = System.currentTimeMillis();

        AVLTree<Integer> avlTree = new AVLTree<>(Integer::compareTo);
        for (int i = 0; i < points; i++) {
            avlTree.insert(pointsArray[i]);
        }

        System.out.println("Time taken to insert " + points + " random numbers in AVL tree: " + (System.currentTimeMillis() - start) + "ms");

        // RB
        start = System.currentTimeMillis();

        var rb = new RBTree<>(Integer::compareTo);
        for (int i = 0; i < points; i++) {
            rb.insert(pointsArray[i]);
        }

        System.out.println("Time taken to insert " + points + " random numbers in RB tree: " + (System.currentTimeMillis() - start) + "ms");

        // BST
        start = System.currentTimeMillis();

        BST<Integer> bst = new BST<>(Integer::compareTo);
        for (int i = 0; i < points; i++) {
            bst.insert(pointsArray[i]);
        }

        System.out.println("Time taken to insert " + points + " random numbers in BST: " + (System.currentTimeMillis() - start) + "ms");

        // DELETION
        // AVL
        start = System.currentTimeMillis();

        for (int i = 0; i < points; i++) {
            avlTree.delete(pointsArray[i]);
        }

        System.out.println("Time taken to delete " + points + " random numbers in AVL tree: " + (System.currentTimeMillis() - start) + "ms");

        // RB
        start = System.currentTimeMillis();

        for (int i = 0; i < points; i++) {
            rb.delete(pointsArray[i]);
        }

        System.out.println("Time taken to delete " + points + " random numbers in RB tree: " + (System.currentTimeMillis() - start) + "ms");

        // BST
        start = System.currentTimeMillis();

        for (int i = 0; i < points; i++) {
            bst.delete(pointsArray[i]);
        }

        System.out.println("Time taken to delete " + points + " random numbers in BST: " + (System.currentTimeMillis() - start) + "ms");
    }
}
