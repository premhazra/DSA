// Problem: Walking Robot Simulation
// Difficulty: Medium
// Source: LeetCode
// Language: Java
// Synced by CodexSync
import java.util.*;

class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        Set<String> set = new HashSet<>();
        for (int[] o : obstacles) {
            set.add(o[0] + "," + o[1]);
        }

        int x = 0, y = 0;
        int d = 0;
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        int max = 0;

        for (int cmd : commands) {
            if (cmd == -1) {
                d = (d + 1) % 4;
            } else if (cmd == -2) {
                d = (d + 3) % 4;
            } else {
                for (int i = 0; i < cmd; i++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];
                    if (set.contains(nx + "," + ny)) break;
                    x = nx;
                    y = ny;
                    max = Math.max(max, x * x + y * y);
                }
            }
        }

        return max;
    }
}