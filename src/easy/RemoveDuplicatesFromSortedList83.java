package easy;


public class RemoveDuplicatesFromSortedList83 {

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

    public ListNode deleteDuplicates(ListNode head) {
        ListNode tmp = head;
        while(tmp != null){
            ListNode nextNode = findNextPossibleNode(tmp);
            tmp.next = nextNode; // Even if next node is null it means end of link if list here
            tmp = tmp.next;
        }

        return head;
    }

    private ListNode findNextPossibleNode(ListNode startFrom) {
        if (startFrom == null) {
            return null;
        }
        int value = startFrom.val;
        ListNode next = startFrom.next;
        while(next != null && next.val == value) {
            next = next.next;
        }

        return next;
    }

    public void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
        // Adding a new line char
        System.out.println("");
    }

    public static void main(String[] args) {
        RemoveDuplicatesFromSortedList83 removeDuplicatesFromSortedList83 = new RemoveDuplicatesFromSortedList83();
        // ->1->1->2
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);

        removeDuplicatesFromSortedList83.printList(removeDuplicatesFromSortedList83.deleteDuplicates(head));
        // ->1->1->2->3->3
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(1);
        head1.next.next = new ListNode(2);
        head1.next.next.next = new ListNode(3);
        head1.next.next.next.next = new ListNode(3);
        removeDuplicatesFromSortedList83.printList(removeDuplicatesFromSortedList83.deleteDuplicates(head1));
    }
}


