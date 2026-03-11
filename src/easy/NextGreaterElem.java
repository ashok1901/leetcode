package easy;

import java.util.Stack;

public class NextGreaterElem {

    //
    // NOT READY
    //
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] nextGreater = new int[nums2.length];
        populateNextGreaterByMonotonicStack(nums2, nextGreater);
        int[] result = new int[nums1.length];
        for (int index = 0; index < nums1.length; index++) {
            for (int grIndex = 1; grIndex < nums2.length; grIndex++) {
                if (nums1[index] == nums2[grIndex]) {
                    result[index] = nextGreater[grIndex];
                }
            }
        }

        return result;
    }

    private void populateNextGreaterByMonotonicStack(int[] nums2, int[] nextGreater) {
        Stack<Integer> monoIncreasingStack = new Stack<>();
        int index = nums2.length - 1;
        nextGreater[index] = -1;
        monoIncreasingStack.push(nums2[index]);
        --index;
        while(index >= 0) {
            int thisNum = nums2[index];
            if (monoIncreasingStack.isEmpty()) {
                monoIncreasingStack.push(thisNum);
                nextGreater[index] = -1;
                --index;
            } else {
                Integer stackTop = monoIncreasingStack.peek();
                if (thisNum < stackTop) {
                    nextGreater[index] = stackTop;
                    --index;
                } else {
                    while (stackTop <= thisNum && !monoIncreasingStack.isEmpty()) {
                        stackTop = monoIncreasingStack.pop();
                    }
                    if (monoIncreasingStack.isEmpty()) {
                        nextGreater[index] = -1;
                        monoIncreasingStack.push(thisNum);
                        --index;
                    } else {
                        nextGreater[index] = stackTop;
                        monoIncreasingStack.push(thisNum);
                        --index;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        NextGreaterElem nextGreaterElem = new NextGreaterElem();
        int[] nums1 = {4,1,2}; int[] nums2 = {1,3,4,2};
        System.out.println("Result " + nextGreaterElem.nextGreaterElement(nums1, nums2));
    }
}


