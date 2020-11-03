package cn.xy.leetcode;

/**
 * @author XiangYu
 * @create2020-11-03-10:06 给定一个整数数组 A，如果它是有效的山脉数组就返回 true，否则返回 false。
 * <p>
 * 让我们回顾一下，如果 A 满足下述条件，那么它是一个山脉数组：
 * <p>
 * A.length >= 3
 * 在 0 < i < A.length - 1 条件下，存在 i 使得：
 * A[0] < A[1] < ... A[i-1] < A[i]
 * A[i] > A[i+1] > ... > A[A.length - 1]
 * <p>
 * 第一次错误：没有考虑A[0]为数组中最大的数的情况
 * 第二次错误: 设置状态后没有及时更新
 * 第三次错误:  没有考虑A为空的情况
 * 第四次错误： 没有考虑A长度为1的情况,第三次和第四都是同一个错误，少看了一个条件。。。。。。。
 */
public class A0941ValidMountainArray {

    public static void main(String[] args) {
        int[] A = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(validMountainArray2(A));
    }

    public static boolean validMountainArray(int[] A) {
        //爬山状态  f为上山   t为下山
        boolean stat = false;

        for (int i = 1; i < A.length; i++) {
            //不能有平地
            if (A[i - 1] == A[i]) {
                return false;
            }
            if (!stat) {
                if (A[i - 1] > A[i]) {
                    //到达山顶，山顶不能在起点
                    if (i == 1) {
                        return false;
                    } else {
                        stat = true;
                    }
                }
            } else {
                //下山路
                if (A[i - 1] < A[i]) {
                    return false;
                }
            }
        }

        if (A.length < 3) {
            return false;
        }

        //只有上山没有下山
        return stat;

    }


    /**
     *
     * @param A
     * @return
     *
     * 双指针
     */
    public static boolean validMountainArray2(int[] A) {

        if(A.length < 3){
            return  false;
        }

        int left = 0;
        int right = A.length-1;

        while(left < A.length -2 && A[left] < A[left+1]){
            left ++;
        }

        while(right > 1 && A[right] < A[right-1]){
            right --;
        }

        return  left == right;
    }


}
