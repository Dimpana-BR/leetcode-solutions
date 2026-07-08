class Solution {
    public int[] sumAndMultiply(String s, int[][] queries) {
        int m = s.length();
        int MOD = 1000000007;

        // 1. Collect non-zero digits
        int[] A = new int[m];
        int n = 0; // Number of non-zero digits
        for (int i = 0; i < m; i++) {
            char c = s.charAt(i);
            if (c != '0') {
                A[n++] = c - '0';
            }
        }

        // 2. Precompute powers of 10 modulo MOD
        long[] pow10 = new long[n + 1];
        pow10[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }

        // 3. Precompute prefix sums and prefix numeric values
        long[] prefSum = new long[n + 1];
        long[] P = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefSum[i + 1] = prefSum[i] + A[i];
            P[i + 1] = (P[i] * 10 + A[i]) % MOD;
        }

        // 4. Precompute next and previous non-zero pointer mappings
        int[] nextNz = new int[m];
        int[] prevNz = new int[m];
        
        int currentNzIdx = 0;
        for (int i = 0; i < m; i++) {
            if (s.charAt(i) != '0') {
                nextNz[i] = currentNzIdx;
                currentNzIdx++;
            } else {
                nextNz[i] = currentNzIdx;
            }
        }

        currentNzIdx = -1;
        for (int i = 0; i < m; i++) {
            if (s.charAt(i) != '0') {
                currentNzIdx++;
            }
            prevNz[i] = currentNzIdx;
        }

        // 5. Answer the queries
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            int idx1 = nextNz[l];
            int idx2 = prevNz[r];

            // If there are no non-zero elements in the range
            if (idx1 > idx2) {
                ans[i] = 0;
                continue;
            }

            // Get sum of digits
            long sum = prefSum[idx2 + 1] - prefSum[idx1];

            // Get the value of x modulo MOD
            int len = idx2 - idx1 + 1;
            long x = (P[idx2 + 1] - (P[idx1] * pow10[len]) % MOD + MOD) % MOD;

            // Calculate final answer for the query
            ans[i] = (int) ((x * (sum % MOD)) % MOD);
        }

        return ans;
    }
}