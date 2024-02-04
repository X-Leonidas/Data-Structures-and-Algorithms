package cn.xy.leetcode.middle.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xiangyu
 * @date 2024-02-02 22:01
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 * <p>
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * <p>
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 */
public class A0054SpiralMatrix {
    public static void main(String[] args) {
        int[][] mat = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(spiralOrder(mat));
    }

    /**
     * 辅助数组
     * @param matrix
     * @return
     */
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();

        boolean[][] flag = new boolean[matrix.length][matrix[0].length];
        int x = 0;
        int y = 0;
        result.add(matrix[0][0]);
        flag[0][0] = true;
        boolean stopFlag = true;
        while (stopFlag) {
            stopFlag = false;
            while (x + 1 < matrix[0].length && !flag[y][x + 1]) {
                result.add(matrix[y][x + 1]);
                flag[y][x + 1] = true;
                x++;
                stopFlag = true;
            }
            while (y + 1 < matrix.length && !flag[y + 1][x]) {
                result.add(matrix[y + 1][x]);
                flag[y + 1][x] = true;
                y++;
                stopFlag = true;
            }
            while (x - 1 > -1 && !flag[y][x - 1]) {
                result.add(matrix[y][x - 1]);
                flag[y][x - 1] = true;
                x--;
                stopFlag = true;
            }
            while (y - 1 > -1 && !flag[y - 1][x]) {
                result.add(matrix[y - 1][x]);
                flag[y - 1][x] = true;
                y--;
                stopFlag = true;
            }
        }

        return result;
    }

    public List<Integer> spiralOrder2(int[][] matrix) {
        if (matrix.length == 0)
            return new ArrayList<Integer>();
        //
        int l = 0;
        int r = matrix[0].length - 1;
        int t = 0;
        int b = matrix.length - 1;
        int x = 0;
        Integer[] res = new Integer[(r + 1) * (b + 1)];
        while (true) {
            // left to right
            for (int i = l; i <= r; i++) {
                res[x++] = matrix[t][i];
            }
            if (++t > b) break;
            // top to bottom
            for (int i = t; i <= b; i++) {
                res[x++] = matrix[i][r];
            }
            if (l > --r) break;
            // right to left
            for (int i = r; i >= l; i--) {
                res[x++] = matrix[b][i];
            }
            if (t > --b) break;
            // bottom to top
            for (int i = b; i >= t; i--) {
                res[x++] = matrix[i][l];
            }
            if (++l > r) break;
        }
        return Arrays.asList(res);
    }
}
