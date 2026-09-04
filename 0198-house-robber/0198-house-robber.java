class Solution {
    public int rob(int[] nums) {
        int prev1=0;
        int prev2=0;
        for(int num:nums){
            int take=prev2+num;
            int skip=prev1;
            int current = Math.max(take,skip);
            prev2=prev1;
            prev1=current;
        }
        return prev1;
    }
}