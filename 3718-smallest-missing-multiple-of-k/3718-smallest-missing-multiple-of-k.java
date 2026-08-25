class Solution {
    public int missingMultiple(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (num > 0 && num % k == 0) {
                set.add(num / k);
            }
        }
        int ans = 1;
        while (set.contains(ans)) {
            ans++;
        }
        return ans * k;
    }
}