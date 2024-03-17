package cn.xy.leetcode.middle;

import java.util.*;

/**
 * @author xiangyu
 * @date 2024-03-13 0:52
 * <p>
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * <p>
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，
 * 其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 * <p>
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 */
public class A0207CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> cache = new HashMap<>(prerequisites.length * 2);

        for (int[] prerequisite : prerequisites) {
            int cur = prerequisite[0];
            int pre = prerequisite[1];
            if (cache.containsKey(cur)) {
                cache.get(cur).add(pre);
            } else {
                HashSet<Integer> integers = new HashSet<>();
                integers.add(pre);
                cache.put(cur, integers);
            }
        }

        // 先学习所有可以直接学习的课程
        Set<Integer> learned = new HashSet<>();
        for (int i = 0; i < numCourses; i++) {
            if (!cache.containsKey(i)) {
                learned.add(i);
            }
        }
        // 可以直接学的课程
        int sum = -1;
        int temp = 0;
        while (sum != temp) {
            sum = learned.size();
            for (Map.Entry<Integer, Set<Integer>> integerSetEntry : cache.entrySet()) {
                integerSetEntry.getValue().removeIf(learned::contains);
                if (integerSetEntry.getValue().isEmpty()) {
                    learned.add(integerSetEntry.getKey());
                }
            }
            temp = learned.size();
        }
        return learned.size() == numCourses;
    }
}
