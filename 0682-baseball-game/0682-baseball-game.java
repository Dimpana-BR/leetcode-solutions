class Solution {
    public int calPoints(String[] operations) {
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<operations.length;i++){
            String op = operations[i];
            if(op.equals("C")){
                stack.pop();
            }else if(op.equals("D")){
                stack.push(stack.peek()*2);
            }else if(op.equals("+")){
                int first=stack.pop();
                int second=stack.peek();
                stack.push(first);
                stack.push(first+second);
            }else{
                int sum =Integer.parseInt(op);
                stack.push(sum);
            }
        }
        int finals=0;
        while(!stack.isEmpty()){
            finals=finals+stack.pop();
        }
        return finals;
    }
}