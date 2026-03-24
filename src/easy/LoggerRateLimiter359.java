package easy;

import java.util.HashMap;
import java.util.Map;

public class LoggerRateLimiter359 {

    /**
     * Approach: Use hashmap to keep record of last printed time.
     * - If message was printed last time within in 10 secs then skip it otherwise allow prining and update the map.
     */
    private Map<String, Integer> allowedMessages = new HashMap<>();

    public LoggerRateLimiter359() {

    }

    public boolean shouldPrintMessage(int timestamp, String message) {
        if (!allowedMessages.containsKey(message)) {
            // This is first occurrence of this message. So allow it.
            allowedMessages.put(message, timestamp);
            return true;
        }

        // Otherwise check when was it printed last time.
        int lastPrintTime = allowedMessages.get(message);
        if (lastPrintTime <= timestamp - 10) {
            allowedMessages.put(message, timestamp);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        LoggerRateLimiter359 loggerRateLimiter359 = new LoggerRateLimiter359();
        System.out.println(loggerRateLimiter359.shouldPrintMessage(1, "foo"));
        System.out.println(loggerRateLimiter359.shouldPrintMessage(2, "bar"));
        System.out.println(loggerRateLimiter359.shouldPrintMessage(3, "foo"));
    }
}


