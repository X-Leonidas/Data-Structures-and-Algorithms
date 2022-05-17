package cn.xy.algorithm.manacher;

/**
 * @author XiangYu
 * @create2021-03-21-18:45
 *  可以在时间**O(N)**的情况下求解一个字符串的最长回文子串长度的问题。
 *
 *
 * + 在每个字符串之间加入一个标志位，例如 11311 -> #1#1#3#1#1#
 * + 然后每个索引上的值向左右遍历对比。
 * + 拿到最大的长度， 长度/2  就是最长回文子串的长度
 *
 * 1. 在每个字符串之间加入一个标志位，例如 11311 -> #1#1#3#1#1#
 *
 * 2. `i`位置不再回文右边界里面，继续暴力求出在`i+1`的回文子串长度
 * 3. `i`位置在回文右边界内，根据回文中心`c`，做出i的对称点$i_2$
 *    1.  $i_2$的回文直径在当前`c`的回文半径中，例如 z k [ a b($i_2$) a t f(`c`) t a b(`i`) a ] k u
 *        + 此时`i`的回文半径和$i_2$一致
 *    2.  $i_2$的回文半径的索引超出了当前`c`的回文半径，例如a b [c d($i_2$) c b a t t(`c`) t a b c d(`i`) c] f
 *        + 此时`i`的回文半径为`i`到c的右边界的长度
 *    3.  $i_2$的回文半径的索引等于了当前`c`的回文半径，例如 t [a b c($i_2$) b a k(`o`) a b c(`i`) b a] k
 *        + 此时`i`的回文半径无法确认，要从c的右边界的下一个继续校验
 *
 *
 */
public class Code_01_Manacher {

    public static char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        //预处理操作，将偶数变为奇数
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        //插入的标志位
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
        //回文半径数组
        int[] pArr = new int[charArr.length];
        //索引
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


    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }

        if (s.length() == 1) {
            return s;
        }
        //滑块

        //数组的长度，回文数的长度
        int length = s.length();


        for (int j = length; j > 0; j--) {
            int i = 0;
            while (i + length - 1 < s.length()) {
                //开始
                int star = i;
                //结束
                int end = i + length - 1;
                //需要校验长度
                int tempLength = (end - star + 1) / 2;
                boolean flag = true;

                for (int k = 0; k < tempLength; k++) {
                    if (s.charAt(star) == s.charAt(end)) {
                        star++;
                        end--;
                    } else {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    return s.substring(i, i + length);
                } else {
                    i++;
                }
            }
            //未匹配到，长度减1
            length--;
        }


        return s.substring(0, 1);
    }
}
