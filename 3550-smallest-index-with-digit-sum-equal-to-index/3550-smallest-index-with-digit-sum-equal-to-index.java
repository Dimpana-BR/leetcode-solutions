class Solution {
    public int smallestIndex(int[] nums) {

        for (int i = 0; i < nums.length; i++) {

            int num = nums[i];
            int sum = 0;

            // Find sum of digits
            while (num > 0) {
                sum += num % 10;
                num = num / 10;
            }

            // Check condition
            if (sum == i) {
                return i;
            }
        }

        return -1;
    }
}