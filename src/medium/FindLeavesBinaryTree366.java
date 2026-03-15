package medium;

import java.util.ArrayList;
import java.util.List;

public class FindLeavesBinaryTree366 {

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
     * Approach: Remove leaves and collect in a list
     * - Keep calling this until tree become null.
     *
     * Problem: This is sort of brute force approach as residual tree is traversed again and again.
     *
     * @param root
     * @return
     */
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        TreeNode tempRoot = root;
        while (tempRoot != null) {
            List<Integer> leaves = new ArrayList<>();
            removeLeaves(tempRoot, leaves);
            if (leaves.size() != 0) {
                res.add(leaves);
            }
            // Now check if tree has shrunk to a single node. If so we are done.
            if (isLeaf(tempRoot)) {
                List<Integer> tempLeaves = new ArrayList<>();
                tempLeaves.add(tempRoot.val);
                res.add(tempLeaves);
                tempRoot = null;
            }
        }
        return res;
    }

    private void removeLeaves(TreeNode node, List<Integer> leaves) {
        if (node == null) {
            return;
        }
        if (isLeaf(node.left)) {
            leaves.add(node.left.val);
            node.left = null;
        }

        if (isLeaf(node.right)) {
            leaves.add(node.right.val);
            node.right = null;
        }
        removeLeaves(node.left, leaves);
        removeLeaves(node.right, leaves);
    }

    private boolean isLeaf(TreeNode node) {
        return node != null && node.left == null && node.right == null;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        FindLeavesBinaryTree366 findLeavesBinaryTree366 = new FindLeavesBinaryTree366();
        List<List<Integer>> res = findLeavesBinaryTree366.findLeaves(root);
        System.out.println(res);

    }
}


