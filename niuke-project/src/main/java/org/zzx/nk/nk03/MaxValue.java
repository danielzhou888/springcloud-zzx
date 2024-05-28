package org.zzx.nk.nk03;

/**
 * 3. 查找数组中的最大值
 * 题目：编写一个函数来查找数组中的最大值。
 */
public class MaxValue {
    public static int findMax(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array is null or empty");
        }
        int max = arr[0];
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {3, 5, 7, 2, 8};
        System.out.println(findMax(arr)); // Output: 8
    }
}