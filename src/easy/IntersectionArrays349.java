package easy;

import java.util.*;

public class IntersectionArrays349 {
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0)  {
            return new int[0];
        }
        Set<Integer> result = new HashSet<>();
        Set<Integer> set;
        int[] iterateArray;
        if (nums1.length > nums2.length) {
            set = buildHashSet(nums1);
            iterateArray = nums2;
        } else {
            set = buildHashSet(nums2);
            iterateArray = nums1;
        }
        for (int val : iterateArray) {
            if (set.contains(val)) {
                result.add(val);
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    private Set<Integer> buildHashSet(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        return set;
    }

    public static void main(String[] args) {
        IntersectionArrays349 intersectionArrays349 = new IntersectionArrays349();
        int[] nums1 = {1,2,2,1}, nums2 = {2,2};
        System.out.println(Arrays.toString(intersectionArrays349.intersection(nums1, nums2)));
        int[] nums11 = {4,9,5}, nums21 = {9,4,9,8,4};
        System.out.println(Arrays.toString(intersectionArrays349.intersection(nums11, nums21)));

    }
}


