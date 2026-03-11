package medium;

import java.util.HashMap;
import java.util.Map;

public class LongestBalancedSubstring3713 {
    /**
     * Approach : Brute force - Consider all the substrings
     * Leetcode also has the same answer
     * @param s
     * @return
     */
    public int longestBalanced(String s) {
        if (s == null || s.isBlank()) {
            return 0;
        }

        int len = s.length();
        int longestBalanced = 1;
        for (int i = 0; i < len; i++) {
            Map<Character, Integer> frequency = new HashMap<>();
            for (int j = i; j < len; j++) {
                char ch = s.charAt(j);
                frequency.put(ch, frequency.getOrDefault(ch, 0) + 1);
                if (isBalanced(frequency)) {
                    longestBalanced = Math.max(longestBalanced, j - i + 1);
                }
            }
        }
        return longestBalanced;
    }

    private boolean isBalanced(Map<Character, Integer> frequency) {
        if (frequency == null || frequency.size() == 0) {
            return true;
        }
        int expectedFreq = -1;
        for (Integer val : frequency.values()) {
            if (expectedFreq == -1) {
                expectedFreq = val;
                continue;
            }
            if (val != expectedFreq) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        LongestBalancedSubstring3713 longestBalancedSubstring3713 = new LongestBalancedSubstring3713();
        System.out.println("Longest balanced substring length : " + longestBalancedSubstring3713.longestBalanced("abbac"));
        System.out.println("Longest balanced substring length : " + longestBalancedSubstring3713.longestBalanced("zzabccy"));
        System.out.println("Longest balanced substring length : " + longestBalancedSubstring3713.longestBalanced("aba"));


    }
}


