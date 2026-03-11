package medium;

public class HitCounter {
    /**
     * Easy way would be 2 arrays one for entries and other for times
     * 1. Keep in mind about concurrency
     * 2. User circular array.
     * 3. Keep track of last entry to decide when to switch the window
     */
    private int lastEntryTime = 0;
    private int currentActiveIndex = 0;
    private int windowInSecs = 300;
    private int[] entries = new int[windowInSecs];
    private int total = 0;
    public HitCounter() {
        initializeEntries();
    }

    /**
     * TODO: Concurrency yet to handle
     * @param timestamp
     */
    public void hit(int timestamp) {
        int expectedIndex = timestamp % windowInSecs;
        if (expectedIndex == currentActiveIndex) {
            // Another hit at the same second. Just add it up.
            total = total + 1;
            entries[currentActiveIndex] = entries[currentActiveIndex] + 1;
        } else {
            int indexToOverwrite = expectedIndex;
            int hitAtIndexToOverWrite = entries[indexToOverwrite];
            total = total - hitAtIndexToOverWrite + 1;
            entries[indexToOverwrite] = 1;
            currentActiveIndex = indexToOverwrite;
        }
        lastEntryTime = timestamp;
    }

    public int getHits(int timestamp) {
        if (timestamp - lastEntryTime > windowInSecs) {
            return 0;
        }

        return total;
    }

    private void initializeEntries() {
        for (int index = 0; index < windowInSecs; index++) {
            entries[index] = 0;
        }
    }
    public static void main(String[] args) {
    }
}


