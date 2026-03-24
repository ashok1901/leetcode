package easy;

import java.util.Arrays;
import java.util.Stack;

public class FinalPriceAfterDiscount1475 {
    public int[] finalPrices(int[] prices) {
        int[] nextSmallerPrices = buildNextSmallerByMonotonicStack(prices);
        int[] result = new int[prices.length];
        for (int i = 0; i < prices.length; i++) {
            int thisPrice = prices[i];
            int nextSmallerPrice = nextSmallerPrices[i];
            if (nextSmallerPrice <= thisPrice) {
                result[i] = thisPrice - nextSmallerPrice;
            } else {
                result[i] = thisPrice;
            }
        }

        return result;
    }

    private int[] buildNextSmallerByMonotonicStack(int[] prices) {
        int[] result = new int[prices.length];
        Stack<Integer> monoIncreasingStack = new Stack<>();
        monoIncreasingStack.push(prices[prices.length - 1]);
        result[prices.length - 1] = Integer.MAX_VALUE;
        int index = prices.length - 2;
        while (index >= 0) {
            int top = monoIncreasingStack.peek();
            int thisPrice = prices[index];
            if (thisPrice >= top) {
                result[index] = top;
                monoIncreasingStack.push(thisPrice);
            } else {
                // Keep popping until stack is empty or we get a bigger top.
                while(!monoIncreasingStack.isEmpty() && thisPrice < monoIncreasingStack.peek()) {
                    monoIncreasingStack.pop();
                }

                result[index] = monoIncreasingStack.isEmpty() ? Integer.MAX_VALUE : monoIncreasingStack.peek();
                monoIncreasingStack.push(thisPrice);
            }
            index--;
        }
        return result;
    }

    public static void main(String[] args) {
        FinalPriceAfterDiscount1475 finalPriceAfterDiscount1475 = new FinalPriceAfterDiscount1475();
        System.out.println(Arrays.toString(finalPriceAfterDiscount1475.finalPrices(new int[]{8,4,6,2,3})));
        System.out.println(Arrays.toString(finalPriceAfterDiscount1475.finalPrices(new int[]{1,2,3,4,5})));
        System.out.println(Arrays.toString(finalPriceAfterDiscount1475.finalPrices(new int[]{10,1,1,6})));

    }
}


