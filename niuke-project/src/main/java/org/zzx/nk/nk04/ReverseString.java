package org.zzx.nk.nk04;

/**
 * 反转字符串
 * 题目：编写一个函数来反转一个字符串。
 */
public class ReverseString {
    public static String reverse(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return new StringBuilder(str).reverse().toString();
    }

    public static String reverse2(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        char[] charArray = s.toCharArray();
        int left = 0;
        int right = charArray.length - 1;
        while (left < right) {
            char temp = charArray[left];
            charArray[left] = charArray[right];
            charArray[right] = temp;
            left++;
            right--;
        }
        return new String(charArray);
    }

    public static void main(String[] args) {
        String str = "Hello, World!";
        System.out.println(reverse(str)); // Output: !dlroW ,olleH
    }
}