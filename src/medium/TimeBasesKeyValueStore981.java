package medium;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TimeBasesKeyValueStore981 {
    /**
     * Approach :
     * - Maintain a Map String -> TreeMap, TreeMap: time -> String
     * - With tree map keys are sorted so get can use binary search to find t such that t <= asked_time
     */
    private Map<String, TreeMap<Integer, String>> timeBasedKeyValueStore;
    public TimeBasesKeyValueStore981() {
        this.timeBasedKeyValueStore = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if (!timeBasedKeyValueStore.containsKey(key)) {
            timeBasedKeyValueStore.put(key, new TreeMap<>());
        }

        timeBasedKeyValueStore.get(key).put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        if (!timeBasedKeyValueStore.containsKey(key)) {
            return "";
        }

        TreeMap<Integer, String> treeMap = timeBasedKeyValueStore.get(key);
        Integer floorKey = treeMap.floorKey(timestamp);

        return floorKey == null ? "" : treeMap.get(floorKey);
    }

    public static void main(String[] args) {
        TimeBasesKeyValueStore981 timeBasesKeyValueStore981 = new TimeBasesKeyValueStore981();
        timeBasesKeyValueStore981.set("foo", "bar", 1);  // store the key "foo" and value "bar" along with timestamp = 1.
        System.out.println(timeBasesKeyValueStore981.get("foo", 1));         // return "bar"
        System.out.println(timeBasesKeyValueStore981.get("foo", 3));         // return "bar", since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 is "bar".
        timeBasesKeyValueStore981.set("foo", "bar2", 4); // store the key "foo" and value "bar2" along with timestamp = 4.
        System.out.println(timeBasesKeyValueStore981.get("foo", 4));         // return "bar2"
        System.out.println(timeBasesKeyValueStore981.get("foo", 5));         // return "bar2"
    }
}


