// Problem: Maximum Walls Destroyed by Robots
// Difficulty: Hard
// Source: LeetCode
// Language: Java
// Synced by CodexSync
import java.util.*;

class Solution {
    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        int n = robots.length;
        int[][] r = new int[n][2];
        for (int i = 0; i < n; i++) {
            r[i][0] = robots[i];
            r[i][1] = distance[i];
        }
        Arrays.sort(r, (a, b) -> Integer.compare(a[0], b[0]));
        Arrays.sort(walls);

        // dp[i][0]: Max walls destroyed considering robots 0...i, robot i fires LEFT
        // dp[i][1]: Max walls destroyed considering robots 0...i, robot i fires RIGHT
        long[][] dp = new long[n][2];

        for (int i = 0; i < n; i++) {
            // Calculate walls hit if firing LEFT
            // Reach is limited by the previous robot's position
            long leftLimit = (i == 0) ? Long.MIN_VALUE : r[i-1][0];
            int wallsLeft = count(walls, Math.max(leftLimit, (long)r[i][0] - r[i][1]), r[i][0]);
            
            if (i == 0) {
                dp[i][0] = wallsLeft;
            } else {
                // To fire left, it doesn't matter what robot i-1 did because 
                // the bullets stop at the robots anyway.
                dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]) + wallsLeft;
            }

            // Calculate walls hit if firing RIGHT
            // Reach is limited by the NEXT robot's position
            long rightLimit = (i == n - 1) ? Long.MAX_VALUE : r[i+1][0];
            int wallsRight = count(walls, r[i][0], Math.min(rightLimit, (long)r[i][0] + r[i][1]));
            
            if (i == 0) {
                dp[i][1] = wallsRight;
            } else {
                // If robot i fires RIGHT, we must ensure we don't double count walls 
                // that robot i-1 might have also destroyed firing RIGHT.
                // However, the "bullet stops at robot" rule means robot i-1's RIGHT 
                // fire stops at robot i, and robot i's RIGHT fire starts at robot i.
                // They are perfectly contiguous but disjoint!
                dp[i][1] = Math.max(dp[i-1][0], dp[i-1][1]) + wallsRight;
            }
        }

        return (int)Math.max(dp[n-1][0], dp[n-1][1]);
    }

    private int count(int[] walls, long L, long R) {
        if (L > R) return 0;
        int start = lowerBound(walls, L);
        int end = upperBound(walls, R);
        return Math.max(0, end - start);
    }

    private int lowerBound(int[] a, long t) {
        int l = 0, h = a.length;
        while (l < h) {
            int m = l + (h - l) / 2;
            if (a[m] >= t) h = m; else l = m + 1;
        }
        return l;
    }

    private int upperBound(int[] a, long t) {
        int l = 0, h = a.length;
        while (l < h) {
            int m = l + (h - l) / 2;
            if (a[m] <= t) l = m + 1; else h = m;
        }
        return l;
    }
}