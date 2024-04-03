package cn.xy.algorithm.str.manacher;

import java.io.*;

/**
 * @author xiangyu
 * @date 2024-04-02 1:31
 * manacher 算法的延续
 * kmp 扩展算法
 * 又叫Z算法
 *  z数组
 *  str是原串
 *  z[i]代表的是str第i个到结尾的子串 与 原串str的最长的公共前缀
 *  例如： aaabaac z[0] = 7   z[1] = 2 z[2] = 1....
 *  用来求某个字符串以i开头子串与str的最大匹配长度
 *
 *  e数组
 *  str1 = aaabaac
 *  str2 = aab
 *  z[i] 表示 str1从i位置出发， 与str2的最长公共前缀
 *
 *  要先拿到str2的z数组
 *
 */
public class Code_01_KMP_WRAPPER {
    public static int MAXN = 20000001;

    public static int[] z = new int[MAXN];

    public static int[] e = new int[MAXN];

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        char[] a = in.readLine().toCharArray();
        char[] b = in.readLine().toCharArray();
        zArray(b, b.length);
        eArray(a, b, a.length, b.length);
        out.println(eor(z, b.length));
        out.println(eor(e, a.length));
        out.flush();
        out.close();
        in.close();
    }

    // 非常像Manacher算法
    public static void zArray(char[] s, int n) {
        z[0] = n;
        for (int i = 1, c = 1, r = 1, len; i < n; i++) {
            len = r > i ? Math.min(r - i, z[i - c]) : 0;
            while (i + len < n && s[i + len] == s[len]) {
                len++;
            }
            // 拿到最大右边界的r和起点c
            if (i + len > r) {
                r = i + len;
                c = i;
            }
            z[i] = len;
        }
    }

    // 非常像Manacher算法
    public static void eArray(char[] a, char[] b, int n, int m) {
        for (int i = 0, c = 0, r = 0, len; i < n; i++) {
            len = r > i ? Math.min(r - i, z[i - c]) : 0;
            while (i + len < n && len < m && a[i + len] == b[len]) {
                len++;
            }
            if (i + len > r) {
                r = i + len;
                c = i;
            }
            e[i] = len;
        }
    }

    public static long eor(int[] arr, int n) {
        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans ^= (long) (i + 1) * (arr[i] + 1);
        }
        return ans;
    }
}
