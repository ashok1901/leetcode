package medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RedundantConnection684 {
    /** 
     * Approach : Use union find.
     */
    public class DisjointUnionFind {
        private int[] parent;
        private int[] size;
        public DisjointUnionFind(int n) {
            parent = new int[n]; 
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;

            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        private boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return false;
            }
            if (size[rootX] < size[rootY]) {
                parent[rootX] = rootY;
                size[rootY] += size[rootX];
            } else {
                parent[rootY] = rootX;
                size[rootX] += size[rootY];
            }
            return true;
        } 
    }


    public int[] findRedundantConnection(int[][] edges) {
        DisjointUnionFind disjointUnionFind = new DisjointUnionFind(edges.length);
        for (int i = 0; i < edges.length; i++) {
            if (!disjointUnionFind.union(edges[i][0] - 1, edges[i][1] - 1)) {
                return edges[i];
            }
        }
        return null;
    }

    /**
     * Approach: Count degree of each node.
     * BAD: This approach does not work because it might disconnect the graph when edge is the only bridge between
     * two connected components.
     * - if degree is more than 1 then it's a redundant connection.
     * - Start from the last edge as we need to return the last redundant connection.
     * @param edges
     * @return
     */
    public int[] findRedundantConnectionBAD(int[][] edges) {
        Map<Integer, Integer> nodeDegreeMap = new HashMap<>();
        for (int[] edge : edges) {
            nodeDegreeMap.put(edge[0], nodeDegreeMap.getOrDefault(edge[0], 0) + 1);
            nodeDegreeMap.put(edge[1], nodeDegreeMap.getOrDefault(edge[1], 0) + 1);
        }

        for (int i = edges.length - 1; i >= 0; i--) {
            int[] edge = edges[i];
            if (nodeDegreeMap.get(edge[0]) > 1 && nodeDegreeMap.get(edge[1]) > 1) {
                return edge;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[][] edges = {{1,2},{1,3},{2,3}};
        System.out.println(Arrays.toString(new RedundantConnection684().findRedundantConnection(edges)));
        int[][] edges2 = {{1,2},{2,3},{3,4},{1,4},{1,5}};
        System.out.println(Arrays.toString(new RedundantConnection684().findRedundantConnection(edges2)));
        int[][] edges3 = {{9,10},{5,8},{2,6},{1,5},{3,8},{4,9},{8,10},{4,10},{6,8},{7,9}};
        System.out.println(Arrays.toString(new RedundantConnection684().findRedundantConnection(edges3)));
    }
}


