package medium;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    private class Node {
        private int key;
        private int value;
        private Node prev;
        private Node next;
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node head;
    private Node tail;
    private int size;
    private int capacity;
    private Map<Integer, Node> lruMap;

    /**
     * Approach : Hashmap and Doubly linked list
     * @param capacity
     */
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.lruMap = new HashMap<>();
    }

    public int get(int key) {
        if (lruMap.containsKey(key)) {
            // Cache hit
            Node node = lruMap.get(key);
            removeNode(node);
            addToHead(node);
            return node.value;
        }

        return -1;
    }

    public void put(int key, int value) {
        if (lruMap.containsKey(key)) {
            Node node = lruMap.get(key);
            node.value = value;
            removeNode(node);
            addToHead(node);
            return;
        }
        if (size == capacity) {
            evictNode();
            --size;
        }
        Node node = new Node(key, value);
        lruMap.put(key, node);
        addToHead(node);
        size++;
    }

    private void evictNode() {
        // Remove the tail node
        Node tailNode = tail;
        removeNode(tailNode);
        lruMap.remove(tailNode.key);
    }

    private void removeNode(Node node) {
        if (node == null) {
            return;
        }
        // Single node linked list
        if (head == tail) {
            head = null;
            tail = null;
            return;
        }
        // Case - 1 : When node is the first node then head will change.
        if (node.prev == null) {
            head = node.next;
            head.prev = null;
            return;
        }
        // Case - 2 : When node is the last node then tail will change.
        if (node.next == null) {
            tail = node.prev;
            tail.next = null;
            return;
        }

        // Case - 3: Adjust the pointers.
        Node prevNode = node.prev;
        Node nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        node.prev = null;
        node.next = null;
    }

    private void addToHead(Node node) {
        // When this is first node itself.
        if (head == null) {
            head = node;
            tail = node;
            return;
        }

        node.next = head;
        head.prev = node;
        head = node;
    }

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        System.out.println(lRUCache.get(1));    // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        System.out.println(lRUCache.get(2));    // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        System.out.println(lRUCache.get(1));    // return -1 (not found)
        System.out.println(lRUCache.get(3));    // return 3
        System.out.println(lRUCache.get(4));    // return 4
    }
}


