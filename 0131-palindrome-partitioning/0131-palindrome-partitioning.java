class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        List<String> path = new ArrayList<>();
        backtrack(s,0,path,result);
        return result;
    }
    public void backtrack(String s,int start,List<String> path,List<List<String>> result){
        if(start==s.length()){
            result.add(new ArrayList<>(path));
            return;
        }
        for(int end=start;end<s.length();end++){
            if(ispalindrome(s,start,end)){
                path.add(s.substring(start,end+1));
                backtrack(s,end+1,path,result);
                path.remove(path.size()-1);
            }
        }
    }
    public boolean ispalindrome(String s,int left,int right){
        while(left<right){
            if(s.charAt(left)!=s.charAt(right)){
                return false;
            }
            left++;
            right--;

        }
        return true;
    }

}