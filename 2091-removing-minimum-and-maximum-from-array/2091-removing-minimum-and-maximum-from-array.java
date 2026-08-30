class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        if (n <= 2) return n;

        int minIdx = 0;
        int maxIdx = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] < nums[minIdx]) {
                minIdx = i;
            }
            if (nums[i] > nums[maxIdx]) {
                maxIdx = i;
            }
        }

        int left = Math.min(minIdx, maxIdx);
        int right = Math.max(minIdx, maxIdx);

        // Scenario 1: Remove both from front
        int option1 = right + 1;

        // Scenario 2: Remove both from back
        int option2 = n - left;

        // Scenario 3: Remove left from front, right from back
        int option3 = (left + 1) + (n - right);

        return Math.min(option1, Math.min(option2, option3));
    }
}