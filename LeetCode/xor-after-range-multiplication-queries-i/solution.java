// Problem: XOR After Range Multiplication Queries I
// Difficulty: Medium
// Source: LeetCode
// Language: Java
// Synced by CodexSync
class Solution {
    public int xorAfterQueries(int[] nums, int[][] queries) {
        int MOD = 1000000007;
        
        for (int[] query : queries) {
            int left = query[0];
            int right = query[1];
            int step = query[2];
            int multiplier = query[3];
            
            for (int idx = left; idx <= right; idx += step) {
                nums[idx] = (int) ((long) nums[idx] * multiplier % MOD);
            }
        }
        
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        
        return result;
    }
}

class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        
        int[] nums1 = {1, 1, 1};
        int[][] queries1 = {{0, 2, 1, 4}};
        System.out.println("Test 1: " + sol.xorAfterQueries(nums1, queries1));
        
        int[] nums2 = {2, 3, 1, 5, 4};
        int[][] queries2 = {{1, 4, 2, 3}, {0, 2, 1, 2}};
        System.out.println("Test 2: " + sol.xorAfterQueries(nums2, queries2));
        
        int[] nums3 = {5};
        int[][] queries3 = {{0, 0, 1, 3}};
        System.out.println("Test 3: " + sol.xorAfterQueries(nums3, queries3));
        
        int[] nums4 = {1, 2, 3, 4};
        int[][] queries4 = {{0, 3, 1, 2}, {1, 2, 2, 3}};
        System.out.println("Test 4: " + sol.xorAfterQueries(nums4, queries4));
        
        int[] nums5 = {10, 20, 30};
        int[][] queries5 = {{0, 2, 1, 5}, {0, 1, 2, 10}};
        System.out.println("Test 5: " + sol.xorAfterQueries(nums5, queries5));
    }
}