package bst;

public class BSTExtras {
    public static <T> void deleteIteratively(BST<T> bst, T value) {
        Node<T> current = bst.root;
        Node<T> parent = null;
        boolean isLeftChild = false;

        while (current != null) {
            int cmp = bst.comparator.compare(value, current.value);
            if (cmp == 0) {
                break;
            }

            parent = current;
            if (cmp < 0) {
                current = current.left;
                isLeftChild = true;
            } else {
                current = current.right;
                isLeftChild = false;
            }
        }

        if (current == null) {
            return; // Node not found
        }

        // Case 1: Node has no children (leaf node)
        if (current.left == null && current.right == null) {
            if (current == bst.root) {
                bst.root = null;
            } else if (isLeftChild) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }

        // Case 2: Node has one child
        else if (current.left == null) {
            if (current == bst.root) {
                bst.root = current.right;
            } else if (isLeftChild) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        } else if (current.right == null) {
            if (current == bst.root) {
                bst.root = current.left;
            } else if (isLeftChild) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        }

        // Case 3: Node has two children
        else {
            Node<T> successor = bst.findMin(current.right);
            current.value = successor.value;
            deleteIteratively(bst, successor.value);
        }
    }
}
