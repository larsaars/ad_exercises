package bst;

import utils.PrintableNode;

public class Node<T> implements PrintableNode {
    public T value;
    public Node<T> left, right;
    public int height = 0;

    public Node(T value) {
        this.value = value;
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
        return value.toString();
    }
}
