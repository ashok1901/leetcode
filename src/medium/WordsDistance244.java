package medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordsDistance244 {
    private Map<String, List<Integer>> map = new HashMap<>();
    public WordsDistance244(String[] wordsDict) {
        this.map = buildWordIndexMap(wordsDict);
    }

    private Map<String, List<Integer>> buildWordIndexMap(String[] words) {
        for (int index = 0; index < words.length; index++) {
            String word = words[index];
            if (!map.containsKey(word)) {
                map.put(word, new ArrayList<>());
            }
            map.get(word).add(index);
        }

        return map;
    }

    public int shortest(String word1, String word2) {
        List<Integer> list1 = map.get(word1);
        List<Integer> list2 = map.get(word2);
        int index1 = 0;
        int index2 = 0;
        int minDistance = Integer.MAX_VALUE;
        while (index1 < list1.size() && index2 < list2.size()) {
            minDistance = Math.min(minDistance, Math.abs(list1.get(index1) - list2.get(index2)));
            if (list1.get(index1) < list2.get(index2)) {
                index1++;
            }  else {
                index2++;
            }
//            if (index1 + 1 < list1.size()) {
//                if (Math.abs(list1.get(index1 + 1) - list2.get(index2)) < minDistance) {
//                    index1++;
//                } else {
//                    index2++;
//                }
//            } else if (index2 + 1 < list2.size()) {
//                if (Math.abs(list2.get(index2 + 1) - list1.get(index1)) < minDistance) {
//                    index2++;
//                } else {
//                    index1++;
//                }
//            } else {
//                index1++;
//                index2++;
//            }
        }

        return minDistance;
    }

    public static void main(String[] args) {
        String[] words = new String[]{"practice", "makes", "perfect", "coding", "makes"};
        WordsDistance244 wd = new WordsDistance244(words);
        System.out.println(wd.shortest("practice", "coding"));
        System.out.println(wd.shortest("makes", "coding"));
    }
}


