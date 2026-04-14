// Problem: Minimum Total Distance Traveled
// Difficulty: Hard
// Source: LeetCode
// Language: Java
// Synced by CodexSync
import java.util.*;

class Solution {
    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        
        // Step 1: Sort robots
        Collections.sort(robot);
        
        // Step 2: Sort factories by position
        Arrays.sort(factory, (a, b) -> a[0] - b[0]);
        
        // Step 3: Expand factory limits
        List<Integer> slots = new ArrayList<>();
        for (int[] f : factory) {
            int pos = f[0];
            int limit = f[1];
            for (int i = 0; i < limit; i++) {
                slots.add(pos);
            }
        }
        
        int n = robot.size();
        int m = slots.size();
        
        // Step 4: DP array
        long[][] dp = new long[n + 1][m + 1];
        
        // Initialize
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dp[i], Long.MAX_VALUE / 2);
        }
        
        // Step 5: DP computation
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                
                // Option 1: skip this factory slot
                dp[i][j] = dp[i][j - 1];
                
                // Option 2: assign robot to this slot
                long cost = Math.abs(robot.get(i - 1) - slots.get(j - 1));
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + cost);
            }
        }
        
        return dp[n][m];
    }
}