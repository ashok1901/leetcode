package easy;

public class ValidPalindromeII {

    public boolean validPalindrome(String s) {
        return isPalin(s, 0, s.length() - 1, false);
    }

    private boolean isPalin(String s, int start, int end, boolean isOneExemptionUsed) {
        if (s == null || s.length() == 1) {
            return true;
        }
        if (start >= end) {
            return true;
        }
        char startChar = s.charAt(start);
        char endChar = s.charAt(end);
        if (startChar == endChar) {
            return isPalin(s, start + 1, end - 1, isOneExemptionUsed);
        } else {
            if (isOneExemptionUsed) {
                return false;
            }
            return isPalin(s, start + 1, end, true) ||
                    isPalin(s, start, end - 1, true);
        }
    }

    public static void main(String[] args) {
        ValidPalindromeII validPalindromeII = new ValidPalindromeII();
        System.out.println("Is Palin " + validPalindromeII.validPalindrome("abcrcbba"));
    }
}


