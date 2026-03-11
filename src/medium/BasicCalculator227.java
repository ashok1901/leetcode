package medium;

import java.util.Stack;

public class BasicCalculator227 {

    public int calculate(String s) {
        String str = cleaniseTheSpaces(s);
        int startIndex = 0;
        Stack<Integer> operandsStack = new Stack<>();
        Stack<String> operatorStack = new Stack<>();
        while (startIndex < str.length()) {
            String nextEntry = parseNextEntry(str, startIndex);
            if (isOperator(nextEntry)) {
                if (operatorStack.isEmpty()) {
                    operatorStack.push(nextEntry);
                } else {
                    // Operate as per the precedence.
                    String thisOperator = nextEntry;
                    if (!operatorStack.isEmpty() && !isHigherPrecedence(thisOperator, operatorStack.peek())) {
                        String topOperator = operatorStack.pop();
                        // Operate it and push back to the stack
                        Integer operand2 = operandsStack.pop();
                        Integer operand1 = operandsStack.pop();
                        int result = operate(operand1, operand2, topOperator);
                        operatorStack.push(thisOperator);
                        operandsStack.push(result);
                    } else {
                        // Just push this operator to the stack
                        operatorStack.push(nextEntry);
                    }
                }

            } else {
                operandsStack.push(parseToInt(nextEntry));
            }
            startIndex = startIndex + nextEntry.length();
        }

        // Here some operators might be in the operator stack and some operands in the operands stack. Operate them now.
        while (!operatorStack.isEmpty()) {
            String operator = operatorStack.pop();
            // TODO: check if we need to check if operator stack is empty here. Should not be if given input is not
            // malformed and no code bug :-)
            Integer operand2 = operandsStack.pop();
            Integer operand1 = operandsStack.pop();
            int result = operate(operand1, operand2, operator);
            operandsStack.push(result);
        }

        return operandsStack.peek();
    }

    private int operate(int operand1, int operand2, String operator) {
        switch (operator) {
            case "+" :
                return operand1 + operand2;
            case "-" :
                return operand1 - operand2;
            case "*" :
                return operand1 * operand2;
            case "/" :
                // TODO: Double check if operand2 = 0 need to be checked ??
                return operand1 / operand2;
            default:
                throw new RuntimeException("Unknown operator " + operator);
        }
    }

    private boolean isHigherPrecedence(String thisOperator, String earlierOperator) {
//        if (thisOperator.equals("*") || thisOperator.equals("/")) {
//            if (earlierOperator.equals("+") || earlierOperator.equals("-")) {
//                return true;
//            } else {
//                return false;
//            }
//        } else {
//            return false;
//        }
        return (thisOperator.equals("*") || thisOperator.equals("/")) &&
                (earlierOperator.equals("+") || earlierOperator.equals("-"));
    }

    private int parseToInt(String intStr) {
        return Integer.valueOf(intStr);
    }

    private String cleaniseTheSpaces(String s) {
        String result = "";
        for (char ch : s.toCharArray()) {
            if (ch == ' ') {
                continue;
            }
            result = result + ch;
        }
        return result;
    }

    private String parseNextEntry(String s, int startIndex) {
        char charAt = s.charAt(startIndex);
        if (isOperator(charAt)) {
            return String.valueOf(charAt);
        }
        String result = "";
        int tempIndex = startIndex;
        while (tempIndex < s.length() && !isOperator(s.charAt(tempIndex))) {
            result = result + s.charAt(tempIndex);
            tempIndex++;
        }

        return result;
    }

    private boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    private boolean isOperator(String st) {
        return st.equals("+") || st.equals("-") || st.equals("*") || st.equals("/");
    }


    public static void main(String[] args) {
        BasicCalculator227 basicCalculator227 = new BasicCalculator227();
        System.out.println("Value is " + basicCalculator227.calculate("3 +2 *2"));
        System.out.println("Value is " + basicCalculator227.calculate("3/2 "));
        System.out.println("Value is " + basicCalculator227.calculate(" 3+5 / 2 "));
        System.out.println("Value is " + basicCalculator227.calculate("0-2147483647"));
        System.out.println("Value is " + basicCalculator227.calculate("2+3-4"));
    }
}


