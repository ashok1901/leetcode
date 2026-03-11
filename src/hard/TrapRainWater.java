package hard;

public class TrapRainWater {
    /**
     * left max and right max approach
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int len = height.length;
        int[] leftMax = new int[len];
        int[] rightMax = new int[len];
        int rightMaxSorFar = 0;
        for (int right = len - 1; right >=0; right--) {
            rightMax[right] = rightMaxSorFar;
            rightMaxSorFar = Math.max(rightMaxSorFar, height[right]);
        }
        int leftMaxSoFar = 0;
        for (int left = 0; left < len; left++) {
            leftMax[left] = leftMaxSoFar;
            leftMaxSoFar = Math.max(leftMaxSoFar, height[left]);
        }
        int totalWaterTrapped = 0;
        for (int index = 0; index < len; index++) {
            int waterHeightHere = Math.min(leftMax[index], rightMax[index]);
            if (waterHeightHere > height[index]) {
                int waterTrappedHere = waterHeightHere - height[index];
                totalWaterTrapped += waterTrappedHere;
            }
        }

        return totalWaterTrapped;
    }

    public static void main(String[] args) {
        TrapRainWater trapRainWater = new TrapRainWater();
        int[] nums = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println("Total water trapped " + trapRainWater.trap(nums));
        int[] ex2 = {4,2,0,3,2,5};
        System.out.println("Total water trapped " + trapRainWater.trap(ex2));
    }
}


