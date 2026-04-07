// Problem: Walking Robot Simulation II
// Difficulty: Medium
// Source: LeetCode
// Language: Java
// Synced by CodexSync
class Robot {
    private int w, h, perimeter, pos;
    private int[][] cells;
    private String[] dirs;

    public Robot(int width, int height) {
        w = width;
        h = height;
        perimeter = 2 * (w + h) - 4;
        pos = 0;

        cells = new int[perimeter][2];
        dirs = new String[perimeter];

        int idx = 0;

        for (int x = 0; x < w; x++) { cells[idx] = new int[]{x, 0}; dirs[idx++] = "East"; }
        for (int y = 1; y < h; y++) { cells[idx] = new int[]{w-1, y}; dirs[idx++] = "North"; }
        for (int x = w-2; x >= 0; x--) { cells[idx] = new int[]{x, h-1}; dirs[idx++] = "West"; }
        for (int y = h-2; y >= 1; y--) { cells[idx] = new int[]{0, y}; dirs[idx++] = "South"; }
    }

    public void step(int num) {
        pos = (pos + num) % perimeter;
    }

    public int[] getPos() {
        return cells[pos];
    }

    public String getDir() {
        return dirs[pos];
    }
}