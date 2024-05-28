package org.zzx.nk.nk02;

/**
 * 3. 实现一个栈
 * 题目描述：使用数组实现一个简单的栈，包含基本的入栈、出栈、取栈顶元素以及判断栈是否为空的操作。
 */
public class Stack {
    private int[] arr;
    private int top;
    private int capacity;

    public Stack(int size) {
        arr = new int[size];
        capacity = size;
        top = -1;
    }

    public void push(int x) {
        if (isFull()) {
            throw new RuntimeException("Stack overflow");
        }
        arr[++top] = x;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack underflow");
        }
        return arr[top--];
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack underflow");
        }
        return arr[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == capacity - 1;
    }

    public static void main(String[] args) {
        Stack stack = new Stack(5);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop()); // 3
        System.out.println(stack.peek()); // 2
        System.out.println(stack.isEmpty()); // false
    }
}