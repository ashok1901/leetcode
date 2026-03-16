package easy;

import java.util.HashMap;
import java.util.Map;

public class ValidAnagrams {

    public boolean isAnagram(String s, String t) {
        Map<Character, Integer> sMap = stringToCharCountMap(s);
        Map<Character, Integer> tMap = stringToCharCountMap(t);

        return sMap.equals(tMap);
    }

    private Map<Character, Integer> stringToCharCountMap(String s){
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }

        return map;
    }

    public static void main(String[] args) {
        ValidAnagrams validAnagrams = new ValidAnagrams();
        System.out.println(validAnagrams.isAnagram("anagram", "nagaram"));
        System.out.println(validAnagrams.isAnagram("rat", "car"));
    }
}


