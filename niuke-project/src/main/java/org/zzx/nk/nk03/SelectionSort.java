package org.zzx.nk.nk03;

import java.util.Arrays;

/**
 * 选择排序（Selection Sort）是一种简单直观的排序算法，其基本思想是每次从待排序的数据元素中选出最小（或最大）的一个元素，
 * 存放在已排序序列的末尾，直到全部待排序的数据元素排完。选择排序的主要步骤如下：
 *
 * 初始状态：假设数组长度为 n，待排序数组为 arr。
 * 遍历未排序部分：从第 0 个位置开始，依次遍历每一个位置，将其作为当前最小值的位置。
 * 寻找最小值：在当前位置之后的未排序部分中寻找最小值，并记录最小值的位置。
 * 交换位置：将找到的最小值与当前位置的值进行交换。
 * 重复步骤：重复上述步骤，直到整个数组有序。
 */
public class SelectionSort {
    /**
     * 选择排序算法
     * @param arr 要排序的数组
     */
    public static void selectionSort(int[] arr) {
        // 检查数组是否为空或长度为0
        if (arr == null || arr.length == 0) {
            return; // 如果数组为空或长度为0，直接返回
        }

        int n = arr.length; // 获取数组的长度

        // 外层循环控制排序轮数
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i; // 初始化最小值索引为当前轮数的起始位置

            // 内层循环找到从当前位置到数组末尾的最小值
            for (int j = i + 1; j < n; j++) {
                // 如果找到更小的值，更新最小值索引
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }

            // 交换当前元素和找到的最小值元素
            int temp = arr[minIdx]; // 临时变量保存最小值
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11}; // 示例数组
        selectionSort(arr); // 调用选择排序算法对数组进行排序
        System.out.println(Arrays.toString(arr)); // 输出排序后的数组结果，输出：[11, 12, 22, 25, 64]
    }
}
