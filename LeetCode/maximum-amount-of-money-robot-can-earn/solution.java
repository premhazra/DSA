// Problem: Maximum Amount of Money Robot Can Earn
// Difficulty: Medium
// Source: LeetCode
// Language: Java
// Synced by CodexSync
class Solution {
    public int maximumAmount(int[][] coins) {
        int m = coins.length;
        int n = coins[0].length;
        // Use a safe INF value to prevent overflow during addition
        int INF = 1_000_000_000; 

        // dp[i][j][k] = max coins from (i, j) with k neutralizations remaining
        int[][][] dp = new int[m][n][3];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                for (int k = 0; k < 3; k++) {
                    // Base case: Bottom-right cell
                    if (i == m - 1 && j == n - 1) {
                        if (coins[i][j] < 0 && k > 0) {
                            dp[i][j][k] = 0;
                        } else {
                            dp[i][j][k] = coins[i][j];
                        }
                        continue;
                    }

                    // Get values from neighbors (with boundary checks)
                    int down = (i + 1 < m) ? dp[i + 1][j][k] : -INF;
                    int right = (j + 1 < n) ? dp[i][j + 1][k] : -INF;

                    // Option 1: Don't neutralize the current cell
                    int res = Math.max(down, right) + coins[i][j];

                    // Option 2: Neutralize the current cell (if it's a robber and we have skips)
                    if (coins[i][j] < 0 && k > 0) {
                        int downSkip = (i + 1 < m) ? dp[i + 1][j][k - 1] : -INF;
                        int rightSkip = (j + 1 < n) ? dp[i][j + 1][k - 1] : -INF;
                        res = Math.max(res, Math.max(downSkip, rightSkip));
                    }
                    
                    dp[i][j][k] = res;
                }
            }
        }

        return dp[0][0][2];
    }
}