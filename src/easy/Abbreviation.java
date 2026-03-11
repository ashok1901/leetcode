package easy;

public class Abbreviation {

    /**
     * Input: word = "internationalization", abbr = "i12iz4n"
     * Output: true
     * Approach:
     * Single pass from left to right with jump as per the numbers in between.
     * Parse numbers from the abbreviation and traverse both the strings in parallel with proper jump value.
     */
    public boolean validWordAbbreviation(String word, String abbr) {
        int wordIndex = 0;
        int abbrIndex = 0;
        while (wordIndex < word.length() && abbrIndex < abbr.length()) {
            char abbrChar = abbr.charAt(abbrIndex);
            char wordChar = word.charAt(wordIndex);
            if (abbrChar == wordChar) {
                ++wordIndex;++abbrIndex;continue;
            }
            if (Character.isDigit(abbrChar)) {
                if (abbrChar == '0') {
                    return false;
                }
                int jumpLength = parseNumber(abbrIndex, abbr);
                wordIndex += jumpLength;
                abbrIndex += Integer.toString(jumpLength).length();
                if (wordIndex > word.length() || abbrIndex > abbr.length()) {
                    return false;
                }
            } else {
                return false;
            }
        }

        return wordIndex == word.length() && abbrIndex == abbr.length() ? true : false;
    }

    private int parseNumber(int start, String abbr) {
        if (start >= abbr.length()) {
            throw new IllegalArgumentException(String.format("Start %d is beyond the given string %s", start, abbr));
        }
        int temp = start;
        int num = 0;
        while (temp < abbr.length() && Character.isDigit(abbr.charAt(temp))) {
            int digit = abbr.charAt(temp) - '0';
            num = num * 10 + digit;
            temp = temp + 1;
        }

        return num;
    }

    public static void main(String[] args) {
        Abbreviation abbreviation = new Abbreviation();
//        System.out.println("isValidAbbr " + abbreviation.validWordAbbreviation("internationalization", "i12iz4n"));
//        System.out.println("isValidAbbr " + abbreviation.validWordAbbreviation("apple", "a2e"));
        System.out.println("isValidAbbr " + abbreviation.validWordAbbreviation("internationalization", "i5a11o1"));
        System.out.println("isValidAbbr " + abbreviation.validWordAbbreviation("a", "2"));
    }
}


