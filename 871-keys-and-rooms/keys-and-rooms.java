import java.util.*;

class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] visited = new boolean[n];
        visited[0] = true;
        
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        
        int count = 1;
        
        while (!queue.isEmpty()) {
            int currentRoom = queue.poll();
            
            for (int key : rooms.get(currentRoom)) {
                if (!visited[key]) {
                    visited[key] = true;
                    queue.offer(key);
                    count++;
                    
                    if (count == n) {
                        return true;
                    }
                }
            }
        }
        
        return count == n;
    }
}
