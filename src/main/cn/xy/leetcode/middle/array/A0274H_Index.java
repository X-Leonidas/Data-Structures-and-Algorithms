package cn.xy.leetcode.middle.array;

import java.util.Arrays;

/**
 * @author xiangyu
 * @date 2024-12-12 23:56
 * <p>
 * 给你一个整数数组 citations ，其中 citations[i] 表示研究者的第 i 篇论文被引用的次数。计算并返回该研究者的 h 指数。
 * <p>
 * 根据维基百科上 h 指数的定义：h 代表“高引用次数” ，一名科研人员的 h 指数 是指他（她）至少发表了 h 篇论文，
 * 并且 至少 有 h 篇论文被引用次数大于等于 h 。
 * 如果 h 有多种可能的值，h 指数 是其中最大的那个。
 * <p>
 * 示例 1：
 * 输入：citations = [3,0,6,1,5]
 * 输出：3
 * 解释：给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 3, 0, 6, 1, 5 次。
 * 由于研究者有 3 篇论文每篇 至少 被引用了 3 次，其余两篇论文每篇被引用 不多于 3 次，所以她的 h 指数是 3。
 * <p>
 * 示例 2：
 * 输入：citations = [1,3,1]
 * 输出：1
 * <p>
 * 提示：
 * n == citations.length
 * 1 <= n <= 5000
 * 0 <= citations[i] <= 1000
 */
public class A0274H_Index {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        // 最大的h 指数
        int result = 0;
        // 反向遍历
        for (int i = citations.length - 1; i >= 0; i--) {
            // 指数慢慢增大，直到不满足为止
            // result 当前应该的指数
            if (citations[i] >= result+1) {
                result++;
            } else {
                break;
            }
        }

        return result;
    }

    // 正向遍历
    public int hIndex1(int[] citations) {
        Arrays.sort(citations);
        // 最大的h 指数
        // 0,1,3,6,5
        for (int i = 0; i < citations.length ; i++) {
            if(citations[i] >= citations.length-i){
                return citations.length - i;
            }
        }

        return 0;
    }

    /**
     * O(n)的算法
     * cnt[a] = b;
     * 达标 引用次数是a的论文有b篇
     *
     *
     * @param cs
     * @return
     */
    public int hIndex2(int[] cs) {
        int n = cs.length;
        int[] cnt = new int[n + 1];
        for (int c : cs) {
            cnt[Math.min(c, n)]++;
        }
        int tot = 0;
        for (int i = n; i >= 0; i--) {
            tot += cnt[i];
            // 因为 i 是慢慢减小的，只要总篇数大于i，就说明这是i最大的满足条件的值
            if (tot >= i) {
                return i;
            }
        }
        // never
        return -1;
    }


}
