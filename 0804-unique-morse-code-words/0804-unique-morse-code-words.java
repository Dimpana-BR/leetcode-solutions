class Solution {
    public int uniqueMorseRepresentations(String[] words) {
        String[] morse={".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        HashSet<String> set = new HashSet<>();
        for(String word:words){
            StringBuilder code = new StringBuilder();
            for(char ch:word.toCharArray()){
                code.append(morse[ch-'a']);
            }
            set.add(code.toString());
        }
        return set.size();
    }
}