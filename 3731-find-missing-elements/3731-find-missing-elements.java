class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        for(int i=0;i<nums.length-1;i++){
            int current=nums[i];
            int next=nums[i+1];
            if(next-current>1){
                for(int j=current+1;j<next;j++){
                list.add(j);
                }
            }
        }
        return list;
    }
}