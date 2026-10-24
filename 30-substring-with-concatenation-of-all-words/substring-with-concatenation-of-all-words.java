import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s == null || words == null || words.length == 0) return res;

        int wordLen = words[0].length();
        int wordCount = words.length;
        int totalLen = wordLen * wordCount;
        if (s.length() < totalLen) return res;

        Map<String, Integer> wordMap = new HashMap<>();
        for (String word : words) {
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }

        for (int i = 0; i < wordLen; i++) {
            int left = i, right = i, count = 0;
            Map<String, Integer> currentMap = new HashMap<>();

            while (right + wordLen <= s.length()) {
                String w = s.substring(right, right + wordLen);
                right += wordLen;

                if (wordMap.containsKey(w)) {
                    currentMap.put(w, currentMap.getOrDefault(w, 0) + 1);
                    count++;

                    while (currentMap.get(w) > wordMap.get(w)) {
                        String leftWord = s.substring(left, left + wordLen);
                        currentMap.put(leftWord, currentMap.get(leftWord) - 1);
                        count--;
                        left += wordLen;
                    }

                    if (count == wordCount) {
                        res.add(left);
                    }
                } else {
                    currentMap.clear();
                    count = 0;
                    left = right;
                }
            }
        }
        return res;
    }
}
