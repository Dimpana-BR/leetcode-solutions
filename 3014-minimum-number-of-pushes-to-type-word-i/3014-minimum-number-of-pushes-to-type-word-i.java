class Solution {
    public int minimumPushes(String word) {
        int n = word.length();
        int pushes = 0;

        for (int i = 0; i < n; i++) {
            // Determine which "round" of key assignments the letter belongs to:
            // First 8 letters require 1 push each
            // Next 8 letters require 2 pushes each
            // Next 8 letters require 3 pushes each
            // Remaining letters require 4 pushes each
            pushes += (i / 8) + 1;
        }

        return pushes;
    }
}