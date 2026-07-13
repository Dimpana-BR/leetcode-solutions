class Solution {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int window=0;
         int count=0;
        for(int i=0;i<k;i++){
            window=window+arr[i];
        }
            if((double)window / k >= threshold){
            count++;
        }
        
        double avg=0;
       
        for(int i=k;i<arr.length;i++){
            window=window-arr[i-k]+arr[i];
            avg=window/k;
            if(avg>=threshold){
                count++;
            }
        }
        return count;
    }
}