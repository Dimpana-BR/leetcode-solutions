class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int left=1;
        int right=0;
        for(int i=0;i<piles.length;i++){
            if(piles[i]>right){
                right=piles[i];
            }
        }
       while(left<right){
        int mid=left+(right-left)/2;
        int hour=0;
        for(int i=0;i<piles.length;i++){
            hour=hour+piles[i]/mid;
            if(piles[i]%mid!=0){
                hour++;
            }
        }
        if(hour<=h){
            right=mid;
        }else{
            left=mid+1;
        }
       }
       return left;
    }
}
//Find the smallest eating speed k so that Koko can finish all the banana piles within h hours.