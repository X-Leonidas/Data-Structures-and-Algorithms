package cn.xy.leetcode.hard.backtrack;

import java.util.*;

/**
 * @author xiangyu
 * @date 2024-03-18 0:44
 * 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * <p>
 * 输入：n = 4
 * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * <p>
 * 输入：n = 1
 * 输出：[["Q"]]
 * <p>
 * 提示：
 * 1 <= n <= 9
 */
public class A0051NQueens {

    public static void main(String[] args) {
        A0051NQueens a0051NQueens = new A0051NQueens();
        List<List<String>> lists = a0051NQueens.solveNQueens(1);
        System.out.println(lists);
    }

    public List<List<String>> solveNQueens(int n) {
        int[][] flag = new int[n][n];
        List<Integer> output = new ArrayList<>();
        List<List<String>> ans = new ArrayList<>();
        backtrack(flag, n, 0, output, ans);
        return ans;
    }

    private void backtrack(int[][] flag, int n, int index, List<Integer> output, List<List<String>> ans) {
        if (n == index) {
            List<String> subAns = new ArrayList<>();
            for (Integer i : output) {
                String s = "";
                for (int j = 0; j < output.size(); j++) {
                    if (j != i) {
                        s = s + ".";
                    } else {
                        s = s + "Q";
                    }
                }
                subAns.add(s);
            }
            ans.add(subAns);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (flag[index][i] == 0) {
                output.add(i);
                fallPieces(flag, i, index, 1);
                backtrack(flag, n, index + 1, output, ans);
                fallPieces(flag, i, index, -1);
                output.remove(output.size() - 1);
            }
        }
    }

    private boolean fallPieces(int[][] flags, int x, int y, int flag) {
        int lengthX = flags[y].length;
        int lengthY = flags.length;

        for (int i = 0; i < lengthY; i++) {
            flags[i][x] = flags[i][x] + flag;
        }
        // 左上角
        int lX = x;
        int lY = y;
        while (lX > 0 && lY > 0) {
            lX--;
            lY--;
        }

        // 右上角
        int rX = x;
        int rY = y;
        while (rX < lengthX - 1 && rY > 0) {
            rX++;
            rY--;
        }

        int tempX = lX;
        int temmY = lY;
        while (temmY < lengthY && tempX < lengthX) {
            flags[temmY][tempX] = flags[temmY][tempX] + flag;
            tempX++;
            temmY++;
        }

        tempX = rX;
        temmY = rY;
        while (temmY < lengthY && tempX >= 0) {
            flags[temmY][tempX] = flags[temmY][tempX] + flag;
            tempX--;
            temmY++;
        }
        return true;
    }


    public List<List<String>> solveNQueens2(int n) {
        List<List<String>> solutions = new ArrayList<>();
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        // 每一列
        Set<Integer> columns = new HashSet<>();
        // 斜着的两个方向
        Set<Integer> diagonals1 = new HashSet<>();
        Set<Integer> diagonals2 = new HashSet<>();
        backtrack2(solutions, queens, n, 0, columns, diagonals1, diagonals2);
        return solutions;
    }

    /**
     * @param solutions  ans
     * @param queens     每一列皇后的放置位置
     * @param n          一共有几列
     * @param row        当前列
     * @param columns    每一列哪里有皇后
     * @param diagonals1 左上到右下哪里有皇后
     * @param diagonals2 右上到左下哪里有皇后
     */
    public void backtrack2(List<List<String>> solutions, int[] queens, int n, int row, Set<Integer> columns, Set<Integer> diagonals1, Set<Integer> diagonals2) {
        if (row == n) {
            List<String> board = generateBoard(queens, n);
            solutions.add(board);
        } else {
            for (int i = 0; i < n; i++) {
                if (columns.contains(i)) {
                    continue;
                }
                // 左上到右下 在同一条斜线上的数 差值都相等
                int diagonal1 = row - i;
                if (diagonals1.contains(diagonal1)) {
                    continue;
                }
                // 右上到左下 在同一条斜线上的数 和都相等
                int diagonal2 = row + i;
                if (diagonals2.contains(diagonal2)) {
                    continue;
                }

                queens[row] = i;
                // 落子
                columns.add(i);
                diagonals1.add(diagonal1);
                diagonals2.add(diagonal2);
                backtrack2(solutions, queens, n, row + 1, columns, diagonals1, diagonals2);
                queens[row] = -1;
                // 移除落子
                columns.remove(i);
                diagonals1.remove(diagonal1);
                diagonals2.remove(diagonal2);
            }
        }
    }

    public List<String> generateBoard(int[] queens, int n) {
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }
}
