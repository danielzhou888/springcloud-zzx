package org.zzx.nk.nk06;

/**
 * 字符串相乘
 * 问题： 给定两个表示非负整数的字符串 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式
 *
 * 这个代码的目的是将两个表示非负整数的字符串 num1 和 num2 相乘，并返回其乘积的字符串形式。由于输入的数字可能非常大，不能直接使用普通的整型变量进行乘法计算，所以需要用字符串模拟手工计算乘法的过程。
 *
 * 详细思路
 * 初始化：
 * - 首先获取两个字符串的长度 m 和 n。
 * - 初始化一个长度为 m + n 的数组 result，用于存储每一位的中间乘积结果。结果数组的长度是 m + n，这是因为两个位数为 m 和 n 的数相乘，其结果最多有 m + n 位。
 *
 * 双重循环遍历字符串：
 * - 从后往前遍历 num1 和 num2 的每一位数字，分别用 i 和 j 作为索引。
 * - 计算 num1 和 num2 当前位的乘积 mul。
 * - 计算乘积在结果数组 result 中的位置 p1 和 p2。
 *    - p1 表示乘积的十位，p2 表示乘积的个位。
 * - 将当前位的乘积加上之前的进位值，存储在 sum 中。
 * - 更新 result[p1] 和 result[p2]，分别存储进位值和当前位的值。
 *
 * 将结果数组转换为字符串：
 * - 使用 StringBuilder 将结果数组转换为字符串。
 * - 跳过结果数组中的前导零，只有在遇到非零数字之后才开始将数字添加到 StringBuilder 中。
 * - 如果 StringBuilder 为空，返回 "0"；否则返回 StringBuilder 转换为字符串的结果。
 */
public class MultiplyStrings {
    // 将两个字符串表示的数字相乘，并返回结果字符串
    public String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length(); // 获取两个字符串的长度
        int[] result = new int[m + n]; // 初始化结果数组，长度为 m + n

        // 从后往前遍历 num1 和 num2 的每一位
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0'); // 计算当前位的乘积
                int p1 = i + j, p2 = i + j + 1; // 计算乘积在结果数组中的位置
                int sum = mul + result[p2]; // 加上之前的进位值
                result[p1] += sum / 10; // 进位
                result[p2] = sum % 10; // 当前位的值
            }
        }

        // 将结果数组转换为字符串
        StringBuilder sb = new StringBuilder();
        for (int num : result) {
            if (!(sb.length() == 0 && num == 0)) { // 去掉前导零
                sb.append(num);
            }
        }
        return sb.length() == 0 ? "0" : sb.toString(); // 返回结果字符串，如果结果为空则返回 "0"
    }

    public static void main(String[] args) {
        MultiplyStrings solver = new MultiplyStrings();
        System.out.println(solver.multiply("123", "456")); // 输出结果："56088"
    }

    // 123
    // 456
    // 738
    //615
//   492
//
}
