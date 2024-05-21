package org.zzx.nk.nk02;

/**
 * @Description:
 * 1. 判断回文字符串
 * 题目描述：编写一个函数来检查一个字符串是否是回文字符串
 * @author: 周志祥
 * @create: 2024-05-21 13:47
 */
public class PalindromeChecker {
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        PalindromeChecker checker = new PalindromeChecker();
        System.out.println(checker.isPalindrome("racecar")); // true
        System.out.println(checker.isPalindrome("hello"));   // false
    }
}