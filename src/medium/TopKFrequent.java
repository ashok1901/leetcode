package medium;

import java.util.*;

public class TopKFrequent {
    /**
     * Approach : k-size min heap
     * - First find the frequency of all the elements O(n)
     * - Then add them to k-size min heap O(log k)
     * - The elements left in the min heap would the required elements.
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqMap = buildFrequencyMap(nums);
        Integer[][] freqAsArray = transformFrequencyMapToArray(freqMap);
        Queue<Integer[]> minHeap = new PriorityQueue<>(k, new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                return o1[1].compareTo(o2[1]);
            }
        });

        for (Integer[] val : freqAsArray) {
            minHeap.add(val);
            if(minHeap.size() > k) {
                minHeap.remove();
            }
        }

        int[] results = new int[minHeap.size()];
        int index = 0;
        for (Integer[] val : minHeap) {
            results[index++] = val[0];
        }

        return results;
    }

    private Integer[][] transformFrequencyMapToArray(Map<Integer, Integer> freqMap) {
        Integer[][] freqArray = new Integer[freqMap.size()][2];
        // Transformation logic here below
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            freqArray[index][0] = entry.getKey();
            freqArray[index][1] = entry.getValue();
            index++;
        }

        return freqArray;
    }
    private Map<Integer, Integer> buildFrequencyMap(int[] nums) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        // Frequency logic here below
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        return freqMap;
    }

    public static void main(String[] args) {
        TopKFrequent topKFrequent = new TopKFrequent();
        int[] nums = {1,1,1,2,2,3}; int k = 2;
        System.out.println("Top k frequent elements " + Arrays.toString(topKFrequent.topKFrequent(nums, k)));
        int[] nums1 = {1,2,1,2,1,2,3,1,3,2}; int k1 = 2;
        System.out.println("Top k frequent elements " + Arrays.toString(topKFrequent.topKFrequent(nums1, k1)));
        int[] nums2 = {1}; int k2 = 1;
        System.out.println("Top k frequent elements " + Arrays.toString(topKFrequent.topKFrequent(nums2, k2)));
    }
}


