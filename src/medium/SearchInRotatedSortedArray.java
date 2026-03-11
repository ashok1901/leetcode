package medium;

public class SearchInRotatedSortedArray {
    private static int searchBinary(int left, int right, int target, int[] arr) {
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            }
            if (arr[left] <= arr[mid]) {
                // It means left half is sorted
                if (arr[left] <= target && target <= arr[mid]) {
                    // Search in the left half
                    right = mid - 1;
                } else {
                    // Search in the right half
                    left = mid + 1;
                }
            } else {
                // It means right half is sorted
                if (arr[mid] <= target && target <= arr[right]) {
                    // Search in the right half
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {4,5,6,7,0,1,2};
        System.out.println("Element is at " + searchBinary(0, arr.length - 1, 0, arr));
        System.out.println("Element is at " + searchBinary(0, arr.length - 1, 5, arr));
        System.out.println("Element is at " + searchBinary(0, arr.length - 1, 12, arr));
    }
}


