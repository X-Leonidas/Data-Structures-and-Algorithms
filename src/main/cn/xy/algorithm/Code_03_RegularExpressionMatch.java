package cn.xy.algorithm;

/**
 * @author XiangYu
 * @create2021-04-08-22:30
 *
 *  字KM符P算串法匹扩配展问题题目二
 * 【 题目】
 * 给定字符串str， 其中绝对不含有字符'.'和'*'。 再给定字符串exp，
 * 其中可以含有'.'或'*'， '*'字符不能是exp的首字符， 并且任意两个
 * '*'字符不相邻。 exp中的'.'代表任何一个字符， exp中的'*'表示'*'
 * 的前一个字符可以有0个或者多个。 请写一个函数， 判断str是否能被
 * exp匹配。
 * 【 举例】
 * str="abc"， exp="abc"， 返回true。
 * str="abc"， exp="a.c"， exp中单个'.'可以代表任意字符， 所以返回
 * true。
 * str="abcd"， exp=".*"。 exp中'*'的前一个字符是'.'， 所以可表示任
 * 意数量的'.'字符， 当exp是"...."时与"abcd"匹配， 返回true。
 * str=""， exp="..*"。 exp中'*'的前一个字符是'.'， 可表示任意数量
 * 的'.'字符， 但是".*"之前还有一个'.'字符， 该字符不受'*'的影响，
 * 所以str起码有一个字符才能被exp匹配。 所以返回false。
 *
 *
 */
public class Code_03_RegularExpressionMatch {

    /**
     * 基本的规则校验
     * @param s
     * @param e
     * @return
     */
    public static boolean isValid(char[] s, char[] e) {
        for (int i = 0; i < s.length; i++) {
            if (s[i] == '*' || s[i] == '.') {
                return false;
            }
        }
        for (int i = 0; i < e.length; i++) {
            if (e[i] == '*' && (i == 0 || e[i - 1] == '*')) {
                return false;
            }
        }
        return true;
    }

    /**
     * 递归的写法
     * @param str
     * @param exp
     * @return
     */
    public static boolean isMatch(String str, String exp) {
        if (str == null || exp == null) {
            return false;
        }
        char[] s = str.toCharArray();
        char[] e = exp.toCharArray();
        return isValid(s, e) ? process(s, e, 0, 0) : false;
    }

    public static boolean process(char[] str, char[] exp, int si, int ei) {
        //当exp耗尽了，str也必须耗尽才能匹配上
        if (ei == exp.length) {
            return si == str.length;
        }
        //ei上还有字符，考察ei+1的情况，当ei到尽头或者ei+1上不为*
        if (ei + 1 == exp.length || exp[ei + 1] != '*') {
            // si != str.length  str上必须还有的匹配
            return si != str.length && (exp[ei] == str[si] || exp[ei] == '.')
                    && process(str, exp, si + 1, ei + 1);
        }
        // exp 的ei+1位置，不仅有字符而且字符是*
        while (si != str.length && (exp[ei] == str[si] || exp[ei] == '.')) {
            if (process(str, exp, si, ei + 2)) {
                return true;
            }
            si++;
        }
        //ei* *取0的情况
        return process(str, exp, si, ei + 2);
    }

    /**
     * DP的方式 需要填一些baseCase
     * @param str
     * @param exp
     * @return
     */
    public static boolean isMatchDP(String str, String exp) {
        if (str == null || exp == null) {
            return false;
        }
        char[] s = str.toCharArray();
        char[] e = exp.toCharArray();
        if (!isValid(s, e)) {
            return false;
        }
        //填baseCase
        boolean[][] dp = initDPMap(s, e);
        for (int i = s.length - 1; i > -1; i--) {
            for (int j = e.length - 2; j > -1; j--) {
                if (e[j + 1] != '*') {
                    dp[i][j] = (s[i] == e[j] || e[j] == '.')
                            && dp[i + 1][j + 1];
                } else {
                    int si = i;
                    while (si != s.length && (s[si] == e[j] || e[j] == '.')) {
                        if (dp[si][j + 2]) {
                            dp[i][j] = true;
                            break;
                        }
                        si++;
                    }
                    if (dp[i][j] != true) {
                        dp[i][j] = dp[si][j + 2];
                    }
                }
            }
        }
        return dp[0][0];
    }

    /**
     * 把最后一排和倒数两列生成好
     * @param s
     * @param e
     * @return
     */
    public static boolean[][] initDPMap(char[] s, char[] e) {
        int slen = s.length;
        int elen = e.length;
        boolean[][] dp = new boolean[slen + 1][elen + 1];
        //右下角
        dp[slen][elen] = true;
        //最后一排
        for (int j = elen - 2; j > -1; j = j - 2) {
            if (e[j] != '*' && e[j + 1] == '*') {
                dp[slen][j] = true;
            } else {
                break;
            }
        }
        //倒数第二列的最后一个值需要计算，其他的在str还有多个，但是exp只有一个的情况下，均为false
        if (slen > 0 && elen > 0) {
            if ((e[elen - 1] == '.' || s[slen - 1] == e[elen - 1])) {
                dp[slen - 1][elen - 1] = true;
            }
        }
        return dp;
    }

    public static void main(String[] args) {
        String str = "abcccdefg";
        String exp = "ab.*d.*e.*";
        System.out.println(isMatch(str, exp));
        System.out.println(isMatchDP(str, exp));

    }

}
