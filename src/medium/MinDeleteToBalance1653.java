package medium;

public class MinDeleteToBalance1653 {
    /**
     * Approach : When first b encountered then two options either have all b's after that or delete this b.
     * @param s
     * @return
     */
    public int minimumDeletions(String s) {
//        return recursiveMinDel(s, 0, 0, false);
        return countAsAndBs(s);
    }

    private int countAsAndBs(String s) {
        int strLen = s.length();
        // Count A's after
        int[] asAfter = new int[strLen];
        int aCount = 0;
        for (int i = strLen - 1; i >= 0; i--) {
            asAfter[i] = aCount;
            if (s.charAt(i) == 'a') {
                aCount++;
            }
        }
        // Count B's before
        int[] bsBefore = new int[strLen];
        int bCount = 0;
        for (int i = 0; i < strLen; i++) {
            bsBefore[i] = bCount;
            if(s.charAt(i) == 'b') {
                bCount++;
            }
        }

        int totalCost = Integer.MAX_VALUE;
        for (int i = 0; i < strLen; i++) {
            totalCost = Math.min(totalCost, asAfter[i] + bsBefore[i]);
        }

        return totalCost;
    }

    /**
     * So many duplicate calculations so might be a DP case. This highly likely will time limit exceed.
     *
     * @param s
     * @param startIndex
     * @param costSoFar
     * @param isBRetained
     * @return
     */
    private int recursiveMinDel(String s, int startIndex, int costSoFar, boolean isBRetained) {
        if (startIndex >= s.length()) {
            // When whole string is exhausted.
            return costSoFar;
        }
        char currentChar = s.charAt(startIndex);
        if (isBRetained) {
            if (currentChar == 'a') {
                // a coming after b is already retained. This a has to be deleted.
                return recursiveMinDel(s, startIndex + 1, costSoFar + 1, isBRetained);
            } else {
                // B is retained and this char is b itself. So just have it.
                return recursiveMinDel(s, startIndex + 1, costSoFar, isBRetained);
            }
        } else {
            // So far b is never seen.
            if (currentChar == 'a') {
                // b is not seen yet and this is a so just have it.
                return recursiveMinDel(s, startIndex + 1, costSoFar, isBRetained);
            } else {
                // This is first b seen ever.
                // Two choices (a) Retain b (a) delete b
                int retainB = recursiveMinDel(s, startIndex + 1, costSoFar, true);
                int deleteB = recursiveMinDel(s, startIndex + 1, costSoFar + 1, isBRetained);
                return Math.min(retainB, deleteB);
            }

        }

    }

    public static void main(String[] args) {
        MinDeleteToBalance1653 minDeleteToBalance1653 = new MinDeleteToBalance1653();
        System.out.println("Min deletes " + minDeleteToBalance1653.minimumDeletions("aababbab"));
        System.out.println("Min deletes " + minDeleteToBalance1653.minimumDeletions("bbaaaaabb"));
    }
}


