package avltree;

import bst.BST;
import bst.Node;

import java.util.Comparator;

public class AVLTree<T> extends BST<T> {
    public AVLTree(Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    public void insert(T o) {
        root = insert(root, o);
    }

    private Node<T> insert(Node<T> node, T value) {
        if (node == null) {
            return new Node<>(value);
        }

        int cmp = comparator.compare(value, node.value);
        if (cmp < 0) {
            node.left = insert(node.left, value);
        } else if (cmp > 0) {
            node.right = insert(node.right, value);
        } else {
            return node; // Duplicate values are not allowed
        }

        updateHeight(node);
        return balance(node);
    }

    @Override
    public void delete(T o) {
        root = delete(root, o);
    }

    private Node<T> delete(Node<T> node, T value) {
        if (node == null) {
            return null;
        }

        int cmp = comparator.compare(value, node.value);
        if (cmp < 0) {
            node.left = delete(node.left, value);
        } else if (cmp > 0) {
            node.right = delete(node.right, value);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            Node<T> successor = findMin(node.right);
            node.value = successor.value;
            node.right = delete(node.right, successor.value);
        }

        updateHeight(node);
        return balance(node);
    }

    private void updateHeight(Node<T> node) {
        node.height = 1 + Math.max(getNodeHeight(node.left), getNodeHeight(node.right));
    }

    private int getNodeHeight(Node<T> node) {
        return (node == null) ? 0 : node.height;
    }

    private Node<T> balance(Node<T> node) {
        int balanceFactor = getBalanceFactor(node);

        // Left heavy
        if (balanceFactor > 1) {
            if (getBalanceFactor(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }

        // Right heavy
        if (balanceFactor < -1) {
            if (getBalanceFactor(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }

        return node;
    }

    private int getBalanceFactor(Node<T> node) {
        return getNodeHeight(node.left) - getNodeHeight(node.right);
    }

    private Node<T> rotateLeft(Node<T> node) {
        Node<T> newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;

        updateHeight(node);
        updateHeight(newRoot);

        return newRoot;
    }

    private Node<T> rotateRight(Node<T> node) {
        Node<T> newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;

        updateHeight(node);
        updateHeight(newRoot);

        return newRoot;
    }
}
