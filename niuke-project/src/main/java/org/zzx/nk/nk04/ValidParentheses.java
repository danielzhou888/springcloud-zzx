package org.zzx.nk.nk04;

import java.util.Stack;

/**
 * 有效的括号
 * 问题： 给定一个只包含 (, ), {, }, [ 和 ] 的字符串，判断字符串是否有效。
 */
public class ValidParentheses {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }
        return stack.isEmpty();
    }
}