package medium;

import java.util.*;

public class GroupShiftedStrings249 {

    /**
     * Approach:
     * - Group by length first
     * - In each group, translate strings such that they start with a, keep this in a map of transformedString -> List of orginal string
     * - The answer would be collection of all the lists
     *
     * @param strings
     * @return
     */
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> results = new ArrayList<>();
        Map<Integer, List<String>> groupByLength = groupByLength(strings);
        for (List<String> listByLength : groupByLength.values()) {
            Map<String, List<String>> groupByTransformed = transformedToOriginalString(listByLength);
            for(List<String> transformedGroup : groupByTransformed.values()) {
                results.add(transformedGroup);
            }
        }

        return  results;
    }

    private Map<String, List<String>> transformedToOriginalString(List<String> strings) {
        Map<String, List<String>> groupByTransform = new HashMap<>();
        for (String str : strings) {
            String transformedStr = transformToStartWithA(str);
            // Transformed -> original string
            // Like abc and bcd will go to the same list like abc -> {abc, bcd} as these are transformable to abc
            if (!groupByTransform.containsKey(transformedStr)) {
                groupByTransform.put(transformedStr, new ArrayList<>());
            }
            groupByTransform.get(transformedStr).add(str);
        }

        return groupByTransform;
    }

    public String transformToStartWithA(String str) {
        String result = "";
        // Assuming blanks and null is already checked.
        char firstChar = str.charAt(0);
        int diff = 'a' - firstChar;

        for (char ch : str.toCharArray()) {
            int origPosition = ch - 'a';
            int newPosition = (origPosition + diff) % 26;
            if (newPosition < 0) {
                newPosition = newPosition + 26;
            }
            char shiftedCharacter = (char) ('a' + newPosition);
            result = result + shiftedCharacter;
        }
        return result;
    }

    private Map<Integer, List<String>> groupByLength(String[] strings) {
        Map<Integer, List<String>> groupByLength = new HashMap<>();
        for (String str : strings) {
            int len = str.length();
            if (!groupByLength.containsKey(len)) {
                groupByLength.put(len, new ArrayList<>());
            }
            groupByLength.get(len).add(str);
        }
        return groupByLength;
    }

    public static void main(String[] args) {
        GroupShiftedStrings249 groupShiftedStrings249 = new GroupShiftedStrings249();
//        System.out.println("Shifted : " + groupShiftedStrings249.transformToStartWithA("bcd"));
        String[] strings = {"abc","bcd","acef","xyz","az","ba","a","z"};
        System.out.println("Shifted group is " + groupShiftedStrings249.groupStrings(strings));

    }
}


