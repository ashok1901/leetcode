package medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class NumOfProvinces547 {

    public int findCircleNum(int[][] isConnected) {
        return solveByDFS(isConnected);
    }

    /**
     * Approach: Solve by DFS
     * - Iterate over nodes, mark all nodes visited from each node. When found unvisited node, increment the counter
     *
     */
    public int solveByDFS(int[][] isConnected) {
        Set<Integer> visited = new HashSet<>();
        int n = isConnected.length;
        int connectedComponents = 0;
        for (int i = 0; i < n; i++) {
            if (!visited.contains(i)) {
                connectedComponents++;
                dfs(i, isConnected, visited);
            }
        }
        return connectedComponents;
    }

    private void dfs(int start, int[][] isConnected, Set<Integer> visited) {
        if (visited.contains(start))  {
            return;
        }

        visited.add(start);
        for (int startNeighbor = 0; startNeighbor < isConnected[start].length; startNeighbor++) {
            if (isConnected[start][startNeighbor] == 1) {
                dfs(startNeighbor, isConnected, visited);
            }
        }
    }

    /**
     * Approach : Union and find approach
     * @param isConnected
     * @return
     */
    private int solveByUnionfind(int[][] isConnected) {
        int[] parents = new int[isConnected.length];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < isConnected.length; i++) {
            for (int j =  i + 1; j < isConnected[i].length; j++) {
                if (i != j && isConnected[i][j] == 1) {
                    union(i, j, parents);
                }
            }
        }

        Set<Integer> provinces = new HashSet<>();
        for (int i = 0; i < parents.length; i++) {
            provinces.add(parents[i]);
        }

        return provinces.size();
    }
    private void union(int x, int y, int[] parents) {
        int parentX = find(x, parents);
        int parentY = find(y, parents);
        parents[parentX] = parentY;
    }
    private int find(int x, int[] parents) {
        if (parents[x] != x) {
            parents[x] = find(parents[x], parents);
        }

        return parents[x];
    }

    public static void main(String[] args) {
        NumOfProvinces547 numOfProvinces547 = new NumOfProvinces547();
        int[][] isConnected = {{1,1,0},{1,1,0},{0,0,1}};
        System.out.println(numOfProvinces547.findCircleNum(isConnected));
        int[][] isConnected2 = {{1,0,0},{0,1,0},{0,0,1}};
        System.out.println(numOfProvinces547.findCircleNum(isConnected2));
        int[][] isConnected3 = {{1,0,0,1}, {0,1,1,0}, {0,1,1,1},{1,0,1,1}};
        System.out.println(numOfProvinces547.findCircleNum(isConnected3));
    }
}


