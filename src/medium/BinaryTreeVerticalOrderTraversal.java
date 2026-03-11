package medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BinaryTreeVerticalOrderTraversal {
    public class TreeNode {
        private TreeNode left;
        private TreeNode right;
        int val;
    }

    /**
     * Hashmap based approach with go left -1 and go right +1
     *
     * @param root
     * @return
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        Map<Integer, Map<Integer, List<Integer>>> verticalMap = new HashMap<>();
        recursiveTraversal(root, 0, 0, verticalMap);
        List<List<Integer>> result = new ArrayList<>();
        for (Integer seed : verticalMap.keySet().stream().sorted().toList()) {
            List<Integer> vertical = new ArrayList<>();
            for (Integer row : verticalMap.get(seed).keySet().stream().sorted().toList()) {
                vertical.addAll(verticalMap.get(seed).get(row));
            }
            result.add(vertical);
        }

        return result;
    }

    private void recursiveTraversal(TreeNode root, int seed, int row,
                                    Map<Integer, Map<Integer, List<Integer>>> verticalMap) {
        if (root == null) {
            return;
        }
        if (!verticalMap.containsKey(seed)) {
            verticalMap.put(seed, new HashMap<>());
        }
        if (!verticalMap.get(seed).containsKey(row)) {
            verticalMap.get(seed).put(row, new ArrayList<>());
        }
        verticalMap.get(seed).get(row).add(root.val);
        recursiveTraversal(root.left, seed - 1, row + 1, verticalMap);
        recursiveTraversal(root.right, seed + 1, row + 1, verticalMap);
    }

    public static void main(String[] args) {
    }
}


