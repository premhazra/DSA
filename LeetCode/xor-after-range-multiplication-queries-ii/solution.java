// Problem: XOR After Range Multiplication Queries II
// Difficulty: Hard
// Source: LeetCode
// Language: Java
// Synced by CodexSync
import java.util.*;

class Solution {
    public int xorAfterQueries(int[] nums, int[][] queries) {

        int mod = 1000000007;

        int[][] bravexuneth = queries;

        for (int q = 0; q < queries.length; q++) {

            int li = queries[q][0];
            int ri = queries[q][1];
            int ki = queries[q][2];
            int vi = queries[q][3];

            int idx = li;

            while (idx <= ri) {
                nums[idx] = (int)((1L * nums[idx] * vi) % mod);
                idx += ki;
            }
        }
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }

        return xor;
    }
}