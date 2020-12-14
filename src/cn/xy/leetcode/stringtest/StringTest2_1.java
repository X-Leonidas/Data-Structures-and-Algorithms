package cn.xy.leetcode.stringtest;

/**
 * @author XiangYu
 * @create2020-11-30-15:06 旋转矩阵
 * 给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。
 * <p>
 * 不占用额外内存空间能否做到？
 * <p>
 * 示例 1:
 * <p>
 * 给定 matrix =
 * [
 * [1,2,3],
 * [4,5,6],
 * [7,8,9]
 * ],
 * <p>
 * 原地旋转输入矩阵，使其变为:
 * [
 * [7,4,1],
 * [8,5,2],
 * [9,6,3]
 * ]
 * 示例 2:
 * <p>
 * 给定 matrix =
 * [
 * [ 5, 1, 9,11],
 * [ 2, 4, 8,10],
 * [13, 3, 6, 7],
 * [15,14,12,16]
 * ],
 * <p>
 * 原地旋转输入矩阵，使其变为:
 * [
 * [15,13, 2, 5],
 * [14, 3, 4, 1],
 * [12, 6, 8, 9],
 * [16, 7,10,11]
 * ]
 */
public class StringTest2_1 {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
        };


        int[][] matrix2 = {
                {5, 1, 9,11},
                { 2, 4, 8,10},
                {13, 3, 6, 7},
                {15,14,12,16}
        };
        rotate2(matrix2);


        int[][] tartget = matrix2;

        for (int i = 0; i < tartget.length; i++) {
            for (int j = 0; j < tartget[i].length; j++) {
                System.out.print(tartget[i][j]+"  ");
            }
            System.out.println();
        }
    }


    /**
     * 占用额外内存
     *
     * @param matrix
     */
    public static int[][] rotate(int[][] matrix) {

        if (matrix.length == 0) {
            return matrix;
        }

        int[][] targetMatrix = new int[matrix[0].length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                targetMatrix[j][matrix.length - 1 - i] = matrix[i][j];
            }
        }

        return targetMatrix;
    }




    /**
     * 不占用额外内存
     *执行用时：0 ms在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.2 MB, 在所有 Java 提交中击败了97.49%的用户
     *
     *
     *
     * 通过递归，翻转四个元素。
     *
     * @param matrix
     */
    public static void  rotate2(int[][] matrix) {

        if (matrix.length == 0) {
            return ;
        }
        int temp = 0;
        for (int i = 0; i < matrix.length/2; i++,temp++) {
            for (int j = temp; j < matrix[i].length-temp-1; j++) {
                recursion(matrix,i,j,i,j);
            }
        }



    }



    public static void recursion(int[][] matrix,int row,int column,int starRow,int starColumn){
        int tagRow = column;
        int tagColumn = matrix.length - 1 - row;
        int temp = matrix[row][column];
        if(tagColumn == starColumn && tagRow == starRow){
            matrix[tagRow][tagColumn] = matrix[row][column];
            return;
        }else{
            recursion(matrix,tagRow,tagColumn,starRow,starColumn);
            matrix[tagRow][tagColumn] = temp;
        }
    }

    /**
     * 力扣大神的题解
     *
     * @param matrix
     */
    public void rotate3(int[][] matrix) {
        int n = matrix.length;
        // 先以对角线（左上-右下）为轴进行翻转
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
        // 再对每一行以中点进行翻转
        int mid = n >> 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < mid; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = tmp;
            }
        }
    }




}
