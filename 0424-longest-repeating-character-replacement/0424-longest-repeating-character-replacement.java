class Solution {
    public int characterReplacement(String s, int k) {
        HashMap<Character,Integer> map = new HashMap<>();
        int left=0;
        int max=0;
        int maxfreq=0;
        for(int right=0;right<s.length();right++){
            char ch=s.charAt(right);
            if(map.containsKey(ch)){
                map.put(ch,map.get(ch)+1);
            }else{
                map.put(ch,1);
            }
            maxfreq=Math.max(maxfreq,map.get(ch));
            while((right-left+1)-maxfreq>k){
                char leftch=s.charAt(left);
                map.put(leftch,map.get(leftch)-1);
                left++;
            }
            max=Math.max(max,right-left+1);
        }
        return max;
    }
}