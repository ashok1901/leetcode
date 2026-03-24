package medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryAllocator2502 {

    private int capacity;
    private int[] allocations;
    // mId -> [{startIndex, count}, {startIndex, count}.....]
    private Map<Integer, List<Integer[]>> allocatedUnits;
    public MemoryAllocator2502(int n) {
        this.capacity = n;
        this.allocations = new int[n];
        initToAvailable();
        this.allocatedUnits = new HashMap<>();
    }

    public int allocate(int size, int mID) {
        int availableIndex = findAvailableIndex(size);
        if (availableIndex == -1) {
            return -1;
        }

        // Update allocations.
        for (int i = availableIndex; i < availableIndex + size; i++) {
            allocations[i] = mID;
        }

        // Update allocated units in the map.
        if (!allocatedUnits.containsKey(mID)) {
            allocatedUnits.put(mID, new ArrayList<>());
        }

        allocatedUnits.get(mID).add(new Integer[]{availableIndex, size});
        return availableIndex;
    }

    private void initToAvailable() {
        for (int i = 0; i < allocations.length; i++) {
            allocations[i] = -1;
        }
    }

    /**
     * For now linear implementation,
     * TODO: Improve this
     * @param size
     * @return
     */
    private int findAvailableIndex(int size) {
        int availableIndex = -1;
        for (int i = 0; i < capacity; i++) {
           if (allocations[i] != -1) {
               continue;
           }

           // This is available, now check if we have next size units available.
            if (nextCountAvailable(i, size)) {
                availableIndex = i;
                break;
            }
       }

       return availableIndex;
    }

    private boolean nextCountAvailable(int startIndex, int size) {
        if (startIndex + size >= capacity) {
            // Next size units can not be available as there are no more size units after this start index.
            return false;
        }
        for (int i = startIndex; i <= size; i++) {
            if (allocations[i] != -1) {
                return false;
            }
        }

        return true;
    }

    public int freeMemory(int mID) {
        if (!allocatedUnits.containsKey(mID)) {
            return 0;
        }

        int counter = 0;
        for (Integer[] array : allocatedUnits.get(mID)) {
            counter += array[1];
            int startIndex = array[0];
            int size = array[1];
            int endIndex = startIndex + size;
            for(int i = array[0]; i < endIndex; i++) {
                allocations[i] = -1;
            }
        }

        allocatedUnits.remove(mID);
        return counter;
    }

    public static void main(String[] args) {
        MemoryAllocator2502 allocator = new MemoryAllocator2502(10);
        allocator.allocate(1, 1); // The leftmost block's first index is 0. The memory array becomes [1,_,_,_,_,_,_,_,_,_]. We return 0.
        allocator.allocate(1, 2); // The leftmost block's first index is 1. The memory array becomes [1,2,_,_,_,_,_,_,_,_]. We return 1.
        allocator.allocate(1, 3); // The leftmost block's first index is 2. The memory array becomes [1,2,3,_,_,_,_,_,_,_]. We return 2.
        allocator.freeMemory(2); // Free all memory units with mID 2. The memory array becomes [1,_, 3,_,_,_,_,_,_,_]. We return 1 since there is only 1 unit with mID 2.
        allocator.allocate(3, 4); // The leftmost block's first index is 3. The memory array becomes [1,_,3,4,4,4,_,_,_,_]. We return 3.
        allocator.allocate(1, 1); // The leftmost block's first index is 1. The memory array becomes [1,1,3,4,4,4,_,_,_,_]. We return 1.
        allocator.allocate(1, 1); // The leftmost block's first index is 6. The memory array becomes [1,1,3,4,4,4,1,_,_,_]. We return 6.
        allocator.freeMemory(1); // Free all memory units with mID 1. The memory array becomes [_,_,3,4,4,4,_,_,_,_]. We return 3 since there are 3 units with mID 1.
        allocator.allocate(10, 2); // We can not find any free block with 10 consecutive free memory units, so we return -1.
        allocator.freeMemory(7); // Free all memory units with mID 7. The memory array remains the same since there is no memory unit with mID 7. We return 0.

    }
}


