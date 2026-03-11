package easy;

public class ConcatArray {

    public int[] getConcatenation(int[] nums) {
        int size = nums.length;
        int[] ans = new int[size * 2];
        for (int index = 0; index < size; index++) {
            ans[index] = nums[index];ans[index + size] = nums[index];
        }

        return ans;
    }

    public static void main(String[] args) {
    }
}


