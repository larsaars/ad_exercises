package bst;

import java.util.Comparator;

public class BST<T> {
    public Node<T> root;
    public Comparator<T> comparator;

    public BST(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    private void insert(Node<T> currentRoot, Node<T> newNode) {
        if (comparator.compare(newNode.value, currentRoot.value) <= 0) {
            if (currentRoot.left == null) {
                currentRoot.left = newNode;
            } else {
                insert(currentRoot.left, newNode);
            }
        } else {
            if (currentRoot.right == null) {
                currentRoot.right = newNode;
            } else {
                insert(currentRoot.right, newNode);
            }
        }
    }

    public void insert(T o) {
        var newNode = new Node<T>(o);
        if (root == null) {
            root = newNode;
        } else {
            insert(root, newNode);
        }
    }

    public Node<T> find(T o) {
        Node<T> current = root;

        while (current != null) {
            if (current.value.equals(o)) {
                return current;
            } else {
                if (comparator.compare(o, current.value) <= 0) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }
        }

        return null;
    }

    public boolean contains(T o) {
        return find(o) != null;
    }

    // get height of subtree
    public int getHeight(Node<T> node) {
        if (node == null) {
            return -1;
        }

        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    // get height from root node off
    public int getHeight() {
        return getHeight(root);
    }


    public Node<T> findMin(Node<T> node) {
        while (node.left != null) {
            node = node.left; // Go to the leftmost node
        }
        return node;
    }

    public void delete(T o) {
        root = delete(root, o);
    }

    private Node<T> delete(Node<T> node, T value) {
        if (node == null) {
            return null; // Node not found
        }

        // Step 1: Find the node to delete recursively
        int cmp = comparator.compare(value, node.value);
        if (cmp < 0) {
            node.left = delete(node.left, value); // Go left
        } else if (cmp > 0) {
            node.right = delete(node.right, value); // Go right
        } else {
            // Step 2: Node to be deleted is found

            // Case 1: Node has no children (leaf node)
            if (node.left == null && node.right == null) {
                return null;
            }

            // Case 2: Node has one child
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            // Case 3: Node has two children
            // Find the in-order successor (smallest node in the right subtree)
            Node<T> successor = findMin(node.right);

            // Replace node's value with successor's value
            node.value = successor.value;

            // Delete the successor
            node.right = delete(node.right, successor.value);
        }

        return node;
    }
}