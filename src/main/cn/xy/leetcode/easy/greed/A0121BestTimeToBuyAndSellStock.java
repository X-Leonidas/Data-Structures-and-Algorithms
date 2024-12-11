package cn.xy.leetcode.easy.greed;

/**
 * @author xiangyu
 * @date 2024-03-26 0:08
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 * <p>
 * 示例 1：
 * 输入：[7,1,5,3,6,4]
 * 输出：5
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * <p>
 * 示例 2：
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
 * <p>
 * 提示
 * 1 <= prices.length <= 105
 * 0 <= prices[i] <= 104
 * <p>
 * 升级提醒：122
 */
public class A0121BestTimeToBuyAndSellStock {

    public static void main(String[] args) {
        A0121BestTimeToBuyAndSellStock a0121BestTimeToBuyAndSellStock = new A0121BestTimeToBuyAndSellStock();
        a0121BestTimeToBuyAndSellStock.maxProfit1(new int[]{7, 1, 5, 3, 6, 4});
    }

    public int maxProfit1(int[] prices) {
        int result = 0;
        int n = prices.length;
        int min = prices[0];

        for (int i = 1; i < n; i++) {
            if (min > prices[i - 1]) {
                min =  prices[i - 1];
            }
            int curValue = prices[i] - min;
            if (curValue > result) {
                result = curValue;
            }

        }

        return result;
    }


    public int maxProfit(int[] prices) {
        int ans = 0;
        int minest = prices[0];
        // 获取当天可以拿到的最小值

        for (int price : prices) {
            if (minest > price) {
                minest = price;
            }
            if (ans < price - minest) {
                ans = price - minest;
            }
        }

        return ans;
    }

    /**
     * 只能买卖一次，改进版
     * 执行用时：1 ms, 在所有 Java 提交中击败了98.55%的用户
     * 内存消耗：38 MB, 在所有 Java 提交中击败了93.40%
     * 的用户
     *
     * @param prices
     * @return
     */
    public int maxProfit4(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        // 最高收益
        int mp = 0;
        // 最低价
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < min) {
                // 设定相对最低价
                min = prices[i];
            } else if (mp < (prices[i] - min)) {
                // 设定最高盈利
                mp = prices[i] - min;
            }
        }
        return mp;
    }

    /**
     * 官方题解
     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices) {
        int result = 0;
        int n = prices.length;
        int min = prices[0];

        for (int i = 0; i < n; i++) {
            if (min > prices[i - 1]) {
                min = prices[i - 1];
            }else if (prices[i] - min > result) {
                result = prices[i] - min;
            }
        }

        return result;
    }
}
