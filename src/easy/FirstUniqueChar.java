package easy;

import java.util.HashMap;
import java.util.Map;

public class FirstUniqueChar {
    /**
     * Approach:
     * - Scan the string and keep the frequency in a map
     * - Scan again and return char index which has 1 frequency.
     *
     * T: O(n) n : length of the string
     * S: O(m) m: unique characters in the string
     *
     * @param s
     * @return
     */
    public int firstUniqChar(String s) {
        Map<Character, Integer> frequencyMap = buildFrequencyMap(s);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (frequencyMap.get(c) == 1) {
                return i;
            }
        }
        return -1;
    }

    private Map<Character, Integer> buildFrequencyMap(String s) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        return frequencyMap;
    }

    public static void main(String[] args) {
        FirstUniqueChar firstUniqueChar = new FirstUniqueChar();
        System.out.println(firstUniqueChar.firstUniqChar("leetcode"));
        System.out.println(firstUniqueChar.firstUniqChar("loveleetcode"));
        System.out.println(firstUniqueChar.firstUniqChar("aabb"));
    }
}


