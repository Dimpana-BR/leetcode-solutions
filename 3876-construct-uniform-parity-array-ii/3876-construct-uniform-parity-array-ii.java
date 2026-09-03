class Solution {
    public boolean uniformArray(int[] nums1) {
        int minVal = Integer.MAX_VALUE;
        boolean hasOdd = false;

        for (int num : nums1) {
            if (num < minVal) {
                minVal = num;
            }
            if (num % 2 != 0) {
                hasOdd = true;
            }
        }

        // If there are no odd numbers, all are even -> true
        if (!hasOdd) {
            return true;
        }

        // If the smallest number is odd, we can make all numbers odd -> true
        return minVal % 2 != 0;
    }
}