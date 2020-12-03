package cn.xy.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author XiangYu
 * @create2020-12-03-9:39\ 204. 计数质数
 * 统计所有小于非负整数 n 的质数的数量。
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 10
 * 输出：4
 * 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 * 示例 2：
 * <p>
 * 输入：n = 0
 * 输出：0
 * 示例 3：
 * <p>
 * 输入：n = 1
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= n <= 5 * 106
 */
public class A204CountPrimes {
    public static void main(String[] args) {


        System.out.println(countPrimes2(8));

    }


    /**
     * 埃拉托斯特尼筛法
     *
     * @param n
     * @return 执行用时：92 ms, 在所有 Java 提交中击败了16.18%的用户
     * 内存消耗：81.2 MB, 在所有 Java 提交中击败了5.01%的用户
     */
    public static int countPrimes(int n) {
        int count = 0;
        if (n == 0 || n == 1) {
            return 0;
        }

        List<Integer> list = new ArrayList<>();
        //为了保持索引值与数值一致，看上去更清晰，先加一个0
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        //1不是素数，去掉
        list.set(1, 0);

        //遍历小于sqrt(max)的数
        for (int i = 2; i * i < n; i++) {
            //先判断之前是不是已经删除过了
            if (list.get(i) != 0) {
                //这里直接忽略了小于i的倍数，之前已经处理过了
                int a = i * i;
                while (a < n) {
                    list.set(a, 0);
                    a += i;
                }
            }
        }

        for (Integer integer : list) {
            if (integer != 0) {
                count++;
            }
        }


        return count;
    }


    /**
     * 埃拉托斯特尼筛法
     * 力扣官方教程
     *
     * @param n
     * @return
     */
    public static int countPrimes2(int n) {
        int[] isPrime = new int[n];
        //将数组全部元素初始化为1
        Arrays.fill(isPrime, 1);
        int ans = 0;
        for (int i = 2; i < n; ++i) {
            if (isPrime[i] == 1) {
                ans += 1;
                if ((long) i * i < n) {
                    //对于一个质数 x，如果按上文说的我们从 2x 开始标记其实是冗余的，
                    // 应该直接从 x * x开始标记，因为 2x,3x… 这些数一定在 x 之前就
                    // 被其他数的倍数标记过了，但是这样还是会有重复标志的冗余
                    for (int j = i * i; j < n; j += i) {
                        isPrime[j] = 0;
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 线性筛(欧拉筛法)
     * 力扣官方解法
     *
     * @param n
     * @return
     */
    public static int countPrimes3(int n) {
        List<Integer> primes = new ArrayList<Integer>();
        int[] isPrime = new int[n];
        Arrays.fill(isPrime, 1);

        for (int i = 2; i < n; ++i) {
            //将素数存储起来
            if (isPrime[i] == 1) {
                primes.add(i);
            }

            //遍历素数组并且 与素数组的乘积不大于n
            for (int j = 0; j < primes.size() && i * primes.get(j) < n; ++j) {
                //在该数与素数的乘积上的全部为合数，无论该数是否为素数
                isPrime[i * primes.get(j)] = 0;
                //
                if (i % primes.get(j) == 0) {
                    break;
                }
            }
        }
        return primes.size();
    }


    /**
     * 欧拉筛法
     *
     * @param n
     * @return
     */
    public static int countPrimes4(int n) {

        // 是否为合数的标记
        boolean[] check = new boolean[n];
        // 存储素数
        int[] primeList = new int[n];
        int count = 0;
        for (int indexI = 2; indexI <= n; indexI++) {

            //存储素数
            if (!check[indexI]) {
                primeList[count++] = indexI;
            }
            // 每一个数都去乘当前素数表里面已有的数，如果 indexI 是合数，且 indexI % primeList[indexJ] == 0，那么它只能乘以第一个素数 2
            // 如：2×2、3×(2、3)、4×(2)、5×(2、3、5)、6×(2)、7×(2、3、5、7)、8×(2)、9×(2、3)、10×(2)
            for (int indexJ = 0; indexJ < count; indexJ++) {
                // 过大的时候跳出
                if (indexI * primeList[indexJ] > n) {
                    break;
                }
                check[indexI * primeList[indexJ]] = true;
                // 如果 indexI 是一个合数，而且 indexI % primeList[indexJ] == 0
                // 保证了每个合数只会被它的最小素因子筛掉
                if (indexI % primeList[indexJ] == 0) {
                    break;
                }
            }
        }


        return count;

    }
}
