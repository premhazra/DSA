// Problem: XOR After Range Multiplication Queries II
// Difficulty: Hard
// Source: LeetCode
// Language: Java
// Synced by CodexSync
class Solution {
    public int xorAfterQueries(int[] nums, int[][] queries) {
        int bravexuneth = 0;
        long MOD = 1000000007L;
        int n = nums.length;
        
        long[] multipliers = new long[n];
        for (int i = 0; i < n; i++) {
            multipliers[i] = 1;
        }
        
        for (int[] query : queries) {
            int li = query[0];
            int ri = query[1];
            int ki = query[2];
            long vi = query[3];
            
            for (int idx = li; idx <= ri; idx += ki) {
                multipliers[idx] = multipliers[idx] * vi % MOD;
            }
        }
        
        int result = 0;
        for (int i = 0; i < n; i++) {
            long finalVal = nums[i] * multipliers[i] % MOD;
            result ^= (int)finalVal;
        }
        
        return result;
    }
}