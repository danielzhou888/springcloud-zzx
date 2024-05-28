package org.zzx.nk.nk05;

import java.util.*;

/**
 * 电话号码的字母组合
 * 问题： 给定一个仅包含数字 2-9 的字符串，返回它能表示的所有字母组合。
 */
public class LetterCombinations {
    // 定义一个数组，用于存储每个数字对应的字母
    private static final String[] KEYS = {
        "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    // 主方法，返回电话号码的所有字母组合
    public List<String> letterCombinations(String digits) {
        // 定义一个列表，用于存储结果
        List<String> result = new ArrayList<>();
        // 如果输入的数字字符串为空，直接返回空结果
        if (digits == null || digits.length() == 0) {
            return result;
        }
        // 调用回溯算法，生成所有组合
        backtrack(result, new StringBuilder(), digits, 0);
        return result;
    }

    // 回溯算法，用于生成字母组合
    private void backtrack(List<String> result, StringBuilder current, String digits, int index) {
        // 如果当前索引等于数字字符串的长度，说明已生成一个完整组合
        if (index == digits.length()) {
            result.add(current.toString()); // 将当前组合添加到结果中
            return;
        }
        // 获取当前数字对应的字母集合
        String letters = KEYS[digits.charAt(index) - '0'];
        // 遍历字母集合中的每个字母
        for (char c : letters.toCharArray()) {
            current.append(c); // 将当前字母添加到当前组合中
            backtrack(result, current, digits, index + 1); // 递归调用，处理下一个数字
            current.deleteCharAt(current.length() - 1); // 回溯，删除最后一个字母
        }
    }

    // 测试主方法
    public static void main(String[] args) {
        LetterCombinations solver = new LetterCombinations();
        // 示例测试用例
        String digits = "236789";
        // 调用字母组合方法
        List<String> combinations = solver.letterCombinations(digits);
        // 输出结果
        System.out.println("电话号码的字母组合：");
        for (String combination : combinations) {
            System.out.println(combination);
        }
    }
}
