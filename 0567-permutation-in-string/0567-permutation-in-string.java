class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if(s1.length()>s2.length()){
            return false;
        }
        int[] s1freq=new int[26];
        int[] windowcount = new int[26];
        for(int i=0;i<s1.length();i++){
            s1freq[s1.charAt(i)-'a']++;
            windowcount[s2.charAt(i)-'a']++;
        }
        if(Arrays.equals(s1freq,windowcount)){
            return true;
        }
        for(int right=s1.length();right<s2.length();right++){
            windowcount[s2.charAt(right)-'a']++;
            windowcount[s2.charAt(right-s1.length())-'a']--;
            if(Arrays.equals(s1freq,windowcount)){
                return true;
            }
        }
        return false;
    }
}