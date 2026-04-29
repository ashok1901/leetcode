package hard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MakingALargeIsland827 {
    public int largestIsland(int[][] grid) {
        Map<Integer, Integer> idToSizeMap = fillIslandWithItsSize(grid);
        int maxSizeIsland = -1;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    Set<Integer> islandIds = new HashSet<>();
                    if (i > 0) {
                        islandIds.add(grid[i - 1][j]);
                    }
                    if (i < grid.length - 1) {
                        islandIds.add(grid[i + 1][j]);
                    }
                    if (j > 0) {
                        islandIds.add(grid[i][j - 1]);
                    }
                    if (j < grid[0].length - 1) {
                        islandIds.add(grid[i][j + 1]);
                    }
                    int totalSize = 0;
                    for (Integer islandSize : islandIds) {
                        totalSize += idToSizeMap.getOrDefault(islandSize, 0);
                    }

                    maxSizeIsland = Math.max(maxSizeIsland, totalSize + 1);
                }
            }
        }
        return maxSizeIsland != -1 ? maxSizeIsland : idToSizeMap.values().stream().max(Integer::compare).get();
    }

    private Map<Integer, Integer> fillIslandWithItsSize(int[][] grid) {
        int islandId = 2;
        Map<Integer, Integer> idSizeMap = new HashMap<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    int size = computeSize(grid, i, j, islandId);
                    idSizeMap.put(islandId, size);
                    fillItWithSize(grid, i, j, islandId);
                    islandId++;
                }
            }
        }

        return idSizeMap;
    }

    private void fillItWithSize(int[][] grid, int row, int col, int size) {
        // Out of boundary check
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
            return;
        }
        if (grid[row][col] < 0) {
            grid[row][col] = size;
            fillItWithSize(grid, row + 1, col, size);
            fillItWithSize(grid, row - 1, col, size);
            fillItWithSize(grid, row, col + 1, size);
            fillItWithSize(grid, row, col - 1, size);
        }
    }

    private int computeSize(int[][] grid, int row, int col, int islandId) {
        // Out of boundary check
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
            return 0;
        }
        if (grid[row][col] != 1) {
            // It means either this is already considered or is 0
            return 0;
        }
        grid[row][col] = islandId;
        // It means this is 1, find size of surroundings
        int size =  1 + computeSize(grid, row + 1, col, islandId) + computeSize(grid, row - 1, col, islandId) +
                computeSize(grid, row, col - 1, islandId) + computeSize(grid, row, col + 1, islandId);
        return size;
    }

    public static void main(String[] args) {
        MakingALargeIsland827 makingALargeIsland827 = new MakingALargeIsland827();
        int[][] grid = {{1,0},{0,1}};
        System.out.println(makingALargeIsland827.largestIsland(grid));
        int[][] grid2 = {{1,1},{1,0}};
        System.out.println(makingALargeIsland827.largestIsland(grid2));
        int[][] grid3 = {{1,1},{1,1}};
        System.out.println(makingALargeIsland827.largestIsland(grid3));

    }
}


