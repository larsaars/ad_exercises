package trees;

import trees.bst.BST;
import utils.TreeDrawer;

import java.util.Random;

public class BSTMain {
    public static final Random random = new Random();

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>(Integer::compareTo);

        for (int i = 0; i < 25; i++) {
            bst.insert(random.nextInt(100));
        }


        TreeDrawer.draw(bst.root);
    }
}