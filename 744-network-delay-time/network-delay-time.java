import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] time : times) {
            graph.computeIfAbsent(time[0], x -> new ArrayList<>()).add(new int[]{time[1], time[2]});
        }
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        minHeap.add(new int[]{0, k});
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;
        while (!minHeap.isEmpty()) {
            int[] current = minHeap.poll();
            int d = current[0];
            int node = current[1];
            if (d > dist[node]) {
                continue;
            }
            if (graph.containsKey(node)) {
                for (int[] edge : graph.get(node)) {
                    int neighbor = edge[0];
                    int weight = edge[1];
                    if (dist[node] + weight < dist[neighbor]) {
                        dist[neighbor] = dist[node] + weight;
                        minHeap.add(new int[]{dist[neighbor], neighbor});
                    }
                }
            }
        }
        int maxDelay = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                return -1;
            }
            maxDelay = Math.max(maxDelay, dist[i]);
        }
        return maxDelay;
    }
}
