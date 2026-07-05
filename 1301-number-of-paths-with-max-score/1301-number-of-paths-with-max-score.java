

class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int MOD = 1_000_000_007;
        
        // dp[r][c][0] -> max score from (r, c) to (0,0)
        // dp[r][c][1] -> path count to get that max score
        int[][][] dp = new int[n][n][2];
        
        // Initialize all scores to -1 and path counts to 0
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][0] = -1;
                dp[i][j][1] = 0;
            }
        }
        
        // Base Case: Destination 'E' at (0, 0)
        dp[0][0][0] = 0;
        dp[0][0][1] = 1;
        
        // Directions to look back: Up, Left, Up-Left
        int[][] dirs = {{-1, 0}, {0, -1}, {-1, -1}};
        
        // Fill the DP table
        for (int r = 0; r < n; r++) {
            String rowStr = board.get(r);
            for (int c = 0; c < n; c++) {
                char cell = rowStr.charAt(c);
                
                // Skip obstacles or the destination cell since it's already initialized
                if (cell == 'X' || (r == 0 && c == 0)) {
                    continue;
                }
                
                int maxPrevScore = -1;
                int totalPaths = 0;
                
                // Check the 3 preceding valid cells
                for (int[] dir : dirs) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    
                    if (nr >= 0 && nc >= 0 && dp[nr][nc][0] != -1) {
                        int prevScore = dp[nr][nc][0];
                        int prevPaths = dp[nr][nc][1];
                        
                        if (prevScore > maxPrevScore) {
                            maxPrevScore = prevScore;
                            totalPaths = prevPaths;
                        } else if (prevScore == maxPrevScore) {
                            totalPaths = (totalPaths + prevPaths) % MOD;
                        }
                    }
                }
                
                // If this cell is reachable from at least one valid cell
                if (maxPrevScore != -1) {
                    int currentVal = (cell == 'S') ? 0 : (cell - '0');
                    dp[r][c][0] = maxPrevScore + currentVal;
                    dp[r][c][1] = totalPaths;
                }
            }
        }
        
        // Return result at starting position 'S'
        int resScore = dp[n - 1][n - 1][0];
        int resPaths = dp[n - 1][n - 1][1];
        
        if (resScore == -1) {
            return new int[]{0, 0};
        }
        return new int[]{resScore, resPaths};
    }
}