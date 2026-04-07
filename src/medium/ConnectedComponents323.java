package medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ConnectedComponents323 {

    /**
     * Approach: Implement by union-find algo
     * @param n
     * @param edges
     * @return
     */
    public int countComponentsUnionFind(int n, int[][] edges) {
        int[] parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
        for (int[] edge: edges) {
            int source = edge[0];
            int target = edge[1];
            union(source, target, parents);
        }

        // Count unique parents now.
        int connectedComponents = 0;
        for (int i = 0; i < n; i++) {
            if (parents[i] == i) {
                connectedComponents++;
            }
        }
        return connectedComponents;
    }

    private void union(int x, int y, int[] parent) {
        int parentX = find(x, parent);
        int parentY = find(y, parent);
        parent[parentX] = parentY;
    }

    private int find(int x, int[] parents) {
        if (parents[x] == x) return x;
        return find(parents[x], parents);
    }

    /**
     * Approach: Traverse the graph until all nodes are visited
     * @param n
     * @param edges
     * @return
     */
    public int countComponents(int n, int[][] edges) {
        return countComponentsUnionFind(n, edges);
    }

    private int connectedComponentsCount(int n, int[][] edges) {
        Map<Integer, Set<Integer>> graph = tranformToAdjecenyMap(edges);
        Set<Integer> visited = new HashSet<>();
        int connectedComponents = 0;
        for (int i = 0; i < n; i++) {
            if (visited.contains(i)) continue;
            connectedComponents++;
            traverseFromNode(i, visited, graph);
        }

        return connectedComponents;

    }

    private Map<Integer, Set<Integer>> tranformToAdjecenyMap(int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            int source = edges[i][0];
            int target = edges[i][1];
            // Note: This is undirected graph so it's two way edge.
            graph.putIfAbsent(source, new HashSet<>());
            graph.get(source).add(target);
            graph.putIfAbsent(target, new HashSet<>());
            graph.get(target).add(source);
        }

        return graph;
    }

    private void traverseFromNode(int start, Set<Integer> visited, Map<Integer, Set<Integer>> graph) {
        if (visited.contains(start)) {
            return;
        }

        visited.add(start);
        if (!graph.containsKey(start)) {
            // If this is not adjacent to any node. i.e. isolated node
            return;
        }

        for (int neighbor : graph.get(start)) {
            if (!visited.contains(neighbor)) {
                traverseFromNode(neighbor, visited, graph);
            }
        }
    }

    public static void main(String[] args) {
        ConnectedComponents323 connectedComponents323 = new ConnectedComponents323();
        int n = 5; int[][] edges = {{0,1},{1,2},{3,4}};
        System.out.println(connectedComponents323.countComponents(n, edges));
    }
}


