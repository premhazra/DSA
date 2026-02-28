class Solution {
    public int minOperations(String s, int k) {
        int n = s.length();
        int c = 0;
        
        // Count the number of '0's in the string
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                c++;
            }
        }
        
        // Already all '1's
        if (c == 0) return 0;
        
        // If k == n, we can only flip the entire string at once.
        if (k == n) {
            return c == n ? 1 : -1;
        }
        
        // Impossible to flip an odd number of '0's if k is even
        if (k % 2 == 0 && c % 2 != 0) {
            return -1;
        }
        
        // Finding the minimum operations (m)
        for (int m = c / k; m <= n + 5; m++) {
            long totalFlips = (long) m * k;
            
            // Checking capacity bounds and parity
            if (totalFlips >= c && totalFlips % 2 == c % 2) {
                // If m is odd
                if (m % 2 == 1 && (long) m * (n - k) >= n - c) {
                    return m;
                }
                // If m is even
                if (m % 2 == 0 && (long) m * (n - k) >= c) {
                    return m;
                }
            }
        }
        
        return -1;
    }
}