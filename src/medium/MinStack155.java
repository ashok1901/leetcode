package medium;

import java.util.Stack;

public class MinStack155 {
    /**
     * Approach: Maintain two stacks
     * - One to track min element and other for standard stack operations
     * - If pushed element is smaller or equal than min then push it to both stacks
     * - Otherwise just push it to the standard stack.
     * - If popped element is same as min then remove it from both the stacks
     * - Otherwise remove it only from the standard stack
     */
    private Stack<Integer> standardStack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();
    public MinStack155() {

    }

    public void push(int val) {
        if (minStack.isEmpty()) {
            minStack.push(val);
            standardStack.push(val);
            return;
        }

        // Check what is min at this moment
        int currentMin = minStack.peek();
        if (val <= currentMin) {
            // Then update the min stack also
            minStack.push(val);
        }

        standardStack.push(val);
    }

    public void pop() {
        if (standardStack.isEmpty())  {
            return;
        }
        int currentMin = minStack.peek();
        // Pop from the standard stack.
        int element = standardStack.pop();
        // If this is smaller element itself then pop from the min stack also
        if (element == currentMin) {
            minStack.pop();
        }
    }

    public int top() {
        return standardStack.isEmpty() ? -1 : standardStack.peek();
    }

    public int getMin() {
        return minStack.isEmpty() ? -1 : minStack.peek();
    }

    public static void main(String[] args) {
        MinStack155 minStack155 = new MinStack155();
        minStack155.push(-2);
        minStack155.push(0);
        minStack155.push(-3);
        System.out.println(minStack155.getMin()); // return -3
        minStack155.pop();
        System.out.println(minStack155.top());    // return 0
        System.out.println(minStack155.getMin()); // return -2
    }
}


