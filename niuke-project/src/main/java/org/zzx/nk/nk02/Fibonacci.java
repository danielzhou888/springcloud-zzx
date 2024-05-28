package org.zzx.nk.nk02;

/**
 * 2 . 计算斐波那契数列
 * 题目描述：编写一个函数来计算斐波那契数列的第n个数。
 */
public class Fibonacci {
    public int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        int a = 0, b = 1, c;
        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return b;
    }

    public static void main(String[] args) {
        Fibonacci fib = new Fibonacci();
        System.out.println(fib.fibonacci(10)); // 55
    }
}