package easy;

public class RangeSumBST {

    public class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
    }

    /**
     * Approach: Post Order traversal
     * @param root
     * @param low
     * @param high
     * @return
     */
    private int globalSum = 0;
    public int rangeSumBST(TreeNode root, int low, int high) {
        rangeSumWithGlobalVariable(root, low, high);
        return globalSum;
    }

    private void rangeSumWithGlobalVariable(TreeNode root, int low, int high) {
        if (root == null) {
            return;
        }

        if (low < root.val) {
            rangeSumBSTPostOrder(root.left, low, high);
        }

        if (low <= root.val && root.val <= high) {
            globalSum += root.val;
        }
        if (root.val < high) {
            rangeSumWithGlobalVariable(root.right, low, high);
        }
    }

    private int rangeSumBSTPostOrder(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }
        int right = 0;
        if (root.val < low) {
            right = rangeSumBSTPostOrder(root.left, low, high);
        }
        int left = 0;
        if (root.val > high) {
            left = rangeSumBSTPostOrder(root.right, low, high);
        }

        if (low <= root.val && root.val <= high) {
            return root.val + left + right;
        }

        return left + right;
    }

    public static void main(String[] args) {
    }
}


