import java.util.Arrays;

public class HashTable {
    private final int[] table;
    private final int m;

    public HashTable(int size) {
        m = size;
        table = new int[m];
        Arrays.fill(table, -1); // -1 indicates an empty slot
    }

    // Insert using linear probing
    public void insertLinear(int key) {
        int hash = key % m;
        int index = hash;

        while (table[index] != -1) { // Find the next available slot
            index = (index + 1) % m;
            if (index == hash) throw new RuntimeException("HashTable is full");
        }

        table[index] = key;
    }

    // Insert using quadratic probing
    public void insertQuadratic(int key) {
        int hash = key % m;
        int index = hash;
        int i = 1;

        while (table[index] != -1) { // Find the next available slot
            index = (hash + i + 3 * i * i) % m; // c1 = 1, c2 = 3
            i++;
            if (i >= m) throw new RuntimeException("HashTable is full");
        }

        table[index] = key;
    }

    // Insert using double hashing
    public void insertDoubleHashing(int key) {
        int hash1 = key % m;
        int hash2 = 1 + (key % (m - 1));
        int index = hash1;

        int i = 0;
        while (table[index] != -1) { // Find the next available slot
            index = (hash1 + i * hash2) % m;
            i++;
            if (i >= m) throw new RuntimeException("HashTable is full");
        }

        table[index] = key;
    }
}
