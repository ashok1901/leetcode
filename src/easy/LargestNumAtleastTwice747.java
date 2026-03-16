package easy;

public class LargestNumAtleastTwice747 {
    public int dominantIndex(int[] nums) {
        int largestIndex = findLargestIndex(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i == largestIndex) {
                // Skip the latest index itself.
                continue;
            }
            if (2*nums[i] > nums[largestIndex]) {
                return -1;
            }
        }
        return largestIndex;
    }

    private int findLargestIndex(int[] nums) {
        int largestIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[largestIndex]) {
                largestIndex = i;
            }
        }
        return largestIndex;
    }

    public static void main(String[] args) {
        LargestNumAtleastTwice747 largestNumAtleastTwice747 = new LargestNumAtleastTwice747();
        int[] nums = {3,6,1,0};
        System.out.println(largestNumAtleastTwice747.dominantIndex(nums));
        int[] nums1 = {1,2,3,4};
        System.out.println(largestNumAtleastTwice747.dominantIndex(nums1));
    }
}


