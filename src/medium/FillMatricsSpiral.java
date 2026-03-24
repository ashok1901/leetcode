package medium;

public class FillMatricsSpiral {

    /**
     * INCOMPLETE
     * @param size
     * @return
     */
    public int[][] fillSpiralMatrics(int size) {
        int startRow = size/2;
        int startCol = size/2;

        int startCount = 0;
        int[][] result = new int[size][size];
        result[startRow][startCol] = startCount++;
        // Right, moving factor is 1
        int right = 1;
        int down = 1;
        int left = 2;
        int up = 2;
        int rightUp = 2;
        int currentRow = startRow;
        int currentCol = startCol;

        // Right in the central row
        currentCol++;
        result[currentRow][currentCol] = startCount++;

        currentRow++;
        // Down after above
        // Col will stay same
        for (int i = 0; i < down; i++) {
            result[currentRow++][currentCol] = startCount++;
        }
        down++;
        currentCol--;
        // Now row will stay same and col will change
        // Left after above
        for (int i = 0; i < left; i++) {
            result[currentRow][currentCol--] = startCount++;
        }

        // Up after above

        // Right-up after above

        return  result;
    }

    public static void main(String[] args) {
    }
}


