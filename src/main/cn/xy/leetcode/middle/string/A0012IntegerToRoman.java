package cn.xy.leetcode.middle.string;

import java.util.*;

/**
 * 七个不同的符号代表罗马数字，其值如下：
 * <p>
 * 符号	值
 * I	1
 * V	5
 * X	10
 * L	50
 * C	100
 * D	500
 * M	1000
 * 罗马数字是通过添加从最高到最低的小数位值的转换而形成的。将小数位值转换为罗马数字有以下规则：
 * 如果该值不是以 4 或 9 开头，请选择可以从输入中减去的最大值的符号，将该符号附加到结果，减去其值，然后将其余部分转换为罗马数字。
 * 如果该值以 4 或 9 开头，使用 减法形式，表示从以下符号中减去一个符号，例如 4 是 5 (V) 减 1 (I): IV ，9 是 10 (X) 减 1 (I)：IX。
 * 仅使用以下减法形式：4 (IV)，9 (IX)，40 (XL)，90 (XC)，400 (CD) 和 900 (CM)。
 * 只有 10 的次方（I, X, C, M）最多可以连续附加 3 次以代表 10 的倍数。你不能多次附加 5 (V)，50 (L) 或 500 (D)。
 * 如果需要将符号附加4次，请使用减法形式。
 * 给定一个整数，将其转换为罗马数字。
 * <p>
 * 示例 1：
 * 输入：num = 3749
 * 输出： "MMMDCCXLIX"
 * 解释：
 * 3000 = MMM 由于 1000 (M) + 1000 (M) + 1000 (M)
 * 700 = DCC 由于 500 (D) + 100 (C) + 100 (C)
 * 40 = XL 由于 50 (L) 减 10 (X)
 * 9 = IX 由于 10 (X) 减 1 (I)
 * 注意：49 不是 50 (L) 减 1 (I) 因为转换是基于小数位
 * <p>
 * 示例 2：
 * 输入：num = 58
 * 输出："LVIII"
 * 解释：
 * 50 = L
 * 8 = VIII
 * <p>
 * 示例 3：
 * 输入：num = 1994
 * 输出："MCMXCIV"
 * 解释：
 * 1000 = M
 * 900 = CM
 * 90 = XC
 * 4 = IV
 * <p>
 * 提示：
 * 1 <= num <= 3999
 *
 * @author xiangyu
 * @date 2024-12-17 0:31
 */
public class A0012IntegerToRoman {

    public static void main(String[] args) {
        new A0012IntegerToRoman().intToRoman(3749);
    }

    Map<Integer, String> cache = new HashMap<>() {{
        put(1, "I");
        put(5, "V");
        put(10, "X");
        put(50, "L");
        put(100, "C");
        put(500, "D");
        put(1000, "M");
    }};

    public String intToRoman(int num) {
        List<String> result = new ArrayList<>();
        int divisor = 10;

        while (num >= divisor / 10) {
            int remainder = num % divisor;
            result.add(getRoman(remainder, divisor));
            divisor = divisor * 10;
            num = num - remainder;
        }

        String ans = "";
        for (int i = result.size() - 1; i >= 0; i--) {
            ans = ans + result.get(i);
        }

        return ans;
    }

    private String getRoman(Integer remainder, Integer divisor) {
        divisor = divisor / 10;
        if (remainder > 5 * divisor) {
            if (remainder == 9 * divisor) {
                return cache.get(divisor) + cache.get(divisor * 10);
            } else {
                String temp = cache.get(5 * divisor);
                int count = (remainder - 5 * divisor) / divisor;
                for (int i = 0; i < count; i++) {
                    temp = temp + cache.get(divisor);
                }
                return temp;
            }
        }else if(remainder == 5 * divisor){
                return cache.get(5 * divisor);
        } else {
            if (remainder == 4 * divisor) {
                return cache.get(divisor) + cache.get(divisor * 5);
            } else {
                String temp ="";
                int count = remainder / divisor;
                for (int i = 0; i < count; i++) {
                    temp = temp + cache.get(divisor);
                }
                return temp;
            }
        }
    }


    int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    /**
     * 官方的写法
     * 隐含的条件就是会越来越小，并且也是从小到打使用
     * @param num
     * @return
     */
    public String intToRoman2(int num) {
        StringBuffer roman = new StringBuffer();
        for (int i = 0; i < values.length; ++i) {
            int value = values[i];
            String symbol = symbols[i];
            while (num >= value) {
                num -= value;
                roman.append(symbol);
            }
            if (num == 0) {
                break;
            }
        }
        return roman.toString();
    }

}
