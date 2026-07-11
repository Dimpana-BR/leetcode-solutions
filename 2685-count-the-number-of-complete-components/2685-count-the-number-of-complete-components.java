

class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        // Build the adjacency list representation of the graph
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        
        boolean[] visited = new boolean[n];
        int completeComponentsCount = 0;
        
        // Traverse all vertices to find all connected components
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                List<Integer> componentNodes = new ArrayList<>();
                // Find all nodes in the current connected component
                dfs(i, adj, visited, componentNodes);
                
                // Check if the component is complete
                // Every node in a complete component must have a degree equal to (total nodes - 1)
                boolean isComplete = true;
                int componentSize = componentNodes.size();
                for (int node : componentNodes) {
                    if (adj.get(node).size() != componentSize - 1) {
                        isComplete = false;
                        break;
                    }
                }
                
                if (isComplete) {
                    completeComponentsCount++;
                }
            }
        }
        
        return completeComponentsCount;
    }
    
    private void dfs(int node, List<List<Integer>> adj, boolean[] visited, List<Integer> componentNodes) {
        visited[node] = true;
        componentNodes.add(node);
        
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, adj, visited, componentNodes);
            }
        }
    }
}