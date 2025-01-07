package trees.rbtree;

import utils.PrintableNode;

public class RBNode<T> implements PrintableNode {
    public T value;
    public RBNode<T> left;
    public RBNode<T> right;
    public boolean isRed; // true for red, false for black

    public RBNode(T value) {
        this.value = value;
        this.isRed = true; // New nodes are always red initially
    }

    @Override
    public PrintableNode getLeft() {
        return left;
    }

    @Override
    public PrintableNode getRight() {
        return right;
    }

    @Override
    public String getText() {
        return value.toString() + (isRed ? "R" : "B");
    }
}