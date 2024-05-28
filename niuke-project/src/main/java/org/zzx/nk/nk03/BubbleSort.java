package org.zzx.nk.nk03;

import java.util.Arrays;

/**
 * 10. 实现冒泡排序
 * 题目：编写一个函数来实现冒泡排序。
 */
public class BubbleSort {
    /**
     * 冒泡排序算法
     * @param arr 要排序的数组
     */
    public static void bubbleSort(int[] arr) {
        // 检查数组是否为空或长度为0
        if (arr == null || arr.length == 0) {
            return; // 如果数组为空或长度为0，直接返回
        }

        int n = arr.length; // 获取数组的长度
        boolean swapped; // 标记是否发生交换

        // 外层循环控制排序轮数
        for (int i = 0; i < n - 1; i++) {
            swapped = false; // 每轮开始时将标志置为false

            // 内层循环进行相邻元素比较和交换
            for (int j = 0; j < n - 1 - i; j++) {
                // 如果前一个元素大于后一个元素，则交换
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j]; // 临时变量保存前一个元素
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true; // 发生交换，标志置为true
                }
            }

            // 如果一轮比较中没有发生交换，说明数组已经有序，提前退出循环
            if (!swapped) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22, 90, 11}; // 示例数组
        bubbleSort(arr); // 调用冒泡排序算法对数组进行排序
        System.out.println(Arrays.toString(arr)); // 输出排序后的数组结果，输出：[11, 12, 22, 25, 34, 64, 90]
    }


}
