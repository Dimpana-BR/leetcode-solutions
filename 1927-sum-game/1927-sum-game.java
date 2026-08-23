class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int leftSum = 0, rightSum = 0;
        int q1 = 0, q2 = 0;

        for (int i = 0; i < n / 2; i++) {
            if (num.charAt(i) == '?') {
                q1++;
            } else {
                leftSum += num.charAt(i) - '0';
            }
        }

        for (int i = n / 2; i < n; i++) {
            if (num.charAt(i) == '?') {
                q2++;
            } else {
                rightSum += num.charAt(i) - '0';
            }
        }

        // If total '?' count is odd, Alice always wins
        if ((q1 + q2) % 2 != 0) {
            return true;
        }

        // Bob wins if and only if the sum difference balances out the '?' difference perfectly
        return (leftSum - rightSum) * 2 != 9 * (q2 - q1);
    }
}