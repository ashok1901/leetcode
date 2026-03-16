package medium;

import java.util.Arrays;

public class SetMatrixZeroes73 {
    /**
     * Constraint : Constant space
     * Approach:
     *  - Scan from top left corner to bottom right corner.
     *      - If 0 found the zero out that column for that row and zero out that row before that column
     *  - Second scan from bottom right corner to upper left corner
     *      - If 0 and 0 right below this or at it;s left then ignore it. It means this is zero came from upper scan
     *      - Otherwise zero out the row after this column and zero out the col after this row
     * @param matrix
     *
     * NOT WORKING CORRECTLY
     */
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        // Scan - 1
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (matrix[r][c] == 0) {
                    zeroOutColumnBeforeRow(r, c, matrix);
                    zeroOutRowBeforeCol(r, c, matrix);
                }
            }
        }

        // Scan - 2
        for (int r = m - 1; r >= 0; r--) {
            for (int c = n - 1; c >= 0;     c--) {
                if (matrix[r][c] == 0) {
                    if (!(r+1 < m && matrix[r + 1][c] == 0)) {
                        zeroOutColumnAfterRow(r, c, matrix);
                    }
                    if (!(c+1 < n && matrix[r][c+1] == 0)) {
                        zeroOutRowAfterCol(r, c, matrix);
                    }
//                    zeroOutColumnAfterRow(r, c, matrix);
//                    zeroOutRowAfterCol(r, c, matrix);
                }
            }
        }
    }

    private void zeroOutColumnBeforeRow(int r, int c, int[][] matrix) {
        while (r >= 0) {
            matrix[r][c] = 0;
            r--;
        }
    }

    private void zeroOutRowBeforeCol(int r, int c, int[][] matrix) {
        while (c >= 0) {
            matrix[r][c] = 0;
            c--;
        }
    }

    private void zeroOutColumnAfterRow(int r, int c, int[][] matrix) {
        while (r < matrix.length) {
            matrix[r][c] = 0;
            r++;
        }
    }

    private void zeroOutRowAfterCol(int r, int c, int[][] matrix) {
        while (c < matrix[0].length) {
            matrix[r][c] = 0;
            c++;
        }
    }

    public static void main(String[] args) {
        SetMatrixZeroes73 setMatrixZeroes73 = new SetMatrixZeroes73();
        int[][] matrix = {{1,1,1},{1,0,1},{1,1,1}};
        setMatrixZeroes73.setZeroes(matrix);
        System.out.println(Arrays.deepToString(matrix));
        int[][] matrix1 = {{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        setMatrixZeroes73.setZeroes(matrix1);
        System.out.println(Arrays.deepToString(matrix1));
    }
}


