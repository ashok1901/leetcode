package easy;

public class MovingAverage {

    // Cyclic array implementation
    private int size;
    private int[] data;
    private double totalSum = 0.0;
    private int elements = 0;
    private int currentIndex = -1;
    public MovingAverage(int size) {
        this.size = size;
        this.   data = new int[size];
    }

    public double next(int val) {
        if (size == 0) {
            return 0;
        }
        if (elements <= size - 1) {
            data[elements] = val;
            elements++;
            totalSum = totalSum + val;
            currentIndex = (currentIndex + 1)%size;
            return  totalSum/elements;
        }

        int nextIndexToOverwrite = (currentIndex + 1)%size;
        totalSum = totalSum - data[nextIndexToOverwrite] + val;
        data[nextIndexToOverwrite] = val;
        return totalSum/size;
    }

    public static void main(String[] args) {
        MovingAverage movingAverage = new MovingAverage(3);
        System.out.print(" " + movingAverage.next(1));
        System.out.print(" " + movingAverage.next(10));
        System.out.print(" " + movingAverage.next(3));
        System.out.print(" " + movingAverage.next(5));
    }
}


