import java.util.Arrays;

public class TextSearch {

    public static int naiveSearch(char[] text, int n, char[] pattern, int m) {
        int count = 0;
        for (int i = 0; i <= n - m; i++) {
            boolean match = true;
            for (int j = 0; j < m; j++) {
                if (text[i + j] != pattern[j]) {
                    match = false;
                    break;
                }
            }
            if (match) count++;
        }
        return count;
    }

    public static int getIndex(char a) {
        if (a == ' ') return 26;
        if (a == ',') return 27;
        return ((int) a - 65);
    }

    public static int boyerMooreSearch(char[] text, int n, char[] pattern, int m) {
        int sizeOfAlphabet = 28;
        int[] shift = new int[sizeOfAlphabet];
        Arrays.fill(shift, m);
        for (int i = 0; i < m; i++) shift[getIndex(pattern[i])] = m - i - 1;

        int[] goodSuffix = preprocessGoodSuffix(pattern, m);

        int count = 0;
        int j = m - 1;
        int i = m - 1;
        while (i < n) {
            if (text[i] == pattern[j]) {
                if (j == 0) {
                    count++;
                    i += m;
                    j = m - 1;
                } else {
                    i--;
                    j--;
                }
            } else {
                int shiftByBadChar = shift[getIndex(text[i])];
                int shiftByGoodSuffix = goodSuffix[j];
                i += Math.max(shiftByBadChar, shiftByGoodSuffix);
                i += shiftByGoodSuffix;
                j = m - 1;
            }
        }
        return count;
    }

    private static int[] preprocessGoodSuffix(char[] pattern, int m) {
        int[] goodSuffix = new int[m];
        int[] borderPos = new int[m + 1];
        int i = m, j = m + 1;
        borderPos[i] = j;

        while (i > 0) {
            while (j <= m && pattern[i - 1] != pattern[j - 1]) {
                if (goodSuffix[j - 1] == 0) goodSuffix[j - 1] = j - i;
                j = borderPos[j];
            }
            i--;
            j--;
            borderPos[i] = j;
        }

        j = borderPos[0];
        for (i = 0; i < m; i++) {
            if (goodSuffix[i] == 0) goodSuffix[i] = j;
            if (i == j) j = borderPos[j];
        }

        return goodSuffix;
    }

    public static void main(String[] args) {
        char[] text = "ALGORITHMEN UND DATENSTRUKTUREN".toCharArray();
        char[] pattern = "DATEN".toCharArray();
        int n = text.length;
        int m = pattern.length;

        int naiveCount = naiveSearch(text, n, pattern, m);
        int bmCount = boyerMooreSearch(text, n, pattern, m);

        System.out.println("Naive Search Count: " + naiveCount);
        System.out.println("Boyer-Moore Search Count: " + bmCount);
    }
}