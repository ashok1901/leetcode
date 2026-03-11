package easy;

import java.util.Arrays;

public class MeetingRooms {

    /**
     * Given an array of meeting time intervals where intervals[i] = [starti, endi], determine if a person could attend all meetings.
     * @param args
     */
    public boolean canAttendMeetings(int[][] intervals) {
        sortTheIntervalsByStartTime(intervals);
        for (int index = 1; index < intervals.length; index++) {
            int startOfThis = intervals[index][0];
            int startOfLast = intervals[index - 1][0];
            int endOfLast = intervals[index - 1][1];

            if (startOfThis >= startOfLast && startOfThis < endOfLast) {
                // Overlap
                return false;
            }
        }
        return true;
    }

    private void sortTheIntervalsByStartTime(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
    }

    public static void main(String[] args) {
        MeetingRooms meetingRooms = new MeetingRooms();
//        int[][] intervals = {{7,10},{2,4}};
        int[][] intervals = {{0,30},{5,10},{15,20}};
        System.out.println("can attend " + meetingRooms.canAttendMeetings(intervals));
    }
}


