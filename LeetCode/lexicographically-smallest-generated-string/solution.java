// Problem: Lexicographically Smallest Generated String
// Difficulty: Hard
// Source: LeetCode
// Language: Java
// Synced by CodexSync
class Solution {
    public String generateString(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int len = n + m - 1;
        
        char[] word = new char[len];
        for(int i = 0; i < len; i++) word[i] = '?';
        
        for(int i = 0; i < n; i++) {
            if(str1.charAt(i) == 'T') {
                for(int j = 0; j < m; j++) {
                    if(word[i + j] == '?' || word[i + j] == str2.charAt(j)) {
                        word[i + j] = str2.charAt(j);
                    } else {
                        return "";
                    }
                }
            }
        }
        
        for(int i = 0; i < len; i++) {
            if(word[i] == '?') word[i] = 'a';
        }
        
        for(int i = 0; i < n; i++) {
            if(str1.charAt(i) == 'F') {
                boolean match = true;
                for(int j = 0; j < m; j++) {
                    if(word[i + j] != str2.charAt(j)) {
                        match = false;
                        break;
                    }
                }
                
                if(match) {
                    boolean fixed = false;
                    for(int j = m - 1; j >= 0 && !fixed; j--) {
                        for(char c = 'a'; c <= 'z'; c++) {
                            if(c != str2.charAt(j)) {
                                char old = word[i + j];
                                word[i + j] = c;
                                
                                boolean ok = true;
                                for(int k = 0; k < n; k++) {
                                    if(str1.charAt(k) == 'T') {
                                        for(int x = 0; x < m; x++) {
                                            if(word[k + x] != str2.charAt(x)) {
                                                ok = false;
                                                break;
                                            }
                                        }
                                        if(!ok) break;
                                    }
                                }
                                
                                if(ok) {
                                    fixed = true;
                                    break;
                                }
                                
                                word[i + j] = old;
                            }
                        }
                    }
                    if(!fixed) return "";
                }
            }
        }
        
        return new String(word);
    }
}