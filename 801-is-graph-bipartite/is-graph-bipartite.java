import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n];

        for (int i = 0; i < n; i++) {
            if (colors[i] != 0) {
                continue;
            }

            Queue<Integer> queue = new LinkedList<>();
            queue.add(i);
            colors[i] = 1;

            while (!queue.isEmpty()) {
                int current = queue.poll();

                for (int neighbor : graph[current]) {
                    if (colors[neighbor] == 0) {
                        colors[neighbor] = -colors[current];
                        queue.add(neighbor);
                    } else if (colors[neighbor] == colors[current]) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
