package easy;

import java.util.Arrays;

public class MergeSortedArrays {

    /**
     * Merge nums1 and nums2 into a single array sorted in non-decreasing order.
     * @param args
     */
    // Logic: Merge from back with two pointers approach.
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int nums1Index = m - 1;
        int nums2Index = n - 1;
        int mergedIndex = m + n - 1;
        while(nums1Index >= 0 && nums2Index >= 0) {
            if (nums1[nums1Index] > nums2[nums2Index]) {
                nums1[mergedIndex] = nums1[nums1Index];
                nums1Index -= 1; mergedIndex -= 1;
            } else {
                nums1[mergedIndex] = nums2[nums2Index];
                nums2Index -= 1; mergedIndex -= 1;
            }
        }

        while (nums2Index >= 0) {
            nums1[mergedIndex] = nums2[nums2Index];
            mergedIndex -= 1; nums2Index -= 1;
        }
        // If some elements left in nums1 those are not considered yet those should be already at the right place.
    }

    private void printArray(int nums[]) {
        System.out.print("[");
        for (int n : nums) {
            System.out.print(n);System.out.print(", ");
        }
        System.out.print("]");
    }

    public static void main(String[] args) {
        MergeSortedArrays mergeSortedArrays = new MergeSortedArrays();
        int[] nums1 = {1,2,3,0,0,0}; int m = 3; int[] nums2 = {2,5,6}; int n = 3;
        mergeSortedArrays.merge(nums1, m, nums2, n);
        mergeSortedArrays.printArray(nums1);
//        System.out.println("Merged array " + Arrays.deepToString(nums1));
    }
}


