class Solution {
    public int maxArea(int[] height) {
        int maxwater = 0, left = 0, right = height.length - 1;
        while (left < right) {
            int width = right - left;
            maxwater = Math.max(maxwater, Math.min(height[left], height[right]) * width);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxwater;
    }
}
