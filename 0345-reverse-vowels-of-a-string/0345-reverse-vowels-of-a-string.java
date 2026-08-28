class Solution {
    public String reverseVowels(String s) {
        char[] ch = s.toCharArray();
        int left=0;
        int right=ch.length-1;
        while(left<right){
            while(left<right && !isvowel(ch[left])){
                left++;
            }
            while(left<right && !isvowel(ch[right])){
                right--;
            }
            char temp=ch[left];
            ch[left]=ch[right];
            ch[right]=temp;
            left++;
            right--;
        }

        return new String(ch);
    }
    public static boolean isvowel(char ch){
        return "aeiouAEIOU".indexOf(ch) != -1;
    }
}