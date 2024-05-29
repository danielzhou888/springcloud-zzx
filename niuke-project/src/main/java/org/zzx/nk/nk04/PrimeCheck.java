package org.zzx.nk.nk04;

/**
 * 判断一个数是否为质数（素数）的方法如下：
 *
 * 定义质数：质数是大于1的自然数，且除了1和其自身外，没有其他约数。
 * 处理特殊情况：如果数小于等于1，直接返回不是质数。
 * 优化判断过程：
 * 一个数如果能被其平方根以内的某个数整除，那么它一定不是质数。
 * 因此，只需要检查从2到该数平方根之间的数是否能整除该数。
 * 遍历检查：如果找到一个数可以整除该数，则该数不是质数，否则是质数。
 */
public class PrimeCheck {

    // 检查一个数是否为素数的函数
    public static boolean isPrime(int num) {
        if (num <= 1) { // 如果数字小于或等于1，则不是素数
            return false; // 返回false
        }
        for (int i = 2; i <= Math.sqrt(num); i++) { // 从2遍历到num的平方根
            if (num % i == 0) { // 如果num可以被i整除
                return false; // 返回false，因为num不是素数
            }
        }
        return true; // 如果循环结束没有发现因数，返回true，num是素数
    }

    public static boolean isPrime2(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i < Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int num = 29; // 定义一个要检查的数字
        System.out.println(isPrime(num)); // 调用isPrime函数并打印结果，输出true
    }
}
