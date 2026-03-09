// Problem: Find All Possible Stable Binary Arrays I
// Difficulty: Medium
// Source: LeetCode
// Language: Java
// Synced by CodexSync
class Solution {

    static final int MOD = 1000000007;
    Integer[][][] dp;

    public int numberOfStableArrays(int zero, int one, int limit) {
        dp = new Integer[zero + 1][one + 1][2];
        long ans = 0;

        if (zero > 0) ans = (ans + dfs(zero - 1, one, 0, 1, limit)) % MOD;
        if (one > 0) ans = (ans + dfs(zero, one - 1, 1, 1, limit)) % MOD;

        return (int) ans;
    }

    private long dfs(int z, int o, int last, int run, int limit) {

        if (z == 0 && o == 0) return 1;

        if (dp[z][o][last] != null && run == 1)
            return dp[z][o][last];

        long res = 0;

        if (last == 0) {
            if (z > 0 && run < limit)
                res = (res + dfs(z - 1, o, 0, run + 1, limit)) % MOD;

            if (o > 0)
                res = (res + dfs(z, o - 1, 1, 1, limit)) % MOD;
        } 
        else {
            if (o > 0 && run < limit)
                res = (res + dfs(z, o - 1, 1, run + 1, limit)) % MOD;

            if (z > 0)
                res = (res + dfs(z - 1, o, 0, 1, limit)) % MOD;
        }

        if (run == 1) dp[z][o][last] = (int) res;

        return res;
    }
}