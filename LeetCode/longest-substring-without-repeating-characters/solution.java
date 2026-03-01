// Problem: Longest Substring Without Repeating Characters
// Difficulty: Medium
// Source: LeetCode
// Language: Java
// Synced by CodexSync
class Solution {
    public int lengthOfLongestSubstring(String s) {
      int n = s.length();
        int maxLen = 0;
        int left = 0; // window start pointer
        int[] index = new int[128]; // store last seen position of each ASCII char

        for (int right = 0; right < n; right++) {
            char ch = s.charAt(right);
            // move left pointer if char already seen
            left = Math.max(index[ch], left);
            maxLen = Math.max(maxLen, right - left + 1);
            index[ch] = right + 1; // store next position (right+1)
        }
        return maxLen;  
    }
}