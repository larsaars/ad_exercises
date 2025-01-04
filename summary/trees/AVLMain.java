import avltree.AVLTree;
import utils.TreeDrawer;

import java.util.Random;

public class AVLMain {

    private static final Random random = new Random();

    public static void main(String[] args) {
        var avl = new AVLTree<>(Integer::compareTo);

        for (int i = 0; i < 35; i++) {
            avl.insert(random.nextInt(100));
        }

        TreeDrawer.draw(avl.root);
    }
}
