package medium;

public class ValidParenthesis678 {
    /**
     * Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.
     *
     * The following rules define a valid string:
     *
     * Any left parenthesis '(' must have a corresponding right parenthesis ')'.
     * Any right parenthesis ')' must have a corresponding left parenthesis '('.
     * Left parenthesis '(' must go before the corresponding right parenthesis ')'.
     * '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".
     *
     * @param args
     */
    public boolean checkValidString(String s) {
        int n = s.length();
        int[][] memo = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                memo[i][j] = -1;   // Not evaluated yet.
            }
        }
        return checkRecursively(s, 0, s.length(), 0, memo);
    }

    /**
     * Time limit exceed.
     *  - DP !
     *  - Add Memoization
     *      memo[start][openSoFar] = -1, 0, 1   -1 means not evaluated yet, 0 means false, 1 means true
     * @param s
     * @param startIndex
     * @param endIndex
     * @param openSoFar
     * @return
     */
    private boolean checkRecursively(String s, int startIndex, int endIndex, int openSoFar, int[][] memo) {
        if (openSoFar < 0) {
            return false;
        }

        if (startIndex == endIndex) {
            return openSoFar == 0;
        }
        if (memo[startIndex][openSoFar] != -1) {
            return memo[startIndex][openSoFar] == 1;
        }

        char ch = s.charAt(startIndex);
        if (ch == '(') {
            boolean isValid = checkRecursively(s, startIndex + 1, endIndex, openSoFar + 1, memo);
            memo[startIndex][openSoFar] = isValid ? 1 : 0;
            return isValid;
        } else if (ch == ')') {
            boolean isValid = checkRecursively(s, startIndex + 1, endIndex,openSoFar - 1, memo);
            memo[startIndex][openSoFar] = isValid ? 1 : 0;
            return isValid;
        }
        // It means this is *, which can be treated as ( or ) or ""
        boolean isValid = checkRecursively(s, startIndex + 1, endIndex,openSoFar + 1, memo) ||  // Treating * as open
                checkRecursively(s, startIndex + 1, endIndex,openSoFar - 1, memo) || // Treating * as close
                checkRecursively(s, startIndex + 1, endIndex, openSoFar, memo); // // Treating * as ""
        memo[startIndex][openSoFar] = isValid ? 1 : 0;
        return isValid;
    }

    public static void main(String[] args) {
        ValidParenthesis678 validParenthesis678 = new ValidParenthesis678();
        System.out.println(validParenthesis678.checkValidString("()"));
        System.out.println(validParenthesis678.checkValidString("(*)"));
        System.out.println(validParenthesis678.checkValidString("())"));
        System.out.println(validParenthesis678.checkValidString("()*)"));
        System.out.println(validParenthesis678.checkValidString("(*))"));
        System.out.println(validParenthesis678.checkValidString("************************************************************"));
    }
}


