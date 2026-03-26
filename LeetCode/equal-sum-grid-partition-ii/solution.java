// Problem: Equal Sum Grid Partition II
// Difficulty: Hard
// Source: LeetCode
// Language: Java
// Synced by CodexSync
import java.util.*;

class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        long total = 0;
        for (int[] row : grid) {
            for (int val : row) total += val;
        }

        // Try horizontal cuts
        long topSum = 0;
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n; j++) {
                topSum += grid[i][j];
            }

            long bottomSum = total - topSum;

            if (check(grid, 0, i, 0, n - 1, i + 1, m - 1, 0, n - 1, topSum, bottomSum))
                return true;
        }

        // Try vertical cuts
        long leftSum = 0;
        for (int j = 0; j < n - 1; j++) {
            for (int i = 0; i < m; i++) {
                leftSum += grid[i][j];
            }

            long rightSum = total - leftSum;

            if (check(grid, 0, m - 1, 0, j, 0, m - 1, j + 1, n - 1, leftSum, rightSum))
                return true;
        }

        return false;
    }

    private boolean check(int[][] grid,
                          int r1s, int r1e, int c1s, int c1e,
                          int r2s, int r2e, int c2s, int c2e,
                          long sum1, long sum2) {

        if (sum1 == sum2) return true;

        if (sum1 > sum2) {
            long diff = sum1 - sum2;
            return canRemove(grid, r1s, r1e, c1s, c1e, diff);
        } else {
            long diff = sum2 - sum1;
            return canRemove(grid, r2s, r2e, c2s, c2e, diff);
        }
    }

    private boolean canRemove(int[][] grid,
                              int rs, int re, int cs, int ce,
                              long target) {

        int count = 0;
        int tr = -1, tc = -1;

        for (int i = rs; i <= re; i++) {
            for (int j = cs; j <= ce; j++) {
                if (grid[i][j] == target) {
                    count++;
                    tr = i;
                    tc = j;
                }
            }
        }

        if (count == 0) return false;

        // If more than one such cell exists → safe (connectivity preserved)
        if (count > 1) return true;

        // If only one cell, ensure removing it doesn't break connectivity
        return isSafeToRemove(rs, re, cs, ce, tr, tc);
    }

    private boolean isSafeToRemove(int rs, int re, int cs, int ce, int r, int c) {
        int rows = re - rs + 1;
        int cols = ce - cs + 1;

        // If region is 1x1 → removing breaks
        if (rows == 1 && cols == 1) return false;

        // If single row → removing middle breaks connectivity
        if (rows == 1) {
            return (c == cs || c == ce);
        }

        // If single column → same logic
        if (cols == 1) {
            return (r == rs || r == re);
        }

        // Otherwise safe
        return true;
    }
}