package cn.xy.leetcode.array;

/**
 * @author XiangYu
 * @create2020-11-12-9:45
 * 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
 *
 * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
 *
 * 你可以返回任何满足上述条件的数组作为答案。
 *
 * 示例：
 *
 * 输入：[4,2,5,7]
 * 输出：[4,5,2,7]
 * 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
 *  
 *
 * 提示：
 *
 * 2 <= A.length <= 20000
 * A.length % 2 == 0
 * 0 <= A[i] <= 1000
 *
 */
public class A922SortArrayByParityII {
    /**
     * 速度80
     * 内存30
     * @param A
     * @return
     */
    public int[] sortArrayByParityII(int[] A) {
        //奇数
        int oddIndex = 1; 
        //偶数
        int evenIndex = 0;

        int [] t = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            if(A[i] % 2 != 0){
                t[oddIndex] = A[i];
                oddIndex += 2;
            }else{
                t[evenIndex] = A[i];
                evenIndex += 2;
            }
        }

        return t;
        
    }


    /**
     * 双指针
     *
     * 执行用时：3 ms, 在所有 Java 提交中击败了78.68%的用户
     * 内存消耗：39.8 MB, 在所有 Java 提交中击败了86.56%的用户
     *
     * @param A
     * @return
     */
    public int[] sortArrayByParityII2(int[] A) {
        //奇数
        int j = 1;
        for (int i = 0; i < A.length; i += 2) {
            if(A[i] % 2 == 1){
                while (A[j] % 2 == 1){
                    j += 2;
                }
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
            }
        }
        return A;
    }
}
