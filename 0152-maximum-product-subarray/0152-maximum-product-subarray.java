class Solution {
    public int maxProduct(int[] nums) {
        int currentmax=nums[0];
        int currentmin=nums[0];
        int result=nums[0];
        for(int i=1;i<nums.length;i++){
            int temp=currentmax;
            currentmax=Math.max(nums[i],Math.max(temp*nums[i],currentmin*nums[i]));
            currentmin=Math.min(nums[i],Math.min(temp*nums[i],currentmin*nums[i]));
            result=Math.max(result,currentmax);
        }
        return result;
    }
}