// Problem: Find All Possible Stable Binary Arrays II
// Difficulty: Hard
// Source: LeetCode
// Language: Java
// Synced by CodexSync
class Solution {
    public int numberOfStableArrays(int zero, int one, int limit) {
        long MOD = 1000000007;
        
        // dp[i][j][0] = valid arrays with i zeros, j ones, ending in 0
        // dp[i][j][1] = valid arrays with i zeros, j ones, ending in 1
        int[][][] dp = new int[zero + 1][one + 1][2];

        // Base Cases: Arrays purely made of 0s or 1s up to the limit
        for (int i = 1; i <= Math.min(zero, limit); i++) {
            dp[i][0][0] = 1;
        }
        for (int j = 1; j <= Math.min(one, limit); j++) {
            dp[0][j][1] = 1;
        }

        // DP Traversal
        for (int i = 1; i <= zero; i++) {
            for (int j = 1; j <= one; j++) {
                
                // Calculate for arrays ending in 0
                long val0 = (long) dp[i - 1][j][0] + dp[i - 1][j][1];
                if (i > limit) {
                    val0 -= dp[i - limit - 1][j][1];
                }
                // Handle modulo for potential negative numbers after subtraction
                dp[i][j][0] = (int) ((val0 % MOD + MOD) % MOD);

                // Calculate for arrays ending in 1
                long val1 = (long) dp[i][j - 1][0] + dp[i][j - 1][1];
                if (j > limit) {
                    val1 -= dp[i][j - limit - 1][0];
                }
                // Handle modulo for potential negative numbers after subtraction
                dp[i][j][1] = (int) ((val1 % MOD + MOD) % MOD);
            }
        }

        // Total valid arrays is the sum of those ending in 0 and those ending in 1
        return (int) (((long) dp[zero][one][0] + dp[zero][one][1]) % MOD);
    }
}