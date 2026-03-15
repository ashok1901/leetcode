package easy;

public class CanPlaceFlower605 {

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (n == 0) {
            return true;
        }
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 0) {
                boolean freeBefore = (i == 0) || flowerbed[i - 1] == 0;
                boolean freeAfter = (i == flowerbed.length - 1) || flowerbed[i + 1] == 0;
                if (freeBefore && freeAfter) {
                    flowerbed[i] = 1;
                    --n;
                    // All flowers are placed.
                    if (n == 0) return true;
                }
            }
        }

        return false;
    }


    public static void main(String[] args) {
        CanPlaceFlower605 canPlaceFlower605 = new CanPlaceFlower605();
        int[] flowerbed = {1,0,0,0,1}; int n = 1;
        System.out.println(canPlaceFlower605.canPlaceFlowers(flowerbed, n));
        int[] flowerbed1 = {1,0,0,0,1}; int n1 = 2;
        System.out.println(canPlaceFlower605.canPlaceFlowers(flowerbed1, n1));
    }
}


