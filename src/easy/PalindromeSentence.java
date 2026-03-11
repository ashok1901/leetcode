package easy;

import java.util.Optional;

public class PalindromeSentence {

    public boolean isPalindrome(String s) {
        int start = 0, end = s.length() - 1;
        while (start < end) {
            if (!Character.isLetterOrDigit(s.charAt(start))) {
                start++;continue;
            }
            if (!Character.isLetterOrDigit(s.charAt(end))) {
                end--;continue;
            }
            if (Character.toLowerCase(s.charAt(start)) != Character.toLowerCase(s.charAt(end))) {
                return false;
            } else {
                start++;
                end--;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        PalindromeSentence palindromeSentence = new PalindromeSentence();
        String sentence = "A man, a plan, a canal: Panama";
        System.out.println("IsPanlindrome " + palindromeSentence.isPalindrome(sentence));
    }
}


