// Problem: Largest Submatrix With Rearrangements
// Difficulty: Medium
// Source: LeetCode
// Language: Java
// Synced by CodexSync
class Solution {
    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int maxArea = 0;
        
        // Step 1: build heights
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    matrix[i][j] += matrix[i - 1][j];
                }
            }
        }
        
        // Step 2: process each row
        for (int i = 0; i < m; i++) {
            int[] row = matrix[i].clone();
            
            // sort descending
            Arrays.sort(row);
            
            for (int j = 0; j < n; j++) {
                int height = row[n - 1 - j]; // largest first
                int width = j + 1;
                
                maxArea = Math.max(maxArea, height * width);
            }
        }
        
        return maxArea;
    }
}