package cn.xy.leetcode.middle.array;

/**
 * @author XiangYu
 * @create2020-11-08-20:27
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 * 示例 2:
 *
 * 输入: [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 示例 3:
 *
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *  
 *
 * 提示：
 *
 * 1 <= prices.length <= 3 * 10 ^ 4
 * 0 <= prices[i] <= 10 ^ 4
 *
 */
public class A0122BestTimeToBuyAndSellStock2 {
    public static void main(String[] args) {
        int [] ints = {7,1,5,3,6,4};
        System.out.println(maxProfit(ints));
    }

    /**
     * 贪心算法
     * @param prices  价格
     * @return
     */
    public static int maxProfit(int[] prices) {
        int profit =0;
        if(prices.length < 2){
            return 0;
        }

        for (int i = 1; i < prices.length; i++) {
            if(prices[i] > prices[i-1]){
                profit = profit + prices[i] - prices[i-1];
            }
        }
        

        return profit;
    }

    /**
     * 只能买卖一次
     * @param prices
     * @return
     */
    public static int maxProfit_1(int[] prices) {
        if(prices.length < 2){
            return 0;
        }
        int profit =0;
        int min = prices[0];
        int max = 0;

        for (int i = 1; i < prices.length; i++) {
            if(prices[i] < min){
                //获取最小值时，要重置最大值并获取上一次的利润
                profit = Math.max(max-min,profit);
                min = prices[i];
                max = prices[i];

            }
            if(prices[i] > max){
                max = prices[i];
            }
        }
        //最后一次最小值的计算
        profit = Math.max(max-min,profit);
        return profit;
    }


    /**
     * 只能买卖一次，改进版
     * 执行用时：1 ms, 在所有 Java 提交中击败了98.55%的用户
     * 内存消耗：38 MB, 在所有 Java 提交中击败了93.40%
     * 的用户
     * @param prices
     * @return
     */
    public int maxProfit_2(int prices[]) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int mp = 0; // 最高收益
        int min = prices[0]; // 最低价
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



}
