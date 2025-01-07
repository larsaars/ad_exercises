package trees.rbtree;

import java.util.Comparator;

public class RBTree<T> {
    public RBNode<T> root;
    private final Comparator<T> comparator;

    public RBTree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public void insert(T value) {
        root = insert(root, value);
        root.isRed = false; // Root must always be black
    }

    private RBNode<T> insert(RBNode<T> node, T value) {
        if (node == null) {
            return new RBNode<>(value); // New node is red by default
        }

        int cmp = comparator.compare(value, node.value);
        if (cmp < 0) {
            node.left = insert(node.left, value);
        } else if (cmp > 0) {
            node.right = insert(node.right, value);
        } else {
            return node; // Duplicates are not allowed
        }

        // Fix violations
        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        return node;
    }

    public void delete(T value) {
        if (root == null || !contains(value)) {
            return;
        }

        if (!isRed(root.left) && !isRed(root.right)) {
            root.isRed = true; // Temporarily make the root red
        }

        root = delete(root, value);
        if (root != null) {
            root.isRed = false; // Root must always be black
        }
    }

    private RBNode<T> delete(RBNode<T> node, T value) {
        int cmp = comparator.compare(value, node.value);

        if (cmp < 0) {
            if (!isRed(node.left) && !isRed(node.left.left)) {
                node = moveRedLeft(node);
            }
            node.left = delete(node.left, value);
        } else {
            if (isRed(node.left)) {
                node = rotateRight(node);
            }
            if (cmp == 0 && node.right == null) {
                return null; // Deleting a leaf node
            }
            if (!isRed(node.right) && !isRed(node.right.left)) {
                node = moveRedRight(node);
            }
            if (cmp == 0) {
                RBNode<T> minNode = findMin(node.right);
                node.value = minNode.value;
                node.right = deleteMin(node.right);
            } else {
                node.right = delete(node.right, value);
            }
        }

        return balance(node);
    }

    private RBNode<T> deleteMin(RBNode<T> node) {
        if (node.left == null) {
            return null;
        }

        if (!isRed(node.left) && !isRed(node.left.left)) {
            node = moveRedLeft(node);
        }

        node.left = deleteMin(node.left);
        return balance(node);
    }

    private RBNode<T> findMin(RBNode<T> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private boolean contains(T value) {
        return find(value) != null;
    }

    private RBNode<T> find(T value) {
        RBNode<T> current = root;

        while (current != null) {
            int cmp = comparator.compare(value, current.value);
            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                return current;
            }
        }

        return null;
    }

    // Utility methods
    private boolean isRed(RBNode<T> node) {
        return node != null && node.isRed;
    }

    private RBNode<T> rotateLeft(RBNode<T> node) {
        RBNode<T> newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;

        newRoot.isRed = node.isRed;
        node.isRed = true;

        return newRoot;
    }

    private RBNode<T> rotateRight(RBNode<T> node) {
        RBNode<T> newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;

        newRoot.isRed = node.isRed;
        node.isRed = true;

        return newRoot;
    }

    private void flipColors(RBNode<T> node) {
        node.isRed = !node.isRed;
        if (node.left != null) {
            node.left.isRed = !node.left.isRed;
        }
        if (node.right != null) {
            node.right.isRed = !node.right.isRed;
        }
    }

    private RBNode<T> moveRedLeft(RBNode<T> node) {
        flipColors(node);

        if (node.right != null && isRed(node.right.left)) {
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
            flipColors(node);
        }

        return node;
    }


    private RBNode<T> moveRedRight(RBNode<T> node) {
        flipColors(node);

        if (node.left != null && isRed(node.left.left)) {
            node = rotateRight(node);
            flipColors(node);
        }

        return node;
    }

    private RBNode<T> balance(RBNode<T> node) {
        if (isRed(node.right)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        return node;
    }
}
