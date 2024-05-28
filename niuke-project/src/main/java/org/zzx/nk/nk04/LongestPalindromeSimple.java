package org.zzx.nk.nk04;

/**
 * 这个类用于找到给定字符串中的最长回文子串。
 * 回文串是指从前向后读和从后向前读都是一样的字符串。
 * 该方法使用中心扩展算法，通过将每个字符及其相邻字符作为中心，向外扩展来找到最长回文子串。
 * 这种方法的时间复杂度为 O(n^2)，空间复杂度为 O(1)。
 */
public class LongestPalindromeSimple {

    // 主方法，找到给定字符串中的最长回文子串
    public String longestPalindrome(String s) {
        // 如果字符串为空或长度小于1，返回空字符串
        if (s == null || s.length() < 1) return "";
        
        int start = 0, end = 0; // 用于记录最长回文子串的起始和结束位置
        
        // 遍历字符串的每一个字符
        for (int i = 0; i < s.length(); i++) {
            // 以当前字符为中心向两边扩展，获取回文长度
            int len1 = expandAroundCenter(s, i, i);
            // 以当前字符和下一个字符为中心向两边扩展，获取回文长度
            int len2 = expandAroundCenter(s, i, i + 1);
            // 取两种情况下的最大长度
            int len = Math.max(len1, len2);
            // 如果找到的回文长度大于已记录的最大回文长度，则更新起始和结束位置
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        
        // 返回最长回文子串
        return s.substring(start, end + 1);
    }

    // 辅助方法，以 left 和 right 为中心向两边扩展，找到最长回文长度
    private int expandAroundCenter(String s, int left, int right) {
        // 当 left 和 right 在字符串范围内且对应字符相等时，继续扩展
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--; // 向左扩展
            right++; // 向右扩展
        }
        // 返回找到的回文长度
        return right - left - 1;
    }

    public static void main(String[] args) {
        LongestPalindromeSimple solver = new LongestPalindromeSimple();
        String s = "babad";
        System.out.println(solver.longestPalindrome(s)); // 输出: "bab" 或 "aba"
    }
}
