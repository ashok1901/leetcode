package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntevalListIntersection {

    /**
     * Approach:
     * - Parallely traverse both the sorted lists and find the overlap part.
     * - If there is overlap then [max(start1, start2), min(end1, end2)] is added to the result, increment both pointers
     * - Otherwise increase the smaller start index.
     *
     * @param firstList
     * @param secondList
     * @return
     */
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<Integer[]> resultList = new ArrayList<>();
        int firstIndex = 0;
        int secondIndex = 0;
        while (firstIndex < firstList.length && secondIndex < secondList.length) {
            int[] firstInterval = firstList[firstIndex];
            int[] secondInterval = secondList[secondIndex];
            if (isOverlap(firstInterval, secondInterval)) {
                int overlapStart = Math.max(firstInterval[0], secondInterval[0]);
                int overlapEnd = Math.min(firstInterval[1], secondInterval[1]);
                resultList.add(new Integer[]{overlapStart, overlapEnd});

            }
            // Move the smaller end index.
            if (firstInterval[1] < secondInterval[1]) {
                firstIndex++;
            } else {
                secondIndex++;
            }
        }
        int[][] result = new int[resultList.size()][2];
        int index = 0;
        for (Integer[] interval : resultList) {
            result[index][0] = interval[0];
            result[index][1] = interval[1];
            index++;
        }

        return result;
    }

    private boolean isOverlap(int[] firstInterval, int[] secondInterval) {
        int firstStart = firstInterval[0];
        int firstEnd = firstInterval[1];
        int secondStart = secondInterval[0];
        int secondEnd = secondInterval[1];
        // Second is starting after first start and before first ends
        boolean isSecondStartingInFirst = firstStart <= secondStart && secondStart <= firstEnd;
        // First is starting after second start and end before second end.
        boolean isFirstStartingInSecond = secondStart <= firstStart && firstStart <= secondEnd;

        return isFirstStartingInSecond || isSecondStartingInFirst;
    }

    public static void main(String[] args) {
        IntevalListIntersection intevalListIntersection = new IntevalListIntersection();
        int[][] firstList = {{0,2},{5,10},{13,23},{24,25}};
        int[][] secondList = {{1,5},{8,12},{15,24},{25,26}};
        System.out.println("Intersection is " +
                Arrays.deepToString(intevalListIntersection.intervalIntersection(firstList, secondList)));
        int[][] firstList1 = {{1,3},{5,9}};
        int[][] secondList1 = {};
        System.out.println("Intersection is " +
                Arrays.deepToString(intevalListIntersection.intervalIntersection(firstList1, secondList1)));

        int[][] firstList2 = {{3,5},{9,20}};
        int[][] secondList2 = {{4,5},{7,10},{11,12},{14,15},{16,20}};
        System.out.println("Intersection is " +
                Arrays.deepToString(intevalListIntersection.intervalIntersection(firstList2, secondList2)));
    }
}


