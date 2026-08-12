class Solution {
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for(int i=0;i<nums.length;i++){
            if(set.contains(nums[i])){
                return true;
            }
            set.add(nums[i]);
        }
        return false;
    }
}

----By using the HashMap-----
class Solution {
    public boolean containsDuplicate(int[] nums) {
       HashMap<Integer,Boolean> map = new HashMap<>();
       for(int i=0;i<nums.length;i++){
        if(map.containsKey(nums[i])){
            return true;
        }
        map.put(nums[i],true);
       }
       return false;
    }
}
