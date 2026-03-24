package medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class DecodeString394 {
    /**
     * Test case failing :-(
     * @param s
     * @return
     */

    public String decodeStringStackBased(String s) {
        int currIndex = 0;
        Stack<String> decodedStack = new Stack<>();
        while (currIndex < s.length()) {
            String currChar = s.substring(currIndex, currIndex + 1);
            if (currChar.equals("]")) {
                // First keep popping until we get [ this is string to repeat
                StringBuilder sb = new StringBuilder();
                while (!decodedStack.isEmpty() && !decodedStack.peek().equals("[")) {
                    String decodedStr = decodedStack.pop();
                    sb.append(decodedStr);
                }

                String repeatingStr = sb.reverse().toString();

                if (decodedStack.isEmpty()) {
                    // This should not happen ideally if encoded string is valid.
                    throw new RuntimeException("Stack empty without [ symbol.");
                }

                // Pop the [ and ignore it
                decodedStack.pop();

                // Then keep popping as long digits, this is times above string is repeated.
                StringBuilder digitsStrbldr = new StringBuilder();
                while(!decodedStack.isEmpty() && decodedStack.peek().chars().allMatch(Character::isDigit)) {
                    digitsStrbldr.append(decodedStack.pop());
                }

                String digitsStr = digitsStrbldr.reverse().toString();
                int repeatCount = Integer.parseInt(digitsStr);

                // Do concatenation
                StringBuilder res = new StringBuilder();
                for (int i = 0; i < repeatCount; i++) {
                    res.append(repeatingStr);
                }

                // Push again the stack
                decodedStack.push(res.toString());
            } else {
//                String elementToPush = currChar + "";
//                if (!decodedStack.isEmpty()) {
//                    String stackTop = decodedStack.peek();
//                    if (stackTop.chars().allMatch(Character::isAlphabetic)) {
//                        elementToPush = decodedStack.pop() + currChar;
//                    }
//                }
                decodedStack.push(currChar);
            }
            currIndex++;
        }

        // Stack might have more elements we need to join them in reverse order
        List<String> sb = new ArrayList<String>();
        while (!decodedStack.isEmpty()) {
            sb.add(decodedStack.pop());
        }
        Collections.reverse(sb);
        return String.join("", sb);
    }

    /**
     * Input: s = "3[a]2[bc]"
     * Output: "aaabcbc"
     *
     * Apporach: Iterative
     *
     * Note: String might be nested decoded also, below logic doesn't handle that case. To handle nested case we
     * need to use stack with logic like
     * - Keep pushing chars into stack until ] is found.
     * - On [, keep poping until ] and make it as a string which will repeat say r
     * - The keep poping until you find [, the poped string would be <fewchars><few digits>
     *    - Push the stack a string like frewChars + r few digits times. Push it back to stack.
     *  - The final result would be in the stack.
     * @param s
     * @return
     */
    public String decodeString(String s) {
        int currIndex = 0;
        StringBuilder sb = new StringBuilder();
        while (currIndex < s.length()) {
            String digits = parseDigits(s, currIndex);
            String repeat = parseRepeat(s, currIndex + digits.length());
            int repeatCount = Integer.parseInt(digits);

            // Joing logic here
            for (int i = 0; i < repeatCount; i++) {
                sb.append(repeat);
            }

            currIndex = currIndex + digits.length() + repeat.length() + 2; // 2 is corresponding to [, ]
        }

        return sb.toString();
    }

    private String parseDigits(String s, int currIndex) {
        StringBuilder sb = new StringBuilder();
        while (currIndex < s.length() && Character.isDigit(s.charAt(currIndex))) {
            sb.append(s.charAt(currIndex));
            currIndex++;
        }

        return sb.toString();
    }

    private String parseRepeat(String s, int currIndex) {
        StringBuilder sb = new StringBuilder();
        currIndex++; // Skip the starting [
        while (currIndex < s.length() && s.charAt(currIndex) != ']') {
            sb.append(s.charAt(currIndex));
            currIndex++;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        DecodeString394 decodeString394 = new DecodeString394();
//        System.out.println(decodeString394.decodeStringStackBased("3[a]2[bc]"));
//        System.out.println(decodeString394.decodeStringStackBased("3[a2[c]]"));
//        System.out.println(decodeString394.decodeStringStackBased("2[abc]3[cd]ef"));
//        System.out.println(decodeString394.decodeStringStackBased("3[z]2[2[y]pq4[2[jk]e1[f]]]ef"));
        System.out.println(decodeString394.decodeStringStackBased("2[2[y]pq4[2[jk]e1[f]]]"));
        System.out.println(decodeString394.decodeStringStackBased("4[2[jk]e1[f]]"));
    }
}
