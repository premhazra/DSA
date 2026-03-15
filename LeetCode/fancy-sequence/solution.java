// Problem: Fancy Sequence
// Difficulty: Hard
// Source: LeetCode
// Language: Java
// Synced by CodexSync
import java.util.*;

class Fancy {

    long MOD = 1000000007;
    ArrayList<Long> list;
    long mul;
    long add;

    public Fancy() {
        list = new ArrayList<>();
        mul = 1;
        add = 0;
    }
    
    public void append(int val) {
        
        long inv = modInverse(mul);
        long stored = ((val - add) % MOD + MOD) % MOD;
        stored = (stored * inv) % MOD;
        
        list.add(stored);
    }
    
    public void addAll(int inc) {
        add = (add + inc) % MOD;
    }
    
    public void multAll(int m) {
        mul = (mul * m) % MOD;
        add = (add * m) % MOD;
    }
    
    public int getIndex(int idx) {
        
        if (idx >= list.size())
            return -1;
        
        long val = list.get(idx);
        
        long res = (val * mul) % MOD;
        res = (res + add) % MOD;
        
        return (int) res;
    }
    
    private long modInverse(long x) {
        return modPow(x, MOD - 2);
    }
    
    private long modPow(long base, long exp) {
        long result = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1)
                result = (result * base) % MOD;
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return result;
    }
}