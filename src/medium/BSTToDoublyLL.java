package medium;

public class BSTToDoublyLL {

    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    };

    /**
     * Approach : Build left and right recursively then attach to the root then return first node of that LL.
     * Returning first node is important as it will help connection with the parent easy.
     * @param root
     * @return
     */
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return root;
        }
        Node newHead = buildLLRecurive(root);
        // Now make it circular
        Node tmp = newHead;
        while (tmp.right != null) {
            tmp = tmp.right;
        }
        // Connect head and tail
        newHead.left = tmp;
        tmp.right = newHead;
        return newHead;
    }

    private Node buildLLRecurive(Node root) {
        if (root == null) {
            return null;
        }
        Node left = buildLLRecurive(root.left);
        Node right = buildLLRecurive(root.right);
        Node leftTail = left;
        if (left != null) {
            while (leftTail.right != null) {
                leftTail = leftTail.right;
            }
        }
        Node rightHead = right;
        if (right != null) {
            // Do we need this loop ?? Because recursive call will return head of LL from the recursive call.
            // Even if we leave it here, this loop will not run actually as rightHead.left would be null.
            while (rightHead.left != null) {
                rightHead = rightHead.left;
            }
        }
        root.left = leftTail;
        if (leftTail != null) {
            leftTail.right = root;
        }
        root.right = rightHead;
        if (rightHead != null) {
            rightHead.left = root;
        }
        Node llStart = root;
        while (llStart.left != null) {
            llStart = llStart.left;
        }
        return llStart;
    }

    public static void main(String[] args) {
    }
}


