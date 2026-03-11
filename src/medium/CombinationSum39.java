package medium;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum39 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        recursiveCombinationSum(candidates, target, result, new ArrayList<>(), 0, 0);
        return result;
    }

    private void recursiveCombinationSum(int[] candidates, int target, List<List<Integer>> results,
                                         List<Integer> part, int partSum, int startIndex) {
        if (partSum == target) {
            results.add(new ArrayList<>(part)); return;
        }
        if (partSum > target) {
            // Already more than target
            return;
        }
        for (int index = startIndex; index < candidates.length; index++) {
            int val = candidates[index];
            part.add(val);
            int valIndex = part.size() - 1;
            recursiveCombinationSum(candidates, target, results, part, partSum + val, index);
            part.remove(valIndex);
        }

    }

    public static void main(String[] args) {
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        System.out.println("Result is " + new CombinationSum39().combinationSum(candidates, 7));
    }
}


