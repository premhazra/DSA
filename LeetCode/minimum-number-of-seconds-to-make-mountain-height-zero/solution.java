// Problem: Minimum Number of Seconds to Make Mountain Height Zero
// Difficulty: Medium
// Source: LeetCode
// Language: Java
// Synced by CodexSync
class Solution {

    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {

        long left = 0;
        long right = (long)1e18;
        long ans = right;

        while (left <= right) {

            long mid = (left + right) / 2;

            if (can(mid, mountainHeight, workerTimes)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return ans;
    }

    boolean can(long time, int mountainHeight, int[] workerTimes) {

        long total = 0;

        for (int w : workerTimes) {

            long val = time / w;

            long x = (long)((Math.sqrt(1 + 8.0 * val) - 1) / 2);

            total += x;

            if (total >= mountainHeight)
                return true;
        }

        return false;
    }
}