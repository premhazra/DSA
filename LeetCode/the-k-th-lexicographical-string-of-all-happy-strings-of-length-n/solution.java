// Problem: The k-th Lexicographical String of All Happy Strings of Length n
// Difficulty: Medium
// Source: LeetCode
// Language: Java
// Synced by CodexSync
class Solution {

    int count = 0;
    String answer = "";

    public String getHappyString(int n, int k) {

        backtrack(n, k, "");

        return answer;
    }

    void backtrack(int n, int k, String current) {

        if(current.length() == n) {
            count++;

            if(count == k)
                answer = current;

            return;
        }

        for(char ch : new char[]{'a','b','c'}) {

            if(current.length() > 0 && current.charAt(current.length()-1) == ch)
                continue;

            backtrack(n, k, current + ch);

            if(!answer.equals(""))
                return;
        }
    }
}