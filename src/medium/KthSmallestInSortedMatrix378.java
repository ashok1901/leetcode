package medium;

import java.util.*;

public class KthSmallestInSortedMatrix378 {

    /**
     * Approach : Maintain n pointers column wise and increment the smaller one.
     * - To find the smaller one to move, use a min heap.
     * - Once increment happened k times then smaller element will be the kth smallest.
     *
     * Heap Structure :
     *  [rowIndex, colIndex, value]
     *      Heap comparator would be based on the value
     */
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0) {
            return -1;
        }
        // TODO : Write the right comparator such that elements are sorted by values.
        Queue<Integer[]> minHeap = new PriorityQueue<>(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                return o1[2].compareTo(o2[2]);
            }
        });
        int numRows = matrix.length;
        int numCols = matrix[0].length;
        // Add first column to the heap.
        for (int index = 0; index < numRows; index++) {
            Integer[] entry = new Integer[3];
            entry[0] = index;
            entry[1] = 0;
            entry[2] = matrix[index][0];      // First column in the corresponding row.
            pushElementInHeap(minHeap, entry, numRows);
        }
        int counter = 0;
        while (!minHeap.isEmpty() && counter < k - 1) {
            Integer[] smallest = minHeap.poll();
            counter++;          // One smallest element is consumed so increment the counter.
            int smallestRowIndex = smallest[0];
            int smallestColIndex = smallest[1];
            if (smallestColIndex >= numCols - 1) {
                continue;
            }
            Integer[] nextElementInThatRow = new Integer[3];
            nextElementInThatRow[0] = smallestRowIndex;
            nextElementInThatRow[1] = smallestColIndex + 1;
            nextElementInThatRow[2] = matrix[smallestRowIndex][smallestColIndex + 1];
            pushElementInHeap(minHeap, nextElementInThatRow, numRows);
        }

        // Value at the top of the min heap would be the kth smallest element.
        return minHeap.isEmpty() ? -1 : minHeap.peek()[2];
    }

    private void pushElementInHeap(Queue<Integer[]> minHeap, Integer[] element, int heapSize) {
        if (minHeap.size() >= heapSize) {
            minHeap.remove();
        }
        minHeap.add(element);
    }

    public static void main(String[] args) {
        KthSmallestInSortedMatrix378 kthSmallestInSortedMatrix378 = new KthSmallestInSortedMatrix378();
        int[][] matrix = {{1,5,9},{10,11,13},{12,13,15}}; int k = 8;
        System.out.println("kth smallest is : " + kthSmallestInSortedMatrix378.kthSmallest(matrix, k));
        int[][] matrix1 = {{-5}}; int k1 = 1;
        System.out.println("kth smallest is : " + kthSmallestInSortedMatrix378.kthSmallest(matrix1, k1));
    }
}


