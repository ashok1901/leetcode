package medium;

import java.util.*;

public class RandomizedSet380 {
    // Key to array list index map
    private Map<Integer, Integer> dataSet = new HashMap<>();
    // Array list for the keys. This is needed to get getRandom() in O(1)

    private List<Integer> keys = new ArrayList<>();
    public RandomizedSet380() {

    }

    public boolean insert(int val) {
        if (dataSet.containsKey(val)) {
            return false;
        }

        keys.add(val);
        // Key -> index map
        dataSet.put(val, keys.size() - 1);
        return true;
    }

    public boolean remove(int val) {
        if (!dataSet.containsKey(val)) {
            return false;
        }
        int keyIndex = dataSet.get(val);
        // O(1) remove from the map
        dataSet.remove(val);
        // Here is the trick, to delete an item from array list in O(1) we have to delete the last item
        // So do the swap trick.
        // Swap last element with the keyIndex and delete the last element
        int lastIndex = keys.size() - 1;
        int lastElement = keys.get(lastIndex);
        keys.set(keyIndex, lastElement);
        dataSet.put(lastElement, keyIndex);
        keys.remove(lastIndex);
        return true;
    }

    public int getRandom() {
        int size = keys.size();
        Random rand = new Random();
        return keys.get(rand.nextInt(size));
    }

    public static void main(String[] args) {
//        RandomizedSet380 randomizedSet = new RandomizedSet380();
//        randomizedSet.insert(1); // Inserts 1 to the set. Returns true as 1 was inserted successfully.
//        randomizedSet.remove(2); // Returns false as 2 does not exist in the set.
//        randomizedSet.insert(2); // Inserts 2 to the set, returns true. Set now contains [1,2].
//        System.out.println(randomizedSet.getRandom()); // getRandom() should return either 1 or 2 randomly.
//        randomizedSet.remove(1); // Removes 1 from the set, returns true. Set now contains [2].
//        randomizedSet.insert(2); // 2 was already in the set, so return false.
//        System.out.println(randomizedSet.getRandom()); // Since 2 is the only number in the set, getRandom() will always return 2.



        RandomizedSet380 randomizedSet = new RandomizedSet380();
        System.out.println(randomizedSet.insert(0));
        System.out.println(randomizedSet.insert(1));
        System.out.println(randomizedSet.remove(0));
        System.out.println(randomizedSet.insert(2));
        System.out.println(randomizedSet.remove(1));
        System.out.println(randomizedSet.getRandom());
    }
}


