package medium;

import java.util.*;

public class KClosestPointsToOrigin {

    public int[][] kClosest(int[][] points, int k) {

        // List entry <x1, y1, distanceFromOrigin>
        PriorityQueue<List<Double>> maxHeap = new PriorityQueue<>(new Comparator<List<Double>>() {
            @Override
            public int compare(List<Double> o1, List<Double> o2) {
                // Index 2 is the distance
                return o2.get(2).compareTo(o1.get(2));
            };
        });
        for (int[] point : points) {
            int x1 = point[0];
            int y1 = point[1];
            double distanceFromOrigin = Math.sqrt(x1*x1 + y1*y1);
            List<Double> queueEntry = new ArrayList<>();
            queueEntry.add((double)x1);queueEntry.add((double)y1);queueEntry.add(distanceFromOrigin);
            maxHeap.add(queueEntry);
            while (maxHeap.size() > k) {
                maxHeap.remove();
            }
        }

        int[][] closestPoints = new int[k][2];
        int index = 0;
        while (!maxHeap.isEmpty()) {
            List<Double> entry = maxHeap.remove();
            closestPoints[index][0] = entry.get(0).intValue();
            closestPoints[index][1] = entry.get(1).intValue();
            index += 1;
        }

        return closestPoints;
    }

    public static void main(String[] args) {
        KClosestPointsToOrigin closestPointsToOrigin = new KClosestPointsToOrigin();
        int[][] points = {{3,3},{5,-1},{-2,4}};
        int k = 2;
        System.out.println("K Closed points are " + Arrays.deepToString(closestPointsToOrigin.kClosest(points, k)));
        int [][] ex2 = {{1,3},{-2,2}};
        int e2k = 1;
        System.out.println("K Closed points are " + Arrays.deepToString(closestPointsToOrigin.kClosest(ex2, e2k)));
    }
}


