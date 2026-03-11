package medium;

import java.util.HashMap;
import java.util.Map;

public class CustomSortString {

    /**
     * Bucket sort of algo with
     * Time : O(n)
     * Space : O(n)
     * @param order
     * @param s
     * @return
     */
    public String customSortString(String order, String s) {
        Map<Character, Integer> frequencyMap = computerequencyMap(s);
        String result = "";
        for (char ch : order.toCharArray()) {
            int freq = frequencyMap.getOrDefault(ch, 0);
            for (int index = 0; index < freq; index++) {
                result = result + ch;
            }
            frequencyMap.remove(ch);
        }
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            Character ch = entry.getKey();
            Integer freq = entry.getValue();
            for (int index = 0; index < freq; index++) {
                result = result + ch;
            }
        }

        return result;
    }

    private Map<Character, Integer> computerequencyMap(String str) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char ch : str.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }
        return freqMap;
    }

    public static void main(String[] args) {
        CustomSortString customSortString = new CustomSortString();
        String order = "cba", s = "abcd";
        System.out.println("Customer sort output " + customSortString.customSortString(order, s));

        String order1 = "bcafg", s1 = "abcd";
        System.out.println("Customer sort output " + customSortString.customSortString(order1, s1));
    }
}


