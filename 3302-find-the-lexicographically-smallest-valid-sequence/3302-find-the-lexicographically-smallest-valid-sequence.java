class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // suf[j] stores the minimum starting index in word1 
        // to form the suffix word2[j...m-1]
        int[] suf = new int[m + 1];
        suf[m] = n;
        
        int ptr = n - 1;
        for (int j = m - 1; j >= 0; j--) {
            while (ptr >= 0 && word1.charAt(ptr) != word2.charAt(j)) {
                ptr--;
            }
            suf[j] = ptr;
            if (ptr >= 0) {
                ptr--; // move to next available character for the prefix
            }
        }

        int[] ans = new int[m];
        boolean changed = false;
        int p = 0; // Pointer for word2

        for (int i = 0; i < n && p < m; i++) {
            boolean isMatch = word1.charAt(i) == word2.charAt(p);
            
            if (isMatch) {
                ans[p++] = i;
            } else if (!changed) {
                // Check if the remaining word2[p + 1...] can be matched after index i
                if (p + 1 == m || suf[p + 1] > i) {
                    ans[p++] = i;
                    changed = true;
                }
            }
        }

        return p == m ? ans : new int[0];
    }
}