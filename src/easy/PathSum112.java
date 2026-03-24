package easy;

public class PathSum112 {
    public class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        return recur(root, sum);
    }

    private boolean recur(TreeNode root, int sum) {
        // Note: Do not call this with root as null, check it outside
        if (root.left == null && root.right == null && sum == root.val) {
            // This is the leaf
            return sum == root.val;
        }

        if (root.left != null) {
            boolean left = recur(root.left, sum - root.val);
            if (left) {
                return true;
            }
        }

//        Otherwise check the right part
        if (root.right != null) {
            boolean right = recur(root.right, sum - root.val);
            if (right) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
    }
}


