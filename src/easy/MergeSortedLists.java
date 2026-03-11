package easy;

public class MergeSortedLists {

    private class ListNode {
        private ListNode next;
        private int val;
    }
    /**
     * You are given the heads of two sorted linked lists list1 and list2.
     *
     * Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.
     *
     * Return the head of the merged linked list.
     * @param args
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode first = list1;
        ListNode second = list2;
        ListNode newListHead = null;
        ListNode newListCurrentEnd = null;
        while (first != null && second != null) {
            if (first.val <= second.val) {
                ListNode nextFirst = first.next;
                newListCurrentEnd = addToNewList(first, newListCurrentEnd);
                if (newListHead == null) {
                    newListHead = first;
                }
                first = nextFirst;
            } else {
                ListNode nextSecond = second.next;
                newListCurrentEnd = addToNewList(second, newListCurrentEnd);
                if (newListHead == null) {
                    newListHead = second;
                }
                second = nextSecond;
            }
        }

        if (first == null) {
            // First is exhausted but second might have some nodes
            newListCurrentEnd.next = second;
        }

        if (second == null) {
            // Second is exhausted but second might have some nodes
            newListCurrentEnd.next = first;
        }

        return newListHead;
    }

    private ListNode addToNewList(ListNode nodeToAdd, ListNode tailNode) {
        ListNode nodeToAddTemp = nodeToAdd;
        if (tailNode == null) {
            nodeToAddTemp.next = null;
            return nodeToAdd;
        }
        tailNode.next = nodeToAdd;
        nodeToAddTemp.next = null;
        return nodeToAdd;
    }

    public static void main(String[] args) {
    }
}


