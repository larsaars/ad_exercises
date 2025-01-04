import utils.TreeDrawer;
import utils.TreePrinter;

import java.util.Random;
import bst.BST;

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