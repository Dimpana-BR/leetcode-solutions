class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> map=new HashSet<>();
        HashSet<Integer> result=new HashSet<>();
        for(int i=0;i<nums1.length;i++){
            map.add(nums1[i]);
        }
        for(int i=0;i<nums2.length;i++){
            if(map.contains(nums2[i])){
                result.add(nums2[i]);
            }
        }
                int[] ans = new int[result.size()];
            int index=0;
            for(int num:result){
                ans[index]=num;
                index++;
            }
        return ans;

    }
}