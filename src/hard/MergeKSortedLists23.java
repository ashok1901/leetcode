package hard;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class MergeKSortedLists23 {
    public static class ListNode {
        public int val;
        public ListNode next;
        public ListNode() {}
        public ListNode(int val) { this.val = val; }
        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        Queue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);
        // Add all to the min heap.
        for (ListNode node : lists) {
            minHeap.add(node);
        }
        ListNode resultHead = null;
        ListNode resultCurrent = null;
        while (!minHeap.isEmpty()) {
            ListNode minNode = minHeap.poll();
            if (resultHead == null) {
                resultHead = minNode;
                resultCurrent = minNode;
            } else {
                resultCurrent.next = minNode;
                resultCurrent = resultCurrent.next;
            }
            if (minNode.next != null) {
                minHeap.add(minNode.next);
            }
        }

        return resultHead;
    }

    public void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        MergeKSortedLists23 mergeKSortedLists23 = new MergeKSortedLists23();
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(4);
        list1.next.next = new ListNode(5);

        ListNode list2 = new ListNode(1);
        list2.next = new ListNode(3);
        list2.next.next = new ListNode(4);

        ListNode list3 = new ListNode(2);
        list3.next = new ListNode(6);

        mergeKSortedLists23.printList(list1);
        mergeKSortedLists23.printList(list2);
        mergeKSortedLists23.printList(list3);
        mergeKSortedLists23.printList(mergeKSortedLists23.mergeKLists(new ListNode[]{list1, list2, list3}));

    }
}


