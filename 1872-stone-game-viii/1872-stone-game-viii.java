class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        
        // Calculate prefix sums in-place or keep track
        long[] prefixSum = new long[n];
        prefixSum[0] = stones[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + stones[i];
        }
        
        // dp represents maximum score difference achievable starting from current index to n - 1
        long maxDiff = prefixSum[n - 1];
        
        // Work backwards from n - 2 down to 1 (since x > 1, index 0 cannot be chosen)
        for (int i = n - 2; i >= 1; i--) {
            maxDiff = Math.max(maxDiff, prefixSum[i] - maxDiff);
        }
        
        return (int) maxDiff;
    }
}