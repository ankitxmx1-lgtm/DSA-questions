import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public String removeDuplicates(String s) {
        Deque<Character> deque = new ArrayDeque<>();
        
        for (char c : s.toCharArray()) {
          
            if (!deque.isEmpty() && deque.peekLast() == c) {
                deque.pollLast();
            } else {
                deque.addLast(c);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            sb.append(deque.pollFirst());
        }
        
        return sb.toString();
    }
}
