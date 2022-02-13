package xy.algorithm.sort;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author XiangYu
 * @create2021-03-01-17:29
 *
 *
 * 比较器Comparable和Comparator
 *
 * 实现Comparable接口需要重写compareTo方法，实现Comparator接口需要重写compare方法，
 * 这两个方法的返回值都是int，用int类型的值来确定比较结果。
 *
 */
public class MyComparator {

    public static void main(String[] args) {
        stackComparatorTest();
    }

    /**
     *  内部比较器
     *  对象实现该接口，o与实现该接口的类进行比较
     */

    static class ComparatorTest1 implements  Comparable{

        @Override
        public int compareTo(Object o) {
            return 0;
        }
    }


    /**
     * 外部比较器
     */
    static class ComparatorTest2 implements Comparator<Integer>{

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1-o2;
        }
    }



    private static void  stackComparatorTest(){
        //传入一个外部比较器,会按照比较器压栈 比较器排序后的前面的在堆顶
        PriorityQueue<Integer> heap = new PriorityQueue<>(new ComparatorTest2());


        //treeSet也可以传入一个比较器，来自定义


        heap.add(2);

        heap.add(1);

        heap.add(3);



        while (!heap.isEmpty()){
            Integer one = heap.poll();
            System.out.println(one);

        }
    }



}
