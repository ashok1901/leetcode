package medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ShortestPathBinaryMatrix {

    /**
     * Approach BFS:
     * @param grid
     * @return
     */
    public int shortestPathBinaryMatrix(int[][] grid) {
        int gridRows = grid.length;
        int gridCols = grid[0].length;
        if (grid[0][0] != 0) {
            // Starting point itself is not a valid move.
            return -1;
        }
        if (gridRows == 1 && gridCols == 1) {
            // 1X1 matric means we are already at the destination
            return 1;
        }
        int[][] visitedCells = new int[gridRows][gridCols];
        initVisitedCells(visitedCells);
        // count cells visited so starting with 1
        visitedCells[0][0] = 1;
        bfsIterative(grid, 0, 0, visitedCells);
        return visitedCells[gridRows - 1][gridCols - 1];
    }

    private void bfsIterative(int[][] grid, int startRow, int startCol, int[][] visitedCells) {
        Queue<Integer[]> queue = new LinkedList<>();
        queue.add(new Integer[]{0,0});
        visitedCells[0][0] = 1;
        while (!queue.isEmpty()) {
            Integer[] entry = queue.remove();
            int row = entry[0];
            int col = entry[1];
            List<Integer[]> validMoves = buildValidMoves(row, col, visitedCells, grid);
            for (Integer[] move : validMoves) {
                int nextMoveRow = move[0];
                int nextMoveCol = move[1];
                if (visitedCells[nextMoveRow][nextMoveCol] != -1) {
                    // Already visited
                    visitedCells[nextMoveRow][nextMoveCol] =
                            Math.min(visitedCells[nextMoveRow][nextMoveCol], visitedCells[row][col] + 1);
                } else {
                    // First time visiting
                    visitedCells[nextMoveRow][nextMoveCol] = visitedCells[row][col] + 1;
                    queue.add(new Integer[]{nextMoveRow, nextMoveCol});
                }
            }
        }
    }

    private List<Integer[]> buildValidMoves(int row, int col, int[][] visitedCells, int[][] grid) {
        List<Integer[]> validNeighbors = new ArrayList<>();
        int neighborRow; int neighborCol;
        // Top left
        neighborRow = row - 1;
        neighborCol = col - 1;
        if (isValidCell(neighborRow, neighborCol, grid) && isValidMove(neighborRow, neighborCol, grid)) {
            validNeighbors.add(new Integer[]{neighborRow, neighborCol});
        }

        // Top
        neighborRow = row - 1;
        neighborCol = col;
        if (isValidCell(neighborRow, neighborCol, grid) && isValidMove(neighborRow, neighborCol, grid)) {
            validNeighbors.add(new Integer[]{neighborRow, neighborCol});
        }

        // Top Right
        neighborRow = row - 1;
        neighborCol = col + 1;
        if (isValidCell(neighborRow, neighborCol, grid) && isValidMove(neighborRow, neighborCol, grid)) {
            validNeighbors.add(new Integer[]{neighborRow, neighborCol});
        }

        // Left
        neighborRow = row;
        neighborCol = col - 1;
        if (isValidCell(neighborRow, neighborCol, grid) && isValidMove(neighborRow, neighborCol, grid)) {
            validNeighbors.add(new Integer[]{neighborRow, neighborCol});
        }

        // Right
        neighborRow = row;
        neighborCol = col + 1;
        if (isValidCell(neighborRow, neighborCol, grid) && isValidMove(neighborRow, neighborCol, grid)) {
            validNeighbors.add(new Integer[]{neighborRow, neighborCol});
        }

        // Bottom
        neighborRow = row + 1;
        neighborCol = col;
        if (isValidCell(neighborRow, neighborCol, grid) && isValidMove(neighborRow, neighborCol, grid)) {
            validNeighbors.add(new Integer[]{neighborRow, neighborCol});
        }

        // Left bottom
        neighborRow = row + 1;
        neighborCol = col - 1;
        if (isValidCell(neighborRow, neighborCol, grid) && isValidMove(neighborRow, neighborCol, grid)) {
            validNeighbors.add(new Integer[]{neighborRow, neighborCol});
        }

        // right bottom
        neighborRow = row + 1;
        neighborCol = col + 1;
        if (isValidCell(neighborRow, neighborCol, grid) && isValidMove(neighborRow, neighborCol, grid)) {
            validNeighbors.add(new Integer[]{neighborRow, neighborCol});
        }

        return validNeighbors;
    }


    private boolean isValidMove(int currentRow, int currentCol, int[][] grid) {
        return grid[currentRow][currentCol] == 0;
    }

    private boolean isValidCell(int currentRow, int currentCol, int[][] grid) {
        return currentRow >= 0 && currentRow < grid.length && currentCol >= 0 && currentCol < grid[0].length;
    }

    private void initVisitedCells(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = -1;
            }
        }
    }

    public static void main(String[] args) {
        ShortestPathBinaryMatrix shortestPathBinaryMatrix = new ShortestPathBinaryMatrix();
        int[][] grid0 = {{0,0,0,0},{1,0,0,1},{0,1,0,0},{0,0,0,0}};
        System.out.println("Shorted path : " + shortestPathBinaryMatrix.shortestPathBinaryMatrix(grid0));
        int[][] grid = {{0,0,0},{1,1,0},{1,1,0}};
        System.out.println("Shorted path : " + shortestPathBinaryMatrix.shortestPathBinaryMatrix(grid));
        int[][] grid1 = {{1,0,0},{1,1,0},{1,1,0}};
        System.out.println("Shorted path : " + shortestPathBinaryMatrix.shortestPathBinaryMatrix(grid1));
        int[][] grid2 = {{0,1},{1,0}};
        System.out.println("Shorted path : " + shortestPathBinaryMatrix.shortestPathBinaryMatrix(grid2));
    }
}


