package easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GoatLatin {

    /**
     * LeetCode 824
     *
     * @param args
     */
    Set<Character> vowels = Set.of('A', 'a', 'E', 'e', 'I', 'i', 'O', 'o', 'U', 'u');
    public String toGoatLatin(String sentence) {
        if (sentence == null || sentence.length() == 0) {
            return "";
        }
        String[] words = sentence.split(" ");
        int wordIndex = 0;
        String suffix = "a";
        for (String word : words) {
            if (word.isBlank()) {
                wordIndex++;
                continue;
            }
            if (isStartingWithVowel(word)) {
                word = word + "ma" + suffix;
            } else {
                String firstLetter = word.substring(0, 1);
                String remaining = word.substring(1);
                word = remaining + firstLetter + "ma" + suffix;
            }
            words[wordIndex] = word;
            suffix = suffix + "a";
            wordIndex++;
        }

        return String.join(" ", words);
    }

    private boolean isStartingWithVowel(String word) {
        if (word == null || word.length() == 0) {
            return false;
        }
        char firstLetter = word.charAt(0);
        return vowels.contains(firstLetter);
    }

    public static void main(String[] args) {
        GoatLatin goatLatin = new GoatLatin();
        System.out.println(goatLatin.toGoatLatin("I speak Goat Latin"));
    }
}


