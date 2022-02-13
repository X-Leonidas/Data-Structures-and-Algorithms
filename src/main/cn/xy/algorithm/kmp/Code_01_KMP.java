package xy.algorithm.kmp;

/**
 * @author XiangYu
 * @create2021-03-19-22:48
 *
 *
 * KMP算法
 *
 *  主要解决包含的问题。例如：str1的子串集合中是否包含str2
 *
 *  **时间复杂度 O(n)**
 *
 *  思路：
 */
public class Code_01_KMP {

    public static int getIndexOf(String s, String m) {
        if (s == null || m == null || m.length() < 1 || s.length() < 1) {
            return -1;
        }

        char[] str1 = s.toCharArray();
        char[] str2 = m.toCharArray();
        int i1 = 0;
        int i2 = 0;

        int[] next = getNextArray(str2);

        while (i1 < str1.length && i2 < str2.length) {
            if (str1[i1] == str2[i2]) {
                i1++;
                i2++;
            } else if (next[i2] == -1) {
                i1++;
            } else {
                i2 = next[i2];
            }
        }

        return i2 == str2.length ? i1 - i2 : -1;

    }


    /**
     * 获得每个位置上的最大前缀字符和后缀字符长度，例如abckfabcy   y位置上的最大前缀和最大后缀长度为3 abc
     *
     * @param str
     * @return
     */
    private static int[] getNextArray(char[] str) {
        if (str.length == 1) {
            return new int[]{-1};
        }

        int[] next = new int[str.length];


        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0;

        while (i < next.length) {
            //如果当前字符的前一个 和 最大前缀和的后一个相等，则直接字符前一个的字符的最大前缀后缀长度+1
            if (str[i - 1] == str[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                //在当前位置继续追查最大前缀后缀
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }

        return next;

    }


    public static void main(String[] args) {
        String str = "abcabcababaccc";
        String match = "abc";
        System.out.println(getIndexOf(str,match));
    }

}
