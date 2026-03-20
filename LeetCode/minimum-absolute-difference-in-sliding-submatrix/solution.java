// Problem: Minimum Absolute Difference in Sliding Submatrix
// Difficulty: Medium
// Source: LeetCode
// Language: Java
// Synced by CodexSync
import java.util.*;

class Solution {
    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int resM = m - k + 1;
        int resN = n - k + 1;
        int[][] ans = new int[resM][resN];

        for (int i = 0; i < resM; i++) {
            for (int j = 0; j < resN; j++) {
                ans[i][j] = getMinDiff(grid, i, j, k);
            }
        }

        return ans;
    }

    private int getMinDiff(int[][] grid, int startR, int startC, int k) {
        List<Integer> elements = new ArrayList<>();
        Set<Integer> seen = new HashSet<>();

        for (int i = startR; i < startR + k; i++) {
            for (int j = startC; j < startC + k; j++) {
                if (seen.add(grid[i][j])) {
                    elements.add(grid[i][j]);
                }
            }
        }

        if (elements.size() <= 1) {
            return 0;
        }

        Collections.sort(elements);

        int minDiff = Integer.MAX_VALUE;
        for (int i = 1; i < elements.size(); i++) {
            minDiff = Math.min(minDiff, Math.abs(elements.get(i) - elements.get(i - 1)));
            if (minDiff == 0) return 0; 
        }

        return minDiff;
    }
}