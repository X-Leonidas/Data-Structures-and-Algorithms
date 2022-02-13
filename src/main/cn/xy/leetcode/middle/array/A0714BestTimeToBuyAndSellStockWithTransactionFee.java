package cn.xy.leetcode.middle.array;

/**
 * @author XiangYu
 * @create2020-12-17-10:18
 * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
 *
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 *
 * 返回获得利润的最大值。
 *
 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 *
 * 示例 1:
 *
 * 输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
 * 输出: 8
 * 解释: 能够达到的最大利润:
 * 在此处买入 prices[0] = 1
 * 在此处卖出 prices[3] = 8
 * 在此处买入 prices[4] = 4
 * 在此处卖出 prices[5] = 9
 * 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 * 注意:
 *
 * 0 < prices.length <= 50000.
 * 0 < prices[i] < 50000.
 * 0 <= fee < 50000.
 *
 */
public class A0714BestTimeToBuyAndSellStockWithTransactionFee {
    public static void main(String[] args) {
        //[4,5,2,4,3,3,1,2,5,4]
       //int[] ints = {1,3,7,5,10,3};
       // int[] ints = {1, 3, 2, 8, 4, 9};
        int[] ints = {4,5,2,4,3,3,1,2,5,4};
        int i = maxProfit(ints,1);
        System.out.println(i);
    }
    /**
     * @param prices
     * @param fee
     * @return
     *
     * 思路与算法
     *
     * 方法一中，我们将手续费放在卖出时进行计算。如果我们换一个角度考虑，将手续费放在买入时进行计算，那么就可以得到一种基于贪心的方法。
     *
     * 我们用 \textit{buy}buy 表示在最大化收益的前提下，如果我们手上拥有一支股票，那么它的最低买入价格是多少。在初始时，\textit{buy}buy 的值为 \textit{prices}[0]prices[0] 加上手续费 \textit{fee}fee。那么当我们遍历到第 i~(i>0)i (i>0) 天时：
     *
     * 如果当前的股票价格 \textit{prices}[i]prices[i] 加上手续费 \textit{fee}fee 小于 \textit{buy}buy，那么与其使用 \textit{buy}buy 的价格购买股票，我们不如以 \textit{prices}[i] + \textit{fee}prices[i]+fee 的价格购买股票，因此我们将 \textit{buy}buy 更新为 \textit{prices}[i] + \textit{fee}prices[i]+fee；
     *
     * 如果当前的股票价格 \textit{prices}[i]prices[i] 大于 \textit{buy}buy，那么我们直接卖出股票并且获得 \textit{prices}[i] - \textit{buy}prices[i]−buy 的收益。但实际上，我们此时卖出股票可能并不是全局最优的（例如下一天股票价格继续上升），因此我们可以提供一个反悔操作，看成当前手上拥有一支买入价格为 \textit{prices}[i]prices[i] 的股票，将 \textit{buy}buy 更新为 \textit{prices}[i]prices[i]。这样一来，如果下一天股票价格继续上升，我们会获得 \textit{prices}[i+1] - \textit{prices}[i]prices[i+1]−prices[i] 的收益，加上这一天 \textit{prices}[i] - \textit{buy}prices[i]−buy 的收益，恰好就等于在这一天不进行任何操作，而在下一天卖出股票的收益；
     *
     * 对于其余的情况，\textit{prices}[i]prices[i] 落在区间 [\textit{buy}-\textit{fee}, \textit{buy}][buy−fee,buy] 内，它的价格没有低到我们放弃手上的股票去选择它，也没有高到我们可以通过卖出获得收益，因此我们不进行任何操作。
     *
     * 上面的贪心思想可以浓缩成一句话，即当我们卖出一支股票时，我们就立即获得了以相同价格并且免除手续费买入一支股票的权利。在遍历完整个数组 \textit{prices}prices 之后之后，我们就得到了最大的总收益。
     *
     *
     */
    public static int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int buy = prices[0] + fee;
        int profit = 0;
        for (int i = 1; i < n; ++i) {
            if (prices[i] + fee < buy) {
                //拿到最低点
                buy = prices[i] + fee;
            } else if (prices[i] > buy) {
                //有收益，但是卖出后，可能下一天还要涨，所以要有反悔操作，扣掉手续费后，拿到所有上涨的点
                profit += prices[i] - buy;
                buy = prices[i];
            }
        }
        return profit;
    }




    //TODO:动态规划
}
