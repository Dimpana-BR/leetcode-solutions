class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
       HashMap<String,List<String>> map = new HashMap<>();
        for(int i=0;i<strs.length;i++){
            String word = strs[i];
            char[] ch = word.toCharArray();
            Arrays.sort(ch);
            String key = new String(ch);
            if(!map.containsKey(key)){
                map.put(key,new ArrayList<String>());
            }
            map.get(key).add(word);
        }
        return new ArrayList<List<String>>(map.values());
    }
}