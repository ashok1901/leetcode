package medium;

public class MinPathSum64 {

    public int minPathSum(int[][] grid) {
//        return minPathSumRecursive(grid, 0, 0, 0);
        return minPathSumDP(grid);
    }

    private int minPathSumDP(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        for (int row = grid.length - 1; row >= 0; row--) {
            for (int col = grid[0].length - 1; col >= 0; col--) {
                if (row == grid.length - 1 && col == grid[0].length - 1) {
                    // Base case
                    dp[row][col] = grid[row][col];
                } else {
                    int bottom = row + 1 < dp.length ? dp[row + 1][col] : Integer.MAX_VALUE;
                    int right = col + 1 < dp[0].length ? dp[row][col + 1] : Integer.MAX_VALUE;
                    int cost = Math.min(bottom, right) + grid[row][col];
                    dp[row][col] = cost;
                }
            }
        }

        return dp[0][0];
    }

    /**
     * Simple implementation but too many double calculations. So this is DP candidate.
     * @param grid
     * @param row
     * @param col
     * @param sumSoFar
     * @return
     */
    private int minPathSumRecursive(int[][] grid, int row, int col, int sumSoFar) {
        if (row >= grid.length || col >= grid[0].length) {
            // Already out of bound, not path exists on this way
            return Integer.MAX_VALUE;
        }
        // At the last cell already, so return
        if (row == grid.length - 1 && col == grid[0].length - 1) {
            return sumSoFar + grid[row][col];
        };

        return Math.min(minPathSumRecursive(grid, row + 1, col, sumSoFar + grid[row][col]),
                minPathSumRecursive(grid, row, col + 1, sumSoFar + grid[row][col]));
    }

    public static void main(String[] args) {
        MinPathSum64 minPathSum64 = new MinPathSum64();
        int[][] grid = {{1,3,1},{1,5,1},{4,2,1}};
        System.out.println(minPathSum64.minPathSum(grid));

        int[][] grid2 = {{1,2,3},{4,5,6}};
        System.out.println(minPathSum64.minPathSum(grid2));
    }
}


