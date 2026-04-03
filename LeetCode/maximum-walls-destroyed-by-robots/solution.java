// Problem: Maximum Walls Destroyed by Robots
// Difficulty: Hard
// Source: LeetCode
// Language: Java
// Synced by CodexSync
import java.util.*;

class Solution {
    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        int n = robots.length;
        Arrays.sort(walls);

        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = robots[i];
            arr[i][1] = distance[i];
        }
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);

        // Precompute gains for segments
        int[] gainLeft = new int[n];   // robot i shooting LEFT into segment (i-1, i)
        int[] gainRight = new int[n];  // robot i shooting RIGHT into segment (i, i+1)

        for (int i = 0; i < n; i++) {
            int r = arr[i][0], d = arr[i][1];

            int leftBlock = (i == 0) ? Integer.MIN_VALUE : arr[i - 1][0];
            int rightBlock = (i == n - 1) ? Integer.MAX_VALUE : arr[i + 1][0];

            // LEFT interval (affects segment i)
            int l1 = Math.max(r - d, leftBlock);
            int r1 = r;
            gainLeft[i] = count(walls, l1, r1);

            // RIGHT interval (affects segment i)
            int l2 = r;
            int r2 = Math.min(r + d, rightBlock);
            gainRight[i] = count(walls, l2, r2);
        }

        // DP
        int[][] dp = new int[n][2];

        // Base
        dp[0][0] = gainLeft[0];   // first robot shoots left
        dp[0][1] = gainRight[0];  // first robot shoots right

        for (int i = 1; i < n; i++) {
            // If robot i shoots LEFT → segment i counted from LEFT
            dp[i][0] = Math.max(
                dp[i - 1][0] + gainLeft[i],  // prev LEFT → no conflict
                dp[i - 1][1] + gainLeft[i]   // prev RIGHT → still fine
            );

            // If robot i shoots RIGHT → segment i counted from RIGHT
            // BUT segment (i-1, i) already handled by previous robot
            dp[i][1] = Math.max(
                dp[i - 1][0] + gainRight[i],
                dp[i - 1][1] + gainRight[i]
            );
        }

        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }

    private int count(int[] walls, int l, int r) {
        int left = lowerBound(walls, l);
        int right = upperBound(walls, r);
        return right - left;
    }

    private int lowerBound(int[] arr, int target) {
        int l = 0, h = arr.length;
        while (l < h) {
            int mid = (l + h) / 2;
            if (arr[mid] < target) l = mid + 1;
            else h = mid;
        }
        return l;
    }

    private int upperBound(int[] arr, int target) {
        int l = 0, h = arr.length;
        while (l < h) {
            int mid = (l + h) / 2;
            if (arr[mid] <= target) l = mid + 1;
            else h = mid;
        }
        return l;
    }
}