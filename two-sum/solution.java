// Problem: Two Sum
// Difficulty: Easy
// Source: LeetCode
// Language: Java
// Synced by CodexSync
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int arr[] = new int[2];
        for (int i = 1; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (nums[j] + nums[j - i] == target) {
                    arr[0] = j - i;
                    arr[1] = j;
                    return arr;
                }
            }
        }
        return arr;
    }
}