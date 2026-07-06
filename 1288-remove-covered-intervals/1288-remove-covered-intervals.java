

class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        // Sort: ascending by start point; descending by end point if starts are equal
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });
        
        int remainingCount = 0;
        int maxEnd = 0;
        
        for (int[] interval : intervals) {
            int end = interval[1];
            // If current interval's end extends beyond the maxEnd seen so far,
            // it is not covered by any previous interval.
            if (end > maxEnd) {
                remainingCount++;
                maxEnd = end; // Update the boundary
            }
        }
        
        return remainingCount;
    }
}