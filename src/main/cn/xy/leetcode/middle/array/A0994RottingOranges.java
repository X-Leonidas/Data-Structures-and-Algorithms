package cn.xy.leetcode.middle.array;

import java.util.HashMap;

/**
 * @author xiangyu
 * @date 2024-02-25 22:09
 * 在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一：
 * <p>
 * 值 0 代表空单元格；
 * 值 1 代表新鲜橘子；
 * 值 2 代表腐烂的橘子。
 * 每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。
 * <p>
 * 返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 10
 * grid[i][j] 仅为 0、1 或 2
 */
public class A0994RottingOranges {
    public int orangesRotting(int[][] grid) {
        int result = 0;
        // 新鲜的橘子的总数
        int fresh = 0;
        for (int[] ints : grid) {
            for (int orangeStatus : ints) {
                if (orangeStatus == 1) {
                    fresh++;
                }
            }
        }
        if (fresh == 0) {
            return 0;
        }
        // 本次腐烂的橘子的个数
        int curRottingSum = -1;
        while (curRottingSum != 0) {
            curRottingSum = 0;
            // 保证本次腐烂的橘子不会传染
            HashMap<Integer, Integer> curRotting = new HashMap<>();
            for (int y = 0; y < grid.length; y++) {
                for (int x = 0; x < grid[y].length; x++) {
                    if (grid[y][x] == 2) {
                        if ((!curRotting.containsKey(x)) || curRotting.get(x) != y) {
                            curRottingSum = curRottingSum + rotting(grid, x, y, curRotting);
                        }
                    }
                }
            }
            fresh = fresh - curRottingSum;
            if (curRottingSum != 0) {
                result++;
            }
        }

        if (fresh == 0) {
            return result;
        } else {
            return -1;
        }
    }

    public int rotting(int[][] grid, int x, int y, HashMap<Integer, Integer> curRotting) {
        int result = 0;
        // 右边
        if (x - 1 >= 0 && grid[y][x - 1] == 1) {
            grid[y][x - 1] = 2;
            curRotting.put(x - 1, y);
            result++;
        }
        // 左边
        if (x + 1 < grid[y].length && grid[y][x + 1] == 1) {
            grid[y][x + 1] = 2;
            curRotting.put(x + 1, y);
            result++;
        }
        // 上边
        if (y - 1 >= 0 && grid[y - 1][x] == 1) {
            grid[y - 1][x] = 2;
            curRotting.put(x, y - 1);
            result++;
        }

        if (y + 1 < grid.length && grid[y + 1][x] == 1) {
            grid[y + 1][x] = 2;
            curRotting.put(x, y + 1);
            result++;
        }
        return result;
    }
}
