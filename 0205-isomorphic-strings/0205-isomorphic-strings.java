class Solution {
    public boolean isIsomorphic(String s, String t) {
        HashMap<Character,Character> map1 = new HashMap<Character,Character>();
        HashMap<Character,Character> map2 = new HashMap<Character,Character>();

        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            char th=t.charAt(i);
            if(map1.containsKey(ch)){
               if(map1.get(ch)!=th){
                return false;
               }}else{
                map1.put(ch,th);
               }
            
            if(map2.containsKey(th)){
                if(map2.get(th)!=ch){
                    return false;
                }}else{
                    map2.put(th,ch);
                }
            }
           
            
        
     return true;
    }
}