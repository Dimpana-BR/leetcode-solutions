class Solution {
    private final int MOD = 1_000_000_007;

    public int subsequencePairCount(int[] nums) {
        int maxVal = 200;
        // dp[g1][g2] stores the number of subsequence pairs with GCD g1 and g2.
        // 0 represents an empty subsequence.
        int[][] dp = new int[maxVal + 1][maxVal + 1];
        dp[0][0] = 1; // Base case: both subsequences are initially empty

        for (int x : nums) {
            int[][] nextDp = new int[maxVal + 1][maxVal + 1];
            
            for (int g1 = 0; g1 <= maxVal; g1++) {
                for (int g2 = 0; g2 <= maxVal; g2++) {
                    if (dp[g1][g2] == 0) continue;
                    
                    long count = dp[g1][g2];
                    
                    // Choice 1: Skip x
                    nextDp[g1][g2] = (int) ((nextDp[g1][g2] + count) % MOD);
                    
                    // Choice 2: Add x to the first subsequence
                    int ng1 = (g1 == 0) ? x : gcd(g1, x);
                    nextDp[ng1][g2] = (int) ((nextDp[ng1][g2] + count) % MOD);
                    
                    // Choice 3: Add x to the second subsequence
                    int ng2 = (g2 == 0) ? x : gcd(g2, x);
                    nextDp[g1][ng2] = (int) ((nextDp[g1][ng2] + count) % MOD);
                }
            }
            dp = nextDp;
        }

        // Sum up all pairs where g1 == g2 and both are non-empty (g > 0)
        long totalPairs = 0;
        for (int g = 1; g <= maxVal; g++) {
            totalPairs = (totalPairs + dp[g][g]) % MOD;
        }

        return (int) totalPairs;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}