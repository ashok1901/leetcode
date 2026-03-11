package medium;

import java.util.List;

public class NestedListSum {
    /**
     * // This is the interface that allows for creating nested lists.
     * // You should not implement it, or speculate about its implementation
     * public interface NestedInteger {
     *     // Constructor initializes an empty nested list.
     *     public NestedInteger();
     *
     *     // Constructor initializes a single integer.
     *     public NestedInteger(int value);
     *
     *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
     *     public boolean isInteger();
     *
     *     // @return the single integer that this NestedInteger holds, if it holds a single integer
     *     // The result is undefined if this NestedInteger holds a nested list
     *     public Integer getInteger();
     *
     *     // Set this NestedInteger to hold a single integer.
     *     public void setInteger(int value);
     *
     *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
     *     public void add(NestedInteger ni);
     *
     *     // @return the nested list that this NestedInteger holds, if it holds a nested list
     *     // The result is undefined if this NestedInteger holds a single integer
     *     public List<NestedInteger> getList();
     * }
     */

    public interface NestedInteger {
        public boolean isInteger();
        public Integer getInteger();
        public void setInteger(int value);
        public void add(NestedInteger ni);
        public List<NestedInteger> getList();
    }
    private int overallSum = 0;
    public int depthSum(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.isEmpty()) {
            return 0;
        }

        return recursiveSum(nestedList, 1);
    }

    private int recursiveSum(List<NestedInteger> nestedList, int depth) {
        if (nestedList.isEmpty()) {
            return 0;
        }
        int localSum = 0;
        for (NestedInteger nestedInteger : nestedList) {
            if (nestedInteger.isInteger()) {
                localSum += nestedInteger.getInteger() * depth;
            } else {
                localSum += recursiveSum(nestedInteger.getList(), depth + 1);
            }
        }

        return localSum;
    }

    public static void main(String[] args) {
    }
}


