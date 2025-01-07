package util;

public class TreePrinter {
    public static void printPreOrder(PrintableNode node) {
        if (node == null) {
            return;
        }

        System.out.println(node.getText());
        printPreOrder(node.getLeft());
        printPreOrder(node.getRight());
    }

    public static void printInOrder(PrintableNode node) {
        if (node == null) {
            return;
        }

        printInOrder(node.getLeft());
        System.out.println(node.getText());
        printInOrder(node.getRight());
    }

    public static void printPostOrder(PrintableNode node) {
        if (node == null) {
            return;
        }

        printPostOrder(node.getLeft());
        printPostOrder(node.getRight());
        System.out.println(node.getText());
    }
}
