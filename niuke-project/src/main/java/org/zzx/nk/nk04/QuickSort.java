package org.zzx.nk.nk04;

import java.util.Arrays;

/**
 * 实现快速排序
 * 题目：编写一个函数来实现快速排序。
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {10, 7, 8, 9, 1, 5}; // 定义一个数组
        quickSort(arr, 0, arr.length - 1); // 调用快速排序函数
        System.out.println(Arrays.toString(arr)); // 输出排序后的数组
    }

    // 快速排序函数，传入数组、起始索引和结束索引
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) { // 如果起始索引小于结束索引
            int pivot = arr[high]; // 选择数组的最后一个元素作为基准点
            int i = low - 1; // i是比基准点小的元素的索引

            for (int j = low; j < high; j++) { // 遍历数组
                if (arr[j] <= pivot) { // 如果当前元素小于或等于基准点
                    i++; // 增加i
                    int temp = arr[i]; // 交换arr[i]和arr[j]
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }

            int temp = arr[i + 1]; // 把基准点放到正确的位置
            arr[i + 1] = arr[high];
            arr[high] = temp;
            int pi = i + 1; // 分区点

            quickSort(arr, low, pi - 1); // 递归排序左半部分
            quickSort(arr, pi + 1, high); // 递归排序右半部分
        }
    }


}
