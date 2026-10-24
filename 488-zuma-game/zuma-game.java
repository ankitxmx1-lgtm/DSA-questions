import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    private int memoMax = 6;
    private Map<String, Integer> memo = new HashMap<>();

    public int findMinStep(String board, String hand) {
        char[] hChars = hand.toCharArray();
        Arrays.sort(hChars);
        int result = dfs(board, new String(hChars));
        return result > 5 ? -1 : result;
    }

    private int dfs(String board, String hand) {
        board = removeConsecutive(board);
        if (board.isEmpty()) {
            return 0;
        }
        if (hand.isEmpty()) {
            return memoMax;
        }

        String key = board + "#" + hand;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int minSteps = memoMax;

        for (int k = 0; k < hand.length(); k++) {
            if (k > 0 && hand.charAt(k) == hand.charAt(k - 1)) {
                continue;
            }
            
            char color = hand.charAt(k);
            String nextHand = hand.substring(0, k) + hand.substring(k + 1);

            for (int j = 0; j <= board.length(); j++) {
                boolean isValidPlacement = false;

                if (j < board.length() && board.charAt(j) == color) {
                    isValidPlacement = true;
                }

                if (j > 0 && j < board.length() && board.charAt(j - 1) == board.charAt(j) && board.charAt(j) != color) {
                    isValidPlacement = true;
                }

                if (!isValidPlacement) {
                    continue;
                }

                String nextBoard = board.substring(0, j) + color + board.substring(j);
                int nextStep = dfs(nextBoard, nextHand);
                
                if (nextStep != memoMax) {
                    minSteps = Math.min(minSteps, nextStep + 1);
                }
            }
        }

        memo.put(key, minSteps);
        return minSteps;
    }

    private String removeConsecutive(String board) {
        int i = 0;
        while (i < board.length()) {
            int j = i;
            while (j < board.length() && board.charAt(j) == board.charAt(i)) {
                j++;
            }
            if (j - i >= 3) {
                return removeConsecutive(board.substring(0, i) + board.substring(j));
            }
            i = j;
        }
        return board;
    }
}

