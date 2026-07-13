class Solution {
    public int minimumRecolors(String blocks, int k) {
        
        int count=0;
        for(int i=0;i<k;i++){
            char ch=blocks.charAt(i);
            if(ch=='W'){
                count++;
            }
        }
        int min=count;
        for(int i=k;i<blocks.length();i++){
           char left=blocks.charAt(i-k);
           if(left=='W'){
            count--;
           }
           char right=blocks.charAt(i);
           if(right=='W'){
            count++;
           }
            min=Math.min(count,min);

        }
        return min;
    }
}