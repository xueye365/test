package src.leetcode.medium;

/**
 * https://leetcode-cn.com/explore/interview/card/bytedance/243/array-and-sorting/1034/
 * https://leetcode-cn.com/problems/max-area-of-island/
 *
 * 岛屿的最大面积
 */
public class MaxAreaOfIsland {


    public static void main(String[] args) {
//        int[][] list = new int[8][13];
//        list[0] = new int[]{0,0,1,0,0,0,0,1,0,0,0,0,0};
//        list[1] = new int[]{0,0,0,0,0,0,0,1,1,1,0,0,0};
//        list[2] = new int[]{0,1,1,0,1,0,0,0,0,0,0,0,0};
//        list[3] = new int[]{0,1,0,0,1,1,0,0,1,0,1,0,0};
//        list[4] = new int[]{0,1,0,0,1,1,0,0,1,1,1,0,0};
//        list[5] = new int[]{0,0,0,0,0,0,0,0,0,0,1,0,0};
//        list[6] = new int[]{0,0,0,0,0,0,0,1,1,1,0,0,0};
//        list[7] = new int[]{0,0,0,0,0,0,0,1,1,0,0,0,0};


        int[][] list = new int[4][5];
        list[0] = new int[]{1,1,0,1,1};
        list[1] = new int[]{1,0,0,0,0};
        list[2] = new int[]{0,0,0,0,1};
        list[3] = new int[]{1,1,0,1,1};

        maxAreaOfIsland(list);
        System.out.println(max);
    }

    /**
     * 回溯算法
     */
    static int max = 0;
    public static int maxAreaOfIsland(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < visited.length; i++) {
            for (int i1 = 0; i1 < visited[i].length; i1++) {
                visited[i][i1] = false;
            }
        }

        for (int i = 0; i < grid.length; i++) {
            for (int i1 = 0; i1 < grid[i].length; i1++) {
                if (visited[i][i1]) {
                    continue;
                }
                if (grid[i][i1] == 1) {
                    check(grid, i, i1, visited, 0);
                }
            }
        }
        return max;
    }

    public static int check(int[][] grid, int i, int i1, boolean[][] visited, int sum) {
        visited[i][i1] = true;
        sum = sum + 1;
        if (sum > max) {
            max = sum;
        }
        int lx = i, ly = i1 - 1;
        int ux = i - 1, uy = i1;
        int rx = i, ry = i1 + 1;
        int dx = i + 1, dy = i1;
        if (lx >= 0 && lx < grid.length && ly >= 0 && ly < grid[lx].length && !visited[lx][ly] && grid[lx][ly] == 1) {
            sum = check(grid, lx, ly, visited, sum);
        }
        if (ux >= 0 && ux < grid.length && uy >= 0 && uy < grid[ux].length && !visited[ux][uy] && grid[ux][uy] == 1) {
            sum = check(grid, ux, uy, visited, sum);
        }
        if (rx >= 0 && rx < grid.length && ry >= 0 && ry < grid[rx].length && !visited[rx][ry] && grid[rx][ry] == 1) {
            sum = check(grid, rx, ry, visited, sum);
        }
        if (dx >= 0 && dx < grid.length && dy >= 0 && dy < grid[dx].length && !visited[dx][dy] && grid[dx][dy] == 1) {
            sum = check(grid, dx, dy, visited, sum);
        }
        return sum;
    }


    /**
     * 不能使用动态规划，因为要遍历一个点的四个方向，不是只从左边和上边过来
     * @param grid
     * @return
     */


}
