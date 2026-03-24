package hard;

import java.util.*;

public class MaxProfitJobScheduling1235 {

    private class StartEndTime {
        int startTime;
        int endTime;
        public StartEndTime(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

    /**
     * Approach: Brute force
     * - Try each job which starts after the last job and build the chain.
     * - Whatever chain gives more profit that is the profit.
     * @param startTime
     * @param endTime
     * @param profit
     * @return
     */
    private int globalMaxProfit = Integer.MIN_VALUE;
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        List<StartEndTime> jobTimings = new ArrayList<>();
        for (int i = 0; i < startTime.length; i++) {
            jobTimings.add(new StartEndTime(startTime[i], endTime[i]));
        }
        Collections.sort(jobTimings,
                (o1, o2) -> o1.startTime - o2.startTime);

        return jobSchedulingRecursive(jobTimings, -1, profit, 0, 0);
    }

    /**
     * Assumption : jobs are sorted with start time.
     * Optimization possible : There are so many repeating calculations, Leetcode will run into time limit exceed.
     * So better to try DP or memoization approach.
     * @param startTime
     * @param endTime
     * @param lastJobEndTime
     * @param profit
     * @param profitSoFar
     * @param startIndex
     * @return
     */
    private int jobSchedulingRecursive(List<StartEndTime> jobTimings, int lastJobEndTime, int[] profit, int profitSoFar, int startIndex) {
        if (startIndex >= jobTimings.size()) {
            return profitSoFar;
        }

        int localMax = Integer.MIN_VALUE;
        for (int i = startIndex; i < jobTimings.size(); i++) {
            int thisStartTime = jobTimings.get(i).startTime;
            int thisEndTime = jobTimings.get(i).endTime;
            if (thisStartTime >= lastJobEndTime) {
                // If this job is starting after the last scheduled job only then we can consider it.
                // We might be more profit by not taking this one but later one so have to check both routes.
                // This makes it unoptimized as many duplicate calls.

                // Case-1 : Just bypass this job and consider the case that later one will give better results.
                int max = Math.max(jobSchedulingRecursive(jobTimings, thisEndTime, profit, profitSoFar + profit[i], i + 1),
                               jobSchedulingRecursive(jobTimings, lastJobEndTime, profit, profitSoFar, i + 1));
                localMax = Math.max(max, localMax);
            }
        }

        return Math.max(localMax, profitSoFar);
    }

    public static void main(String[] args) {
        MaxProfitJobScheduling1235 maxProfitJobScheduling1235 = new MaxProfitJobScheduling1235();
//        int[] startTime = {1,2,3,3}, endTime = {3,4,5,6}, profit = {50,10,40,70};
//        System.out.println(maxProfitJobScheduling1235.jobScheduling(startTime, endTime, profit));
//
//        int[] startTime1 = {1,2,3,4,6}, endTime1 = {3,5,10,6,9}, profit1 = {20,20,100,70,60};
//        System.out.println(maxProfitJobScheduling1235.jobScheduling(startTime1, endTime1, profit1));
//
//        int[] startTime2 = {1,1,1}, endTime2 = {2,3,4}, profit2 = {5,6,4};
//        System.out.println(maxProfitJobScheduling1235.jobScheduling(startTime2, endTime2, profit2));

        int[] startTime3 = {4,2,4,8,2}, endTime3 = {5,5,5,10,8}, profit3 = {1,2,8,10,4};
        System.out.println(maxProfitJobScheduling1235.jobScheduling(startTime3, endTime3, profit3));
    }
}


