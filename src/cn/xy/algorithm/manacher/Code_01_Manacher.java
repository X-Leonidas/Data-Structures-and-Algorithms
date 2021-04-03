package cn.xy.algorithm.manacher;

/**
 * @author XiangYu
 * @create2021-03-21-18:45
 *  可以在时间**O(N)**的情况下求解一个字符串的最长回文子串长度的问题。
 *
 */
public class Code_01_Manacher {

    public static char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        //添加标志位
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }

    public static int maxLcpsLength(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        //构建算法的前置数据结构
        char[] charArr = manacherString(str);
        int[] pArr = new int[charArr.length];
        int index = -1;
        //回文右边界
        int pR = -1;
        //
        int max = Integer.MIN_VALUE;

        for (int i = 0; i != charArr.length; i++) {
            //2 * index - i   i`点
            //Math.min(pArr[2 * index - i], pR - i)  起码不用验证的区域 (i到pr和i`的回文半径，谁小就是谁)
            pArr[i] = pR > i ? Math.min(pArr[2 * index - i], pR - i) : 1;
            //i + pArr[i] < charArr.length && i - pArr[i] > -1左右不越界
            while (i + pArr[i] < charArr.length && i - pArr[i] > -1) {
                if (charArr[i + pArr[i]] == charArr[i - pArr[i]]){
                    pArr[i]++;
                } else {
                    break;
                }
            }
            //更新回文右边界
            if (i + pArr[i] > pR) {
                pR = i + pArr[i];
                index = i;
            }
            max = Math.max(max, pArr[i]);
        }
        return max - 1;
    }

    public static void main(String[] args) {
        String str1 = "abc1234321ab";
        System.out.println(maxLcpsLength(str1));
    }
}
