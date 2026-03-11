package easy;

public class ClosestBSTValue {

    class TreeNode{
        TreeNode left, right;
        int val;
    }

    private int currentClosestVal = Integer.MAX_VALUE;
    public int closestValue(TreeNode root, double target) {
       closestValueRec(root, target);
       return currentClosestVal;
    }

    private int closestValueRec(TreeNode root, double target) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        int leftSide = closestValueRec(root.left, target);
        int rightSide = closestValueRec(root.right, target);

        double leftDiff = Math.abs(target - leftSide);
        double rightDiff = Math.abs(target - rightSide);
        double rootDiff = Math.abs(target - root.val);
        double currentClosesDiff = Math.abs(target - currentClosestVal);
        if (leftDiff <= currentClosesDiff) {
            currentClosestVal = leftDiff == currentClosesDiff ? Math.min(leftSide, currentClosestVal) : leftSide;
        }
        currentClosesDiff = Math.abs(target - currentClosestVal);
        if (rightDiff <= currentClosesDiff) {
            currentClosestVal = rightDiff == currentClosesDiff ? Math.min(rightSide, currentClosestVal) : rightSide;
        }
        currentClosesDiff = Math.abs(target - currentClosestVal);
        if (rootDiff <= currentClosesDiff) {
            currentClosestVal = rootDiff == currentClosesDiff ? Math.min(root.val, currentClosestVal) : root.val;
            currentClosesDiff = Math.abs(target - currentClosestVal);
        }

        return currentClosestVal;
    }

    public static void main(String[] args) {
    }
}


