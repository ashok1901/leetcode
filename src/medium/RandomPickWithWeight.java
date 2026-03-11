package medium;

import java.util.Random;

public class RandomPickWithWeight {

    Random random;
    int[] cumulativeSum;
    public RandomPickWithWeight(int[] w) {
        cumulativeSum = new int[w.length];
        cumulativeSum[0] = w[0];
        for (int index = 2; index < w.length; index++) {
            cumulativeSum[index] = cumulativeSum[index - 1] + w[index];
        }
        random = new Random();
    }

    public int pickIndex() {
        int totalSum = cumulativeSum[cumulativeSum.length - 1];
        int nextRandom = random.nextInt(totalSum) + 1;
        int left = 0, right = cumulativeSum.length - 1;
        while(left < right) {
            int mid = left + (right - left)/2;
            if (cumulativeSum[mid] == nextRandom) {
                return mid;
            }
            if (cumulativeSum[mid] < nextRandom) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
    }
}


