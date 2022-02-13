package cn.xy.leetcode.easy;

import java.util.Arrays;

/**
 * @author XiangYu
 * @create2020-12-25-13:50
 *  假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
 *
 * 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；
 * 并且每块饼干 j，都有一个尺寸 s[j] 。如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。
 * 你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
 *
 *  
 * 示例 1:
 *
 * 输入: g = [1,2,3], s = [1,1]
 * 输出: 1
 * 解释:
 * 你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。
 * 虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。
 * 所以你应该输出1。
 * 示例 2:
 *
 * 输入: g = [1,2], s = [1,2,3]
 * 输出: 2
 * 解释:
 * 你有两个孩子和三块小饼干，2个孩子的胃口值分别是1,2。
 * 你拥有的饼干数量和尺寸都足以让所有孩子满足。
 * 所以你应该输出2.
 *  
 *
 * 提示：
 *
 * 1 <= g.length <= 3 * 104
 * 0 <= s.length <= 3 * 104
 * 1 <= g[i], s[j] <= 231 - 1
 *
 */
public class A0455AssignCookies {



    /**
     *
     * @param g  胃口
     * @param s  饼干尺寸
     * @return   最大可以满足孩子的数量
     *
     * 执行用时：8 ms, 在所有 Java 提交中击败了89.07%的用户
     * 内存消耗：39.3 MB, 在所有 Java 提交中击败了51.67%的用户
     *
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int gindex = 0;
        int sindex = 0;
        int sum = 0;
        boolean flag = true;
        while (flag){
            for (int i = sindex; i < s.length; i++) {
                if(s[i] >= g[gindex]){
                    sum++;
                    gindex++;
                    if(gindex == g.length){
                        return  g.length;
                    }
                    sindex = i+1;
                    flag = false;
                }
            }
            if(!flag){
                flag = true;
            }else {
                return sum;
            }
        }

        return  sum;

    }

    /**
     * 优化
     * 执行用时：8 ms, 在所有 Java 提交中击败了89.07%的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了83.83%的用户
     *
     * @param g  胃口
     * @param s  饼干尺寸
     * @return   最大可以满足孩子的数量
     */
    public int findContentChildren2(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int gIndex = 0;
        int sIndex = 0;
        int sum = 0;
        while (gIndex < g.length){
            boolean flag = true;
            for (int i = sIndex; i < s.length; i++) {
                if(s[i] >= g[gIndex]){
                    sum++;
                    gIndex++;
                    sIndex = i+1;
                    flag = false;
                    break;
                }
            }
            if(flag){
                return sum;
            }
        }
        return  sum;
    }


    /**
     * 官方写法
     * 贪心+排序
     * @param g  胃口
     * @param s  饼干尺寸
     * @return   最大可以满足孩子的数量
     */
    public int findContentChildren3(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        //小孩数量
        int numOfChildren = g.length;
        //饼干个数
        int numOfCookies = s.length;
        int count = 0;

        for (int i = 0, j = 0; i < numOfChildren && j < numOfCookies; i++, j++) {
            //遍历到饼干可以满足孩子的位置
            while (j < numOfCookies && g[i] > s[j]) {
                j++;
            }
            if (j < numOfCookies) {
                count++;
            }
        }
        return count;
    }


}
