class Solution {
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] parted = path.split("/");
        for(int i=0;i<parted.length;i++){
            String part = parted[i];
            if(part.equals("")|| part.equals(".")){
                continue;
            }if(part.equals("..")){
                if(!stack.isEmpty()){
                    stack.pop();
                }
            }else{
                stack.push(part);
            }
        }
        if(stack.isEmpty()){
            return "/";
        }
        String result ="";
        while(!stack.isEmpty()){
            result = "/"+stack.pop()+result;
        }
        return result;

    }
}