class Solution {
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        int[][] dp = new int[n][n];

        // Base case: 1 pile left, the current player takes all of it
        for (int i = 0; i < n; i++) {
            dp[i][i] = piles[i];
        }

        // Fill table for subproblems of length 2 to n
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
            }
        }

        // Alice wins if her score difference > 0
        return dp[0][n - 1] > 0;
    }
}