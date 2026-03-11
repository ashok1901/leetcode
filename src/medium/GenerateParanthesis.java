package medium;

import java.util.ArrayList;
import java.util.List;

// LeetCode - 22
public class GenerateParanthesis {
    private static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        recurseToAddRightParanthesis(0, 0, "", res, n);
        return res;
    }

    /**
     * @param open : Counter of open paraethesis added so far
     * @param close : Counter of close parenthesis added so far
     * @param intermediate : Intermediate string looking so far
     * @param result : Overall result
     * @param n : Given n
     */
    private static void recurseToAddRightParanthesis(int open, int close, String intermediate, List<String> result, int n) {
        if (intermediate.length() == 2*n) {
            result.add(intermediate);return;
        }
        if (open < n) {
            recurseToAddRightParanthesis(open + 1, close, intermediate + "(", result, n);
        }
        if (close < open) {
            // Room to add one close parenthesis
            recurseToAddRightParanthesis(open, close + 1, intermediate + ")", result, n);
        }
    }

    public static void main(String[] args) {
        int n = 3;
        System.out.println("Result " + generateParenthesis(n).toString());
    }
}

