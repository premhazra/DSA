// Problem: Complement of Base 10 Integer
// Difficulty: Easy
// Source: LeetCode
// Language: Java
// Synced by CodexSync
class Solution {
    public int bitwiseComplement(int n) {

        if(n == 0)
            return 1;

        int mask = 0;
        int temp = n;

        while(temp > 0){
            mask = (mask << 1) | 1;
            temp >>= 1;
        }

        return mask ^ n;
    }
}