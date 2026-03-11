package medium;

import java.util.*;

public class GroupAnagrams49 {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> annagramsMaps = new HashMap<>();
        for(String str : strs) {
            String sortedStr = sortString(str);
            if (!annagramsMaps.containsKey(sortedStr)) {
                annagramsMaps.put(sortedStr, new ArrayList<>());
            }
            annagramsMaps.get(sortedStr).add(str);
        }

        List<List<String>> result = new ArrayList<>(annagramsMaps.values());
        return result;
    }

    private String sortString(String str) {
        char[] charsArray = str.toCharArray();
        Arrays.sort(charsArray);
        return new String(charsArray);
    }

    public static void main(String[] args) {
        String[] strs = {"eat","tea","tan","ate","nat","bat"};
        System.out.println("Annagram groups are " + new GroupAnagrams49().groupAnagrams(strs));
        // Some other test
        int[][] intervals = {{1,2}, {1,5}};
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
    }
}


