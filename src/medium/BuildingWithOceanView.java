package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BuildingWithOceanView {

    /**
     * Approach: Find max from right in maxInRight array
     * if (heigts[i] > maxInRight[i] then building i has ocean view otherwise no
     * @param heights
     * @return
     */
    public int[] findBuildings(int[] heights) {
        int[] maxInRight = new int[heights.length];
        maxInRight[maxInRight.length - 1] = 0;
        int maxSoFar = heights[heights.length  - 1];
        for (int index = maxInRight.length - 2; index >= 0; index--) {
            maxSoFar = Math.max(maxSoFar, heights[index + 1]);
            maxInRight[index] = maxSoFar;
        }

        List<Integer> resList = new ArrayList<>();
        for (int index = 0; index < heights.length; index++) {
            if (heights[index] > maxInRight[index]) {
                resList.add(index);
            }
        }

        return resList.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        BuildingWithOceanView buildingWithOceanView = new BuildingWithOceanView();
        int[] heights1 = {4,2,3,1};
        System.out.println("Ocean view buildings " + Arrays.toString(buildingWithOceanView.findBuildings(heights1)));
        int[] heights2 = {4,3,2,1};
        System.out.println("Ocean view buildings " + Arrays.toString(buildingWithOceanView.findBuildings(heights2)));
        int[] heights3 = {1,3,2,4};
        System.out.println("Ocean view buildings " + Arrays.toString(buildingWithOceanView.findBuildings(heights3)));
    }
}


