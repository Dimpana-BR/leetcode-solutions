class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // Array to store the component ID for each node
        int[] component = new int[n];
        int currentComponentId = 0;
        component[0] = currentComponentId;
        
        // Group nodes into contiguous connected components
        for (int i = 1; i < n; i++) {
            if (nums[i] - nums[i - 1] > maxDiff) {
                currentComponentId++;
            }
            component[i] = currentComponentId;
        }
        
        // Process all queries in O(1) each
        boolean[] answer = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];
            answer[i] = (component[u] == component[v]);
        }
        
        return answer;
    }
}