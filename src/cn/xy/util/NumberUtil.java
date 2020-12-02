package cn.xy.util;

/**
 * @author XiangYu
 * @create2020-12-01-23:38 数字类型的对数器
 */
public class NumberUtil {
    public static int[] generateRandomArray(int size, int value) {
        int[] array = new int[(int) ((size + 1) * Math.random())];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (value + 1 * Math.random()) - (int) (value * Math.random());
        }
        return array;
    }


}
