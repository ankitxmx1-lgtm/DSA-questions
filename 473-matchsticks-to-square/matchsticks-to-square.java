import java.util.Arrays;

class Solution {
    public boolean makesquare(int[] matchsticks) {
        if (matchsticks == null || matchsticks.length < 4) return false;
        int sum = 0;
        for (int match : matchsticks) {
            sum += match;
        }
        if (sum % 4 != 0) return false;
        
        Arrays.sort(matchsticks);
        reverse(matchsticks);
        
        return backtrack(matchsticks, new int[4], 0, sum / 4);
    }

    private boolean backtrack(int[] matchsticks, int[] sides, int index, int target) {
        if (index == matchsticks.length) {
            return sides[0] == target && sides[1] == target && sides[2] == target;
        }
        
        for (int i = 0; i < 4; i++) {
            if (sides[i] + matchsticks[index] > target) continue;
            
            int j = i - 1;
            while (j >= 0) {
                if (sides[i] == sides[j]) break;
                j--;
            }
            if (j >= 0) continue;
            
            sides[i] += matchsticks[index];
            if (backtrack(matchsticks, sides, index + 1, target)) return true;
            sides[i] -= matchsticks[index];
        }
        return false;
    }

    private void reverse(int[] arr) {
        int i = 0, j = arr.length - 1;
        while (i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }
}
