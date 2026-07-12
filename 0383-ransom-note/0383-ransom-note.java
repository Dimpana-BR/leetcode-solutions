class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character,Integer> map = new HashMap<>();
        for(int i=0;i<magazine.length();i++){
            char ch=magazine.charAt(i);
            if(map.containsKey(ch)){
                map.put(ch,map.get(ch)+1);
            }else{
                map.put(ch,1);
            }
        }
        for(int i=0;i<ransomNote.length();i++){
            char th=ransomNote.charAt(i);
            if(!map.containsKey(th)){
                return false;
            }
            
            map.put(th,map.get(th)-1);
            if(map.get(th)==0){
                map.remove(th);
            }
        }
        return true;
    }
}