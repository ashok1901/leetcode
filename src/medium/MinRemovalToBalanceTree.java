package medium;

import java.util.Arrays;

public class MinRemovalToBalanceTree {
    public int minRemoval(int[] nums, int k) {
        Arrays.sort(nums);
        int totalElements = nums.length;
        int left = totalElements - 1;
        int right = left;
        int overallMin = Integer.MAX_VALUE;
        while (right >= 0 && left >= 0) {
            // Mind overflow here :-) :-) So use long
            long max = nums[right];
            long min = nums[left];
            long prod = min*k;

            if (max <= prod) {
                int removedElements = totalElements - (right - left + 1);
                overallMin = Math.min(overallMin, removedElements);
                --left;
            } else {
                --right;
            }
        }

        return overallMin;
    }

    public static void main(String[] args) {
        MinRemovalToBalanceTree minRemovalToBalanceTree = new MinRemovalToBalanceTree();
        int[] nums = {1,6,2,9}; int k = 3;
        System.out.println("Min removal is " + minRemovalToBalanceTree.minRemoval(nums, k));
        int[] nums2 = {2,1,5}; int k2 = 2;
        System.out.println("Min removal is " + minRemovalToBalanceTree.minRemoval(nums2, k2));
        int[] nums3 = {4,6}; int k3 = 2;
        System.out.println("Min removal is " + minRemovalToBalanceTree.minRemoval(nums3, k3));
    }
}


