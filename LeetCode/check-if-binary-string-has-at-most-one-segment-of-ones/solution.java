// Problem: Check if Binary String Has at Most One Segment of Ones
// Difficulty: Easy
// Source: LeetCode
// Language: Java
// Synced by CodexSync
class Solution {
    public boolean checkOnesSegment(String s) {
        return !s.contains("01");
    }
}