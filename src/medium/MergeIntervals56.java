package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals56 {

    /**
     * Approach: Sort intervals by start time then use two pointers to merge the intervals
     *  - Add first interval to the result
     *  - Start iterating from 2nd interval
     *      - if there is overlap then merge these two and add to the result.
     *      - else move the iterator
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][];
        }
        List<List<Integer>> sortedIntervals = sortIntervalsByStart(intervals);
        List<List<Integer>> resultsList = new ArrayList<>();
        // Add first interval as it is. We will merge into it if needed.
        List<Integer> firstEntry = new ArrayList<>(Arrays.asList(sortedIntervals.get(0).get((0)),
                sortedIntervals.get(0).get(1)));
        resultsList.add(firstEntry);
        int resultIndex = 0;
        int intervalIndex = 1;
        while(intervalIndex < sortedIntervals.size()) {
            int intervalStart = sortedIntervals.get(intervalIndex).get(0);
            int intervalEnd = sortedIntervals.get(intervalIndex).get(1);
            int resultIndexStart = resultsList.get(resultIndex).get(0);
            int resultIndexEnd = resultsList.get(resultIndex).get(1);
            if (isOverlap(intervalStart, intervalEnd, resultIndexStart, resultIndexEnd)) {
                int mergedIntervalStart = Math.min(intervalStart, resultIndexStart);
                int mergedIntervalEnd = Math.max(intervalEnd, resultIndexEnd);
                resultsList.get(resultIndex).set(0, mergedIntervalStart);
                resultsList.get(resultIndex).set(1, mergedIntervalEnd);
                intervalIndex++;
            } else {
                // No overlap with the recent interval in the result so this will go to the result as it is.
                List<Integer> entry = new ArrayList<>();
                entry.add(intervalStart);entry.add(intervalEnd);
                resultsList.add(entry);
                resultIndex++;
                intervalIndex++;
            }
        }

        int[][] resultsAsArray = new int[resultsList.size()][2];
        int index = 0;
        for (List<Integer> interval : resultsList) {
            resultsAsArray[index][0] = interval.get(0);
            resultsAsArray[index][1] = interval.get(1);
            index++;
        }

        return resultsAsArray;
    }

    private boolean isOverlap(int firstStart, int firstEnd, int secStart, int secEnd) {
        return firstStart <= secStart && secStart <= firstEnd || secStart <= firstStart && firstStart <= secEnd;
    }


    private List<List<Integer>> sortIntervalsByStart(int[][] intervals) {
        List<List<Integer>> intervalsAsList = new ArrayList<>();
        for (int[] interval : intervals) {
            List<Integer> entry = new ArrayList<>();
            entry.add(interval[0]);entry.add(interval[1]);
            intervalsAsList.add(entry);
        }

        intervalsAsList.sort(new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return o1.get(0).compareTo(o2.get(0));
            }
        });

        return intervalsAsList;
    }

    public static void main(String[] args) {
        MergeIntervals56 mergeIntervals56 = new MergeIntervals56();
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        System.out.println("Merged intervals are " + Arrays.deepToString(mergeIntervals56.merge(intervals)));
        int[][] intervals1 = {{1,4},{4,5}};
        System.out.println("Merged intervals are " + Arrays.deepToString(mergeIntervals56.merge(intervals1)));
        int[][] intervals2 = {{4,7},{1,4}};
        System.out.println("Merged intervals are " + Arrays.deepToString(mergeIntervals56.merge(intervals2)));
    }
}


