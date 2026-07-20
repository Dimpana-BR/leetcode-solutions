class Solution {
    public int mySqrt(int x) {
        int left=0;
        int right=x;
        long num=0;
        while(left<=right){
            int mid=left+(right-left)/2;
            num=(long) mid*mid;
            if(num==x){
                return mid;
            }else if(num<x){
                left=mid+1;
            }else{
                right=mid-1;
            }
        }
        return right;
    }
}

//Memory Trick

//right is the last valid answer.