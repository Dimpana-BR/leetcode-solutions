class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<tokens.length;i++){
            String st = tokens[i];
            if(st.equals("+")){
                int a = stack.pop();
                int b=stack.pop();
                stack.push(a+b);
            }else if(st.equals("-")){
                int a = stack.pop();
                int b= stack.pop();
                stack.push(b-a);
            }else if(st.equals("*")){
                int a = stack.pop();
                int b = stack.pop();
                stack.push(a*b);
            }else if(st.equals("/")){
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b/a);
            }else{
                int num = Integer.parseInt(st);
                stack.push(num);
            }
        }
        return stack.peek();
    }
}