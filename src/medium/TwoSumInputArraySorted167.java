package medium;

import java.util.Arrays;

public class TwoSumInputArraySorted167 {

    /**
     * Approach: Two pointers
     * - Start two pointers from front and last
     * - if sum of these two is target then we are done
     * - if sum < target then increment right
     * - else increment left
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        int left = 0; int right = numbers.length-1;
        while(left < right){
            int sum = numbers[left] + numbers[right];
            if (sum == target){
                return new int[]{left + 1, right + 1};
            }
            if (sum > target){
                right--;
            } else {
                left++;
            }
        }

        return new int[] {-1, -1};
    }

    public static void main(String[] args) {
        TwoSumInputArraySorted167 twoSum = new TwoSumInputArraySorted167();
        int[] numbers = new int[]{2,7,11,15}; int target = 9;
        int[] res = twoSum.twoSum(numbers, target);
        System.out.println(String.format("[%s, %s]", res[0], res[1]));
        int[] numbers1 = new int[]{2,3,4}; int target1 = 6;
        int[] res1 = twoSum.twoSum(numbers1, target1);
        System.out.println(String.format("[%s, %s]", res1[0], res1[1]));
        int[] numbers2 = new int[]{-1,0}; int target2 = -1;
        int[] res2 = twoSum.twoSum(numbers2, target2);
        System.out.println(String.format("[%s, %s]", res2[0], res2[1]));
    }
}


