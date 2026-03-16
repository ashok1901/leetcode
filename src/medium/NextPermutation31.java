package medium;

import java.util.Arrays;

public class NextPermutation31 {
    /**
     * Approach : Find a dip
     * - When dip is found then swap dip and dip+1 arrange the remaining elements after dip+1 in reverse order
     * - If no dip then reverse the whole array
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        int dip = -1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                dip = i;
                break;
            }
        }
        if (dip != -1) {
            int fromLast = nums.length - 1;
            while (nums[fromLast] <= nums[dip]) {
                fromLast--;
            }
            swap(fromLast, dip, nums);
        }

        reverseArray(nums, dip+1);
    }

    private void reverseArray(int[] nums, int from) {
        int start = from;
        int end = nums.length - 1;
        while (start < end) {
            swap(start, end, nums);
            start++;end--;
        }
    }

    private void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        NextPermutation31 nextPermutation31 = new NextPermutation31();
        int[] nums = {1,2,3};
        nextPermutation31.nextPermutation(nums);
        System.out.println("Next permutation " + Arrays.toString(nums));

        int[] nums1 = {3,2,1};
        nextPermutation31.nextPermutation(nums1);
        System.out.println("Next permutation " + Arrays.toString(nums1));

        int[] nums2 = {1,1,5};
        nextPermutation31.nextPermutation(nums2);
        System.out.println("Next permutation " + Arrays.toString(nums2));

    }
}


