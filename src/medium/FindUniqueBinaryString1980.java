package medium;

import java.util.HashSet;
import java.util.Set;

public class FindUniqueBinaryString1980 {
    public String findDifferentBinaryString(String[] nums) {
        Set<String> uniqueGiveStrings = buildMap(nums);
        return uniqueStringRecusrively(uniqueGiveStrings, nums.length, 0, "");
    }

    private String uniqueStringRecusrively(Set<String> uniqueGiveStrings, int maxLen, int lenSoFar, String str) {
        if (lenSoFar == maxLen) {
            return !uniqueGiveStrings.contains(str) ? str : null;
        }
        String str1 = uniqueStringRecusrively(uniqueGiveStrings, maxLen, lenSoFar + 1, str + "0");
        if (str1 != null) {
            return str1;
        }
        // Go to str2 only if str1 does not give result
        String str2 = uniqueStringRecusrively(uniqueGiveStrings, maxLen, lenSoFar + 1, str + "1");
        return str2;
    }

    private Set<String> buildMap(String[] nums) {
        Set<String> uniqueGiveStrings = new HashSet<>();
        for (String s : nums) {
            uniqueGiveStrings.add(s);
        }
        return uniqueGiveStrings;
    }

    public static void main(String[] args) {
        FindUniqueBinaryString1980 findUniqueBinaryString1980 = new FindUniqueBinaryString1980();
        String[] nums = {"01","10"};
        System.out.println(findUniqueBinaryString1980.findDifferentBinaryString(nums));
        String[] nums1 = {"00","01"};
        System.out.println(findUniqueBinaryString1980.findDifferentBinaryString(nums1));
        String[] nums2 = {"111","011","001"};
        System.out.println(findUniqueBinaryString1980.findDifferentBinaryString(nums2));

    }
}


