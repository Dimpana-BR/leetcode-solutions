class Solution {
    public long findKthSmallest(int[] coins, int k) {
        long low = 1;
        long minCoin = coins[0];
        for (int coin : coins) {
            minCoin = Math.min(minCoin, coin);
        }
        long high = minCoin * (long) k;
        long ans = high;

        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (countAmounts(coins, mid) >= k) {
                ans = mid;
                high = mid - 1; // Try finding a smaller valid amount
            } else {
                low = mid + 1;  // Not enough amounts, need a larger bound
            }
        }

        return ans;
    }

    private long countAmounts(int[] coins, long target) {
        int n = coins.length;
        long count = 0;

        // Iterate through all 2^n - 1 non-empty subsets using bitmasking
        for (int mask = 1; mask < (1 << n); mask++) {
            long lcmVal = 1;
            int bitCount = 0;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    bitCount++;
                    lcmVal = lcm(lcmVal, coins[i]);
                    // Optimization: stop early if LCM exceeds target
                    if (lcmVal > target) break; 
                }
            }

            if (bitCount % 2 == 1) {
                count += target / lcmVal;
            } else {
                count -= target / lcmVal;
            }
        }

        return count;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}