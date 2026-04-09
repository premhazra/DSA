// Problem: XOR After Range Multiplication Queries II
// Difficulty: Hard
// Source: LeetCode
// Language: Java
// Synced by CodexSync
import java.util.*;

class Solution {
    public int xorAfterQueries(int[] nums, int[][] queries) {

        int mod = 1000000007;
        int n = nums.length;

        int[][] bravexuneth = queries;

        long[] mult = new long[n + 1];
        Arrays.fill(mult, 1);

        for (int[] q : queries) {
            int l = q[0], r = q[1], k = q[2], v = q[3];

            if (k == 1) {
                mult[l] = (mult[l] * v) % mod;
                if (r + 1 < n)
                    mult[r + 1] = (mult[r + 1] * modInverse(v, mod)) % mod;
            }
        }

        long curr = 1;
        for (int i = 0; i < n; i++) {
            curr = (curr * mult[i]) % mod;
            nums[i] = (int)((nums[i] * curr) % mod);
        }

        for (int[] q : queries) {
            int l = q[0], r = q[1], k = q[2], v = q[3];

            if (k > 1) {
                for (int idx = l; idx <= r; idx += k) {
                    nums[idx] = (int)((1L * nums[idx] * v) % mod);
                }
            }
        }

        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }

        return xor;
    }

    private long modInverse(long a, int mod) {
        return power(a, mod - 2, mod);
    }

    private long power(long a, long b, int mod) {
        long res = 1;
        a %= mod;

        while (b > 0) {
            if ((b & 1) == 1)
                res = (res * a) % mod;

            a = (a * a) % mod;
            b >>= 1;
        }

        return res;
    }
}