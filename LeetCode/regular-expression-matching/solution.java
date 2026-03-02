// Problem: Regular Expression Matching
// Difficulty: Hard
// Source: LeetCode
// Language: Java
// Synced by CodexSync
class Solution {
    public boolean isMatch(String s, String p) {
      Boolean[][] memo = new Boolean[s.length() + 1][p.length() + 1];
        return dp(0, 0, s, p, memo);
    }

    private boolean dp(int i, int j, String s, String p, Boolean[][] memo) {
        // If we have already computed this subproblem, return the stored result
        if (memo[i][j] != null) {
            return memo[i][j];
        }

        boolean ans;
        // Base case: If we've reached the end of the pattern
        if (j == p.length()) {
            ans = (i == s.length());
        } else {
            // Check if the current characters match
            boolean firstMatch = (i < s.length() && 
                                  (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.'));

            // Scenario 1: The next character is '*'
            if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                // Choice 1: Ignore the p[j]* part (zero matches)
                boolean zeroMatches = dp(i, j + 2, s, p, memo);
                // Choice 2: Use the p[j]* part (one or more matches)
                boolean oneOrMoreMatches = firstMatch && dp(i + 1, j, s, p, memo);
                ans = zeroMatches || oneOrMoreMatches;
            } 
            // Scenario 2: Standard one-to-one match
            else {
                ans = firstMatch && dp(i + 1, j + 1, s, p, memo);
            }
        }

        // Store the result in the memo and return it
        memo[i][j] = ans;
        return ans;  
    }
}