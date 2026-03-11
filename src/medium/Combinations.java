package medium;

import java.util.ArrayList;
import java.util.List;

public class Combinations {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        populateCombinations(n, k, 1, result, new ArrayList<>());
        return result;
    }

    private void populateCombinations(int n, int k, int startValue, List<List<Integer>> results, List<Integer> resultSoFar) {
        if (k == 0) {
            results.add(List.copyOf(resultSoFar));
            return;
        }
        if (startValue > n) {
            return;
        }

        for (int index = startValue; index <= n; index++) {
            resultSoFar.add(index);
            populateCombinations(n, k - 1, index + 1, results, resultSoFar);
            // Back track remove the element added in the last iteration before we backtrack
            resultSoFar.remove(resultSoFar.remove(resultSoFar.size() - 1));
        }
    }

    public static void main(String[] args) {
        Combinations combinations = new Combinations();
        int n = 4, k = 2;
        System.out.print("Combinations are " + combinations.combine(n, k));
    }
}


