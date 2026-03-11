package medium;

public class CountPalindrominSubstrings {

    /**
     * Approach : Dynamic programming
     * - Single length strings are always palindrome
     * - Two length strings are palindrome only if both chars are same
     * - Three and more length strings are palindrome if first and last char are same AND inner string is palindrome.
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        int count = 0;
        int strLen = s.length();
        boolean[][] isPalindrome = new boolean[strLen][strLen];
        // Single char strings are always palindrome
        // i.e i = j palindrome
        for (int i = 0; i < strLen; i++) {
            isPalindrome[i][i] = true;
            count++;
        }

        // Two chars strings
        for (int i = 1; i < strLen; i++) {
//            if (s.charAt(i-1) == s.charAt(i)) {
//                isPalindrome[i-1][i] = true;
//            } else {
//                isPalindrome[i-1][i] = false;
//            }

            // A little fancy
            isPalindrome[i-1][i] = s.charAt(i-1) == s.charAt(i);
            if (isPalindrome[i-1][i]) {
                count++;
            }
        }

        // Three and more length chars
        for (int len = 3; len <= strLen; ++len) {
            for (int i = 0, j = i + len - 1; j < strLen; ++i,++j) {
                isPalindrome[i][j] = isPalindrome[i+1][j-1] && (s.charAt(i) == s.charAt(j));
                if (isPalindrome[i][j]) {
                    count++;
                }
            }
        }

        // Now count how many true values we have in the isPalindrome matrix.
//        int count = 0;
//        for (int i = 0; i < strLen; i++) {
//            for (int j = i; j < strLen; j++) {
//                if (isPalindrome[i][j]) {
//                    count++;
//                }
//            }
//        }

        return count;
    }

    public static void main(String[] args) {
        CountPalindrominSubstrings countPalindrominSubstrings = new CountPalindrominSubstrings();
        String s = "aba";
        System.out.println("Palindrome count is " + countPalindrominSubstrings.countSubstrings(s));
        System.out.println("Palindrome count is " + countPalindrominSubstrings.countSubstrings("abc"));
        System.out.println("Palindrome count is " + countPalindrominSubstrings.countSubstrings("aaa"));
        System.out.println("Palindrome count is " + countPalindrominSubstrings.countSubstrings("aaaaa"));
    }
}


