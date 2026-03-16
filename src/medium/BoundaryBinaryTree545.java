package medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BoundaryBinaryTree545 {

     // Definition for a binary tree node.
     public static class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode() {}
          TreeNode(int val) { this.val = val; }
          TreeNode(int val, TreeNode left, TreeNode right) {
              this.val = val;
              this.left = left;
              this.right = right;
          }
     }

    /**
     * Left boundary
     * Leaves
     * Right boundary
     *
     * Join leftBoundary <-> Leaves <-> Reverse of right boundary
     * @param root
     * @return
     *
     * NOT WORKING - WRONG VALUES
     */
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        if (root == null) return new ArrayList<>();

        List<Integer> leftBoundary = new ArrayList<>();
        leftBoundary(root.left, leftBoundary);

        List<Integer> rightBoundary = new ArrayList<>();
        rightBoundary(root.right, rightBoundary);

        List<Integer> leaves = new ArrayList<>();
        leavesBoundary(root, leaves);

        List<Integer> result = new ArrayList<>();
        // Root and then attach the boundaries and leaves.
        if (!isLeaf(root)) {
            // Add root only if this is not leave otherwise leaves will take care of this.
            result.add(root.val);
        }

        result.addAll(leftBoundary);
        result.addAll(leaves);
        Collections.reverse(rightBoundary);
        result.addAll(rightBoundary);

        return result;
    }

    private boolean isLeaf(TreeNode root) {
        return root.left == null && root.right == null;
    }

    /**
     * Keep going left until left is null in that case go right and again recurse
     * @param root
     * @return
     */
    private void leftBoundary(TreeNode root, List<Integer> leftBoundary) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            // Do not consider leaf because leaves would be considered in a separate step.
            return;
        }
        leftBoundary.add(root.val);
        if (root.left != null) {
            leftBoundary(root.left, leftBoundary);
        } else {
            leftBoundary(root.right, leftBoundary);
        }
    }

    /**
     * Keep going right until right is null in that case go right and again recurse
     * @param root
     * @return
     */
    private void rightBoundary(TreeNode root, List<Integer> rightBoundary) {
        if (root == null) {
            return;
        }
        if (root.right == null && root.left == null) {
            // Leaves should not be considered in this
            return;
        }
        rightBoundary.add(root.val);
        if (root.right != null) {
            rightBoundary(root.right, rightBoundary);
        } else {
            rightBoundary(root.left, rightBoundary);
        }
    }

    private void leavesBoundary(TreeNode root, List<Integer> leaves) {
        if (root == null) {
            return;
        }
        // Leaf node
        if (root.left == null && root.right == null) {
            leaves.add(root.val);
            return;
        }
        leavesBoundary(root.left, leaves);
        leavesBoundary(root.right, leaves);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(4);

        BoundaryBinaryTree545 boundary = new BoundaryBinaryTree545();
        System.out.println(boundary.boundaryOfBinaryTree(root));
    }
}


