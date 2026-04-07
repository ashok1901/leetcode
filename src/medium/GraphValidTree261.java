package medium;

import java.util.*;

public class GraphValidTree261 {

    /**
     * Approach: Valid tree graph has no cycle/loop
     * - Traverse in BFS, if same node is visited again then return false otherwise true
     *
     * @param n
     * @param edges
     * @return
     */
    public boolean validTree(int n, int[][] edges) {
        Map<Integer, Set<Integer>> graphMap = transformToMapGraph(edges);
        // To check tree we can start with any node as this is undirected graph
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            Set<Integer> neighbors = graphMap.get(node);
            if (neighbors == null) {
                continue;
            }
            for (Integer neighbor : neighbors) {
                if (visited.contains(neighbor)) {
                    return false;
                }
                visited.add(neighbor);
                queue.offer(neighbor);
                graphMap.get(neighbor).remove(node);
            }
        }

        return visited.size() == n;
    }

    private Map<Integer, Set<Integer>> transformToMapGraph(int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            graph.putIfAbsent(edges[i][0], new HashSet<>());
            graph.get(edges[i][0]).add(edges[i][1]);
            graph.putIfAbsent(edges[i][1], new HashSet<>());
            graph.get(edges[i][1]).add(edges[i][0]);
        }

        return graph;
    }

    public static void main(String[] args) {
    }
}


