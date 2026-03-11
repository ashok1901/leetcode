package medium;

import java.util.HashMap;

public class LongestSubstringWithoutRepeat {

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int maxLength = 1;
        int right = 0, left = 0;
        HashMap<Character, Integer> lastSeenIndexMap = new HashMap<Character, Integer>();
        while (right < s.length()) {
            Character rightChar = s.charAt(right);
            int rightIndex = right;
            if (lastSeenIndexMap.containsKey(rightChar) && lastSeenIndexMap.get(rightChar) >= left) {
                int lastSeenIndex = lastSeenIndexMap.get(rightChar);
                left = lastSeenIndex + 1;
            }
            maxLength = Math.max(maxLength, right - left + 1);
            lastSeenIndexMap.put(rightChar, rightIndex);
            right++;
        }

        return maxLength;
    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeat longestSubstringWithoutRepeat = new LongestSubstringWithoutRepeat();
        int longestSubStr = longestSubstringWithoutRepeat.lengthOfLongestSubstring("abcabcbb");
        System.out.println("Longest substring is : " + longestSubStr);
    }
}


