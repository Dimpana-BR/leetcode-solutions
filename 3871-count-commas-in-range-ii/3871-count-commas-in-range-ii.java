class Solution {
    public long countCommas(long n) {
        long totalCommas = 0;
        long threshold = 1000L; // First comma appears at 1,000 (10^3)

        while (n >= threshold) {
            totalCommas += (n - threshold + 1);
            
            // Move to the next threshold (10^6, 10^9, 10^12, 10^15)
            // Check overflow before multiplying
            if (threshold > Long.MAX_VALUE / 1000) {
                break;
            }
            threshold *= 1000L;
        }

        return totalCommas;
    }
}