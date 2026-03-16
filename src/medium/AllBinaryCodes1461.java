package medium;

import java.util.*;
import java.util.stream.Collectors;

public class AllBinaryCodes1461 {

    /**
     * Leetcode solution : Parse all substrings of size k and put them in a set. If set size grows uptp 2^k then true,
     * false otherwise
     *
     * T : O(n) n is length of the given string
     * S : O(2^k)
     *
     * @param s
     * @param k
     * @return
     */
    public boolean hasAllCodes(String s, int k) {
        Set<String> subStrings = new HashSet<>();
        for (int i = 0; i <= s.length() - k; i++) {
            int endIndex = i + k; // exclusive as below substring call expects exclusive
            String subString = s.substring(i, endIndex);
            subStrings.add(subString);
        }

        int expectedSize = (int)Math.pow(2, k);
        return subStrings.size() >= expectedSize;
    }

    /**
     * This is also leading to Time Limit Exceed
     *
     * @param s
     * @param k
     * @return
     */
    public boolean hasAllCodes2(String s, int k) {
        return buildCodeAndCheck(s, k, 0, "");
    }

    private boolean buildCodeAndCheck(String s, int k, int codeLength, String code) {
        if (codeLength == k) {
            // This is of the desired length. Check it in the given string
            return s.contains(code);
        }

        boolean zeroAppend = buildCodeAndCheck(s, k, codeLength + 1, code + "0");
        if (!zeroAppend) {
            // If false found then no need to check any more.
            return false;
        }
        boolean oneAppend = buildCodeAndCheck(s, k, codeLength + 1, code + "1");
        return oneAppend;
    }

    /**
     * Creating all binary codes and then checking all is leading to Time Limit Exceed.
     * @param s
     * @param k
     * @return
     */
    public boolean hasAllCodes1(String s, int k) {
        List<String> binaryCodes = generateBinaryCodes(k);
        for (String binaryCode : binaryCodes) {
            if (!s.contains(binaryCode)) {
                return false;
            }
        }
        return true;
    }

    private List<String> generateBinaryCodes(int k) {
        Queue<String> queue = new LinkedList<>();
        queue.add("0");queue.add("1");
        int count = 1;
        while (count < k) {
            int size = queue.size();
            int removedItems = 0;
            while (removedItems < size) {
                String s = queue.remove();
                queue.add(s + "0");
                queue.add(s + "1");
                removedItems++;
            }
            count++;
        }

        return queue.stream().collect(Collectors.toList());
    }

    public static void main(String[] args) {
        AllBinaryCodes1461 allBinaryCodes1461 = new AllBinaryCodes1461();
        System.out.println(allBinaryCodes1461.hasAllCodes("00110110", 2));
        System.out.println(allBinaryCodes1461.hasAllCodes("0110", 1));
        System.out.println(allBinaryCodes1461.hasAllCodes("0110", 2));
    }
}


