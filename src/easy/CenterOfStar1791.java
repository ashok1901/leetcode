package easy;

import java.util.HashMap;
import java.util.Map;

public class CenterOfStar1791 {
    /**
     * Approach: Center of star will have degree(cardinality) n - 1
     * 3 <= n <= 105
     * edges.length == n - 1
     * @param edges
     * @return
     */
    public int findCenter(int[][] edges) {
        Map<Integer, Integer> nodeDegreeMap = new HashMap<>();
        int n = edges.length + 1;
        for (int[] edge : edges) {
            nodeDegreeMap.put(edge[0], nodeDegreeMap.getOrDefault(edge[0], 0) + 1);
            nodeDegreeMap.put(edge[1], nodeDegreeMap.getOrDefault(edge[1], 0) + 1);
        }

        for (int key : nodeDegreeMap.keySet()) {
            if (nodeDegreeMap.get(key) == n - 1)
                return key;
        }

        return -1;
    }

    public static void main(String[] args) {
        int[][] edges = {{1,2},{2,3},{4,2}};
        System.out.println(new CenterOfStar1791().findCenter(edges));
        int[][] edges2 = {{1,2},{5,1},{1,3},{1,4}};
        System.out.println(new CenterOfStar1791().findCenter(edges2));
    }
}


