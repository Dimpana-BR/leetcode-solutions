class Solution {
    public int maxProfit(int[] prices) {
        int minprofit=prices[0];
        int maxprofit=0;
        for(int i=0;i<prices.length;i++){
            if(prices[i]<minprofit){
                minprofit=prices[i];
            }
            int profit=prices[i]-minprofit;
            if(profit>maxprofit){
                maxprofit=profit;
            }
        }
        return maxprofit;
    }
}