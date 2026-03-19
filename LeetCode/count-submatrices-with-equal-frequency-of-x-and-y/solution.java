// Problem: Count Submatrices With Equal Frequency of X and Y
// Difficulty: Medium
// Source: LeetCode
// Language: Java
// Synced by CodexSync
class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] prefX = new int[rows + 1][cols + 1];
        int[][] prefY = new int[rows + 1][cols + 1];
        int count = 0;

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                prefX[i][j] = prefX[i - 1][j] + prefX[i][j - 1] - prefX[i - 1][j - 1] + (grid[i - 1][j - 1] == 'X' ? 1 : 0);
                prefY[i][j] = prefY[i - 1][j] + prefY[i][j - 1] - prefY[i - 1][j - 1] + (grid[i - 1][j - 1] == 'Y' ? 1 : 0);

                if (prefX[i][j] > 0 && prefX[i][j] == prefY[i][j]) {
                    count++;
                }
            }
        }

        return count;
    }
}