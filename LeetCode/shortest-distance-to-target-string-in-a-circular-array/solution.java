// Problem: Shortest Distance to Target String in a Circular Array
// Difficulty: Easy
// Source: LeetCode
// Language: Java
// Synced by CodexSync
class Solution {
    public int closestTarget(String[] words, String target, int startIndex) {
        int n = words.length;
        int minDist = Integer.MAX_VALUE;
        
        for (int i = 0; i < n; i++) {
            if (words[i].equals(target)) {
                int diff = Math.abs(i - startIndex);
                int dist = Math.min(diff, n - diff);
                minDist = Math.min(minDist, dist);
            }
        }
        
        return minDist == Integer.MAX_VALUE ? -1 : minDist;
    }
}