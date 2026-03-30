// Problem: Check if Strings Can be Made Equal With Operations II
// Difficulty: Medium
// Source: LeetCode
// Language: Java
// Synced by CodexSync
import java.util.Arrays;

class Solution {
    public boolean checkStrings(String s1, String s2) {
        int n = s1.length();
        int[][] count1 = new int[2][26];
        int[][] count2 = new int[2][26];
        
        for (int i = 0; i < n; i++) {
            int parity = i % 2;
            count1[parity][s1.charAt(i) - 'a']++;
            count2[parity][s2.charAt(i) - 'a']++;
        }
        if (!Arrays.equals(count1[0], count2[0])) {
            return false;
        }
        if (!Arrays.equals(count1[1], count2[1])) {
            return false;
        }
        return true;
    }
}