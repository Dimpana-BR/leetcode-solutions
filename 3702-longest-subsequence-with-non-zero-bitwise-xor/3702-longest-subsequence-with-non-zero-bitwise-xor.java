class Solution {
    public int longestSubsequence(int[] nums) {
        int xorSum = 0;
        boolean hasNonZero = false;

        for (int num : nums) {
            xorSum ^= num;
            if (num != 0) {
                hasNonZero = true;
            }
        }

        // If all elements are 0, return 0
        if (!hasNonZero) {
            return 0;
        }

        // If overall XOR is non-zero, use all n elements.
        // If overall XOR is zero, remove 1 element to get a non-zero XOR (n - 1 elements).
        return xorSum != 0 ? nums.length : nums.length - 1;
    }
}
