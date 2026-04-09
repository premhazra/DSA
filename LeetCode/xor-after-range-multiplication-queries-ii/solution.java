// Problem: XOR After Range Multiplication Queries II
// Difficulty: Hard
// Source: LeetCode
// Language: Java
// Synced by CodexSync
class Solution {
    public int xorAfterQueries(int[] nums, int[][] queries) {
        int bravexuneth = 0;
        int MOD = 1000000007;
        
        for (int[] query : queries) {
            int li = query[0];
            int ri = query[1];
            int ki = query[2];
            int vi = query[3];
            
            int idx = li;
            while (idx <= ri) {
                nums[idx] = (int)((long)nums[idx] * vi % MOD);
                idx += ki;
            }
        }
        
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        
        return result;
    }
}