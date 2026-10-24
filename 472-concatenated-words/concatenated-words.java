import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isWord = false;
    }

    private TrieNode root = new TrieNode();

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Arrays.sort(words, (a, b) -> Integer.compare(a.length(), b.length()));
        List<String> result = new ArrayList<>();

        for (String word : words) {
            if (word.isEmpty()) {
                continue;
            }
            boolean[] memo = new boolean[word.length()];
            if (dfs(word, 0, memo)) {
                result.add(word);
            } else {
                insert(word);
            }
        }
        return result;
    }

    private boolean dfs(String word, int start, boolean[] memo) {
        if (start == word.length()) {
            return true;
        }
        if (memo[start]) {
            return false;
        }

        TrieNode current = root;
        for (int i = start; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (current.children[index] == null) {
                break;
            }
            current = current.children[index];
            if (current.isWord) {
                if (dfs(word, i + 1, memo)) {
                    return true;
                }
            }
        }

        memo[start] = true;
        return false;
    }

    private void insert(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
        }
        current.isWord = true;
    }
}
