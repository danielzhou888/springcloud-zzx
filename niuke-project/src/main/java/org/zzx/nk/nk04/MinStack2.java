package org.zzx.nk.nk04;

import java.util.Stack;

/**
 * 最小栈
 * 问题： 设计一个支持 push, pop, top 操作，并能在常数时间内检索到最小元素的栈。
 * @author: 周志祥
 * @create: 2024-05-27 17:45
 */
public class MinStack2 {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public MinStack2() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x);
        }
    }

    public void pop() {
        if (stack.peek().equals(minStack.peek())) {
            minStack.pop();
        }
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}