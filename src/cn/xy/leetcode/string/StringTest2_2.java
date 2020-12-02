package cn.xy.leetcode.string;

import java.util.HashSet;

/**
 * @author XiangYu
 * @create2020-12-01-0:11 编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：
 * [
 * [1,1,1],
 * [1,0,1],
 * [1,1,1]
 * ]
 * 输出：
 * [
 * [1,0,1],
 * [0,0,0],
 * [1,0,1]
 * ]
 * 示例 2：
 * <p>
 * 输入：
 * [
 * [0,1,2,0],
 * [3,4,5,2],
 * [1,3,1,5]
 * ]
 * 输出：
 * [
 * [0,0,0,0],
 * [0,4,5,0],
 * [0,3,1,0]
 * ]
 */
public class StringTest2_2 {


    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了44.26%的用户
     * 内存消耗：39.8 MB, 在所有 Java 提交中击败了91.75%的用户
     *
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        HashSet<Integer> rowZero = new HashSet<>();
        HashSet<Integer> columnZero = new HashSet<>();


        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    rowZero.add(i);
                    columnZero.add(j);
                }
            }
        }

        for (Integer row : rowZero) {
            for (int i = 0; i < matrix[row].length; i++) {
                matrix[row][i] = 0;
            }
        }


        for (Integer column : columnZero) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][column] = 0;
            }
        }


    }


    /**
     * 除了第一行需要保存列号信息之外，其他行遍历完之后可以直接清零，
     * 全部矩阵遍历完之后，再根据第一行保存的列号清零列，最后再清零第一行。
     *
     * @param matrix
     */
    public void setZeroes2(int[][] matrix) {
        int h = matrix.length, w = matrix[0].length;
        boolean delRow = false, del1stRow = false;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (matrix[i][j] == 0) {
                    // 标记第一行是否清零
                    if (i == 0) {
                        del1stRow = true;
                    } else {
                        // 标记除第一行外的当前行是否清零
                        delRow = true;
                    }
                    // 用第一行保存所需清零的列的下标
                    matrix[0][j] = 0;
                }
            }
            // 遍历完此行之后判断是否清零
            if (delRow && i != 0) {
                matrix[i] = new int[w];
                // 清零标记重置
                delRow = false;
            }
        }
        // 根据第一行保存的列下标清零
        for (int i = 1; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }

            }

        }

        // 最后清零第一行
        if (del1stRow){
            matrix[0] = new int[w];
        }
    }

}
