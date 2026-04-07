package easy;

import java.util.*;

public class FindIfPathExists1971 {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        if (source == destination) return true;
        Map<Integer, List<Integer>> graphAdjMap = buildAdjGraphMap(edges);
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.add(source);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            List<Integer> adjList = graphAdjMap.get(node);
            if (adjList == null) continue;
            for (Integer adj : adjList) {
                if (adj == destination) {
                    return true;
                }
                if (!visited.contains(adj)) {
                    visited.add(adj);
                    queue.offer(adj);
                }
            }
        }

        return false;
    }

    private Map<Integer, List<Integer>> buildAdjGraphMap(int[][] edges) {
        Map<Integer, List<Integer>> graphMap = new HashMap<>();
        for (int[] edge : edges) {
            int source = edge[0];
            int destination = edge[1];
            graphMap.putIfAbsent(source, new ArrayList<>());
            graphMap.get(source).add(destination);
            graphMap.putIfAbsent(destination, new ArrayList<>());
            graphMap.get(destination).add(source);
        }

        return graphMap;
    }

    public static void main(String[] args) {
        int n = 3; int[][] edges = {{0,1},{1,2},{2,0}}; int source = 0, destination = 2;
        FindIfPathExists1971 find = new FindIfPathExists1971();
        System.out.println(find.validPath(n, edges, source, destination));
        int n2 = 6; int[][] edges2 = {{0,1},{0,2},{3,5},{5,4},{4,3}}; int source2 = 0, destination2 = 5;
        System.out.println(find.validPath(n2, edges2, source2, destination2));
    }
}


