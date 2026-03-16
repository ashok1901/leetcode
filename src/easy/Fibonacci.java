package easy;

public class Fibonacci {

    public int fib(int n) {
        int prev = 0;
        if (n < 1) {
            return prev;
        }
        int curr = 1;
        if (n == 1) {
            return curr;
        }

        int count = 2;
        while (count <= n) {
            int temp = curr + prev;
            prev = curr;
            curr = temp;
            count++;
        }

        return curr;
    }

    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        System.out.println(fibonacci.fib(2));
        System.out.println(fibonacci.fib(3));
        System.out.println(fibonacci.fib(4));
    }
}


