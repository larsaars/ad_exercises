import utils.TreePrinter;

public class BSTMain {
    public static void main(String[] args) {
        BST<Integer> bst = new BST<>(Integer::compareTo);

        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);
        bst.insert(22);
        bst.insert(19);
        bst.insert(21);

        TreePrinter.print(bst.root);

        bst.deleteValue(20);

        TreePrinter.print(bst.root);
    }
}