

class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // Create an array of indices and sort them by their corresponding values in nums
        Integer[] sortedIndices = new Integer[n];
        for (int i = 0; i < n; i++) {
            sortedIndices[i] = i;
        }
        Arrays.sort(sortedIndices, (a, b) -> Integer.compare(nums[a], nums[b]));

        // Position mapping to find where an original index lies in the sorted array
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) {
            rank[sortedIndices[i]] = i;
        }

        // Binary lifting table initialization
        int LOG = 18; // since 2^17 = 131072 > 10^5
        int[][] up = new int[LOG][n];
        
        // Find the furthest reachable node to the right for each sorted index
        int right = 0;
        for (int i = 0; i < n; i++) {
            while (right < n && nums[sortedIndices[right]] - nums[sortedIndices[i]] <= maxDiff) {
                right++;
            }
            // right - 1 is the furthest node index in sortedIndices reachable from i
            up[0][i] = right - 1;
        }

        // Fill the sparse table for binary lifting
        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < n; i++) {
                up[j][i] = up[j - 1][up[j - 1][i]];
            }
        }

        int[] answer = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {
            int u = queries[q][0];
            int v = queries[q][1];

            if (u == v) {
                answer[q] = 0;
                continue;
            }

            // Ensure u is the smaller value node, v is the larger
            int rU = rank[u];
            int rV = rank[v];
            if (rU > rV) {
                int temp = rU;
                rU = rV;
                rV = temp;
            }

            // Check if v is completely unreachable from u
            // Max reach from rU using maximum possible jumps in the component
            int maxReach = rU;
            for (int j = LOG - 1; j >= 0; j--) {
                maxReach = up[j][maxReach];
            }
            if (maxReach < rV) {
                answer[q] = -1;
                continue;
            }

            // Count the minimum steps needed to reach or pass rV
            int steps = 0;
            int curr = rU;
            for (int j = LOG - 1; j >= 0; j--) {
                if (up[j][curr] < rV) {
                    steps += (1 << j);
                    curr = up[j][curr];
                }
            }
            
            // One final jump from `curr` is needed to cross or equal rV
            answer[q] = steps + 1;
        }

        return answer;
    }
}