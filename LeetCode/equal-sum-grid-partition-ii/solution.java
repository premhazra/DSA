// Problem: Equal Sum Grid Partition II
// Difficulty: Hard
// Source: LeetCode
// Language: Java
// Synced by CodexSync
class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        long totalSum = 0;
        
        // Max value is 10^5 according to constraints
        int MAX_VAL = 100005; 
        
        for (int[] row : grid) {
            for (int val : row) {
                totalSum += val;
            }
        }
        
        // --- 1. Horizontal Cuts ---
        if (m > 1) {
            int[] bottomFreq = new int[MAX_VAL];
            int[] topFreq = new int[MAX_VAL];
            
            // Initially, all elements belong to the bottom section
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    bottomFreq[grid[i][j]]++;
                }
            }
            
            long topSum = 0;
            for (int i = 0; i < m - 1; i++) {
                // Move row 'i' from the bottom section to the top section
                for (int j = 0; j < n; j++) {
                    int val = grid[i][j];
                    bottomFreq[val]--;
                    topFreq[val]++;
                    topSum += val;
                }
                long bottomSum = totalSum - topSum;
                
                if (topSum == bottomSum) return true;
                
                if (topSum > bottomSum) {
                    long target = topSum - bottomSum;
                    // O(1) check using the frequency array
                    if (target < MAX_VAL && checkValidRemoval(grid, 0, i, 0, n - 1, (int)target, topFreq)) {
                        return true;
                    }
                } else {
                    long target = bottomSum - topSum;
                    if (target < MAX_VAL && checkValidRemoval(grid, i + 1, m - 1, 0, n - 1, (int)target, bottomFreq)) {
                        return true;
                    }
                }
            }
        }
        
        // --- 2. Vertical Cuts ---
        if (n > 1) {
            int[] rightFreq = new int[MAX_VAL];
            int[] leftFreq = new int[MAX_VAL];
            
            // Initially, all elements belong to the right section
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    rightFreq[grid[i][j]]++;
                }
            }
            
            long leftSum = 0;
            for (int j = 0; j < n - 1; j++) {
                // Move col 'j' from the right section to the left section
                for (int i = 0; i < m; i++) {
                    int val = grid[i][j];
                    rightFreq[val]--;
                    leftFreq[val]++;
                    leftSum += val;
                }
                long rightSum = totalSum - leftSum;
                
                if (leftSum == rightSum) return true;
                
                if (leftSum > rightSum) {
                    long target = leftSum - rightSum;
                    if (target < MAX_VAL && checkValidRemoval(grid, 0, m - 1, 0, j, (int)target, leftFreq)) {
                        return true;
                    }
                } else {
                    long target = rightSum - leftSum;
                    if (target < MAX_VAL && checkValidRemoval(grid, 0, m - 1, j + 1, n - 1, (int)target, rightFreq)) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    private boolean checkValidRemoval(int[][] grid, int r1, int r2, int c1, int c2, int target, int[] freq) {
        int rows = r2 - r1 + 1;
        int cols = c2 - c1 + 1;
        
        // 1x1 grids cannot have elements removed (must be non-empty)
        if (rows == 1 && cols == 1) return false;
        
        // 1D Row: Only the outer edges can be removed safely
        if (rows == 1) {
            return grid[r1][c1] == target || grid[r1][c2] == target;
        }
        
        // 1D Column: Only the outer edges can be removed safely
        if (cols == 1) {
            return grid[r1][c1] == target || grid[r2][c1] == target;
        }
        
        // 2D Grid: Any target value present in the section can be removed safely
        return freq[target] > 0;
    }
}