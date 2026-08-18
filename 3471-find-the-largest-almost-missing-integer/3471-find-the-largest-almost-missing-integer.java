

class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;

        // Case 1: k = 1
        // An element appears in exactly 1 subarray of size 1 if it appears exactly once in nums.
        if (k == 1) {
            Map<Integer, Integer> freq = new HashMap<>();
            for (int num : nums) {
                freq.put(num, freq.getOrDefault(num, 0) + 1);
            }
            int ans = -1;
            for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
                if (entry.getValue() == 1) {
                    ans = Math.max(ans, entry.getKey());
                }
            }
            return ans;
        }

        // Case 2: k = n
        // There is only 1 subarray of size n, so every unique element in nums is valid.
        if (k == n) {
            int maxVal = -1;
            for (int num : nums) {
                maxVal = Math.max(maxVal, num);
            }
            return maxVal;
        }

        // Case 3: 1 < k < n
        // Only boundary elements (nums[0] and nums[n - 1]) can appear in exactly 1 subarray.
        int countFirst = 0;
        int countLast = 0;
        for (int num : nums) {
            if (num == nums[0]) countFirst++;
            if (num == nums[n - 1]) countLast++;
        }

        int ans = -1;
        if (countFirst == 1) {
            ans = Math.max(ans, nums[0]);
        }
        if (countLast == 1) {
            ans = Math.max(ans, nums[n - 1]);
        }

        return ans;
    }
}