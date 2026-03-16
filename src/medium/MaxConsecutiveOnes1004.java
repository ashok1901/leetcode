package medium;

public class MaxConsecutiveOnes1004 {


    /**
     * Approach : Sliding window to find max ones with allowed tempN zeros
     * @param nums
     * @param tempN
     * @return
     */
    public int longestOnes(int[] nums, int k) {
        int left = 0, max = -1;
        int tempN = 0;
        int right = 0;
        while(right < nums.length) {
            if (nums[right] == 0) {
                tempN++;
            }

            while (tempN > k) {
                if (nums[left] == 0) {
                    tempN--;
                }
                left++;
            }

            max = Math.max(max, right - left + 1);
            right++;
        }
        return max;
    }

    /**
     * BELOW IS THE LEETCODE SOLUTION
     *
     * @param nums
     * @param k
     * @return
     */
    public int longestOnes_leetcode(int[] nums, int k) {
        int left = 0;
        int curr = 0;
        int ans = 0;
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                curr++;
            }

            while (curr > k) {
                if (nums[left] == 0) {
                    curr--;
                }

                left++;
            }

            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }


    public static void main(String[] args) {
        MaxConsecutiveOnes1004 maxConsecutiveOnes1004 = new MaxConsecutiveOnes1004();
        int[] nums = {1,1,1,0,0,0,1,1,1,1,0}; int k = 2;
        System.out.println(maxConsecutiveOnes1004.longestOnes(nums, k));
        int[] nums1 = {0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1}; int k1 = 3;
        System.out.println(maxConsecutiveOnes1004.longestOnes(nums1, k1));
        int[] nums2 = {0,0,1,1,1,0,0}; int k2 = 0;
        System.out.println(maxConsecutiveOnes1004.longestOnes(nums2, k2));
        int[] nums3 = {0,0,0,0}; int k3 = 0;
        System.out.println(maxConsecutiveOnes1004.longestOnes(nums3, k3));
    }
}


