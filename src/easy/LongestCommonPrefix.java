package easy;

public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        int currentCharIndex = -1;
        boolean isDone = false;
        while (!isDone) {
            currentCharIndex += 1;
            if (!isValidIndexForString(strs[0], currentCharIndex)) {
                isDone = true; currentCharIndex -= 1; continue;
            }
            char expectedChar = strs[0].charAt(currentCharIndex);
            for(int strIndex = 1; strIndex < strs.length; strIndex++) {
                String thisString = strs[strIndex];
                if (!isValidIndexForString(thisString, currentCharIndex)) {
                    isDone = true; currentCharIndex -= 1; break;
                }
                if (thisString.charAt(currentCharIndex) != expectedChar) {
                    isDone = true; currentCharIndex -= 1; break;
                }
            }
        }

        return currentCharIndex < 0 ? "" : strs[0].substring(0, currentCharIndex + 1);
    }

    private boolean isValidIndexForString(String str, int index) {
        return index < str.length();
    }

    public static void main(String[] args) {
        LongestCommonPrefix longestCommonPrefix = new LongestCommonPrefix();
        String[] strs = {"flower","flow","flight"};
        System.out.println("Longest prefix is " + longestCommonPrefix.longestCommonPrefix(strs));
        String[] strs1 = {"dog","racecar","car"};
        System.out.println("Longest prefix is " + longestCommonPrefix.longestCommonPrefix(strs1));
    }
}


