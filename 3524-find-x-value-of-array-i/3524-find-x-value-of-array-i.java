class Solution {
    public long[] resultArray(int[] nums, int k) {

        long[] ans = new long[k];
        long[] dp = new long[k];   // dp[r] = subarrays ending at previous index with remainder r

        for (int num : nums) {

            long[] newDp = new long[k];
            int val = num % k;

            // Start a new subarray with only this element
            newDp[val]++;

            // Extend all previous subarrays
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    int newRem = (r * val) % k;
                    newDp[newRem] += dp[r];
                }
            }

            // Add counts to answer
            for (int r = 0; r < k; r++) {
                ans[r] += newDp[r];
            }

            dp = newDp;
        }

        return ans;
    }
}