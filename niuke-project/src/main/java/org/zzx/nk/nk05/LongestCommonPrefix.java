package org.zzx.nk.nk05;

/**
 * 最长公共前缀
 * 问题：编写一个方法来查找字符串数组中的最长公共前缀。
 */
public class LongestCommonPrefix {
    // 主方法，返回字符串数组中的最长公共前缀
    public String longestCommonPrefix(String[] strs) {
        // 如果字符串数组为空或长度为0，返回空字符串
        if (strs == null || strs.length == 0) {
            return "";
        }
        // 将第一个字符串作为初始前缀
        String prefix = strs[0];
        // 遍历数组中的其他字符串
        for (int i = 1; i < strs.length; i++) {
            // 当当前字符串不以当前前缀开头时，缩短前缀
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                // 如果前缀为空，返回空字符串
                if (prefix.isEmpty()) return "";
            }
        }
        // 返回找到的最长公共前缀
        return prefix;
    }

    // 测试主方法
    public static void main(String[] args) {
        LongestCommonPrefix solver = new LongestCommonPrefix();
        // 示例测试用例
        String[] strs = {"flower", "flow", "flight"};
        // 调用最长公共前缀方法
        String result = solver.longestCommonPrefix(strs);
        // 输出结果
        System.out.println("最长公共前缀：" + result); // 输出: "fl"
    }
}
