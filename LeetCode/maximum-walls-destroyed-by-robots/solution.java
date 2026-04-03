// Problem: Maximum Walls Destroyed by Robots
// Difficulty: Hard
// Source: LeetCode
// Language: Java
// Synced by CodexSync
import java.util.*;

class Solution {
    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        int n = robots.length;
        int[][] robotPairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            robotPairs[i][0] = robots[i];
            robotPairs[i][1] = distance[i];
        }

        // Sort robots by position
        Arrays.sort(robotPairs, (a, b) -> Integer.compare(a[0], b[0]));
        // Sort walls to use binary search for counting
        Arrays.sort(walls);

        // dp[i] = max walls destroyed by first i robots
        int[] dp = new int[n + 1];

        for (int i = 0; i < n; i++) {
            int pos = robotPairs[i][0];
            int dist = robotPairs[i][1];

            // Option 1: Robot i fires LEFT
            // Bullet stops at robot i-1
            long leftLimit = (i == 0) ? Long.MIN_VALUE : robotPairs[i-1][0];
            long leftReach = Math.max(leftLimit, (long)pos - dist);
            // Note: If wall is AT leftLimit (another robot), it's NOT destroyed 
            // because the bullet stops AT the robot. 
            // Actually, the problem says "stops at that robot". 
            // If robot is at 5 and wall is at 5, wall is destroyed. 
            // BUT, if bullet comes from the right and hits robot at 5, 
            // it stops. It includes the position of the robot it hits.
            int wallsLeft = countWalls(walls, leftReach, (long)pos);

            // Option 2: Robot i fires RIGHT
            // Bullet stops at robot i+1
            long rightLimit = (i == n - 1) ? Long.MAX_VALUE : robotPairs[i+1][0];
            long rightReach = Math.min(rightLimit, (long)pos + dist);
            int wallsRight = countWalls(walls, (long)pos, rightReach);

            dp[i+1] = dp[i] + Math.max(wallsLeft, wallsRight);
        }

        return dp[n];
    }

    private int countWalls(int[] walls, long L, long R) {
        if (L > R) return 0;
        
        // Find first index where walls[idx] >= L
        int start = lowerBound(walls, L);
        // Find first index where walls[idx] > R
        int end = upperBound(walls, R);
        
        return Math.max(0, end - start);
    }

    private int lowerBound(int[] arr, long target) {
        int low = 0, high = arr.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] >= target) high = mid;
            else low = mid + 1;
        }
        return low;
    }

    private int upperBound(int[] arr, long target) {
        int low = 0, high = arr.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] <= target) low = mid + 1;
            else high = mid;
        }
        return low;
    }
}