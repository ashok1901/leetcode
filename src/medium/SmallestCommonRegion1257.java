package medium;

import java.util.*;

public class SmallestCommonRegion1257 {
    private static class NaryTreeNode {
        private String value;
        private NaryTreeNode parent;
        private List<NaryTreeNode> children;
        public NaryTreeNode(String value) {
            this.value = value;
            this.children = new ArrayList<>();
        }

        public String getValue() {
            return value;
        }
        public List<NaryTreeNode> getChildren() {
            return children;
        }

        public void addChild(NaryTreeNode childNode) {
            this.children.add(childNode);
        }
    }

    private Map<String, NaryTreeNode> nodesMap = new HashMap<>();
    /**
     * Approach: n-ary tree and then it's lowest common ancestor.
     * @param regions
     * @param region1
     * @param region2
     * @return
     */
    public String findSmallestRegion(List<List<String>> regions, String region1, String region2) {
        NaryTreeNode root = buildTree(regions);
        NaryTreeNode region1Node = nodesMap.get(region1);
        List<NaryTreeNode> region1ToRootPath = new ArrayList<>();
        NaryTreeNode region1TempNode = region1Node;
        while(region1TempNode != null) {
            region1ToRootPath.add(region1TempNode);
            region1TempNode = region1TempNode.parent;
        }
        NaryTreeNode region2Node = nodesMap.get(region2);
        List<NaryTreeNode> region2ToRootPath = new ArrayList<>();
        NaryTreeNode region2TempNode = region2Node;
        while(region2TempNode != null) {
            region2ToRootPath.add(region2TempNode);
            region2TempNode = region2TempNode.parent;
        }

        // Now traverse the path from backward until keep getting common node.
//        int minLen = Math.min(region1ToRootPath.size(), region2ToRootPath.size());
//        int counter = minLen - 1;
//        while (counter >= 0 && (region1ToRootPath.get(counter) == region2ToRootPath.get(counter))) {
//            counter--;
//        }
//        if (counter < 0) {
//            return minLen == region1ToRootPath.size() ? region1ToRootPath.get(0).value : region2ToRootPath.get(0).value;
//        }
//
//        return minLen == region1ToRootPath.size() ? region1ToRootPath.get(counter + 1).getValue() : region2ToRootPath.get(counter + 1).getValue();

        Collections.reverse(region2ToRootPath);
        Collections.reverse(region1ToRootPath);
        int i = 0, j = 0;
        String lowestCommonAncestor = "";
        while (i < region1ToRootPath.size() && j < region2ToRootPath.size() && region1ToRootPath.get(i).equals(region2ToRootPath.get(j))) {
            lowestCommonAncestor = region1ToRootPath.get(i).value;
            i++;
            j++;
        }

        return lowestCommonAncestor;
    }

    private NaryTreeNode buildTree(List<List<String>> regions) {
        if (regions == null || regions.size() == 0) return null;
        // value to tree node map
        for (int i = 0; i < regions.size(); i++) {
            if (regions.get(i) == null || regions.get(i).isEmpty()) {
                // Blank array is not expected though. Throw exception for now.
                throw new IllegalArgumentException("Invalid region");
            }
            String parent = regions.get(i).get(0);
            if (!nodesMap.containsKey(parent)) {
                nodesMap.put(parent, new NaryTreeNode(parent));
            }

            NaryTreeNode root = nodesMap.get(parent);

            for (int j = 1; j < regions.get(i).size(); j++) {
                // Add children
                String value = regions.get(i).get(j);
                NaryTreeNode childNode = nodesMap.get(value);
                if (childNode == null) {
                    childNode = new NaryTreeNode(value);
                    nodesMap.put(value, childNode);
                }
                root.addChild(childNode);
                childNode.parent = root;
            }
        }

        // Now we have to find the root of the tree, we are maintaining parent so traverse up the tree.
        // Start with any node.
        NaryTreeNode potantialRoot = nodesMap.get(regions.get(0).get(0));
        NaryTreeNode potentialRootParent = potantialRoot.parent;
        while (potentialRootParent != null) {
            potantialRoot = potentialRootParent;
            potentialRootParent = potentialRootParent.parent;
        }
        return potantialRoot;
    }

    public static void main(String[] args) {
        SmallestCommonRegion1257 smallestCommonRegion1257 = new SmallestCommonRegion1257();
        List<List<String>> regions = new ArrayList<>();
        regions.add(Arrays.asList("Earth","North America","South America"));
        regions.add(Arrays.asList("North America","United States","Canada"));
        regions.add(Arrays.asList("United States","New York","Boston"));
        regions.add(Arrays.asList("Canada","Ontario","Quebec"));
        regions.add(Arrays.asList("South America","Brazil"));
        String region1 = "Quebec";
        String region2 = "New York";

        System.out.println(smallestCommonRegion1257.findSmallestRegion(regions, region1, region2));
    }
}


