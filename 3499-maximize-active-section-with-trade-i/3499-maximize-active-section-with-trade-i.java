class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int n = s.length();
        int totalOnes = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                totalOnes++;
            }
        }

        // Collect lengths of all zero segments in order
        java.util.List<Integer> zeroLengths = new java.util.ArrayList<>();
        int currentZeros = 0;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                currentZeros++;
            } else {
                if (currentZeros > 0 || zeroLengths.size() > 0) {
                    // Store zero segment length (can be 0 if '1's are adjacent, 
                    // but we only track actual '0' blocks)
                }
            }
        }

        // Alternative cleaner parsing: parse s into zero block lengths
        java.util.List<Integer> zeroBlocks = new java.util.ArrayList<>();
        int i = 0;
        // Due to augmentation '1' + s + '1', leading and trailing zeros form valid zero blocks
        while (i < n) {
            if (s.charAt(i) == '0') {
                int start = i;
                while (i < n && s.charAt(i) == '0') {
                    i++;
                }
                zeroBlocks.add(i - start);
            } else {
                i++;
            }
        }

        // If there are fewer than 2 zero blocks, no '1' block can be between two zero blocks
        if (zeroBlocks.size() < 2) {
            return totalOnes;
        }

        int maxDelta = 0;
        
        // Check adjacent zero blocks surrounding a '1' block
        // Note: consecutive zero blocks in zeroBlocks list are separated by exactly one '1' block
        for (int j = 0; j < zeroBlocks.size() - 1; j++) {
            int gain = zeroBlocks.get(j) + zeroBlocks.get(j + 1);
            maxDelta = Math.max(maxDelta, gain);
        }

        return totalOnes + maxDelta;
    }
}