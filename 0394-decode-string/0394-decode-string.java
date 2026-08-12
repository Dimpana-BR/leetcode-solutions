class Solution {
    public String decodeString(String s) {
        Stack<Integer> number = new Stack<>();
        Stack<String> letters = new Stack<>();
        String current = "";
        int num =0 ;
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if(Character.isDigit(ch)){
                num = num * 10+(ch - '0');
            }else if(ch=='['){
                number.push(num);
                letters.push(current);
                num=0;
                current = "";
            }else if(Character.isLetter(ch)){
                current = current + ch;
            }else if(ch == ']'){
                int times = number.pop();
                String previous = letters.pop();
                String temp = "";
                for(int j=0;j<times;j++){
                    temp = temp + current;
                }
                current = previous + temp;
            }
        }
        return current;
    }
}