package easy;

public class SmallestLetterGreaterThanTarget744 {

    /**
     * Approach: Binary search
     * - if letters[mid] < target then search in right including mid
     * - otherwise search in left including mid
     * - In the last if mid is last element then return -1 otherwise return next of mid
     *
     * @param letters
     * @param target
     * @return
     */
    public char nextGreatestLetter(char[] letters, char target) {
        int left = 0, right = letters.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (letters[mid] <= target) {
                left = mid;
            } else {
                right = mid;
            }
        }

        if (letters[left] > target) return letters[left];
        if (letters[right] > target) return letters[right];
        // No such letter case.
        return letters[0];
    }

    /**
     * Binary search best practices
     * 1. User left <= right in the main while loop, otherwise it will skip one element.
     * 2. Always be careful when you user left = mid or right = mid, it might endup in infinite loop.
     * 3. Compute mid = left + (right - left)/2 to avoid risk of overflow if you compute mid like mid = (left+right)/2
     *
     * Below is the crisp code for this problem
     * @param args
     */
    public char nextGreatestLetter1(char[] letters, char target) {
        int left = 0, right = letters.length - 1; int mid;
        while(left <= right) {
            mid = left + (right - left) / 2;
            if (letters[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if (left == letters.length) return letters[0]; else return letters[left];
    }

    public static void main(String[] args) {
        SmallestLetterGreaterThanTarget744 smallestLetterGreaterThanTarget744 = new SmallestLetterGreaterThanTarget744();
        char[] letters = {'c','f','j'}; char target = 'a';
        System.out.println("smallestGreaterThanTarget : " +
                smallestLetterGreaterThanTarget744.nextGreatestLetter1(letters, target));
        char[] letters1 = {'c','f','j'}; char target1 = 'c';
        System.out.println("smallestGreaterThanTarget : " +
                smallestLetterGreaterThanTarget744.nextGreatestLetter1(letters1, target1));

        char[] letters2 = {'x','x','y','y'};  char target2 = 'z';
        System.out.println("smallestGreaterThanTarget : " +
                smallestLetterGreaterThanTarget744.nextGreatestLetter1(letters2, target2));

        char[] letters3 = {'e','e','e','e','e','e','n','n','n','n'}; char target3 = 'e';
        System.out.println("smallestGreaterThanTarget : " +
                smallestLetterGreaterThanTarget744.nextGreatestLetter1(letters3, target3));

    }
}


