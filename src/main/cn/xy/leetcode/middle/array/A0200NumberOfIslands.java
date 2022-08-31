package cn.xy.leetcode.middle.array;

/**
 * @author xiangyu
 * @date 2022-08-31 22:54
 * <p>
 * 给你一个由'1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * <p>
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * <p>
 * 此外，你可以假设该网格的四条边均被水包围。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：grid = [
 * ['1','1','1','1','0'],
 * ['1','1','0','1','0'],
 * ['1','1','0','0','0'],
 * ['0','0','0','0','0']
 * ]
 * 输出：1
 * 示例 2：
 * <p>
 * 输入：grid = [
 * ['1','1','0','0','0'],
 * ['1','1','0','0','0'],
 * ['0','0','1','0','0'],
 * ['0','0','0','1','1']
 * ]
 * 输出：3
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] 的值为 '0' 或 '1'
 */
public class A0200NumberOfIslands {
    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        System.out.println(numIslands2(grid));
    }


    public  int numIslands(char[][] grid) {
        int n = grid[0].length;
        int m = grid.length;

        boolean[][] flags = new boolean[m][n];
        int result = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && !flags[i][j]) {
                    // diffuse
                    flags[i][j] = true;
                    result++;
                    diffuse(grid, flags, i, j, m, n);
                }
            }
        }


        return result;
    }

    public  void diffuse(char[][] grid, boolean[][] flags, int i, int j, int maxI, int maxJ) {
        // return condition
        if (i > 0 && grid[i - 1][j] == '1' && !flags[i - 1][j]) {
            flags[i - 1][j] = true;
            diffuse(grid, flags, i - 1, j, maxI, maxJ);
        }
        if (i < maxI - 1 && grid[i + 1][j] == '1' && !flags[i + 1][j]) {
            flags[i + 1][j] = true;
            diffuse(grid, flags, i + 1, j, maxI, maxJ);
        }
        if (j > 0 && grid[i][j - 1] == '1' && !flags[i][j - 1]) {
            flags[i][j - 1] = true;
            diffuse(grid, flags, i, j - 1, maxI, maxJ);
        }
        if (j < maxJ - 1 && grid[i][j + 1] == '1' && !flags[i][j + 1]) {
            flags[i][j + 1] = true;
            diffuse(grid, flags, i, j + 1, maxI, maxJ);
        }
    }



    public static int numIslands2(char[][] grid) {
        int n = grid[0].length;
        int m = grid.length;
        int result = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    // diffuse
                    grid[i][j] = '0';
                    result++;
                    diffuse2(grid, i, j, m, n);
                }
            }
        }
        return result;
    }

    public static void diffuse2(char[][] grid, int i, int j, int maxI, int maxJ) {
        // return condition
        if (i > 0 && grid[i - 1][j] == '1') {
            grid[i - 1][j] = '0';
            diffuse2(grid, i - 1, j, maxI, maxJ);
        }
        if (i < maxI - 1 && grid[i + 1][j] == '1' ) {
            grid[i + 1][j] = '0';
            diffuse2(grid, i + 1, j, maxI, maxJ);
        }
        if (j > 0 && grid[i][j - 1] == '1' ) {
            grid[i][j - 1] = '0';
            diffuse2(grid, i, j - 1, maxI, maxJ);
        }
        if (j < maxJ - 1 && grid[i][j + 1] == '1') {
            grid[i][j + 1] = '0';
            diffuse2(grid, i, j + 1, maxI, maxJ);
        }
    }
}
