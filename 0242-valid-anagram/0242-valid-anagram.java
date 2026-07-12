class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length()!=t.length()){
            return false;
        }
        HashMap<Character,Integer> map = new HashMap<>();
       for(int i=0;i<s.length();i++){
        char ch=s.charAt(i);
        if(map.containsKey(ch)){
            map.put(ch,map.get(ch)+1);
        }else{
            map.put(ch,1);
        }
       }
       for(int j=0;j<t.length();j++){
        char th=t.charAt(j);
        if(!map.containsKey(th)){
            return false;
        }
        map.put(th,map.get(th)-1);
        if(map.get(th)==0){
            map.remove(th);
        }

       }
       if(map.isEmpty()){
        return true;
       }
       return false;

      
    }

}