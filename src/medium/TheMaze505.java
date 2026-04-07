package medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TheMaze505 {
    /**
     * Note: This is not simple maze. Pay attention to "but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction"
     * So once direction is chosen then it keep going in that direction until hit the wall. Below implementation
     * missed this fact.
     * @param maze
     * @param start
     * @param destination
     * @return
     */
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        return shortestDistanceDFS(maze, start[0], start[1], destination[0], destination[1], new HashMap<>());
    }

    /**
     * Approach : DFS
     * @param args
     */
    // Ball can roll up, down, left and right.
    private int shortestDistanceDFS(int[][] maze, int startRow, int startCol, int endRow, int endCol, Map<Integer, Set<Integer>> visited) {
        if (startRow == endRow && startCol == endCol) {
            // Reached at the destination.
            return 0;
        }

        // Outside the maze so no path exists.
        if (startRow < 0 || startRow >= maze.length || startCol < 0 || startCol >= maze[0].length) {
            return Integer.MAX_VALUE;
        }

        if (visited.containsKey(startRow) && visited.get(startRow).contains(startCol)) {
            return Integer.MAX_VALUE;
        }

        if (maze[startRow][startCol] == 1) {
            // No path here
            return Integer.MAX_VALUE;
        }
        // Mark this cell visited.
        visited.putIfAbsent(startRow, new HashSet<>());
        visited.get(startRow).add(startCol);

        int minDistance = Integer.MAX_VALUE;
        // Up move
        minDistance = Math.min(minDistance, shortestDistanceDFS(maze, startRow - 1, startCol, endRow, endCol, visited));
        // Down move
        minDistance = Math.min(minDistance, shortestDistanceDFS(maze, startRow + 1, startCol, endRow, endCol, visited));
        // Left move
        minDistance = Math.min(minDistance, shortestDistanceDFS(maze, startRow, startCol - 1, endRow, endCol, visited));
        // Right move
        minDistance = Math.min(minDistance, shortestDistanceDFS(maze, startRow, startCol + 1, endRow, endCol, visited));

        return minDistance == Integer.MAX_VALUE ? Integer.MAX_VALUE : minDistance + 1;
    }

    public static void main(String[] args) {
        TheMaze505 theMaze505 = new TheMaze505();

        int[][] maze = {{0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 0, 1, 1}, {0, 0, 0, 0, 0}};
        int[] start = {0, 4};
        int[] destination = {4,4};
        System.out.println(theMaze505.shortestDistance(maze, start, destination));
    }
}


