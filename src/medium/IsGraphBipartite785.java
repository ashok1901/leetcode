package medium;

import java.util.HashMap;
import java.util.Map;

public class IsGraphBipartite785 {
    public boolean isBipartite(int[][] graph) {
        return isBipartiteDFS(graph);
    }

    public boolean isBipartiteDFS(int[][] graph) {
        Map<Integer, String> perNodeColor = new HashMap<>();
        for (int i = 0; i < graph.length; i++) {
            isBipartiteDFSByColoring(graph, i, -1, perNodeColor);
        }

        // Now look for contradiction
        for (int i = 0; i < graph.length; i++) {
            String nodeColor = perNodeColor.get(i);
            for (int adj : graph[i]) {
                if  (perNodeColor.containsKey(adj) &&  perNodeColor.get(adj).equals(nodeColor)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isBipartiteDFSByColoring(int[][] graph, int startNode, int prevNode, Map<Integer, String> perNodeColor) {
        if (perNodeColor.containsKey(startNode)) {
            return true;
        }
        if (prevNode == -1) {
            perNodeColor.put(startNode, "A");
        } else {
            String otherColor = perNodeColor.get(prevNode).equals("A") ? "B" : "A";
            perNodeColor.put(startNode, otherColor);
        }

        for (int adjNode : graph[startNode]) {
            if (perNodeColor.containsKey(adjNode) && perNodeColor.get(adjNode).equals(perNodeColor.get(startNode))) {
                return false;
            }
            boolean isPossibleBipartite = isBipartiteDFSByColoring(graph, adjNode, startNode, perNodeColor);
            if (!isPossibleBipartite) {
                return false;
            }
        }

        return true;
    }

    /**
     * Approach: Try to assign two colors to the two ends of each edge in a BFS fashion.
     * - If contradiction then return false
     * - otherwise return true
     * @param graph  graph[i] is the adjacents of node i
     * @return
     */
    public boolean isBipartiteIterative(int[][] graph) {
        Map<Integer, String> assignedSet = new HashMap<>();
        for (int i = 0; i < graph.length; i++) {
            int[] adjNeighbors = graph[i];
            if (assignedSet.containsKey(i)) {
                // i already knows it's set. then neighbors must go to other set
                String otherSet = assignedSet.get(i) == "A" ? "B" : "A";
                for  (int adj : adjNeighbors) {
                    if (assignedSet.containsKey(adj) && !assignedSet.get(adj).equals(otherSet)) {
                        return false;
                    } else {
                        assignedSet.put(adj, otherSet);
                    }
                }

            } else {
                String neighborSet = null;
                for  (int adj : adjNeighbors) {
                    if (assignedSet.containsKey(adj)) {
                        if (neighborSet == null) {
                            neighborSet = assignedSet.get(adj);
                        } else if (!neighborSet.equals(assignedSet.get(adj))) {
                            return false;
                        }
                    }
                }

                String nodeSet = neighborSet == null ? "A" : (neighborSet.equals("A") ? "B" : "A");
                assignedSet.put(i, nodeSet);
            }
        }

        return true;
    }

    public static void main(String[] args) {
        IsGraphBipartite785 isGraphBipartite785 = new IsGraphBipartite785();
        int[][] graph = {{1,2,3},{0,2},{0,1,3},{0,2}};
        System.out.println(isGraphBipartite785.isBipartite(graph));

        int[][] graph2 = {{1,3},{0,2},{1,3},{0,2}};
        System.out.println(isGraphBipartite785.isBipartite(graph2));

        int[][] graph3 = {{1},{0,3},{3},{1,2}};
        System.out.println(isGraphBipartite785.isBipartite(graph3));

        int[][] graph4 = {{4,1},{0,2},{1,3},{2,4},{3,0}};
        System.out.println(isGraphBipartite785.isBipartite(graph4));
    }
}


