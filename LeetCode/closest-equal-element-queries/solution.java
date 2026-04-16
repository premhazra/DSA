// Problem: Closest Equal Element Queries
// Difficulty: Medium
// Source: LeetCode
// Language: Java
// Synced by CodexSync
import java.util.*;

class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        
        // Step 1: store indices
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        
        List<Integer> result = new ArrayList<>();
        
        // Step 2: process queries
        for (int q : queries) {
            int val = nums[q];
            List<Integer> list = map.get(val);
            
            // only one occurrence
            if (list.size() == 1) {
                result.add(-1);
                continue;
            }
            
            int pos = Collections.binarySearch(list, q);
            
            int left = (pos - 1 + list.size()) % list.size();
            int right = (pos + 1) % list.size();
            
            int leftIdx = list.get(left);
            int rightIdx = list.get(right);
            
            int dist1 = Math.abs(q - leftIdx);
            dist1 = Math.min(dist1, n - dist1);
            
            int dist2 = Math.abs(q - rightIdx);
            dist2 = Math.min(dist2, n - dist2);
            
            result.add(Math.min(dist1, dist2));
        }
        
        return result;
    }
}