package medium;

import misc.cache.lru.Node;

import java.util.List;
import java.util.Map;

public class NestedListWeigthSum364 {
    public interface NestedInteger {
          // @return true if this NestedInteger holds a single integer, rather than a nested list.
          public boolean isInteger();
          // @return the single integer that this NestedInteger holds, if it holds a single integer
          // Return null if this NestedInteger holds a nested list
          public Integer getInteger();
          // Set this NestedInteger to hold a single integer.
          public void setInteger(int value);
          // Set this NestedInteger to hold a nested list and adds a nested integer to it.
          public void add(NestedInteger ni);
          // @return the nested list that this NestedInteger holds, if it holds a nested list
          // Return empty list if this NestedInteger holds a single integer
          public List<NestedInteger> getList();
    }

    private int maxDepth;
    private int maxDepthSum;
    public int depthSumInverse(List<NestedInteger> nestedList) {
        maxDepth = computeMaxDepth(nestedList);
        maxDepthSum = computeDepthSum(nestedList, 1);
        return maxDepthSum;
    }

    private int computeMaxDepth(List<NestedInteger> nestedList) {
        int localMaxDepth = 1;
        for (NestedInteger nestedInteger : nestedList) {
            if (!nestedInteger.isInteger()) {
                localMaxDepth = Math.max(localMaxDepth, 1 + computeMaxDepth(nestedInteger.getList()));
            }
        }
        return localMaxDepth;
    }

    private int computeDepthSum(List<NestedInteger> nestedList, int depth) {
        int localDepthSum = 0;
        for (NestedInteger nestedInteger : nestedList) {
            if (!nestedInteger.isInteger()) {
                localDepthSum += computeDepthSum(nestedInteger.getList(),  depth + 1);
            } else  {
                int weight = maxDepth - depth + 1;
                localDepthSum += nestedInteger.getInteger()*weight;
            }
        }

        return localDepthSum;
    }

    public static void main(String[] args) {
        // Example : [[1,1],2,[1,1]]
    }

}


