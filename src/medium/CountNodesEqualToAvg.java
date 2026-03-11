package medium;

public class CountNodesEqualToAvg {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * Approach:Post Order Traversa; Left -> Right -> Root
     * Find left sum and left elements count
     * Find right sum and right elements count
     * increment the counter when root.val = (leftSum + rightSum + root.val)/(leftCount + rightCount + 1)
     * collect the counter in some collection or global variable.
     * @param root
     * @return
     */
    private int globalCounter = 0;
    public int averageOfSubtree(TreeNode root) {
        sumAndCountTraversal(root);
        return globalCounter;
    }

    // Index 0 : Nodes Sum
    // Index 1 : Nodes Count
    private int[] sumAndCountTraversal(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }
        int[] leftSumAndCount = sumAndCountTraversal(root.left);
        int[] rightSumAndCount = sumAndCountTraversal(root.right);
        int totalSumIncludingRoot = leftSumAndCount[0] + rightSumAndCount[0] + root.val;
        int totalCountIncludingRoot = leftSumAndCount[1] + rightSumAndCount[1] + 1;
        // TODO: Keep in mind the arthematic rounding off when int division.
        int avg = totalSumIncludingRoot/totalCountIncludingRoot; // totalCountIncludingRoot can never be zero
        if (root.val == avg) {
            globalCounter++;
        }

        return new int[]{totalSumIncludingRoot, totalCountIncludingRoot};
    }

    public static void main(String[] args) {
        CountNodesEqualToAvg countNodesEqualToAvg = new CountNodesEqualToAvg();
        TreeNode treeNode = new TreeNode(1);
        System.out.println("Nodes count " + countNodesEqualToAvg.averageOfSubtree(treeNode));
    }
}


