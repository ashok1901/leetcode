package medium;

public class JumpGame54 {
    public int jump(int[] nums) {
        int[] minJumpDP = new int[nums.length];
        for (int index = 0; index < minJumpDP.length; index++) {
            minJumpDP[index] = Integer.MAX_VALUE;
        }

        minJumpDP[minJumpDP.length - 1] = 0;
        for (int index = minJumpDP.length - 2; index >= 0; index--) {
            int possibleJumps = nums[index];
            if (possibleJumps <= 0) {
                minJumpDP[index] = Integer.MAX_VALUE;
            }
            int min = Integer.MAX_VALUE;
            for (int jump = 1; jump <= possibleJumps; jump++) {
                if (index + jump < nums.length) {
                    min = Math.min(min,
                            minJumpDP[index + jump] == Integer.MAX_VALUE ? Integer.MAX_VALUE : minJumpDP[index + jump] + 1);
                }
            }
            minJumpDP[index] = min;
        }

        return minJumpDP[0];
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println("minJumps value : " + new JumpGame54().jump(nums));
    }
}


