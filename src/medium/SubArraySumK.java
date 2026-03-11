package medium;

import java.util.HashMap;
import java.util.Map;

public class SubArraySumK {

    /**
     * Solution is right but ends up in Memory Exceed.
     */
    public int subarraySum(int[] nums, int k) {
        int kCounter = 0;
        int[][] dp = new int[nums.length][nums.length];
        for (int index = 0; index < nums.length; index++) {
            dp[index][index] = nums[index];
            if (dp[index][index] == k) {
                kCounter += 1;
            }
        }

        for (int r = 0; r < dp.length; r++) {
            for (int c = r + 1; c < dp.length; c++) {
                dp[r][c] = dp[r][c-1] + nums[c];
                if (dp[r][c] == k) {
                    kCounter += 1;
                }
            }
        }

        return kCounter;
    }

    // The memory efficient solution is Hashmap based approach.
    public int subArraySumMapApproach(int[] nums, int k) {
        Map<Integer, Integer> sumMap = new HashMap<>();
        sumMap.put(0, 1); // 0 sum means don't choose any, this is start seed
        int sum = 0;
        int kCounter = 0;
        for (int index = 0; index < nums.length; index++) {
            sum += nums[index];
            if (sumMap.containsKey(sum - k)) {
                kCounter += sumMap.get(sum - k);
            }
            sumMap.put(sum, sumMap.getOrDefault(sum, 0) + 1);
        }
        return kCounter;
    }

    public static void main(String[] args) {
        SubArraySumK subArraySumK = new SubArraySumK();
        int[] nums = {1,2,3};
        System.out.println("k count " + subArraySumK.subarraySum(nums, 3));
        int[] nums1 = {1,1,1};
        System.out.println("k count " + subArraySumK.subarraySum(nums1, 2));
    }
}


