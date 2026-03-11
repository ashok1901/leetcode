package easy;

public class DiameterBinaryTree {
    private class TreeNode {
        private TreeNode left;
        private TreeNode right;
        private int value;

    }
    private int overallMax = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        int valueAtRoot = recursiveApproach(root);
        return overallMax;
    }

    private int recursiveApproach(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = recursiveApproach(root.left);
        int right = recursiveApproach(root.right);

        int leftDistance = left != 0 ? left + 1 : left;
        int rightDistance = right != 0 ? right + 1 : right;
        int pathViaRoot = leftDistance + rightDistance;
        overallMax = Math.max(overallMax, pathViaRoot);
        return Math.max(leftDistance, rightDistance);
    }

    public static void main(String[] args) {
    }
}


