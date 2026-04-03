// Problem: Maximum Walls Destroyed by Robots
// Difficulty: Hard
// Source: LeetCode
// Language: Java
// Synced by CodexSync
import java.util.*;

class Solution {
    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        int n = robots.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = robots[i];
            arr[i][1] = distance[i];
        }
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);
        Arrays.sort(walls);
        int result = 0;
        for (int i = 0; i < n; i++) {
            int r = arr[i][0];
            int d = arr[i][1];
            int leftBlock = (i == 0) ? Integer.MIN_VALUE : arr[i - 1][0];
            int rightBlock = (i == n - 1) ? Integer.MAX_VALUE : arr[i + 1][0];
            int leftStart = Math.max(r - d, leftBlock + 1);
            int leftEnd = r;
            int leftCount = countWalls(walls, leftStart, leftEnd);
            int rightStart = r;
            int rightEnd = Math.min(r + d, rightBlock - 1);
            int rightCount = countWalls(walls, rightStart, rightEnd);
            result += Math.max(leftCount, rightCount);
        }
        return result;
    }
    private int countWalls(int[] walls, int left, int right) {
        int l = lowerBound(walls, left);
        int r = upperBound(walls, right);
        return r - l;
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