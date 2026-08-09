class Solution {
    private int[][] memo;
    private int[] suffixSum;

    public int stoneGameII(int[] piles) {
        int n = piles.length;
        memo = new int[n][n + 1];
        suffixSum = new int[n];

        // Compute suffix sums to quickly get total remaining stones from index i
        suffixSum[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }

        return solve(0, 1, piles);
    }

    private int solve(int i, int M, int[] piles) {
        int n = piles.length;

        // Base case: If remaining piles can all be taken, take all of them
        if (i + 2 * M >= n) {
            return suffixSum[i];
        }

        // Return memoized result if available
        if (memo[i][M] != 0) {
            return memo[i][M];
        }

        int maxStones = 0;

        // Try taking X piles where 1 <= X <= 2M
        for (int X = 1; X <= 2 * M; X++) {
            int nextM = Math.max(M, X);
            // Current player gets remaining total minus whatever the next player gets optimally
            int stones = suffixSum[i] - solve(i + X, nextM, piles);
            maxStones = Math.max(maxStones, stones);
        }

        memo[i][M] = maxStones;
        return maxStones;
    }
}