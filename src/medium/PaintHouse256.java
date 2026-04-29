package medium;

import java.util.Arrays;

public class PaintHouse256 {

    public int minCost(int[][] costs) {
        if (costs.length == 0) {
            return 0;
        }
        int[][] mem = new int[costs.length][costs[0].length];
        for (int i = 0; i < costs.length; i++) {
            Arrays.fill(mem[i], -1);
        }

        return Math.min(minPaintingCost(costs, 0, 0, mem),
                Math.min(minPaintingCost(costs, 1, 0, mem),
                         minPaintingCost(costs, 2, 0, mem)));
    }

    /**
     * Approach : Backtracking but this will give time limit exceed so either DP or memoization has to be added.
     * @param args
     */
    private int minPaintingCost(int[][] costs, int lastColor, int currentHouse) {
        // No more house to paint
        if (currentHouse == costs.length) {
            return 0;
        }

        // I have option of only two colors or 3 colors for the first house.
        int minCost = Integer.MAX_VALUE;
        if (lastColor == -1) {
            // First house itself.
            minCost = Math.min(minPaintingCost(costs, 0, currentHouse + 1) + costs[currentHouse][0], minCost);
            minCost = Math.min(minPaintingCost(costs, 1, currentHouse + 1) + costs[currentHouse][1], minCost);
            minCost = Math.min(minPaintingCost(costs, 2, currentHouse + 1) + costs[currentHouse][2], minCost);
        } else {
            int[] remainigColors = findRemainingColors(lastColor);
            minCost = Math.min(minPaintingCost(costs, remainigColors[0], currentHouse + 1) + costs[currentHouse][remainigColors[0]], minCost);
            minCost = Math.min(minPaintingCost(costs, remainigColors[1], currentHouse + 1) + costs[currentHouse][remainigColors[1]], minCost);
        }

        return minCost;
    }

    /**
     * Approach : Backtracking with memoization
     * mem per house per color cost
     * @param args
     */
    private int minPaintingCost(int[][] costs, int currentColor, int currentHouse, int[][] mem) {
        // No more house to paint
        if (currentHouse == costs.length) {
            return 0;
        }

        if (mem[currentHouse][currentColor] != -1) {
            return mem[currentHouse][currentColor];
        }

        int totalCost = costs[currentHouse][currentColor];
        if (currentHouse == costs.length - 1) {
            // last house to paint
            totalCost = costs[currentHouse][currentColor];
        } else {
            if (currentColor == 0) {
                totalCost += Math.min(minPaintingCost(costs, 1, currentHouse + 1),
                                      minPaintingCost(costs, 2, currentHouse + 1));
            } else if (currentColor == 1) {
                totalCost += Math.min(minPaintingCost(costs, 0, currentHouse + 1),
                                      minPaintingCost(costs, 2, currentHouse + 1));
            } else {
                totalCost += Math.min(minPaintingCost(costs, 0, currentHouse + 1),
                                      minPaintingCost(costs, 1, currentHouse + 1));
            }
        }
        mem[currentHouse][currentColor] = totalCost;
        return totalCost;
    }

    private int[] findRemainingColors(int lastColor) {
        switch (lastColor) {
            case 0: return new int[]{1, 2};
            case 1: return new int[]{0, 2};
            case 2: return new int[]{0, 1};
            default: throw new IllegalArgumentException("Invalid color " + lastColor);
        }
    }

    public static void main(String[] args) {
        PaintHouse256 paintHouse256 = new PaintHouse256();
        int[][] costs = {{17,2,17},{16,16,5},{14,3,19}};
        System.out.println(paintHouse256.minCost(costs));
        int[][] costs2 = {{7,6,2}};
        System.out.println(paintHouse256.minCost(costs2));
    }
}


