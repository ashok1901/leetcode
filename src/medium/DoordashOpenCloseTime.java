package medium;

import java.util.ArrayList;
import java.util.List;

public class DoordashOpenCloseTime {

    /**
     * Tokenize the time in string format to number format and then generate 5 mins intervals from
     * start to end time.
     * e.g: mon 10:45 am is tokenized to 11045
     * mon -> 1, tue -> 2, ..... sun -> 7
     *
     * - Validate the input
     * - Translate to 23 hours format depending on AM and PM
     * - Keep in mind about the rolling window of hour after 59th min, day after 23'th hour.
     *
     * @param args
     */
    public List<String> tokenize(String openTime, String closeTime) {
        int openTimeToken = tokenizeTime(openTime);
        int closeTimeToken = tokenizeTime(closeTime);
        System.out.println("Open Time : " + openTimeToken + " Closed Time : " + closeTimeToken);
        List<Integer> openTimeTokens = generateIntervals(openTimeToken, closeTimeToken);
        List<String> result = new ArrayList<>();
        for (Integer i : openTimeTokens) {
            result.add(String.valueOf(i.intValue()));
        }

        return result;
    }

    private List<Integer> generateIntervals(int startTime, int closeTime) {
        int tempStart = startTime;
        List<Integer> intervals = new ArrayList<>();
        while (tempStart <= closeTime) {
            intervals.add(tempStart);
            tempStart = nextToken(tempStart, 5);
        }

        return intervals;
    }

    private int nextToken(int start, int intervalSize) {
        int minPart = start%100;
        if (minPart + intervalSize < 60) {
            return start + intervalSize;
        }

        int hourPart = (start/100)%100;
        int dayPart = start/10000;
        if (hourPart + 1 < 24) {
            int hour = hourPart + 1;
            int min = (minPart + intervalSize)%60;
            int hourminPart = hour * 100 + min;
            return dayPart*10000 + hourminPart;
        }

        if (dayPart + 1 < 8) {
            int day = dayPart + 1;
            int hour = 0;
            int min = (minPart + intervalSize)%60;
            return day*10000 + hour*100 + min;
        }

        int day = 1;
        int hour = 0;
        int min = (minPart + intervalSize)%60;
        return day*10000 + hour*100 + min;
    }

    /**
     * Expected example input format: mon 10:45 am
     * Expected example output : 11045
     */
    private int tokenizeTime(String timeStr) {
        if (timeStr == null || timeStr.length() != 12) {
            throw new IllegalArgumentException("Invalid time string " + timeStr);
        }

        String[] parts = timeStr.split(" ");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid time string " + timeStr);
        }

        int dayToken = parseDayToken(parts[0]);
        boolean isAM = isAM(parts[2]);
        int hourMinToken = parseTimeToken(parts[1], isAM);

        return dayToken * 10000 + hourMinToken;
    }

    private int parseTimeToken(String timeStr, boolean isAM) {
        if (timeStr == null || timeStr.length() != 5) {
            throw new IllegalArgumentException("Invalid time string " + timeStr);
        }
        String[] parts = timeStr.split(":");
        if (parts.length != 2 || !Character.isDigit(parts[0].charAt(0)) || !Character.isDigit(parts[0].charAt(1))) {
            throw new IllegalArgumentException("Invalid time string " + timeStr);
        }
        int hour = Integer.parseInt(parts[0]);
        if (isAM && hour >= 12) {
            throw new IllegalArgumentException("Invalid time string " + timeStr);
        }

        if (!isAM && hour > 12) {
            throw new IllegalArgumentException("Invalid time string " + timeStr);
        }

        if (!isAM) {
            hour = hour != 12 ? hour + 12 : hour;
        }

        if (parts.length != 2 || !Character.isDigit(parts[1].charAt(0)) || !Character.isDigit(parts[1].charAt(1))) {
            throw new IllegalArgumentException("Invalid time string " + timeStr);
        }
        int minute = Integer.parseInt(parts[1]);

        return hour * 100 + minute;
    }

    private boolean isAM(String amPmPart) {
        if ("am".equals(amPmPart)) {
            return true;
        } else  if ("pm".equals(amPmPart)) {
            return false;
        }

        throw new IllegalArgumentException("Invalid time string " + amPmPart);
    }

    private int parseDayToken(String part) {
        switch (part) {
            case "mon": return 1;
            case "tue": return 2;
            case "wed": return 3;
            case "thu": return 4;
            case "fri": return 5;
            case "sat": return 6;
            case "sun": return 7;
            default: throw new IllegalArgumentException("Invalid time string " + part);
        }
    }

    public static void main(String[] args) {
        DoordashOpenCloseTime doordash = new DoordashOpenCloseTime();
        System.out.println(doordash.tokenize("mon 10:45 am", "mon 12:01 pm"));
    }
}


