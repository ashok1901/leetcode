package medium;

import java.util.Stack;

public class MinRemoveParenthesis1249 {

    /**
     * Approach : Stack based
     * Maintain a stack
     *  If regular char add it to the result string
     *  If ( then add it to the stack
     *  If ) then
     *      if Stack empty then have to drop it otherwise pop one from stack
     *
     * At last stack might have some ( these have to be removed from the tail of the resulting string
     * @param s
     * @return
     */
    public String minRemoveToMakeValid(String s) {
        String result = "";
        Stack<String> stack = new Stack<>();
        for (int charIndex = 0; charIndex < s.length(); charIndex++) {
            char ch = s.charAt(charIndex);
            if (ch != ')') {
                stack.push(String.valueOf(ch));
                continue;
            }
            // Now it's close parenthesis
            if (stack.isEmpty()) {
                // We have to drop this non-matching parenthesis.
                continue;
            }
            // Try to find the matching open parenthesis
            String tmpString = "";
            while (!stack.isEmpty() && !stack.peek().equals("(")) {
                tmpString = stack.pop() + tmpString;
            }
            if (!stack.isEmpty()) {
                String open = stack.pop();
                stack.push("(" + tmpString + ")");
            } else {
                // Have to drop the first unbalanced close parenthesis and concatenate the String so far.
                stack.push(tmpString);
            }
        }

        while(!stack.isEmpty()) {
            String top = stack.pop();
            if (top.equals("(")) {
                // Skip the unbalanced open parenthesis.
                continue;
            }
            result = top + result;
        }

        return result;
    }

    public static void main(String[] args) {
        MinRemoveParenthesis1249 minRemoveParenthesis1249 = new MinRemoveParenthesis1249();
        System.out.println("Min remove string " + minRemoveParenthesis1249.minRemoveToMakeValid("lee(t(c)o)de)"));
        System.out.println("Min remove string " + minRemoveParenthesis1249.minRemoveToMakeValid("a)b(c)d"));
        System.out.println("Min remove string " + minRemoveParenthesis1249.minRemoveToMakeValid("))(("));
    }
}


