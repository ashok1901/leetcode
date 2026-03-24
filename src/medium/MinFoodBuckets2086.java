package medium;

public class MinFoodBuckets2086 {

    public int minimumBuckets(String hamsters) {
        // Unfortunately this is a tricky problem
         return (hamsters.equals("H") || hamsters.startsWith("HH") || hamsters.endsWith("HH") || hamsters.contains("HHH")) ?
                         -1 : hamsters.replace("H.H", "  ").length() - hamsters.replace("H", "").length();
    }

    /**
     * Approach: Two pointers
     * - pointer first points to first hamster
     * - pointer second points to next hamser
     * - If diff is 1(adjecnt) and place right before first then food counter ++ otherwise return -1
     * - If diff is 2 then incr
     * - If diff is more than one then increase the counter to 1
     * - Move first pointer to 2nd
     * @param hamsters
     * @return
     */
    public int minimumBuckets1(String hamsters) {
        // Unfortunately this is a tricky problem
        /**
         *         return (s.equals("H") || s.startsWith("HH") || s.endsWith("HH") || s.contains("HHH")) ?
         *                -1 : s.replace("H.H", "  ").length() - s.replace("H", "").length();
         */


        int foodCounter = 0;
        char[] food = new char[hamsters.length()];
        // Pass-1, keep a food in between adjacent onces and also try to find 3 hamseters next to each other
        for(int i = 0; i < hamsters.length(); i++){
            if (i > 0 && i < hamsters.length() - 1 && hamsters.charAt(i) == 'H' && hamsters.charAt(i-1) == 'H' && hamsters.charAt(i+1) == 'H') {
                return -1;
            }

            if (i < hamsters.length() - 2 && hamsters.charAt(i) == 'H' && hamsters.charAt(i+2) == 'H') {
                food[i] = 'F';
                foodCounter++;
            }
        }

        // Pass 2 to make sure every hamster is able to eat.
        for (int i = 0; i < hamsters.length(); i++) {
            if (hamsters.charAt(i) == 'H' && i > 0 && i < hamsters.length() - 1 && (food[i - 1] == 'F' || food[i + 1] == 'F')) {
                // This hamster has food either on right or on left
                continue;
            }

            // Check the bounday values
            if (i == 0 && hamsters.charAt(i) == 'H' && food[i + 1] == 'F') {
                continue;
            }
            if (i == hamsters.length() - 1 && hamsters.charAt(i) == 'H' && food[i - 1] == 'F') {
                continue;
            }

            // Have to provide food either on left or on right
            if (i > 0 && hamsters.charAt(i - 1) == '.' && food[i - 1] != 'F') {
                // Food before
                food[i - 1] = 'F';
                foodCounter++;
            } else if (i < hamsters.length() - 1 && hamsters.charAt(i + 1) == '.' && food[i + 1] != 'F') {
                food[i + 1] = 'F';
                foodCounter++;
            } else if (hamsters.charAt(i - 1) == '.' &&  food[i - 1] != 'F') {
                food[i - 1] = 'F';
                foodCounter++;
            } else if (hamsters.charAt(i + 1) == '.' && food[i + 1] == 'F') {
                food[i + 1] = 'F';
                foodCounter++;
            } else {
                return -1;
            }
        }

        return foodCounter;
    }

    public static void main(String[] args) {
        MinFoodBuckets2086 minFoodBuckets2086 = new MinFoodBuckets2086();
        System.out.println(minFoodBuckets2086.minimumBuckets("H..H"));
        System.out.println(minFoodBuckets2086.minimumBuckets(".H.H."));
        System.out.println(minFoodBuckets2086.minimumBuckets(".HHH."));
    }
}


