package easy;

public class FirstBadVersion {

    /**
     * Binary Search Approach
     * @param n
     * @return
     */
    public int firstBadVersion(int n) {
        int left = 1, right = n;
        while(left < right) {
            int mid = left + (right - left)/2;
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    /**
     * This is dummy implementation to keep code compiling. In Leetcode this function's implementation
     * is provided
     * @param n
     * @return
     */
    private boolean isBadVersion(int n) {
        return n%2 == 0;
    }

    public static void main(String[] args) {
    }
}


