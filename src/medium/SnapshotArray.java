package medium;

import java.util.HashMap;

public class SnapshotArray {
    /**
     * Implement a SnapshotArray that supports the following interface:
     *
     * SnapshotArray(int length) initializes an array-like data structure with the given length. Initially, each element equals 0.
     * void set(index, val) sets the element at the given index to be equal to val.
     * int snap() takes a snapshot of the array and returns the snap_id: the total number of times we called snap() minus 1.
     * int get(index, snap_id) returns the value at the given index, at the time we took the snapshot with the given snap_id
     * @param args
     */
    private int[] array;
    private int snapshotId = 0;
    private HashMap<Integer, Integer[]> snapshotsMap = new HashMap<>();
    public SnapshotArray(int length) {
        initArray(length);
    }

    private void initArray(int len) {
        array = new int[len];
        for (int index = 0; index < len; index++) {
            array[index] = 0;
        }
    }

    private Integer[] deepCopyArray(int[] nums) {
        Integer[] copy = new Integer[nums.length];
        int index = 0;
        for (int val : nums) {
            copy[index++] = val;
        }
        return copy;
    }

    public void set(int index, int val) {
        array[index] = val;
    }

    public int snap() {
        Integer[] copySnap = deepCopyArray(array);
        int currentSnapshotId = snapshotId;
        snapshotsMap.put(currentSnapshotId, copySnap);
        snapshotId++;
        return currentSnapshotId;
    }

    public int get(int index, int snap_id) {
        if (snapshotsMap.containsKey(snap_id)) {
            return snapshotsMap.get(snap_id)[index];
        }
        char k = 'k';
        // Ideally this shouldnot happen if snap_id is valid
        throw new IllegalArgumentException("Invalid snapshot id " + snap_id);
    }

    public static void main(String[] args) {
        char k = 'K';
        if ( k >= 'A' && k <= 'z') {
            System.out.println("k is a alphaneumeric, so chars can be compared like this");
        }

    }
}


