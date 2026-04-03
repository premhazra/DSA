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

        int m = walls.length;

        // Precompute intervals for each robot
        int[][] left = new int[n][2];
        int[][] right = new int[n][2];

        for (int i = 0; i < n; i++) {
            int r = arr[i][0], d = arr[i][1];

            int leftBlock = (i == 0) ? Integer.MIN_VALUE : arr[i - 1][0];
            int rightBlock = (i == n - 1) ? Integer.MAX_VALUE : arr[i + 1][0];

            int l1 = Math.max(r - d, leftBlock + 1);
            int r1 = r;

            int l2 = r;
            int r2 = Math.min(r + d, rightBlock - 1);

            left[i] = new int[]{l1, r1};
            right[i] = new int[]{l2, r2};
        }

        // Prefix DP
        int[] dp = new int[n + 1];

        for (int i = 0; i < n; i++) {
            dp[i + 1] = dp[i];

            int leftCount = count(walls, left[i][0], left[i][1]);
            int rightCount = count(walls, right[i][0], right[i][1]);

            dp[i + 1] = Math.max(dp[i + 1], dp[i] + Math.max(leftCount, rightCount));
        }

        return dp[n];
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