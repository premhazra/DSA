class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1 || s.length() <= numRows)
            return s;

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++)
            rows.add(new StringBuilder());

        int currRow = 0;
        int direction = 1; // +1 = down, -1 = up

        for (char ch : s.toCharArray()) {
            rows.get(currRow).append(ch);

            if (currRow == 0) direction = 1;               // go down
            else if (currRow == numRows - 1) direction = -1; // go up

            currRow += direction;
        }

        StringBuilder ans = new StringBuilder();
        for (StringBuilder sb : rows)
            ans.append(sb);

        return ans.toString();
    }
}