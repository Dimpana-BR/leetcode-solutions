class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] answer = nums.clone();
        Arrays.sort(answer);
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<answer.length;i++){
            map.putIfAbsent(answer[i],i);
        }
        int[] result = new int[nums.length];
        for(int i=0;i<nums.length;i++){
            result[i] = map.get(nums[i]);
        }
        return result;
    }
}