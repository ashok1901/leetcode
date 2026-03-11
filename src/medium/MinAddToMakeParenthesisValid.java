package medium;

public class MinAddToMakeParenthesisValid {

    /**
     * Approach: Count the open parenthesis,
     * - reduce the counter on closing parenthesis
     * - if this counter goes negative, increase other main counter
     * - Add the open counter to the maincounter in the end
     * @param s
     * @return
     */
    public int minAddToMakeValid(String s) {
        int openCount = 0;
        int resultCounter = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                // Open parenthesis encountered
                openCount++;
            } else {
                // Close parenthesis encountered
                if (openCount > 0) {
                    openCount--;
                } else {
                    if (openCount == 0) {
                        resultCounter++;
                    } else {
                        resultCounter += Math.abs(openCount);
                        openCount = 0;
                    }
                }
            }
        }

        return resultCounter + Math.abs(openCount);
    }

    public static void main(String[] args) {
        MinAddToMakeParenthesisValid minAddToMakeParenthesisValid = new MinAddToMakeParenthesisValid();
        System.out.println("Min add parenthesis " + minAddToMakeParenthesisValid.minAddToMakeValid("())"));
        System.out.println("Min add parenthesis " + minAddToMakeParenthesisValid.minAddToMakeValid(")))"));
        System.out.println("Min add parenthesis " + minAddToMakeParenthesisValid.minAddToMakeValid("((())"));
        System.out.println("Min add parenthesis " + minAddToMakeParenthesisValid.minAddToMakeValid("((()))"));
    }
}


