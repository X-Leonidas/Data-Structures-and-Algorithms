package cn.xy.algorithm.str.kmp;

/**
 * @author XiangYu
 * @create2021-03-19-22:48 KMP算法
 * <p>
 * 主要解决包含的问题。例如：str1的子串集合中是否包含str2
 * 时间复杂度 O(n)
 * 1. next数组： str2中不含当前字符，当前字符之前的字符串,前后缀的最大相等的长度(不能包含整体) 由此可得 0位置永远是-1，1位置永远是0,
 * 举例： i前面的字符是 aabaab则最大前后缀相等的长度是3
 * 2. 如何加速的
 * 2.1 当找到不匹配的字符x时，看对应的str的X1的最大前后缀相等的长度是len，当前位置x直接和str2中的 len 位置进行匹配(因为0占了一个位置，所以不用+1)
 * 3. 如何加速构建next数组
 * 3.1 求i位置的next 值，看 i-1位置的最大长度len，然后比较len  和  i-1 的值是否相等 相等则是len+1
 * 否则看i-1位置的最大长度的next[len]的最大长度是len1 然后比较 len 位置和 i-1 位置是否相等 相等则是len1 +1 ，
 * 一直重复知道跳到尽头nuxt[len] = -1 则len 是0
 */
public class Code_01_KMP {
    public static int getIndexOf(String s, String m) {
        if (s == null || m == null || m.isEmpty() || s.isEmpty()) {
            return -1;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = m.toCharArray();
        int i1 = 0;
        int i2 = 0;
        // 子串的next数组
        int[] next = getNextArray(str2);
        while (i1 < str1.length && i2 < str2.length) {
            if (str1[i1] == str2[i2]) {
                i1++;
                i2++;
            } else if (next[i2] == 0) {
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
     */
    private static int[] getNextArray(char[] str) {
        if (str.length == 1) {
            return new int[]{-1};
        }

        int[] next = new int[str.length];
        next[0] = -1;
        next[1] = 0;
        // 当前下标
        int i = 2;
        // 要比较的下标
        int cn = 0;

        while (i < next.length) {
            //如果当前字符的前一个 和 最大前缀和的后一个相等，则直接字符前一个的字符的最大前缀后缀长度+1
            if (str[i - 1] == str[cn]) {
                // 这里++cn， 即代表 最长是++cn，也代表下次要比较的下标是++cn
                next[i] = ++cn;
                i++;
            } else if (cn > 0) {
                // 说明还可以往前跳
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
        System.out.println(getIndexOf(str, match));
    }

}
