
class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        // 1. Find the maximum value in nums
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }

        // 2. Count frequencies of each number in nums
        int[] freq = new int[maxVal + 1];
        for (int num : nums) {
            freq[num]++;
        }

        // 3. Count how many elements are divisible by each i
        long[] countDivisible = new long[maxVal + 1];
        for (int i = 1; i <= maxVal; i++) {
            for (int j = i; j <= maxVal; j += i) {
                countDivisible[i] += freq[j];
            }
        }

        // 4. Calculate exact pairs with GCD == i using inclusion-exclusion backwards
        long[] gcdPairsCount = new long[maxVal + 1];
        for (int i = maxVal; i >= 1; i--) {
            long totalPairsWithDivisorI = (countDivisible[i] * (countDivisible[i] - 1)) / 2;
            gcdPairsCount[i] = totalPairsWithDivisorI;
            
            // Subtract pairs that have a strictly larger GCD which is a multiple of i
            for (int j = 2 * i; j <= maxVal; j += i) {
                gcdPairsCount[i] -= gcdPairsCount[j];
            }
        }

        // 5. Create a prefix sum array of the counts
        long[] prefixSum = new long[maxVal + 1];
        for (int i = 1; i <= maxVal; i++) {
            prefixSum[i] = prefixSum[i - 1] + gcdPairsCount[i];
        }

        // 6. Answer each query using Binary Search
        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            long targetIndex = queries[i];
            
            // Binary search to find the smallest GCD value where prefixSum[gcd] > targetIndex
            int low = 1, high = maxVal, ans = maxVal;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (prefixSum[mid] > targetIndex) {
                    ans = mid;
                    high = mid - 1; // Try to find a smaller valid GCD
                } else {
                    low = mid + 1;
                }
            }
            answer[i] = ans;
        }

        return answer;
    }
}