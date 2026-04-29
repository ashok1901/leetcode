package medium;

import java.util.*;

public class MinMaxEdgeWeight3419 {
    private class Pair {
        int node;
        int weight;
        public Pair(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    public int minMaxWeight(int n, int[][] edges, int threshold) {
        Map<Integer, List<Pair>> graph = translateToGraph(edges);
        Map<Integer, Integer> visited = new HashMap<>();
        minMaxWeightDij(graph, 0, visited, 0);

        // Now find max in the visited map.
        return visited.size() == n ? visited.values().stream().max(Integer::compareTo).get() : -1 ;
    }

    private Map<Integer, List<Pair>> translateToGraph(int[][] edges) {
        Map<Integer, List<Pair>> graph = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            // Note graph edge needs to be reversed.
            int source = edges[i][1];
            int target = edges[i][0];
            int weight = edges[i][2];
            graph.putIfAbsent(source, new ArrayList<>());
            // If there was already a path from source to target then pick the minimum one.
            Pair existingPair = null;
            for (Pair pair : graph.get(source)) {
                if (pair.node == target) {
                    existingPair = pair;
                }
            }
            if (existingPair != null) {
                existingPair.weight = Math.min(existingPair.weight, weight);
            } else {
                graph.get(source).add(new Pair(target, weight));
            }
        }
        return graph;
    }

    /**
     * Approach : Modified Disjikstra's algo with reverse links of the given graph. Starting with 0.
     * @param args
     */
    private void minMaxWeightDij(Map<Integer, List<Pair>> graph, int startNode, Map<Integer, Integer> visited, int maxWeightOnPath) {
        if (visited.containsKey(startNode)) {
            // Update the weight of this node if needed.
            if (maxWeightOnPath < visited.get(startNode)) {
                visited.put(startNode, maxWeightOnPath);
            }
            return;
        }
        // Mark it visited.
        visited.put(startNode, maxWeightOnPath);
        // Do DFS on it's adjecent nodes.
        List<Pair> adjList = graph.get(startNode);
        if (adjList == null) {
            return;
        }

        // Pick minimum one, under Dijikstra
        Queue<Pair> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
        queue.addAll(adjList);
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            int weight = pair.weight;
            int adjNode = pair.node;
            minMaxWeightDij(graph, adjNode, visited, Math.max(weight, maxWeightOnPath));
        }
    }

    public static void main(String[] args) {
        MinMaxEdgeWeight3419 minMaxEdgeWeight3419 = new MinMaxEdgeWeight3419();
        int n = 5;
        int[][] edges = {{1,0,1},{2,0,2},{3,0,1},{4,3,1},{2,1,1}};
//        int threshold = 2;
//        System.out.println(minMaxEdgeWeight3419.minMaxWeight(n, edges, threshold));
//
//        int n2 = 5;
//        int[][] edges2 = {{0,1,1},{0,2,2},{0,3,1},{0,4,1},{1,2,1},{1,4,1}};
//        int threshold2 = 1;
//        System.out.println(minMaxEdgeWeight3419.minMaxWeight(n2, edges2, threshold2));
//
//        int n3 = 5;
//        int[][] edges3 = {{1,2,1},{1,3,3},{1,4,5},{2,3,2},{3,4,2},{4,0,1}};
//        int threshold3 = 1;
//        System.out.println(minMaxEdgeWeight3419.minMaxWeight(n3, edges3, threshold3));
//
//        int n4 = 5;
//        int[][] edges4 = {{1,2,1},{1,3,3},{1,4,5},{2,3,2},{4,0,1}};
//        int threshold4 = 1;
//        System.out.println(minMaxEdgeWeight3419.minMaxWeight(n4, edges4, threshold4));

        int n5 = 4;
        int[][] edges5 = {{1,0,92},{3,1,9},{3,2,24},{3,2,30},{2,0,15},{1,3,59}};
        int threshold5 = 3;
        System.out.println(minMaxEdgeWeight3419.minMaxWeight(n5, edges5, threshold5));
    }
}


