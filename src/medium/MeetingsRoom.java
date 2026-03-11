package medium;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MeetingsRoom {

    /**
     * Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], return the minimum number of conference rooms required.
     * @param args
     */
    public int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }

        int[] sortedStartTimes = parseStartTimesInIncreasingOlder(intervals);
        int[] sortedEndTimes = parseEndTimesInIncreasingOlder(intervals);
        int usedRooms = 0;
        int start = 0;
        int end = 0;
        while (start < sortedStartTimes.length) {
            if (sortedStartTimes[start] >= sortedEndTimes[end]) {
                usedRooms--;
                end++;
            }
            usedRooms++;
            start++;
        }
        return usedRooms;
    }

    private int[] parseStartTimesInIncreasingOlder(int[][] intervals) {
        int[] startTimes = IntStream.range(0, intervals.length).map( i -> intervals[i][0]).toArray();
        sortIntervalsByStart(startTimes);
        return startTimes;
    }

    private int[] parseEndTimesInIncreasingOlder(int[][] intervals) {
        int[] endTimes = IntStream.range(0, intervals.length).map( i -> intervals[i][1]).toArray();
        sortIntervalsByStart(endTimes);
        return endTimes;
    }

    private void sortIntervalsByStart(int[] array) {
        Arrays.sort(array);
    }

    public static void main(String[] args) {
        MeetingsRoom meetingRoom = new MeetingsRoom();
        int[][] intervals = {{0,30},{5,10},{15,20}};
        System.out.println("Min Rooms " + meetingRoom.minMeetingRooms(intervals));
    }
}


