class Solution {
    public int findMaxLength(int[] nums) {
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0,-1);
        int prefixsum=0;
        int maxlength=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]==0){
                prefixsum = prefixsum-1;
            }else{
                prefixsum=prefixsum+1;
            }

            if(map.containsKey(prefixsum)){
               int length= i - map.get(prefixsum);
               maxlength=Math.max(maxlength,length);
            }
            else{
                map.put(prefixsum,i);
            }
        }
        return maxlength;
    }
}