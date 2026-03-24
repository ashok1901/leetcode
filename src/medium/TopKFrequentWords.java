package medium;

import java.util.*;

public class TopKFrequentWords {

    private class HeapNode implements Comparable<HeapNode> {
        String word;
        int freq;
        HeapNode next;
        public HeapNode(String word, int freq) {
            this.word = word;
            this.freq = freq;
        }

        @Override
        public int compareTo(HeapNode o) {
            if (this.freq == o.freq) {
                return o.word.compareTo(this.word);
            }

            return this.freq - o.freq;
        }
    }

    /**
     * Approach: Use min heap of size k, sort the heap with frequency and then lexicograhical order if freq is same.
     * @param args
     */
    public List<String> topKFrequent(String[] words, int k) {
        Queue<HeapNode> queue = new PriorityQueue<>();
        Map<String, Integer> freqMap = new HashMap<>();
        for (String word : words) {
            freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
        }

        for (String key : freqMap.keySet()) {
            queue.offer(new HeapNode(key, freqMap.get(key)));
            if (queue.size() > k) {
                queue.poll();
            }
        }

        List<String> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            HeapNode node = queue.poll();
            res.add(node.word);
        }

        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        TopKFrequentWords topKFrequentWords = new TopKFrequentWords();
        String[] words = {"i","love","leetcode","i","love","coding"}; int k = 2;
        System.out.println(topKFrequentWords.topKFrequent(words, k));
        String[] words1 = {"the","day","is","sunny","the","the","the","sunny","is","is"}; int k1 = 4;
        System.out.println(topKFrequentWords.topKFrequent(words1, k1));
    }
}


