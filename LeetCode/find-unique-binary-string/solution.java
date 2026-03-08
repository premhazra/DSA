// Problem: Find Unique Binary String
// Difficulty: Medium
// Source: LeetCode
// Language: Java
// Synced by CodexSync
class Solution {
    public String findDifferentBinaryString(String[] nums) {
        int n = nums.length;
        char[] res = new char[n];
        for(int i = 0; i < n; i++){
            res[i] = nums[i].charAt(i) == '0' ? '1' : '0';
        }
        return new String(res);
    }
}