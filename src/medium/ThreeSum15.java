package medium;

import java.util.*;

public class ThreeSum15 {

    /**
     * Approach : 2Sum style
     *
     * @param nums
     * @return
     */
    private Map<Integer, List<Integer>> valuesToIndices = new HashMap<>();
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return results;
        }
        populateValuesToIndices(nums);
        Set<String> avoidDuplicatesSet = new HashSet<>();
        // Fix first element and try to find the remaining two
        for (int index = 0; index < nums.length; index++) {
            int num = nums[index];
            int remainingSum = -1 * num;
            List<List<Integer>> twoIndices = findIndex2Sum(nums, remainingSum, index + 1);
            for (List<Integer> twoIndex : twoIndices) {
                int index2 = twoIndex.size() > 0 ? twoIndex.get(0) : -1;
                int index3 = twoIndex.size() > 1 ? twoIndex.get(1) : -1;
                if (index2 == -1 || index3 == -1) {
                    continue;
                }
                int num1 = nums[index];
                int num2 = nums[index2];
                int num3 = nums[index3];
                String hashKey = buildHashKey(num1, num2, num3);
                if (!avoidDuplicatesSet.contains(hashKey)) {
                    results.add(Arrays.asList(new Integer[]{num1, num2, num3}));
                    avoidDuplicatesSet.add(hashKey);
                }
            }
        }
        return results;
    }

    private void populateValuesToIndices(int[] nums) {
        for(int index = 0; index < nums.length; index++) {
            int num = nums[index];
            if (!valuesToIndices.containsKey(num)) {
                valuesToIndices.put(num, new ArrayList<>());
            }
            List<Integer> currentIndices = valuesToIndices.get(num);
            currentIndices.add(index);
            valuesToIndices.put(num, currentIndices);
        }
    }

    private String buildHashKey(int num1, int num2, int num3) {
        int[] values = {num1, num2, num3};
        Arrays.sort(values);
        return values[0] + "_" + values[1] + "_" + values[2];
    }

    private List<List<Integer>> findIndex2Sum(int[] nums, int twoSum, int startIndex) {
        int secondIndex = -1;
        int firstIndex = -1;
        List<List<Integer>> targetTwoIndices = new ArrayList<>();
        for (int index = startIndex; index < nums.length; index++) {
            int num = nums[index];
            int remainingTarget = twoSum - num;
            List<Integer> remainingTargetIndices = findIndexOf(remainingTarget);
            for (Integer remainlingTargetIndex : remainingTargetIndices) {
                List<Integer> entry = new ArrayList<>();
                entry.add(index);
                entry.add(remainlingTargetIndex);
                targetTwoIndices.add(entry);
            }
        }

        return targetTwoIndices;
    }

    private List<Integer> findIndexOf(int value) {
        return valuesToIndices.getOrDefault(value, new ArrayList<>());
    }

    public static void main(String[] args) {
        ThreeSum15 threeSum15 = new ThreeSum15();
        int[] nums = {-1,0,1,2,-1,-4};
        System.out.println("3 Sum : " + threeSum15.threeSum(nums));
        int[] nums1 = {0,1,1};
        System.out.println("3 Sum : " + threeSum15.threeSum(nums1));
        int[] nums2 = {0,0,0};
        System.out.println("3 Sum : " + threeSum15.threeSum(nums2));
        int[] nums3 = {0,0,0,0};
        System.out.println("3 Sum : " + threeSum15.threeSum(nums3));
        int[] nums4 = {-2,0,1,1,2};
        System.out.println("3 Sum : " + threeSum15.threeSum(nums4));
    }
}


