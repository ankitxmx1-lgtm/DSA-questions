import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0; 
        }

        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int freshHein = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 2) {
                    queue.add(new int[]{r, c});
                } else if (grid[r][c] == 1) {
                    freshHein++;
                }
            }
        }

        if (freshHein == 0) {
            return 0;
        }

        int minutes = 0;
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!queue.isEmpty() && freshHein > 0) {
            int size = queue.size();
            minutes++;
            
            for (int i = 0; i < size; i++) {
                int[] point = queue.poll();
                int r = point[0];
                int c = point[1];

                for (int[] dir : directions) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];

                    if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] == 1) {
                        grid[nr][nc] = 2;
                        freshHein--;
                        queue.add(new int[]{nr, nc});
                    }
                }
            }
        }

        return freshHein == 0 ? minutes : -1; 
    }
}
