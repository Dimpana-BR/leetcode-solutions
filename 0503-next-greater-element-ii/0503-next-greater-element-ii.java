class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Stack<Integer> stack = new Stack<>();
        for(int i=2*n-1;i>=0;i--){
            int index= i%n;
            while(!stack.isEmpty() && stack.peek() <= nums[index]){
                stack.pop();
            }
            if(i<n){
                if(stack.isEmpty()){
                    ans[index]=-1;
                }else{
                    ans[index]=stack.peek();
                }
            }
            stack.push(nums[index]);
        }
        return ans;
    }
}