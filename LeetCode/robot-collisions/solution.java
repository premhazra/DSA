// Problem: Robot Collisions
// Difficulty: Hard
// Source: LeetCode
// Language: Java
// Synced by CodexSync
import java.util.*;

class Solution {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        int[][] robots = new int[n][4]; 
        for (int i = 0; i < n; i++) {
            robots[i][0] = positions[i];
            robots[i][1] = healths[i];
            robots[i][2] = directions.charAt(i) == 'R' ? 0 : 1;
            robots[i][3] = i;
        }
        Arrays.sort(robots, (a, b) -> a[0] - b[0]);

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            if (robots[i][2] == 0) {
                stack.push(i);
            } 
            else {
                while (!stack.isEmpty() && robots[i][1] > 0) {
                    int top = stack.peek();
                    if (robots[top][2] == 1) break;

                    if (robots[top][1] > robots[i][1]) {
                        robots[top][1]--; 
                        robots[i][1] = 0; 
                    } 
                    else if (robots[top][1] < robots[i][1]) {
                        robots[i][1]--;
                        robots[top][1] = 0;
                        stack.pop();
                    } 
                    else {
                        robots[top][1] = 0;
                        robots[i][1] = 0;
                        stack.pop();
                        break;
                    }
                }
            }
        }
        int[] result = new int[n];
        Arrays.fill(result, -1);

        for (int[] robot : robots) {
            if (robot[1] > 0) {
                result[robot[3]] = robot[1];
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int val : result) {
            if (val != -1) ans.add(val);
        }

        return ans;
    }
}