// Problem: Equal Sum Grid Partition II
// Difficulty: Hard
// Source: LeetCode
// Language: Java
// Synced by CodexSync
import java.util.*;

class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        long totalSum = 0;
        for (int[] row : grid) {
            for (int val : row) totalSum += val;
        }
        long topSum = 0;
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n; j++) topSum += grid[i][j];
            long bottomSum = totalSum - topSum;
            if (check(grid, 0, i, 0, n - 1, topSum, bottomSum)) return true;
            if (check(grid, i + 1, m - 1, 0, n - 1, bottomSum, topSum)) return true;
        }
        long leftSum = 0;
        long[] colSums = new long[n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) colSums[j] += grid[i][j];
        }
        for (int j = 0; j < n - 1; j++) {
            leftSum += colSums[j];
            long rightSum = totalSum - leftSum;
            if (check(grid, 0, m - 1, 0, j, leftSum, rightSum)) return true;
            if (check(grid, 0, m - 1, j + 1, n - 1, rightSum, leftSum)) return true;
        }
        return false;
    }
    private boolean check(int[][] grid, int r1, int r2, int c1, int c2, long selfSum, long otherSum) {
        if (selfSum == otherSum) return true;
        long target = selfSum - otherSum;
        if (target <= 0) return false;
        int rows = r2 - r1 + 1;
        int cols = c2 - c1 + 1;
        if (rows == 1 && cols == 1) return false; 
        if (rows == 1) {
            return grid[r1][c1] == target || grid[r1][c2] == target;
        } else if (cols == 1) {
            return grid[r1][c1] == target || grid[r2][c1] == target;
        } else {
            for (int i = r1; i <= r2; i++) {
                for (int j = c1; j <= c2; j++) {
                    if (grid[i][j] == target) return true;
                }
            }
        }
        return false;
    }
}