// Problem: Maximum Walls Destroyed by Robots
// Difficulty: Hard
// Source: LeetCode
// Language: Java
// Synced by CodexSync
import java.util.*;

class Solution {
    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        int n = robots.length;

        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = robots[i];
            arr[i][1] = distance[i];
        }

        Arrays.sort(arr, (a, b) -> a[0] - b[0]);
        Arrays.sort(walls);

        Set<Integer> destroyed = new HashSet<>();

        for (int i = 0; i < n; i++) {
            int r = arr[i][0];
            int d = arr[i][1];

            int leftBlock = (i == 0) ? Integer.MIN_VALUE : arr[i - 1][0];
            int rightBlock = (i == n - 1) ? Integer.MAX_VALUE : arr[i + 1][0];

            // LEFT RANGE
            int leftStart = Math.max(r - d, leftBlock + 1);
            int leftEnd = r;

            // RIGHT RANGE
            int rightStart = r;
            int rightEnd = Math.min(r + d, rightBlock - 1);

            // Count both
            List<Integer> leftWalls = getWalls(walls, leftStart, leftEnd);
            List<Integer> rightWalls = getWalls(walls, rightStart, rightEnd);

            // Choose better direction (greedy)
            if (leftWalls.size() > rightWalls.size()) {
                destroyed.addAll(leftWalls);
            } else {
                destroyed.addAll(rightWalls);
            }
        }

        return destroyed.size();
    }

    private List<Integer> getWalls(int[] walls, int l, int r) {
        List<Integer> res = new ArrayList<>();
        for (int w : walls) {
            if (w >= l && w <= r) {
                res.add(w);
            }
        }
        return res;
    }
}