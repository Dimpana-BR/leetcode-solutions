
class Solution {
    private int[][] memo;
    private int[] prefix;

    private int getSum(int l, int r) {
        return prefix[r + 1] - prefix[l];
    }

    private int solve(int i, int j, int[] stoneValue) {
        if (i == j) return 0;
        if (memo[i][j] != -1) return memo[i][j];

        int maxScore = 0;
        for (int k = i; k < j; k++) {
            int leftSum = getSum(i, k);
            int rightSum = getSum(k + 1, j);

            if (leftSum < rightSum) {
                maxScore = Math.max(maxScore, leftSum + solve(i, k, stoneValue));
            } else if (leftSum > rightSum) {
                maxScore = Math.max(maxScore, rightSum + solve(k + 1, j, stoneValue));
            } else {
                maxScore = Math.max(maxScore, leftSum + Math.max(solve(i, k, stoneValue), solve(k + 1, j, stoneValue)));
            }
        }

        return memo[i][j] = maxScore;
    }

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        memo = new int[n][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }

        return solve(0, n - 1, stoneValue);
    }
}