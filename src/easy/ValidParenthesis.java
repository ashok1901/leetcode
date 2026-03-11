package easy;

import java.util.Stack;

public class ValidParenthesis {

    /**
     * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
     *
     * An input string is valid if:
     *
     * Open brackets must be closed by the same type of brackets.
     * Open brackets must be closed in the correct order.
     * Every close bracket has a corresponding open bracket of the same type.
     * @param args
     */
    public boolean isValid(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c : chars) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if (c == ')' && top == '(') {
                    continue;
                }
                if (c == ']' && top == '[') {
                    continue;
                }

                if (c == '}' && top == '{') {
                    continue;
                }
                return false;
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
    }
}


