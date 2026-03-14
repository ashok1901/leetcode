package easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MineSweep1 {
    /**
     * This was asked in my Meta phonescreen. The trick was get numbers and shuffle. I came up with a
     * sub-optimal solution and asked interviewer if I should improve. He said lets code it and I did then got
     * rejected.
     */
    public char[][] fillMines(int rows, int cols, int kMines) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("0 size grid not allowed.");
        }

        if (kMines > rows * cols) {
            throw new IllegalArgumentException("kMines more than grid capacity not allowed.");
        }
        char[][] res = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                res[i][j] = ' ';
            }
        }
        // Number all cells from 0 to rows*cols - 1. Add these numbers to a list and shuffle. Then choose first kMines
        // to add the mines.
        List<Integer> cellNumbers = new ArrayList<>();
        for (int i = 0; i < rows*cols; i++) {
            cellNumbers.add(i);
        }

        Collections.shuffle(cellNumbers);
        for (int i = 0; i < kMines; i++) {
            int mineCell = cellNumbers.get(i);
            int row = mineCell / cols;
            int col = mineCell % cols;
            res[row][col] = 'X';
        }

        return res;
    }

    public void populateMinesCount(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != 'X') {
                    grid[i][j] = countSurroundingMines(i, j, grid);
                }
            }
        }
    }

    private char countSurroundingMines(int row, int col, char[][] grid) {
        int startRow = row - 1;
        int endRow = row + 1;
        int startCol = col - 1;
        int endCol = col + 1;

        int counter = 0;
        for (int i = startRow; i <= endRow; i++) {
            for (int j = startCol; j <= endCol; j++) {
                if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length) {
                    if (grid[i][j] == 'X') {
                        counter++;
                    }
                }
            }
        }

        return (char)(counter + '0');
    }

    public static void main(String[] args) {
        MineSweep1 mineSweep1 = new MineSweep1();
        char[][] grid = mineSweep1.fillMines(10, 10, 10);
        System.out.println(Arrays.deepToString(grid));
        mineSweep1.populateMinesCount(grid);
        System.out.println("Populating mines count");
        System.out.println(Arrays.deepToString(grid));
    }
}


