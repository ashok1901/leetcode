package medium;

public class JumpGame55 {

    /**
     * You are given an integer array nums. You are initially positioned at the array's first index, and each element
     * in the array represents your maximum jump length at that position.
     *
     * Return true if you can reach the last index, or false otherwise.
     *
     * Approach : Try possible moved and optimize with memoization.
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        Boolean[] mem = new Boolean[nums.length];
        // Initialize this to null, Java takes care of this though
        // Last element is the destination so this is always true.
        mem[nums.length - 1] = true;
        return canJumpRecursive(nums, mem, 0);
    }

    private boolean canJumpRecursive(int[] nums, Boolean[] mem, int startIndex) {
        if (startIndex >= nums.length) {
            return false;
        }
        if (mem[startIndex] != null) {
            return mem[startIndex];
        }

        int possibleJumps = nums[startIndex];
        boolean isPossible = false;
        for(int jumpLen = 1; jumpLen <= possibleJumps; jumpLen++) {
            isPossible = canJumpRecursive(nums, mem, startIndex + jumpLen);
            if (isPossible) {
                break;
            }
        }
        mem[startIndex] = isPossible;
        return isPossible;
    }

    public static void main(String[] args) {
        JumpGame55 jumpGame55 = new JumpGame55();
        int[] nums = {2,3,1,1,4};
        System.out.println("isPossible " + jumpGame55.canJump(nums));
        int[] nums1 = {3,2,1,0,4};
        System.out.println("isPossible " + jumpGame55.canJump(nums1));
    }
}


