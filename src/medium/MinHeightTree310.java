package medium;

import java.util.*;

public class MinHeightTree310 {

    /**
     * Timelimit exceed
     * @param n
     * @param edges
     * @return
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        Map<Integer, Set<Integer>> graph = translateToGraphMap(edges);
        List<Integer> result = new ArrayList<>();
        int minHeight = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++){
            int minHeightFromI = maxHeight(i,graph);
            System.out.println("Height from " + i + " is " + minHeightFromI );
            if (minHeightFromI <  minHeight) {
                // Got the new min height so discard the old ones and consider the new min
                minHeight = minHeightFromI;
                result = new ArrayList<>();
                result.add(i);
            } else if (minHeightFromI == minHeight) {
                result.add(i);
            }
        }

        return result;
    }

    private int maxHeight(int start, Map<Integer, Set<Integer>> graph) {
        Set<Integer> neighbors = graph.get(start);
        if (neighbors == null || neighbors.isEmpty()) {
            return 0;
        }
        int minHeight = Integer.MIN_VALUE;
        for (int neighbor : neighbors) {
            graph.get(neighbor).remove(start);
            minHeight = Math.max(minHeight, maxHeight(neighbor, graph));
            graph.get(neighbor).add(start);
        }

        return minHeight + 1;
    }

    private Map<Integer, Set<Integer>> translateToGraphMap(int[][] edges) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            map.putIfAbsent(edges[i][0], new HashSet<>());
            map.get(edges[i][0]).add(edges[i][1]);
            map.putIfAbsent(edges[i][1], new HashSet<>());
            map.get(edges[i][1]).add(edges[i][0]);
        }
        return map;
    }

    public static void main(String[] args) {
        int n = 4; int[][] edges = {{1,0},{1,2},{1,3}};
        MinHeightTree310 minHeightTree = new MinHeightTree310();
        System.out.println(minHeightTree.findMinHeightTrees(n, edges));
        int n2 = 6; int[][] edges2 = {{3,0},{3,1},{3,2},{3,4},{5,4}};
        System.out.println(minHeightTree.findMinHeightTrees(n2, edges2));
        int n3 = 6; int[][] edges3 = {{0,1},{0,2},{0,3},{3,4},{4,5}};
        System.out.println(minHeightTree.findMinHeightTrees(n3, edges3));
    }
}


