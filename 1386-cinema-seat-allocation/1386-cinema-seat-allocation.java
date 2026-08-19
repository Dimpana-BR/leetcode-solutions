

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> rowMasks = new HashMap<>();
        
        // Build bitmask for seats 2 to 9 for rows that have reserved seats
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            if (col >= 2 && col <= 9) {
                rowMasks.put(row, rowMasks.getOrDefault(row, 0) | (1 << (col - 2)));
            }
        }
        
        // Start with maximum possible groups (2 groups per row)
        int maxGroups = 2 * n;
        
        for (int mask : rowMasks.values()) {
            boolean leftPossible = (mask & 0b00001111) == 0;   // Seats 2, 3, 4, 5
            boolean rightPossible = (mask & 0b11110000) == 0;  // Seats 6, 7, 8, 9
            boolean midPossible = (mask & 0b00111100) == 0;    // Seats 4, 5, 6, 7
            
            if (leftPossible && rightPossible) {
                // Both 2 groups fit, no deduction needed
                continue;
            } else if (leftPossible || rightPossible || midPossible) {
                // Only 1 group fits
                maxGroups -= 1;
            } else {
                // 0 groups fit
                maxGroups -= 2;
            }
        }
        
        return maxGroups;
    }
}