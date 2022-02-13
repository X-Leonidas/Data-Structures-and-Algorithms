package xy.leetcode.practice.stringtest;

/**
 * @author XiangYu
 * @create2020-12-01-15:34 对角线遍历
 * 给定一个含有 M x N 个元素的矩阵（M 行，N 列），请以对角线遍历的顺序返回这个矩阵中的所有元素，对角线遍历如下图所示。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * <p>
 * 输出:  [1,2,4,7,5,3,6,8,9]
 * <p>
 * 解释:
 * <p>
 * 说明:
 * <p>
 * 给定矩阵中的元素总数不会超过 100000 。
 */
public class StringTest2_3 {


    public static void main(String[] args) {
        int[][] matrix = {{6,9,7}};
       // matrix =

//        int[][] matrix = {
//                {1,  2,  3,  0},
//                {4,  5,  6,  0},
//                {7,  8,  9,  0},
//                {10, 11, 12, 0}
//        };
        int[] diagonalOrder = findDiagonalOrder(matrix);
        for (int i : diagonalOrder) {

            System.out.print(i + "    ");
        }
    }

    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了96.98%的用户
     * 内存消耗：40.4 MB, 在所有 Java 提交中击败了79.66%的用户
     * @param matrix
     * @return
     */

    public static int[] findDiagonalOrder(int[][] matrix) {

        if (matrix.length == 0) {
            return new int[0];
        }
        if (matrix.length == 1){
            return matrix[0];
        }

        int sum = matrix.length * matrix[0].length;
        int[] ints = new int[sum];

        if (matrix[0].length == 1) {
            for (int i = 0; i < matrix.length; i++) {
                ints[i] = matrix[i][0];
            }
            return ints;
        }


        //设置边界
        int hight = matrix.length - 1;
        int width = matrix[0].length - 1;

        //起点
        int x = 0;
        int y = 0;
        ints[0] = matrix[y][x];

        boolean upflag = true;


        for (int i = 1; i < sum; i++) {
            //数组到了上底或者下底
            if (y == 0 || y == hight) {
                //碰到后坐标平移一位
                if (x == width) {
                    y++;
                } else {
                    x++;
                }
                //完成拐弯操作
                ints[i] = matrix[y][x];
                //反置标识位
                upflag = !upflag;
                i++;

            } else if (x == 0 || x == width) {
                //碰到后坐标平移一位
                y++;
                //完成拐弯操作
                ints[i] = matrix[y][x];
                upflag = !upflag;
                i++;
            }

            if (x == width && y == hight) {
                break;
            }
            //未到边界，正常操作
            if (upflag) {
                x++;
                y--;
            } else {
                x--;
                y++;
            }
            ints[i] = matrix[y][x];
        }
        return ints;
    }
}
