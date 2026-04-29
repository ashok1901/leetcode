package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NetworkDelayTime743 {

    private class Pair {
        int nodeId;
        int time;
        public Pair(int nodeId, int time) {
            this.nodeId = nodeId;
            this.time = time;
        }
    }

    public int networkDelayTime(int[][] times, int n, int k) {
        // Note: keeping it zero indexed array
        int[] nodeDelay = new int[n];
        Arrays.fill(nodeDelay, -1);
        nodeDelay[k - 1] = 0;
        Map<Integer, List<Pair>> graph = buildGraph(times);

        dfs(k, graph, nodeDelay);
        int timeToReachToAll = -1;
        for (int i = 0; i < n; i++) {
            if (nodeDelay[i] == -1) {
                // Node is not reachable from k.
                return -1;
            }
            timeToReachToAll = Math.max(timeToReachToAll, nodeDelay[i]);
        }

        return timeToReachToAll;
    }

    private Map<Integer, List<Pair>> buildGraph(int[][] times) {
        Map<Integer, List<Pair>> graph = new HashMap<>();
        for (int[] time : times) {
            int source = time[0];
            int destination = time[1];
            int timeTaken = time[2];
            graph.putIfAbsent(source, new ArrayList<>());
            graph.get(source).add(new Pair(destination, timeTaken));
        }

        return graph;
    }
    
    private void dfs(int start, Map<Integer, List<Pair>> graph, int[] nodeDelay) {
        List<Pair> adj = graph.get(start);
        if (adj == null || adj.isEmpty()) {
            return;
        }
        int timeUntilStart = nodeDelay[start - 1];
        for (Pair adjNode : adj) {
            int adjNodeId = adjNode.nodeId;
            int adjNodeTime = adjNode.time;
            int timeUntilAdjNode = timeUntilStart + adjNodeTime;
            if (nodeDelay[adjNodeId - 1] == -1 || nodeDelay[adjNodeId - 1] > timeUntilAdjNode) {
                nodeDelay[adjNodeId - 1] = timeUntilAdjNode;
                dfs(adjNodeId, graph, nodeDelay);
            }
        }
    }

    public static void main(String... args) {
        NetworkDelayTime743 networkDelayTime743 = new NetworkDelayTime743();
        int[][] times = {{2,1,1},{2,3,1},{3,4,1}};
        int n = 4, k = 2;
        System.out.println(networkDelayTime743.networkDelayTime(times, n, k));
        int[][] times2 = {{1,2,1}};
        int n2 = 2, k2 = 1;
        System.out.println(networkDelayTime743.networkDelayTime(times2, n2, k2));
        int[][] times3 = {{1,2,1}};
        int n3 = 2, k3 = 2;
        System.out.println(networkDelayTime743.networkDelayTime(times3, n3, k3));
    }
}
