package medium;

import java.util.ArrayList;
import java.util.List;

public class DistanceKNodes863 {

    /**
     * Definition for a binary tree node.
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private class PathEntry {
        private TreeNode treeNode;
        boolean isGoLeft;

        public PathEntry(TreeNode treeNode, boolean isGoLeft) {
            this.treeNode = treeNode;
            this.isGoLeft = isGoLeft;
        }
    }

    /**
     * Approach:
     * - Find the path to the target tree node with information if this is left or right of the parent.
     * - Write a utility function to find elements at certain depth from the given root
     * - Use above function for each node in the path node to it's suitable subtree.
     *
     * Path Array :
     *  [TreeNode, isGoLeft]
     * @param root
     * @param target
     * @param k
     * @return
     */
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        // Find the path to the target node
        List<PathEntry> pathToTarget = buildPathToTheTargetNode(root, target);
        List<Integer> result = new ArrayList<>();
        // K distance from the target.
        List<Integer> kDistanceFromTarget = findKDistanceNodes(target, k);
        result.addAll(kDistanceFromTarget);
        // Now iterate over the path list and keep decreasing k until path is exhausted or k is exhausted.
        int tempK = k;
        // -1 because one branch is already traversed to get parent of target from the parent list.
        tempK = tempK - 1;
        int pathListIndex = 0;
        int pathListSize = pathToTarget.size();
        while(tempK >= 0 && pathListIndex < pathListSize) {
            PathEntry pathEntry = pathToTarget.get(pathListIndex);
            if (tempK == 0) {
                result.add(pathEntry.treeNode.val);
                pathListIndex++; tempK--;
                continue;
            }
            TreeNode gotoBranch = pathEntry.isGoLeft ? pathEntry.treeNode.right : pathEntry.treeNode.left;
            // One more -1 because it is branching to either left or right.
            List<Integer> nextKDistanceFromParent = findKDistanceNodes(gotoBranch, tempK - 1);
            if (nextKDistanceFromParent != null && !nextKDistanceFromParent.isEmpty()) {
                result.addAll(nextKDistanceFromParent);
            }

            pathListIndex++; tempK--;
        }
        return result;
    }

    /**
     * Helper function to build path until target node, excluding target node.
     * @param root
     * @param target
     * @return
     */
    private List<PathEntry> buildPathToTheTargetNode(TreeNode root, TreeNode target) {
        if (root == null) {
            return null;
        }
        if (root == target) {
            // Blank because we reached to the target and we are excluding target for now.
            return new ArrayList<>();
        }

        List<PathEntry> leftSide = buildPathToTheTargetNode(root.left, target);
        if (leftSide != null) {
            // Node found in the left side
            leftSide.add(new PathEntry(root, true));
            return leftSide;
        }
        // Node not in the left so have to check in the right.
        List<PathEntry> rightSide = buildPathToTheTargetNode(root.right, target);
        if (rightSide != null) {
            rightSide.add(new PathEntry(root, false));
            return rightSide;
        }

        return null;
    }

    /**
     * Helper function to find k distance nodes from the given root.
     * @param root
     * @param k
     * @return
     */
    private List<Integer> findKDistanceNodes(TreeNode root, int k) {
        if (root == null || k < 0) {
            return null;
        }
        if (k == 0) {
            List<Integer> values = new ArrayList<>();
            values.add(root.val);
            return values;
        }

        List<Integer> leftEntries = findKDistanceNodes(root.left, k-1);
        List<Integer> rightEntries = findKDistanceNodes(root.right, k-1);
        List<Integer> results = new ArrayList<>();
        if (leftEntries != null && !leftEntries.isEmpty()) {
            results.addAll(leftEntries);
        }
        if (rightEntries != null && !rightEntries.isEmpty()) {
            results.addAll(rightEntries);
        }

        return results;
    }

    private TreeNode[] buildTestCaseTree() {
        TreeNode root = new TreeNode(3);
            // It's left subTree
            TreeNode leftSubTree = new TreeNode(5);
                // It's left subTree
                TreeNode left = new TreeNode(6);
                // It's right Sub tree
                TreeNode right = new TreeNode(2);
                    right.left = new TreeNode(7);
                    right.right = new TreeNode(4);
                leftSubTree.left = left;
                leftSubTree.right = right;
            // It's right subtree
            TreeNode rightSubTree = new TreeNode(1);
            rightSubTree.left = new TreeNode(0);
            rightSubTree.right = new TreeNode(8);
        root.left = leftSubTree;
        root.right = rightSubTree;
        return new TreeNode[]{root, leftSubTree};
    }
    public static void main(String[] args) {
        DistanceKNodes863 distanceKNodes863 = new DistanceKNodes863();
        TreeNode[] testCase = distanceKNodes863.buildTestCaseTree();
        TreeNode root = testCase[0];
        TreeNode target = testCase[1];
        System.out.println("The k distance nodes are " + distanceKNodes863.distanceK(root, target, 2));
    }
}


