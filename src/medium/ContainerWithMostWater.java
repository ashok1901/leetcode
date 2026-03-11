package medium;

public class ContainerWithMostWater {

    public static int computeMaxWater(int[] data) {
        if (data == null || data.length <= 1) {
            return 0;
        }

        int rightIndex = 0, leftIndex = data.length - 1 ;
        int maxWater = 0;
        while(rightIndex <= leftIndex) {
            maxWater = Math.max(maxWater, (leftIndex - rightIndex) * Math.min(data[rightIndex], data[leftIndex]));
            if (data[rightIndex] < data[leftIndex]) {
                rightIndex++;
            } else {
                leftIndex--;
            }
        }

        return maxWater;
    }

    public static void main(String[] args) {
        int[] data = {1,8,6,2,5,4,8,3,7};
        int maxWater = computeMaxWater(data);
        System.out.println("Max water is : " + maxWater);
    }
}
