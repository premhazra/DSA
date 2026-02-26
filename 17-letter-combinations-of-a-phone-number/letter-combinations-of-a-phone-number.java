class Solution {
    private static final String[] KEYS = {
        "",    "",    "abc",  "def",
        "ghi", "jkl", "mno",
        "pqrs","tuv", "wxyz"
    };
    
    public List<String> letterCombinations(String digits) {
        
        List<String> result = new ArrayList<>();
        
        if (digits == null || digits.length() == 0)
            return result;
        
        backtrack(result, digits, 0, new StringBuilder());
        return result;
    }
    
    private void backtrack(List<String> result, String digits, int index, StringBuilder current) {
        
        // If full combination is formed
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }
        
        // Get mapped letters for current digit
        String letters = KEYS[digits.charAt(index) - '0'];
        
        // Try each letter
        for (char c : letters.toCharArray()) {
            current.append(c);                // choose
            backtrack(result, digits, index + 1, current);  // explore
            current.deleteCharAt(current.length() - 1);     // un-choose (backtrack)
        }
    }
}