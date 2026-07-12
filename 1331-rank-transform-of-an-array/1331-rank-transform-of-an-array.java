class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int[] temparr=arr.clone();
        Arrays.sort(temparr);
        HashMap<Integer,Integer> map = new HashMap<>();
        int rank=1;
        for(int i=0;i<temparr.length;i++){
            if(!map.containsKey(temparr[i])){
             map.put(temparr[i],rank);
             rank++;
            }
        }
    for(int i=0;i<arr.length;i++){
        arr[i]=map.get(arr[i]);
    }
    return arr;
    }
}