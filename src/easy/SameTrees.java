package easy;

public class SameTrees {
    class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
    }
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null && q != null) {
            return  false;
        }

        if (p != null && q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        // If nodes are not null then make sure their left and right subtrees are same.
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static void main(String[] args) {
    }
}


