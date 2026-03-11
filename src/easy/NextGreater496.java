package easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class NextGreater496 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] nextGreater = buildNextGreaterArray(nums2);
        Map<Integer, Integer> valuesToIndexMap = buildValueToIndexMap(nums2);
        int[] result = new int[nums1.length];
        for (int index = 0; index < nums1.length; index++) {
            int num = nums1[index];
            int itsIndexInNum2 = valuesToIndexMap.getOrDefault(num, -1);
            if (itsIndexInNum2 == -1) {
                result[index] = -1;
            } else {
                int itsNextGreater = nextGreater[itsIndexInNum2];
                result[index] = itsNextGreater;
            }
        }

        return result;
    }

    private Map<Integer, Integer> buildValueToIndexMap(int[] nums) {
        Map<Integer, Integer> valueToIndex = new HashMap<>();
        int index = 0;
        for (int num : nums) {
            valueToIndex.put(num, index);
            ++index;
        }

        return valueToIndex;
    }

    private int[] buildNextGreaterArray(int[] nums) {
        int[] nextGreater = new int[nums.length];
        Stack<Integer> increasingStack = new Stack<>();
        increasingStack.push(nums[nums.length - 1]);
        nextGreater[nums.length - 1] = -1;
        int index = nums.length - 2;
        while (index >= 0) {
            int numToConsider = nums[index];
            while (!increasingStack.isEmpty() && increasingStack.peek() < numToConsider) {
                increasingStack.pop();
            }
            nextGreater[index] = increasingStack.isEmpty() ? - 1 : increasingStack.peek();
            increasingStack.push(numToConsider);
            index--;
        }

        return nextGreater;
    }

    public static void main(String[] args) {
        NextGreater496 nextGreater496 = new NextGreater496();
        int[] nums1 = {4,1,2}, nums2 = {1,3,4,2};
        System.out.println("Next greater are " + Arrays.toString(nextGreater496.nextGreaterElement(nums1, nums2)));
        int[] nums11 = {2, 4}, nums21 = {1,2,3,4};
        System.out.println("Next greater are " + Arrays.toString(nextGreater496.nextGreaterElement(nums11, nums21)));
    }
}


