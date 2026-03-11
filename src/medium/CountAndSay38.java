package medium;

public class CountAndSay38 {

    public static String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }

        String res = countAndSay(n - 1);

        return rleEncode(res);
    }

    private static String rleEncode(String value) {
        int start = 0; String result = "";
        while (start < value.length()) {
            int repeatCount = findRepeatCount(value, start);
            result = result + repeatCount + value.charAt(start);
            start = start + repeatCount;
        }
        return result;
    }

    private static int findRepeatCount(String value, int index) {
        if (index == value.length() - 1) {
            return 1;
        }

        int counter = 1;
        int start = index + 1;
        while(start < value.length()) {
            if (value.charAt(index) == value.charAt(start)) {
                counter++; start++;
            } else {
                break;
            }
        }
        return counter;
    }

    public static void main(String[] args) {
        System.out.println("Value at 4 is " + countAndSay(4));
        System.out.println("Value at 1 is " + countAndSay(1));
        System.out.println("Value at 5 is " + countAndSay(5));
    }
}


