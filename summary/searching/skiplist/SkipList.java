package skiplist;

import utils.ArrayUtils;

import java.util.Comparator;
import java.util.Random;

public class SkipList<T> {
    private final int maxLevel;
    private final Node<T> head;
    private final Random random;
    private final Comparator<T> comparator;

    private static class Node<T> {
        T key;
        Node<T>[] forward;

        Node(T key, int level) {
            this.key = key;
            forward = new Node[level + 1];
        }
    }

    public SkipList(int maxLevel, Comparator<T> comparator) {
        this.maxLevel = maxLevel;
        this.comparator = comparator;

        head = new Node<>(null, maxLevel);
        random = new Random();
    }

    public void print() {
        System.out.println("SkipList:");
        for (int i = maxLevel; i >= 0; i--) {
            System.out.print("Level " + i + ": ");
            Node<T> current = head.forward[i];
            while (current != null) {
                System.out.print(current.key + " ");
                current = current.forward[i];
            }
            System.out.println();
        }
    }

    public void insert(T key) {
        Node<T>[] update = new Node[maxLevel + 1];
        Node<T> current = head;

        for (int i = maxLevel; i >= 0; i--) {
            while (current.forward[i] != null && comparator.compare(current.forward[i].key, key) < 0) {
                current = current.forward[i];
            }
            update[i] = current;
        }

        current = current.forward[0];
        if (current == null || !current.key.equals(key)) {
            int level = randomLevel();
            var newNode = new Node<>(key, level);
            for (int i = 0; i <= level; i++) {
                newNode.forward[i] = update[i].forward[i];
                update[i].forward[i] = newNode;
            }
        }
    }

    public void delete(T key) {
        Node<T>[] update = new Node[maxLevel + 1];
        Node<T> current = head;

        for (int i = maxLevel; i >= 0; i--) {
            while (current.forward[i] != null && comparator.compare(current.forward[i].key, key) < 0) {
                current = current.forward[i];
            }
            update[i] = current;
        }

        current = current.forward[0];
        if (current != null && current.key.equals(key)) {
            for (int i = 0; i <= maxLevel; i++) {
                if (update[i].forward[i] != current) break;
                update[i].forward[i] = current.forward[i];
            }
        }
    }

    public boolean search(T key) {
        Node<T> current = head;

        for (int i = maxLevel; i >= 0; i--) {
            while (current.forward[i] != null && comparator.compare(current.forward[i].key, key) < 0) {
                current = current.forward[i];
            }
        }

        current = current.forward[0];
        return current != null && current.key.equals(key);
    }

    private int randomLevel() {
        int level = 0;
        while (random.nextInt(2) == 0 && level < maxLevel) {
            level++;
        }
        return level;
    }

    public static void main(String[] args) {
        var random = new Random();
        var skipList = new SkipList<>(8, Integer::compareTo);
        var keys = new Integer[20000];

        for (int i = 0; i < keys.length; i++) {
            keys[i] = random.nextInt(10000);
        }

        // test insertion
        for (Integer key : keys) {
            skipList.insert(key);
        }

        System.out.println("After Insertions:");
        skipList.print();

        // test deletion
        ArrayUtils.shuffle(keys);

        for (int i = 1; i < keys.length / 3; i++) {
            skipList.delete(keys[i]);
        }

        System.out.println("After Deletions:");
        skipList.print();
    }
}
