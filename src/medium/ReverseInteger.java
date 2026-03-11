package medium;

public class ReverseInteger {

    public int reverse(int x) {
        int remainingx = x;
        boolean isNegative = x < 0;
        remainingx = isNegative ? -1 * remainingx : remainingx;
        int newNum = 0;
        while(remainingx != 0) {
            int lastDigit = remainingx%10;
            remainingx = remainingx/10;
            if (newNum*10 + lastDigit < newNum) {
                // Flipped the number other side
                return 0;
            }
            newNum = newNum*10 + lastDigit;
        }

        return isNegative ? -1 * newNum : newNum;
    }

    public static void main(String[] args) {
        ReverseInteger reverseInteger = new ReverseInteger();
//        int value = reverseInteger.reverse(-123);
        System.out.println(String.format("Reversed value of %s is %d", -123, reverseInteger.reverse(-123)));
        System.out.println(String.format("Reversed value of %d is %d", 123, reverseInteger.reverse(123)));
        System.out.println(String.format("Reversed value of %d is %d", 120, reverseInteger.reverse(120)));
    }
}


