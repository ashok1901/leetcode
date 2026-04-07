package medium;

import java.util.*;

public class CloneGraph133 {
    class Node {
        public int val;
        public List<Node> neighbors;
        public Node(int _val) {
            this.val = _val;
            this.neighbors = new ArrayList<>();
        }

        public List<Node> getNeighbors() {
            return neighbors;
        }
    }

    /**
     * Approach : Iterate over original graph and keep building cloned graph.
     * Note: We have to keep track of new nodes created for the values as nodes will be traversed in the
     * order as per the original graph and some nodes would have been already created and some yet to create.
     * @param node
     * @return
     */
    public Node cloneGraph(Node node) {
        return cloneGraphRecursively(node, new HashMap<>(), new HashSet<>());
    }

    private Node cloneGraphRecursively(Node originalNode, Map<Integer, Node> newNodesMap, Set<Node> alreadyBuiltNodes) {
        Node newNode;
        if (!newNodesMap.containsKey(originalNode.val)) {
            newNode = new Node(originalNode.val);
            newNodesMap.put(originalNode.val, newNode);
        } else {
            return newNodesMap.get(originalNode.val);
        }
        // Connect adjacent nodes
        List<Node> origNeighbors = originalNode.getNeighbors();
        for (Node origNeighbor : origNeighbors) {
            if (!alreadyBuiltNodes.contains(origNeighbor)) {
                newNode.getNeighbors().add(cloneGraphRecursively(origNeighbor, newNodesMap, alreadyBuiltNodes));
            }
        }
        alreadyBuiltNodes.add(newNode);
        return newNode;
    }

    public static void main(String[] args) {
    }
}


