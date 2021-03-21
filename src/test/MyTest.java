/**
 * @author XiangYu
 * @create2021-03-20-20:46
 */
public class MyTest {
    public static void main(String[] args) {

        int i = 5;
        int j = 10;
        Integer integer = Integer.valueOf(~j);
        System.out.println(Integer.toBinaryString(integer));
    }
}
