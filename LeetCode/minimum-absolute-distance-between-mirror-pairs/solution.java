// Problem: Minimum Absolute Distance Between Mirror Pairs
// Difficulty: Medium
// Source: LeetCode
// Language: Java
// Synced by CodexSync
class Solution {
    public int minMirrorPairDistance(int[] nums) {
        Map<Integer, Integer> lastRevIndex = new HashMap<>();
        int ans = Integer.MAX_VALUE;
        for (int j = 0; j < nums.length; j++) {
            if (lastRevIndex.containsKey(nums[j])) {
                ans = Math.min(ans, j - lastRevIndex.get(nums[j]));
            }
            int rev = reverse(nums[j]);
            lastRevIndex.put(rev, j);
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
    
    private int reverse(int x) {
        int r = 0;
        while (x > 0) {
            r = r * 10 + x % 10;
            x /= 10;
        }
        return r;
    }
}