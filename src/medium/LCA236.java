package medium;

public class LCA236 {

    class TreeNode {
        TreeNode left, right;
        int val;
    }

    /**
     * Approach : Recursive search left and right for the nodes
     *  if (root == p || root == q) return root
     *  TreeNode left = recursiveLeftSide
     *  TreeNode right = recursiveRightSide
     *  if (left == null) return right; // Both nodes in the right
     *  if (right == null) return left; // Both nodes in the left
     *  Otherwise one in the left and second in the right so this root is the LCA
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return recursiveLCA(root, p ,q);
    }

    private TreeNode recursiveLCA(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }
        TreeNode leftSideSearch = recursiveLCA(root.left, p, q);
        TreeNode rightSideSearch = recursiveLCA(root.right, p, q);
        if (leftSideSearch == null) {
            // It means nodes are in the right side, if any
            return rightSideSearch;
        }

        if (rightSideSearch == null) {
            // It means nodes are in the left side, if any
            return leftSideSearch;
        }
        // It means left and right search gave some results, so this should be the LCA.
        return root;
    }

    public static void main(String[] args) {
    }
}


