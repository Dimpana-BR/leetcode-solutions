

class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();
        
        // minHealthLost[i][j] will store the minimum health cost to reach cell (i, j)
        int[][] minHealthLost = new int[m][n];
        for (int[] row : minHealthLost) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        
        // Deque for 0-1 BFS
        // Each element in the deque is an array representing {row, col}
        Deque<int[]> deque = new ArrayDeque<>();
        
        // Initialize the starting position
        int startCost = grid.get(0).get(0);
        minHealthLost[0][0] = startCost;
        deque.offerFirst(new int[]{0, 0});
        
        // Direction vectors for moving Up, Down, Left, Right
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        while (!deque.isEmpty()) {
            int[] curr = deque.pollFirst();
            int r = curr[0];
            int c = curr[1];
            
            // If we've reached the destination, we can break early
            if (r == m - 1 && c == n - 1) {
                break;
            }
            
            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                
                // Check grid boundaries
                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    int weight = grid.get(nr).get(nc);
                    int newCost = minHealthLost[r][c] + weight;
                    
                    // If we found a path with fewer health deductions
                    if (newCost < minHealthLost[nr][nc]) {
                        minHealthLost[nr][nc] = newCost;
                        
                        // 0-1 BFS optimization: 
                        // If weight is 0, push to front. If weight is 1, push to back.
                        if (weight == 0) {
                            deque.offerFirst(new int[]{nr, nc});
                        } else {
                            deque.offerLast(new int[]{nr, nc});
                        }
                    }
                }
            }
        }
        
        // We need the remaining health to be at least 1
        // Remaining health = health - minimum health lost
        return health - minHealthLost[m - 1][n - 1] >= 1;
    }
}