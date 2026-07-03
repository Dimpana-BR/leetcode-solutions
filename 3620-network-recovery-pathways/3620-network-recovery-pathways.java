
public class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        
        // Build adjacency list
        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        int maxCost = 0;
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int cost = edge[2];
            adj[u].add(new int[]{v, cost});
            maxCost = Math.max(maxCost, cost);
        }
        
        // Binary search for the maximum possible minimum-edge-cost
        int low = 0, high = maxCost;
        int ans = -1;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canReach(n, adj, online, k, mid)) {
                ans = mid;       // mid is possible, try to find a larger score
                low = mid + 1;
            } else {
                high = mid - 1;  // mid is too large, reduce the score threshold
            }
        }
        
        return ans;
    }
    
    private boolean canReach(int n, List<int[]>[] adj, boolean[] online, long k, int minEdgeCostThreshold) {
        // Distances array to keep track of the minimum total cost to reach each node
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;
        
        // Priority Queue for Dijkstra's algorithm: {node, current_total_cost}
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
        pq.offer(new long[]{0, 0});
        
        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            int u = (int) curr[0];
            long currentCost = curr[1];
            
            if (currentCost > dist[u]) continue;
            if (u == n - 1) return currentCost <= k;
            
            for (int[] edge : adj[u]) {
                int v = edge[0];
                int weight = edge[1]; // Fixed: changed index from 2 to 1
                
                // Condition 1: Node must be online
                // Condition 2: Edge weight must be at least the binary search pivot threshold
                if (!online[v] || weight < minEdgeCostThreshold) {
                    continue;
                }
                
                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.offer(new long[]{v, dist[v]});
                }
            }
        }
        
        return dist[n - 1] <= k;
    }
}