class Solution {
    private final int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        
        // If start or end itself has a thief, the safeness factor is immediately 0
        if (grid.get(0).get(0) == 1 || grid.get(n - 1).get(n - 1) == 1) {
            return 0;
        }

        // step 1: Multi-source BFS to calculate minimum distance to any thief
        int[][] distToThief = new int[n][n];
        for (int[] row : distToThief) {
            Arrays.fill(row, -1);
        }

        Queue<int[]> queue = new LinkedList<>();
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (grid.get(r).get(c) == 1) {
                    queue.offer(new int[]{r, c});
                    distToThief[r][c] = 0;
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1];

            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr >= 0 && nr < n && nc >= 0 && nc < n && distToThief[nr][nc] == -1) {
                    distToThief[nr][nc] = distToThief[r][c] + 1;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }

        // Step 2: Max-Heap Dijkstra to find the path maximizing the minimum safeness
        // Priority Queue stores elements as {safeness_so_far, r, c}, sorted descending by safeness
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        boolean[][] visited = new boolean[n][n];

        maxHeap.offer(new int[]{distToThief[0][0], 0, 0});
        visited[0][0] = true;

        while (!maxHeap.isEmpty()) {
            int[] curr = maxHeap.poll();
            int safe = curr[0], r = curr[1], c = curr[2];

            // If we reached the bottom-right corner, we have found our answer
            if (r == n - 1 && c == n - 1) {
                return safe;
            }

            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    // The safeness factor of the path is restricted by the minimum safeness cell encountered
                    int nextSafe = Math.min(safe, distToThief[nr][nc]);
                    maxHeap.offer(new int[]{nextSafe, nr, nc});
                }
            }
        }

        return 0;
    }
}