package medium;

import java.util.*;

public class CheapestFlight787 {
    private class Pair {
        public int first;
        public int second;
        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<Pair>> graph = translateToGraph(flights);
        Map<Integer, Pair> visited = new HashMap<>();
        findCheapestPriceDFS(k, src, dst, 0, graph, 0, visited);


        return visited.containsKey(dst) ? visited.get(dst).first: -1;
    }

    private Map<Integer, List<Pair>> translateToGraph(int[][] flights) {
        Map<Integer, List<Pair>> graph = new HashMap<>();
        for (int[] flight : flights) {
            int src = flight[0];
            int dst = flight[1];
            int cost = flight[2];
            graph.putIfAbsent(src, new ArrayList<>());
            graph.get(src).add(new Pair(dst, cost));
        }

        return graph;
    }

    /**
     * Approach : DFS
     * if no reachable in k hops then return max, if more hops possible then recurse
     * @param args
     */
    private int findCheapestPriceDFS(int k, int source, int destination, int hopsUsed, Map<Integer, List<Pair>> graph,
                                     int pathCostSoFar, Map<Integer, Pair> visited) {
        if (source == destination) {
            return pathCostSoFar;
        }
        if (hopsUsed > k) {
            return Integer.MAX_VALUE;
        }

        if (visited.containsKey(source)) {
            int cost = visited.get(source).first;
            int hops = visited.get(source).second;
            if (hops == hopsUsed && cost > pathCostSoFar) {
                visited.get(source).second = pathCostSoFar;
                return pathCostSoFar;
            }
        }

        List<Pair> adj = graph.get(source);
        visited.put(source, new Pair(pathCostSoFar, hopsUsed));
        if (adj == null || adj.isEmpty()) {
            return Integer.MAX_VALUE;
        }

        int minCost = Integer.MAX_VALUE;
        for (Pair pair : adj) {
            int dest = pair.first;
            int weight = pair.second;
            minCost = Math.min(minCost,
                    findCheapestPriceDFS(k, dest, destination, hopsUsed + 1, graph, pathCostSoFar + weight, visited));
        }

        return minCost;
    }

    public static void main(String[] args) {
        CheapestFlight787 cheapestFlight787 = new CheapestFlight787();
        int n = 4;
        int[][] flights = {{0,1,100},{1,2,100},{2,0,100},{1,3,600},{2,3,200}};
        int src = 0, dst = 3, k = 1;
        System.out.println(cheapestFlight787.findCheapestPrice(n, flights, src, dst, k));
    }
}


