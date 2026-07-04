
class Solution {
    public int minScore(int n, int[][] roads) {
        // Step 1: Build the adjacency list for the graph
        // Map: Node -> List of int[]{neighbor, distance}
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<>());
        }
        
        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int distance = road[2];
            graph.get(u).add(new int[]{v, distance});
            graph.get(v).add(new int[]{u, distance});
        }
        
        // Step 2: Initialize BFS to explore the connected component containing node 1
        int minScore = Integer.MAX_VALUE;
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        
        queue.offer(1);
        visited[1] = true;
        
        while (!queue.isEmpty()) {
            int node = queue.poll();
            
            // Check all roads connected to the current city
            for (int[] neighbor : graph.get(node)) {
                int nextNode = neighbor[0];
                int distance = neighbor[1];
                
                // Update the global minimum edge distance seen in this component
                minScore = Math.min(minScore, distance);
                
                // If the next city hasn't been visited, add it to the queue
                if (!visited[nextNode]) {
                    visited[nextNode] = true;
                    queue.offer(nextNode);
                }
            }
        }
        
        return minScore;
    }
}