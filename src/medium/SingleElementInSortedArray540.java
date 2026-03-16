package medium;

public class SingleElementInSortedArray540 {
    /**
     * Approach: Binary search
     * - if mid element is different than it's left and right then we got it.
     * - if left of mid is same as mid and elements in left are even so search in left otherwise search in right
     * @param nums
     * @return
     */
    public int singleNonDuplicate(int[] nums) {
        // If there is only one element then that is the element
        if (nums.length == 1) return nums[0];
        // Otherwise there must be 3 or more elements
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == nums[mid + 1]) {
                // Mid's partner is in it's right
                int rightLengthAfterPartner = right - (mid + 1);
                if (rightLengthAfterPartner %2 == 0) {
                    // So result is in the left
                    right = mid - 1;
                } else {
                    left = mid + 2;
                }
            } else if (nums[mid] == nums[mid - 1]) {
                // Mid's partner is in it's left
                int leftLengthBeforePartner = (mid - 1) - left;
                if (leftLengthBeforePartner %2 == 0) {
                    // Result is in right
                    left = mid + 1;
                } else {
                    right = mid - 2;
                }
            } else {
                // No partner so this is the number.
                return nums[mid];
            }
        }

        return nums[left];
    }

    public static void main(String[] args) {
        SingleElementInSortedArray540 singleElementInSortedArray540 = new SingleElementInSortedArray540();
        int[] nums = {1,1,2,3,3,4,4,8,8};
        System.out.println(singleElementInSortedArray540.singleNonDuplicate(nums));
        int[] nums1 = {3,3,7,7,10,11,11};
        System.out.println(singleElementInSortedArray540.singleNonDuplicate(nums1));
    }
}


