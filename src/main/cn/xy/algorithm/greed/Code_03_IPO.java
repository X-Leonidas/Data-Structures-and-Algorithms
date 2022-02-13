package xy.algorithm.greed;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author XiangYu
 * @create2021-03-12-18:49
 *
 *
 *  参数1， 正数数组costs    成本数组
 *  参数2， 正数数组profits  纯利润数组
 *  参数3， 正数k   最多可以做多少个项目
 *  参数4， 正数m   启动资金
 *
 *  costs[i]表示i号项目的花费
 *  profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
 *  k表示你不能并行、 只能串行的最多做k个项目
 *  m表示你初始的资金
 *  说明： 你每做完一个项目，马上获得的收益，可以支持你去做下一个项目。每个项目只能做一次
 *  输出： 你最后获得的最大钱数
 *
 *
 *
 *  解题思路： 按照花费组成小根堆
 *            根据初始资金，弹出所有满足条件的元素放入一个大根堆中，该大根堆是按照收益排序
 */
public class Code_03_IPO {
    public static class Node {
        public int p;
        public int c;

        public Node(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    public static class MinCostComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o1.c - o2.c;
        }

    }

    public static class MaxProfitComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o2.p - o1.p;
        }

    }

    public static int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        Node[] nodes = new Node[Profits.length];
        for (int i = 0; i < Profits.length; i++) {
            nodes[i] = new Node(Profits[i], Capital[i]);
        }

        //小根堆  大根退
        PriorityQueue<Node> minCostQ = new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Node> maxProfitQ = new PriorityQueue<>(new MaxProfitComparator());
        for (int i = 0; i < nodes.length; i++) {
            minCostQ.add(nodes[i]);
        }
        for (int i = 0; i < k; i++) {
            while (!minCostQ.isEmpty() && minCostQ.peek().c <= W) {
                maxProfitQ.add(minCostQ.poll());
            }
            if (maxProfitQ.isEmpty()) {
                return W;
            }
            W += maxProfitQ.poll().p;
        }
        return W;
    }

}
