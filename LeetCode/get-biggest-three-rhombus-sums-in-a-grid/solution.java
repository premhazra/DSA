// Problem: Get Biggest Three Rhombus Sums in a Grid
// Difficulty: Medium
// Source: LeetCode
// Language: Java
// Synced by CodexSync
import java.util.*;

class Solution {
    public int[] getBiggestThree(int[][] grid) {
        
        int m = grid.length, n = grid[0].length;
        TreeSet<Integer> set = new TreeSet<>(Collections.reverseOrder());
        
        for(int r = 0; r < m; r++){
            for(int c = 0; c < n; c++){
                
                set.add(grid[r][c]); // size 0 rhombus
                
                for(int k = 1; r-k>=0 && r+k<m && c-k>=0 && c+k<n; k++){
                    
                    int sum = 0;
                    
                    for(int i=0;i<k;i++){
                        sum += grid[r-k+i][c+i];     // top -> right
                        sum += grid[r+i][c+k-i];     // right -> bottom
                        sum += grid[r+k-i][c-i];     // bottom -> left
                        sum += grid[r-i][c-k+i];     // left -> top
                    }
                    
                    set.add(sum);
                }
            }
        }
        
        int size = Math.min(3, set.size());
        int[] ans = new int[size];
        
        int i=0;
        for(int val : set){
            if(i==size) break;
            ans[i++] = val;
        }
        
        return ans;
    }
}