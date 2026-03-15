package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LetterCombinationOnPhone17 {

    /**
     * Approach : Recursive by considering each char
     * @param digits
     * @return
     */
    List<String> res = new ArrayList<>();
    public List<String> letterCombinations(String digits) {
        return buildLetterCombinations(digits);
    }

    private List<String> buildLetterCombinations(String digits) {
        List<String> combinations = new ArrayList<>();
        if (digits == null || digits.length() == 0)  {
            return combinations;
        }
        // Add the first char first
        char firstChar = digits.charAt(0);
        char[] correspondingLetters = getCharsForDigit(firstChar);
        for (char c : correspondingLetters) {
            combinations.add(String.valueOf(c));
        }

        // Now start from 2nd letter onwards.
        for (int i = 1; i < digits.length(); i++) {
            char digit = digits.charAt(i);
            correspondingLetters = getCharsForDigit(digit);


            // Remove the elements one by one and push them back after concatenation.
            int size = combinations.size();
            for (int combIndex = 0; combIndex < size; combIndex++) {
                String combination = combinations.get(combIndex);
                for (char c : correspondingLetters) {
                    combinations.add(combination + c);
                }
            }

            // Now remove the first size entrires
            // Better to build a new list and discard the old one unless have another trick
            // Back tracking solution might be more easy, try that also when time permits
            for (int combIndex = 0; combIndex < size; combIndex++) {
                combinations.remove(0);
            }
        }

        return combinations;
    }

    private char[] getCharsForDigit(char digitsChar) {
        switch (digitsChar) {
            case '2': return new char[]{'a', 'b', 'c'};
            case '3' : return new char[]{'d', 'e', 'f'};
            case '4' : return new char[]{'g', 'h', 'i'};
            case '5' : return new char[]{'j', 'k', 'l'};
            case '6' : return new char[]{'m', 'n', 'o'};
            case '7' : return new char[]{'p', 'q', 'r', 's'};
            case '8' : return new char[]{'t', 'u', 'v'};
            case '9' : return new char[]{'w', 'x', 'y', 'z'};
            default: return new char[]{'0'};
        }
    }


    public static void main(String[] args) {
        LetterCombinationOnPhone17 lc = new LetterCombinationOnPhone17();
        String digits = "23";
        System.out.println("Letter combinations " + lc.letterCombinations(digits));
        String digits1 = "2";
        System.out.println("Letter combinations " + lc.letterCombinations(digits1));
    }
}


