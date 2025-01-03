import utils.TreePrinter;

public class Node<T> implements TreePrinter.PrintableNode {
    public T value;
    public Node<T> left, right;

    public Node(T value) {
        this.value = value;
    }

    @Override
    public TreePrinter.PrintableNode getLeft() {
        return left;
    }

    @Override
    public TreePrinter.PrintableNode getRight() {
        return right;
    }

    @Override
    public String getText() {
        return value.toString();
    }
}
