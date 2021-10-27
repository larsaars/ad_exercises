
public class Main {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, -1, 7, 3, 2, 9},
                {-7, 6, -5, -13, 12, 12, 1},
                {6, 7, 1, -5, 1, 1, 0},
                {0, -13, 10, 11, 18, 6},
                {1, 2, -3, -4, 5, -6, 7}
        };

        System.out.println(maxPartSum(matrix));
        System.out.println(maxPartSum(matrix[0]));
    }

    private static int maxPartSum(int[][] matrix) {
        // accessing a 2d array:
        // matrix[columnIndex][rowIndex]

        int columnLen = matrix.length, rowLen = matrix[0].length;

        int max = Integer.MIN_VALUE;

        for (int l = 0; l < rowLen; l++) {
            // the row index limiter l
            // calculating first max part sum from row
            // 1
            // then max from (1 + 2) etc.

            // the variable saving max value from the limited approach sums
            int limitedMax = Integer.MIN_VALUE;

            // in this loop go from zero up to the limiter
            for (int i = 0; i <= l; i++) { // row looper!

                // loop through the columns (through all, since we cannot conclude that a global max is also still max when
                // dependent to two-dimensionality)
                // build 2 dim array v for making problem 1dim
                int[] v = new int[columnLen];
                for (int j = 0; j < columnLen; j++)  // column looper
                    v[i] += matrix[j][i];

                // get the greatest partial sum of v
                limitedMax = Math.max(maxPartSum(v), limitedMax);
            }

            max = Math.max(limitedMax, max);
        }
        return max;
    }

    private static int maxPartSum(int[] a) {
        // {1,2,-1,7,3,2,9}

        int max = Integer.MIN_VALUE, curSum = 0;

        for (int j : a) {
            curSum = Math.max(curSum + j, j);
            if (curSum > max) max = curSum;
        }

        return max;
    }
}
