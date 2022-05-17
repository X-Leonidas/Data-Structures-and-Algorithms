package cn.xy.algorithm.greed;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author XiangYu
 * @create2021-03-12-21:01
 *一些项目要占用一个会议室宣讲， 会议室不能同时容纳两个项目
 * 的宣讲。 给你每一个项目开始的时间和结束的时间(给你一个数
 * 组， 里面 是一个个具体的项目)， 你来安排宣讲的日程， 要求会
 * 议室进行 的宣讲的场次最多。 返回这个最多的宣讲场次。
 *
 * 解题思路：先找出最早结束的某场会议，然后排除掉因为这场会议无法举行的会议
 *          然后再在剩下的未举行的会议中找出最早结束的会议，如此知道结束
 *
 *
 */
public class Code_04_BestArrange {

    public static class Program {
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static class ProgramComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }

    }

    public static int bestArrange(Program[] programs, int start) {
        Arrays.sort(programs, new ProgramComparator());
        int result = 0;
        for (int i = 0; i < programs.length; i++) {
            if (start <= programs[i].start) {
                result++;
                start = programs[i].end;
            }
        }
        return result;
    }

    public static void main(String[] args) {

    }
}
