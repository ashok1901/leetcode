package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class InsertInterval57 {

    /**
     * Approach: Iterate over the given intervals and merge when needed.
     * - Any interval ending before even start of new interval can go to the result as it is. Because it can not cause any overlap
     * - For the remaining intervals, check if this needs to be merged in case of overlap
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        // Note intervals are already sorted by start.
        List<List<Integer>> result = new ArrayList<>();
        // Handle special case when new interval should come as first interval.
        // Handle special case when new interval should come as last interval.
        // Iterate over the given intervals and merge when overlap
        int prevStart = newInterval[0];
        int prevEnd = newInterval[1];
        for(int index = 0; index < intervals.length; index++) {
            int start1 = intervals[index][0];
            int end1 = intervals[index][1];
            int start2 = prevStart;
            int end2 = prevEnd;
            if (end1 < start2) {
                // It means the current interval has already ended before the start of interval to be adjusted.
                // Just add this to the result and continue
                addToResult(result, start1, end1);
                continue;
            }

            if (isOverlap(start1, end1, start2, end2)) {
                int mergedStart = Math.min(start1, start2);
                int mergedEnd = Math.max(end1, end2);
                prevStart = mergedStart;
                prevEnd = mergedEnd;
            } else {
                // Smaller will safely go to the result and other will become prev
                int smallerStart = Math.min(start1, start2);
                int smallerEnd = Math.min(end1, end2);
                int biggerStart = Math.max(start1, start2);
                int biggerEnd = Math.max(end1, end2);
                addToResult(result, smallerStart, smallerEnd);
                prevStart = biggerStart;
                prevEnd = biggerEnd;
            }
        }
        addToResult(result, prevStart, prevEnd);
        return translateListToIntMatrix(result);
    }

    private int[][] translateListToIntMatrix(List<List<Integer>> res) {
        int[][] resMatrix = new int[res.size()][2];
        int index = 0;
        for (List<Integer> entry : res) {
            resMatrix[index][0] = entry.get(0);
            resMatrix[index][1] = entry.get(1);
            index++;
        }
        return resMatrix;
    }

    private void addToResult(List<List<Integer>> res, int start1, int end1) {
        List<Integer> entry = new ArrayList<>();
        entry.add(start1);
        entry.add(end1);
        res.add(entry);
    }

    private boolean isOverlap(int start1, int end1, int start2, int end2) {
        return  (start1 <= start2 && start2 <= end1) || // 2 is starting within 1
                (start2 <= start1 && start1 <= end2);    // 1 is starting within 2
    }

    public static void main(String[] args) {
        InsertInterval57 insertInterval57 = new InsertInterval57();
        int[][] intervals = {{1,3},{6,9}}; int[] newInterval = {2,5};
        System.out.println("Merged intervals are " + Arrays.deepToString(insertInterval57.insert(intervals, newInterval)));
        int[][] intervals1 = {{1,2},{3,5},{6,7},{8,10},{12,16}}; int[] newInterval1 = {4,8};
        System.out.println("Merged intervals are " + Arrays.deepToString(insertInterval57.insert(intervals1, newInterval1)));
    }
}


