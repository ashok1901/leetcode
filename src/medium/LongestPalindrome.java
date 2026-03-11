package medium;

public class LongestPalindrome {
    public String longestPalindrome(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        for(int index = 0; index < s.length(); index++) {
            for (int indexj = 0; indexj < s.length(); indexj++) {
                dp[index][indexj] = index == indexj ? true : false;
            }
        }

        // One lenght sub strings
        int[] ans = {0, 0};
        // Two length sub strings
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                ans[0] = i;
                ans[1] = i + 1;
            }
        }
        for (int diff = 2; diff < s.length(); diff++) {
            for (int i = 0; i < s.length() - diff; i++) {
                int j = i + diff;
                if (s.charAt(i) == s.charAt(j) && dp[i+1][j-1]) {
                    dp[i][j] = true;
                    ans[0] = i;
                    ans[1] = j;
                }
            }
        }

        return s.substring(ans[0], ans[1] + 1);
    }

    public static void main(String[] args) {
        LongestPalindrome longestPalindrome = new LongestPalindrome();
        System.out.println("LongestPalindrome = " + longestPalindrome.longestPalindrome("babad"));
        System.out.println("LongestPalindrome = " + longestPalindrome.longestPalindrome("cbbd"));

    }
}


