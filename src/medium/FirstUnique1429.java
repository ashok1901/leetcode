package medium;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FirstUnique1429 {
    /**
     * Approach:
     * - Maintain a Doubly linked list of unique integers  S: O(n)
     * - Maintain hashmap with int to node map S:O(n)
     * - If same number added again then remove that from the list  T: O(1)
     * - If this number is added first time then add it to the tail. T:O(1)
     *
     * @param nums
     */
    private static class ListNode {
        int val;
        ListNode next;
        ListNode prev;
        ListNode(int x) {
            val = x;
            next = null;
            prev = null;
        }

    }
    ListNode head;
    ListNode tail;
    Map<Integer, ListNode> map = new LinkedHashMap<>();
    public FirstUnique1429(int[] nums) {
        for (int in : nums) {
            add(in);
        }
    }

    public int showFirstUnique() {
        if (head == null) {
            return -1;
        }

        return head.val;
    }

    public void add(int value) {
        if (map.containsKey(value)) {
            ListNode node = map.get(value);
            removeNode(node);
        } else {
            ListNode newNode = new ListNode(value);
            map.put(value, newNode);
            addNodeAtTail(newNode);
        }
    }

    private void addNodeAtTail(ListNode node) {
        if (tail == null) {
            tail = node;
            head = tail;
            return;
        }
        tail.next = node;
        node.prev = tail;
        tail = node;
    }

    private void removeNode(ListNode node) {
        // Skip the zero node
        if (node == null) {
            return;
        }

        // Case : Head and Tail are same i.e single node case
        if (head == tail) {
            head = null;
            tail = null;
        }

        // Case : Old node from the map which has been deleted from the linked list on duplicate.
        if (node.prev == null && node.next == null) {
            return;
        }

        // Case: Head node deletion
        if (node.prev == null) {
            head = node.next;
        } else {
            node.prev.next = node.next;
        }

        // Case: Tail node deletion
        if (node.next == null) {
            tail = tail.prev;
        } else {
            node.next.prev = node.prev;
        }
        node.next = null;
        node.prev = null;
    }

    public static void main(String[] args) {
        FirstUnique1429 firstUnique1429 = new FirstUnique1429(new int[]{2,3,5});
        System.out.println(firstUnique1429.showFirstUnique());
        firstUnique1429.add(5);
        System.out.println(firstUnique1429.showFirstUnique());
        firstUnique1429.add(2);
        System.out.println(firstUnique1429.showFirstUnique());
        firstUnique1429.add(3);
        System.out.println(firstUnique1429.showFirstUnique());

        System.out.println("Failing test case");
        int[] s = {439,264,80,424,379,943,654,152,23,613,811,417,554,626,920,450,344,931,444,183,525,843,310,515,332,966,774,121,409,194,523,828,850,621,609,739,847,678,189,499,708,594,483,528,902,191,219,297,715,283,402,962,365,4,577,300,356,892,708,326,98,378,80,736,99,215,472,991,603,612,896,374,104,363,926,744,862,389,838,636,167,85,274,574,762,163,108,223,79,487,896,32,202,196,751,654,994,627,93,101,495,379,511,519,151,95,517,209,518,218,905,708,383,768,355,252,982,984,667,239,42,278,76,924,523,67,169,792,69,135,546,869,670,597,631,4,967,84,696,687,603,897,126,333,912,398,52,350};
//        int[] s = {439,264,80,424,379,943,654,152,23,613,811,417,554,626,920,450,344,931,444,183,525,843,310,515,332,966,774,121,409,194,523,828,850,621,609,739,847,678,189,499,708,594,483,528,902,191,219,297,715,283,402,962,365,4,577,300,356,892,708,326,98,378,80,736,99,215,472,991,603,612,896,374,104,363,926,744,862,389,838,636,167,85,274,574,762,163,108,223,79,487,896,32,202,196,751,654,994,627,93,101,495,379,511,519,151,95,517,209,518,218,905,708};
//        int[] s = {439,264,80,424,379,943,654};

        FirstUnique1429 firstUnique = new FirstUnique1429(s);
        System.out.println(firstUnique.showFirstUnique());


    }
}


