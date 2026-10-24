import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] result = new double[nums.length - k + 1];
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        Map<Integer, Integer> delayedDeletes = new HashMap<>();
        
        int i = 0;
        while (i < k) {
            maxHeap.add(nums[i++]);
        }
        for (int j = 0; j < k / 2; j++) {
            minHeap.add(maxHeap.poll());
        }
        
        result[0] = getMedian(maxHeap, minHeap, k);
        
        for (int dest = 1; i < nums.length; i++, dest++) {
            int outgoing = nums[dest - 1];
            int incoming = nums[i];
            int balance = 0;
            
            balance += (outgoing <= maxHeap.peek()) ? -1 : 1;
            delayedDeletes.put(outgoing, delayedDeletes.getOrDefault(outgoing, 0) + 1);
            
            if (!maxHeap.isEmpty() && incoming <= maxHeap.peek()) {
                maxHeap.add(incoming);
                balance++;
            } else {
                minHeap.add(incoming);
                balance--;
            }
            
            if (balance < 0) {
                maxHeap.add(minHeap.poll());
                balance++;
            }
            if (balance > 0) {
                minHeap.add(maxHeap.poll());
                balance--;
            }
            
            while (!maxHeap.isEmpty() && delayedDeletes.getOrDefault(maxHeap.peek(), 0) > 0) {
                int head = maxHeap.poll();
                delayedDeletes.put(head, delayedDeletes.get(head) - 1);
            }
            while (!minHeap.isEmpty() && delayedDeletes.getOrDefault(minHeap.peek(), 0) > 0) {
                int head = minHeap.poll();
                delayedDeletes.put(head, delayedDeletes.get(head) - 1);
            }
            
            result[dest] = getMedian(maxHeap, minHeap, k);
        }
        return result;
    }

    private double getMedian(PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap, int k) {
        if (k % 2 != 0) {
            return (double) maxHeap.peek();
        } else {
            return ((double) maxHeap.peek() + minHeap.peek()) * 0.5;
        }
    }
}
