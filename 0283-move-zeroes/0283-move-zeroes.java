class Solution {
    public void moveZeroes(int[] nums) {
        int left=0;
        for(int right=0;right<nums.length;right++){
            if(nums[right]!=0){
                swap(left,right,nums);
                left++;
            }
        }
    }
    public void swap(int left,int right,int[] nums){
        int temp=nums[left];
        nums[left]=nums[right];
        nums[right]=temp;
    }
}