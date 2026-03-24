package medium;

public class StringCompression443 {

    /**
     * Approach: Two pointers
     * - Keep a scanner pointer and compressed pointer
     * - Keep increasing scanner pointer as long as char is same as compressed pointer but only upto 9.
     * - Beyond 9 start a new cycle
     *
     * T: O(n) : Each char is vissited only once.
     * S: O(n) : No extra space except few variables.
     * @param chars
     * @return
     */
    public int compress(char[] chars) {
        int scanner = 0;
        int scannerStart = 0;
        int compressedChar = 0;
        while (scannerStart < chars.length) {
            int counter = 0;
            while (scanner < chars.length && chars[scanner] == chars[scannerStart]) {
                scanner++;
                counter++;
            }

            if (counter != 1) {
                chars[compressedChar] = chars[scannerStart];
                int counterCharLen = String.valueOf(counter).length();
                int divFactor = (int) Math.pow(10, counterCharLen - 1);
                int nextIndex = compressedChar + 1;
                int c = 0;
                while (c < counterCharLen) {
                    int mostSignificantChar = counter / divFactor;
                    counter = counter % divFactor;
                    divFactor = divFactor/10;

                    char countChar = (char) (mostSignificantChar + '0');
                    chars[nextIndex] = countChar;
                    nextIndex++;
                    c++;
                }

                compressedChar = nextIndex;
            } else {
                chars[compressedChar] = chars[scannerStart];
                compressedChar = compressedChar + 1;
            }
            // Start scanning from where last cycle ended.
            scannerStart = scanner;
        }

        int newLength = compressedChar;
        return newLength;
    }

    public static void main(String[] args) {
        StringCompression443 compression = new StringCompression443();
//        char[] chars = {'a','a','b','b','c','c','c'};
//        System.out.println(compression.compress(chars));
//        char[] chars1 = {'a'};
//        System.out.println(compression.compress(chars1));
//        char[] chars2 = {'a','b','b','b','b','b','b','b','b','b','b','b','b'};
//        System.out.println(compression.compress(chars2));
        char[] chars3 = {'b','b','b','b','b','b','b','b','b','b'};
        System.out.println(compression.compress(chars3));

    }
}


