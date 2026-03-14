package misc;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AverageSessionTime {
    public Map<String, Double> computeAverageSessionTime(String filePath) throws IOException {
        // File contents are CSV like below
        // user_id, session_time,session_action (Open|Close)
        Map<String, Integer> countPerUser = new HashMap<>();
        Map<String, Long> sumPerUser = new HashMap<>();
        Map<String, List<Long>> openSessionsPerUser = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("session_action")) {
                    // Skip the header line
                    continue;
                }

                String[] parts = line.split(",");
                String userId = parts[0];
                int time = Integer.valueOf(parts[1].trim());
                String sessionType = parts[2].trim();
                if (sessionType.contains("close")) {
                    List<Long> openedSessionsForUser = openSessionsPerUser.get(userId);
                    if (openedSessionsForUser == null || openedSessionsForUser.isEmpty()) {
                        throw new IllegalArgumentException("Session closed even before opening the session " + userId);
                    }

                    // Remove the last one to close this session. Assuming LIFO session pattern
                    Long startTime = openedSessionsForUser.remove(openedSessionsForUser.size() - 1);
                    long sum = sumPerUser.getOrDefault(userId, 0L) + (time - startTime);
                    sumPerUser.put(userId, sum);
                    countPerUser.put(userId, countPerUser.getOrDefault(userId, 0) + 1);
                } else {
                    // New session is opened.
                    List<Long> sessions = openSessionsPerUser.getOrDefault(userId, new ArrayList<>());
                    sessions.add((long)time);
                    openSessionsPerUser.put(userId, sessions);
                }
            }
        }

        Map<String, Double> averageSessionTime = new HashMap<>();
        // Now find the average
        for (String user : countPerUser.keySet()) {
            int count = countPerUser.get(user);
            long sum = sumPerUser.get(user);
            double avg = (double)sum / count;
            averageSessionTime.put(user, avg);
        }

        return averageSessionTime;
    }

    public static void main(String[] args) {
        String testFilePath = "/Users/ashok/Desktop/Projects/LeetCode/src/misc/sessiondata.csv";
        AverageSessionTime averageSessionTime = new AverageSessionTime();
        try {
            Map<String, Double> avgSessionTime = averageSessionTime.computeAverageSessionTime(testFilePath);
            System.out.println("Average session time: " + avgSessionTime.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


