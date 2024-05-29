package org.zzx.nk.lambda.lambda_binaryoperator01;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;

/**
 * 案例7：使用 BinaryOperator 进行归约操作
 * 问题：使用 BinaryOperator 接口进行归约操作。
 */
public class LambdaAdvanced7 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        BinaryOperator<Integer> sum = Integer::sum;
        
        int result = list.stream().reduce(0, sum);
        System.out.println(result); // 输出: 15
    }

    public void test() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        int result = list.stream().reduce(0, Integer::sum);
        System.out.println(result);
    }

    /**
     * 求两个数的最大值：
     */
    public void test1() {
        BinaryOperator<Integer> max = Integer::max;
        System.out.println(max.apply(5, 8)); // 输出：8


        int maxVal = getMaxInt(5, 8);
        System.out.println(maxVal);
    }

    private int getMaxInt(int a, int b) {
        BinaryOperator<Integer> max = Integer::max;
        return max.apply(a, b);
    }

    /**
     * 求两个数的和：
     */
    public void test2() {
        BinaryOperator<Integer> sum = (a, b) -> a + b;
        System.out.println(sum.apply(3, 4)); // 输出：7

        int sumVal = getSum(3, 4);
        System.out.println(sumVal);
    }

    private int getSum(int a, int b) {
        BinaryOperator<Integer> sum = (i, j) -> i + j;
        return sum.apply(a, b);
    }

    /**
     * 求两个字符串的连接：
     */
    public void test3() {
        BinaryOperator<String> concat = (str1, str2) -> str1 + str2;
        System.out.println(concat.apply("Hello, ", "world!")); // 输出：Hello, world!

        String mergeStr = mergeString("Hello, ", "world!");
        System.out.println(mergeStr);
    }

    private String mergeString(String s1, String s2) {
        BinaryOperator<String> concat = (str1, str2) -> str1 + str2;
        return concat.apply(s1, s2);
    }

    /**
     * 查找两个日期中较晚的日期：
     */
    public void test4() {
//        BinaryOperator<LocalDate> latestDate = LocalDate::max;
//        LocalDate date1 = LocalDate.of(2022, 5, 15);
//        LocalDate date2 = LocalDate.of(2022, 5, 20);
//        System.out.println(latestDate.apply(date1, date2)); // 输出：2022-05-20
    }
}