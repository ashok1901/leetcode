package medium;

import java.util.Arrays;

public class QuadTree427 {
// Definition for a QuadTree node.
    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }


    public Node construct(int[][] grid) {
        return buildRecursively(0, 0, grid.length, grid);

    }

    private Node buildRecursively(int startRow, int startCol, int length, int[][] grid) {
        System.out.println(String.format("%s %s %s", startRow, startCol, length));
        if (areAllSame(startRow, startCol, length, grid)) {
            return new Node(grid[startRow][startCol] == 1 ? true : false, true);
        }
        // Split and recurse
        Node topLeft = buildRecursively(startRow, startCol, length/2,  grid);
        Node topRight = buildRecursively(startRow, startCol + length/2, length/2, grid);
        Node bottomLeft = buildRecursively(startRow + length/2, startCol, length/2, grid);
        Node bottomRight = buildRecursively(startRow + length/2, startCol + length/2, length/2, grid);
        Node root = new Node(false, false);
        root.topLeft = topLeft;
        root.topRight = topRight;
        root.bottomLeft = bottomLeft;
        root.bottomRight = bottomRight;

        return root;
    }

    private boolean areAllSame(int startRow, int startCol, int length, int[][] grid) {
        int expectedValue = grid[startRow][startCol];
        for (int row = startRow; row < startRow + length; row++) {
            for (int col = startCol; col < startCol + length; col++) {
                if (grid[row][col] != expectedValue) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        QuadTree427 quadTree427 = new QuadTree427();
        int[][] grid = {{0,1},{1,0}};
        quadTree427.construct(grid);
        int[][] grid2 = {{1,1,0,0},{0,0,1,1},{1,1,0,0},{0,0,1,1}};
        quadTree427.construct(grid2);
    }
}


