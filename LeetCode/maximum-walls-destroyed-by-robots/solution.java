// Problem: Maximum Walls Destroyed by Robots
// Difficulty: Hard
// Source: LeetCode
// Language: Java
// Synced by CodexSync
import java.util.Arrays;

class Solution {
    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        int n = robots.length;
        int m = walls.length;

        // Pair robots with their distances to keep them linked after sorting
        int[][] robotPairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            robotPairs[i][0] = robots[i];
            robotPairs[i][1] = distance[i];
        }

        // Sort both robots and walls for binary search efficiency
        Arrays.sort(robotPairs, (a, b) -> Integer.compare(a[0], b[0]));
        Arrays.sort(walls);

        int totalDestroyed = 0;

        for (int i = 0; i < n; i++) {
            int pos = robotPairs[i][0];
            int dist = robotPairs[i][1];

            // 1. Calculate walls destroyed firing LEFT
            // Boundary is the robot to the left (exclusive)
            int leftLimit = (i == 0) ? Integer.MIN_VALUE : robotPairs[i - 1][0] + 1;
            int leftReach = Math.max(leftLimit, pos - dist);
            int countLeft = countWallsInRange(walls, leftReach, pos);

            // 2. Calculate walls destroyed firing RIGHT
            // Boundary is the robot to the right (exclusive)
            int rightLimit = (i == n - 1) ? Integer.MAX_VALUE : robotPairs[i + 1][0] - 1;
            int rightReach = Math.min(rightLimit, pos + dist);
            int countRight = countWallsInRange(walls, pos, rightReach);

            // Add the better option to total
            totalDestroyed += Math.max(countLeft, countRight);
        }

        return totalDestroyed;
    }

    // Helper to find number of walls in [L, R] using binary search
    private int countWallsInRange(int[] walls, int L, int R) {
        if (L > R) return 0;
        
        // Find first element >= L
        int start = Arrays.binarySearch(walls, L);
        if (start < 0) start = -(start + 1);

        // Find last element <= R
        int end = Arrays.binarySearch(walls, R);
        if (end < 0) end = -(end + 1) - 1;

        if (start <= end) {
            return end - start + 1;
        }
        return 0;
    }
}