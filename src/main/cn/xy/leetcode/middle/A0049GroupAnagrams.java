package cn.xy.leetcode.middle;

import java.util.*;

/**
 * @author XiangYu
 * @create2020-12-14-9:55 字母异位词分组
 * <p>
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * <p>
 * 示例:
 * <p>
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出:
 * [
 * ["ate","eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * 说明：
 * <p>
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 */
public class A0049GroupAnagrams {

    public static void main(String[] args) {
//        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
//        List<List<String>> lists = new A049GroupAnagrams().groupAnagrams(strs);
//
//
//        for (List<String> list : lists) {
//            for (String s : list) {
//                System.out.print(s+ " ");
//            }
//            System.out.println();
//        }


        String duh = getKey("duh");

        String ill = getKey("ill");

        System.out.println(duh + "ill=" + ill);
    }


    /**
     * 执行用时：8 ms, 在所有 Java 提交中击败了74.94%的用户
     * 内存消耗：42 MB, 在所有 Java 提交中击败了28.40%的用户
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) {
            return null;
        }

        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String key = getKey(str);
            List<String> list = map.get(key);
            if (list == null) {
                ArrayList<String> newList = new ArrayList<>();
                newList.add(str);
                map.put(key, newList);
            } else {
                list.add(str);
            }
        }
        Set<Map.Entry<String, List<String>>> entries = map.entrySet();
        List<List<String>> lists = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : entries) {
            List<String> list = entry.getValue();
            lists.add(list);
        }
        return lists;
    }


    public static String getKey(String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        StringBuilder id = new StringBuilder();
        for (char c : chars) {
            id.append(c);
        }
        return id.toString();
    }


    /**
     * 自己的方法，官方的优雅写法
     * 执行用时：7 ms, 在所有 Java 提交中击败了95.84%的用户
     * 内存消耗：41.6 MB, 在所有 Java 提交中击败了57.71%的用户
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams2(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String key = new String(array);
//
//             将 此处代码优化后，性能有提高
//             List<String> list = map.getOrDefault(key, new ArrayList<String>());
//             list.add(str);
//             map.put(key, list);

            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(str);
        }
        return new ArrayList<List<String>>(map.values());
    }

    /**
     * 官方解法2，使用出现的字母和出现的次数作为key
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams3(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            int[] counts = new int[26];
            int length = str.length();
            for (int i = 0; i < length; i++) {
                counts[str.charAt(i) - 'a']++;
            }
            // 将每个出现次数大于 0 的字母和出现次数按顺序拼接成字符串，作为哈希表的键
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 26; i++) {
                if (counts[i] != 0) {
                    sb.append((char) ('a' + i));
                    sb.append(counts[i]);
                }
            }
            String key = sb.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }
}


/*
[["buy"],["cab"],["may"],["tin"],["pew"],["duh","ill"],["doc"],["max"],["bar"]]
预期结果：
[["doc"],["bar"],["buy"],["ill"],["tin"],["cab"],["pew"],["may"],["max"],["duh"]]
 */