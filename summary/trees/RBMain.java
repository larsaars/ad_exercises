import bst.BST;
import rbtree.RBTree;
import utils.TreeDrawer;

import java.util.Random;

public class RBMain {
    public static final Random random = new Random();

    public static void main(String[] args) {
        var rb = new RBTree<>(Integer::compareTo);

        for (int i = 0; i < 25; i++) {
            rb.insert(random.nextInt(100));
        }


        TreeDrawer.draw(rb.root);
    }
}