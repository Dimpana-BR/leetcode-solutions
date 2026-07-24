class Solution {
    public int uniqueXorTriplets(int[] nums) {
        boolean[] set1 = new boolean[2048];
        for (int x : nums) {
            set1[x] = true;
        }

        boolean[] set2 = new boolean[2048];
        for (int i = 0; i < 2048; i++) {
            if (!set1[i]) continue;
            for (int j = 0; j < 2048; j++) {
                if (set1[j]) {
                    set2[i ^ j] = true;
                }
            }
        }

        boolean[] set3 = new boolean[2048];
        for (int i = 0; i < 2048; i++) {
            if (!set2[i]) continue;
            for (int j = 0; j < 2048; j++) {
                if (set1[j]) {
                    set3[i ^ j] = true;
                }
            }
        }

        int count = 0;
        for (int i = 0; i < 2048; i++) {
            if (set3[i]) count++;
        }

        return count;
    }
}