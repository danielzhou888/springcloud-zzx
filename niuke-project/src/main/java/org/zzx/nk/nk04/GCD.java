package org.zzx.nk.nk04;

/**
 * 求两个整数的最大公约数
 * 题目：编写一个函数来计算两个整数的最大公约数。
 */
public class GCD {

    /**
     * 计算两个整数的最大公约数
     * @param a 第一个整数
     * @param b 第二个整数
     * @return 两个整数的最大公约数
     */
    public static int gcd(int a, int b) {
        // 当 b 不为 0 时，执行循环
        while (b != 0) {
            // 将 b 的值暂存到 temp 中
            int temp = b;
            // b 赋值为 a 对 b 取余的结果
            b = a % b;
            // 将 a 赋值为 temp，即之前的 b 的值
            a = temp;
        }
        // 返回 a，此时 a 为最大公约数
        return a;
    }

    public static void main(String[] args) {
        int a = 56; // 第一个测试整数
        int b = 98; // 第二个测试整数
        // 输出 56 和 98 的最大公约数，应该为 14
        System.out.println(gcd(a, b)); // Output: 14
    }
}
