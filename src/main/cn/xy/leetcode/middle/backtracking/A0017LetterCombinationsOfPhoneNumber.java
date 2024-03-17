package cn.xy.leetcode.middle.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiangyu
 * @date 2024-03-17 20:09
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * 示例 1：
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * <p>
 * 示例 2：
 * 输入：digits = ""
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 * <p>
 * 提示：
 * 0 <= digits.length <= 4
 * digits[i] 是范围 ['2', '9'] 的一个数字。
 */
public class A0017LetterCombinationsOfPhoneNumber {

    public static void main(String[] args) {
        A0017LetterCombinationsOfPhoneNumber a0017LetterCombinationsOfPhoneNumber = new A0017LetterCombinationsOfPhoneNumber();
        System.out.println(a0017LetterCombinationsOfPhoneNumber.letterCombinations("23"));
    }

    // 错误写法，没有考虑 7和9对应的是四位树的情况
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.isEmpty()) {
            return new ArrayList<>();
        }
        List<String> ans = new ArrayList<>();
        StringBuilder output = new StringBuilder();
        char[] chars = new char[digits.length()];
        for (int i = 0; i < digits.length(); i++) {
            chars[i] = (char) (3 * (digits.charAt(i) - 48 - 2) + 97);
        }
        int index = digits.length();

        backtrack(index, chars, ans, output, 0);
        return ans;
    }

    private void backtrack(int n, char[] chars, List<String> ans, StringBuilder output, int index) {
        if (n == index) {
            ans.add(output.toString());
            return;
        }
        int start = chars[index];
        for (int i = start; i < start + 3; i++) {
            output.append((char) i);
            backtrack(n, chars, ans, output, index + 1);
            output.delete(output.length() - 1, output.length());
        }
    }

    public List<String> letterCombinations2(String digits) {
        List<String> combinations = new ArrayList<>();
        if (digits == null || digits.isEmpty()) {
            return combinations;
        }
        Map<Character, String> phoneMap = new HashMap<>(16) {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(combinations, phoneMap, digits, 0, new StringBuffer());
        return combinations;
    }

    public void backtrack(List<String> combinations, Map<Character, String> phoneMap, String digits, int index, StringBuffer combination) {
        if (index == digits.length()) {
            combinations.add(combination.toString());
            return;
        }

        char digit = digits.charAt(index);
        String letters = phoneMap.get(digit);
        int lettersCount = letters.length();
        for (int i = 0; i < lettersCount; i++) {
            combination.append(letters.charAt(i));
            backtrack(combinations, phoneMap, digits, index + 1, combination);
            combination.deleteCharAt(index);
        }
    }
}
