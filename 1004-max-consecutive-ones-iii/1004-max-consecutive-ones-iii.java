class Solution {
    public int longestOnes(int[] nums, int k) {
        int left = 0;
        int max = 0;
        int zeros = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                zeros++;
            }
            
            // Shrink the window only when the number of zeros exceeds k
            while (zeros > k) {
                if (nums[left] == 0) {
                    zeros--; // Reduce the zero count as it leaves the window
                }
                left++; // Always move the left pointer forward
            }
            
            max = Math.max(max, right - left + 1);
        }
        return max;
    }
}