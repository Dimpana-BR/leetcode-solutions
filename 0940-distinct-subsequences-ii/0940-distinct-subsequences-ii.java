class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        long[] ends = new long[26];

        for (char c : s.toCharArray()) {
            int idx = c - 'a';
            long currentSum = 0;
            for (long count : ends) {
                currentSum = (currentSum + count) % MOD;
            }
            // 1 (for single character 'c') + currentSum (appending 'c' to existing subsequences)
            ends[idx] = (currentSum + 1) % MOD;
        }

        long total = 0;
        for (long count : ends) {
            total = (total + count) % MOD;
        }

        return (int) total;
    }
}