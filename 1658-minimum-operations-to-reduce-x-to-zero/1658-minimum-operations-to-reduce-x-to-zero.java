class Solution {
    public int minOperations(int[] nums, int x) {
        long total = 0;
        for (int num : nums) {
            total += num;
        }
        
        long target = total - x;
        int n = nums.length;
        
        if (target < 0) return -1;
        if (target == 0) return n;
        
        int left = 0;
        long currentSum = 0;
        int maxLen = -1;
        
        for (int right = 0; right < n; right++) {
            currentSum += nums[right];
            
            while (currentSum > target && left <= right) {
                currentSum -= nums[left++];
            }
            
            if (currentSum == target) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
        }
        
        return maxLen == -1 ? -1 : n - maxLen;
    }
}